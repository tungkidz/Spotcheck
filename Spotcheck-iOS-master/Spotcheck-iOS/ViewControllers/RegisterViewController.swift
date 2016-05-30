import UIKit

class RegisterViewController: UIViewController
{
    
    let MyKeychainWrapper = KeychainWrapper()
    
    @IBOutlet var passwordField: UITextField!
    @IBOutlet var emailField: UITextField!
    @IBOutlet var lastNameField: UITextField!
    @IBOutlet var firstNameField: UITextField!
    
    override func viewDidLoad()
    {
        super.viewDidLoad()

        // Set the input field delegates
        passwordField.delegate = self
        emailField.delegate = self
        firstNameField.delegate = self
        lastNameField.delegate = self
    }
    
    /**
     Validates the register input form.
     - parameter accountForm: the accountForm object to validate
     
     - returns: true if the input form is valid.
     */
    func formIsValid(accountForm: AccountForm) -> Bool
    {
        return !accountForm.email.isEmpty &&
            !accountForm.password.isEmpty &&
            !accountForm.firstName.isEmpty &&
            !accountForm.lastName.isEmpty &&
            accountForm.email.containsString("@")
    }
    
    /**
     Registers a new user.
    */
    @IBAction func signUp(sender: AnyObject)
    {
        // Set form data
        let accountForm: AccountForm = AccountForm()
        accountForm.firstName = firstNameField.text
        accountForm.lastName = lastNameField.text
        accountForm.email = emailField.text
        accountForm.password = passwordField.text
        
        // Validate Form
        if !formIsValid(accountForm)
        {
            // Display error message
            displayError()
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
        let query : QuerySpotcheckApi = QuerySpotcheckApi.createAccount(accountForm) as QuerySpotcheckApi
        service!.executeQuery(query, completionHandler:
            { (ticket: GTLServiceTicket!, object: AnyObject!, error: NSError!) -> Void in
                
                // Get response from Api service
                let resp = object as! User
                if (resp.userId == nil)
                {
                    // Display sign up error
                    self.displayError()
                    return
                }
                
                // Set user in data model
                Data.model.user = resp
                
                // Store authentication locally in keychain
                let hasAutoLoginKey = NSUserDefaults.standardUserDefaults().boolForKey("hasAutoLoginKey")
                if hasAutoLoginKey == false
                {
                    NSUserDefaults.standardUserDefaults().setValue(accountForm.email, forKey: "email")
                    self.MyKeychainWrapper.mySetObject(accountForm.password, forKey:kSecValueData)
                    self.MyKeychainWrapper.writeToKeychain()
                    NSUserDefaults.standardUserDefaults().setBool(true, forKey: "hasAutoLoginKey")
                    NSUserDefaults.standardUserDefaults().synchronize()
                    
                }
                
                // Transition into application as an authenticated user
                self.performSegueWithIdentifier("registerSegue", sender: self)
            })
    }
    
    
    func displayError()
    {
        let alertView = UIAlertController(title: "Register Problem",
                                          message: "Invalid email or password." as String, preferredStyle:.Alert)
        let okAction = UIAlertAction(title: "Okay", style: .Default, handler: nil)
        alertView.addAction(okAction)
        self.presentViewController(alertView, animated: true, completion: nil)
    }

}



// MARK: UITextField delegate

extension RegisterViewController: UITextFieldDelegate
{
    /**
     Changes from one login input field to the next when the return key is pressed.
     */
    func textFieldShouldReturn(textField: UITextField) -> Bool
    {
        textField.resignFirstResponder()
        switch (textField)
        {
        case firstNameField:
            lastNameField.becomeFirstResponder()
            break
        case lastNameField:
            emailField.becomeFirstResponder()
            break
        case emailField:
            passwordField.becomeFirstResponder()
            break
        case passwordField:
            signUp(self)
            break
        default: break
        }
        return true
    }
}
