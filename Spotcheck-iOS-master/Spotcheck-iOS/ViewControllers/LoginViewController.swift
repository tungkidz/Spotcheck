import UIKit

class LoginViewController: UIViewController
{
    @IBOutlet weak var loginEmailText: UITextField!
    @IBOutlet weak var loginPasswordText: UITextField!
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        
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
            // TODO: Display error message
            return
        }
        
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
                    
                    // TODO: Store authentication token locally
                    
                    // Transition into application as an authenticated user
                    self.performSegueWithIdentifier("loginSegue", sender: self)
                } else
                {
                    // TODO: Display error message
                }
            })
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


