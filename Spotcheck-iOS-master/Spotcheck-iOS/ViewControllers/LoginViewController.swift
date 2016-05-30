import UIKit
import CoreData

class LoginViewController: UIViewController
{
    
    let MyKeychainWrapper = KeychainWrapper()
    
    @IBOutlet weak var loginEmailText: UITextField!
    @IBOutlet weak var loginPasswordText: UITextField!
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        
        // Check if credentials are in keychain
        let email = NSUserDefaults.standardUserDefaults().valueForKey("email") as? String
        let password = MyKeychainWrapper.myObjectForKey("v_Data") as? String
        if (email != nil && password != nil)
        {
            loginEmailText.text = email
            loginPasswordText.text = password
            
            // Automatically login
            let hasAutoLogin = NSUserDefaults.standardUserDefaults().boolForKey("hasAutoLoginKey")
            if hasAutoLogin
            {
                authenticate(email!, password: password!)
            }
        }
        
        
        // Set input field delegates
        loginEmailText.delegate = self
        loginPasswordText.delegate = self
    }
    
    /**
     Validates the login input form.
     - parameter email: the email to validate
     - parameter password: the password to validate
     
    - returns: true if the input form is valid.
     */
    func formIsValid(email:String, password:String) -> Bool
    {
        return !email.isEmpty && !password.isEmpty && email.containsString("@")
    }
    
    func authenticate(email:String, password:String)
    {
        // Set up Api service
        var service : ServiceSpotcheckApi? = nil
        if service == nil
        {
            service = ServiceSpotcheckApi()
            service?.retryEnabled = true
        }
        
        // Call the Api service
        let query : QuerySpotcheckApi = QuerySpotcheckApi.authenticateAccount(email,password) as QuerySpotcheckApi
        service!.executeQuery(query, completionHandler:
            { (ticket: GTLServiceTicket!, object: AnyObject!, error: NSError!) -> Void in
                
                // Get response from Api service
                let resp = object as! User
                if (resp.userId != nil)
                {
                    // Set user in data model
                    Data.model.user = resp
                    
                    let hasAutoLoginKey = NSUserDefaults.standardUserDefaults().boolForKey("hasAutoLoginKey")
                    if hasAutoLoginKey == false
                    {
                        // Store authentication locally in keychain
                        NSUserDefaults.standardUserDefaults().setValue(email, forKey: "email")
                        self.MyKeychainWrapper.mySetObject(password, forKey:kSecValueData)
                        self.MyKeychainWrapper.writeToKeychain()
                        NSUserDefaults.standardUserDefaults().setBool(true, forKey: "hasAutoLoginKey")
                        NSUserDefaults.standardUserDefaults().synchronize()
                    }
                    
                    // Transition into application as an authenticated user
                    self.performSegueWithIdentifier("loginSegue", sender: self)
                } else
                {
                    //Display error message
                    self.displayError()
                }
        })
    }
    
    func displayError()
    {
        let alertView = UIAlertController(title: "Login Problem",
                                          message: "Invalid email or password." as String, preferredStyle:.Alert)
        let okAction = UIAlertAction(title: "Okay", style: .Default, handler: nil)
        alertView.addAction(okAction)
        self.presentViewController(alertView, animated: true, completion: nil)
    }
    
    /**
        Authenticates a user's login.
     */
    @IBAction func loginClicked(sender: AnyObject)
    {
        // Get form inputs
        var email : String = loginEmailText.text!
        var password : String = loginPasswordText.text!
        
        // For testing purposes login quickly with email = '$'
        if (email.containsString("$"))
        {
            email = "fake@email.com"
            password = "password"
        }
        
        // Validate form inputs
        if !formIsValid(email, password: password)
        {
            displayError()
            return
        }
        
        authenticate(email, password: password)
    }
}


// MARK: UITextField delegate

extension LoginViewController: UITextFieldDelegate
{
    
    /**
        Changes from one login input field to the next
     */
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        switch (textField)
        {
        case loginEmailText:
            loginPasswordText.becomeFirstResponder()
            break
        case loginPasswordText:
            loginClicked(self)
            break
        default: break
        }
        return true
    }
}


