//
//  RegisterViewController.swift
//  Spotcheck-iOS
//
//  Created by evo baby on 4/22/16.
//  Copyright © 2016 Spotcheck. All rights reserved.
//

import UIKit

class RegisterViewController: UIViewController {
    
    @IBOutlet var passwordField: UITextField!
    @IBOutlet var emailField: UITextField!
    @IBOutlet var lastNameField: UITextField!
    @IBOutlet var firstNameField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func signUp(sender: AnyObject) {
        
        
        var service : ServiceSpotcheckApi? = nil
        if service == nil
        {
            service = ServiceSpotcheckApi()
            service?.retryEnabled = true
        }
        
        let accountForm: AccountForm = AccountForm()
        accountForm.firstName = firstNameField.text
        accountForm.lastName = lastNameField.text
        accountForm.email = emailField.text
        accountForm.password = passwordField.text
        
        
        print("\n\nAccountForm:\nf= \(accountForm.firstName)\nl= \(accountForm.lastName)\ne= \(accountForm.email)\np= \(accountForm.password)", terminator: "\n")
        
        let query : QuerySpotcheckApi = QuerySpotcheckApi.createAccount(accountForm) as QuerySpotcheckApi
        
        service!.executeQuery(query, completionHandler:
            { (ticket: GTLServiceTicket!, object: AnyObject!, error: NSError!) -> Void in
                
                print("Results: \(object) ", terminator: "\n\n\n")
                
                let resp = object as! Account
                
                print("Account:\nf= \(resp.firstName)\nl= \(resp.lastName)\ne= \(resp.email)\np= \(resp.password)", terminator: "\n")
                
                self.firstNameField.text = "\(resp.firstName) "
                self.lastNameField.text = "\(resp.lastName) "
                self.emailField.text = "\(resp.email) "
                self.passwordField.text = "\(resp.password) "
        })
    }


    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
