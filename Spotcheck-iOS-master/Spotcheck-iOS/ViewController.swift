//
//  ViewController.swift
//  Spotcheck-iOS
//
//  Created by Matthew Reiss on 04/09/16.
//  Copyright © 2016 Matthew Reiss. All rights reserved.
//

import UIKit

class ViewController: UIViewController
{
    
    @IBOutlet var passwordField: UITextField!
    @IBOutlet var emailField: UITextField!
    @IBOutlet var lastNameField: UITextField!
    @IBOutlet var firstNameField: UITextField!
    
    @IBAction func saveAccount(sender: AnyObject)
    {
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
        
        let query : QuerySpotcheckApi = QuerySpotcheckApi.saveAccount(accountForm) as QuerySpotcheckApi
        
        service!.executeQuery(query, completionHandler:
            { (ticket: GTLServiceTicket!, object: AnyObject!, error: NSError!) -> Void in
                
                print("Results: \(object) ", terminator: "\n\n\n")
                
                let resp = object as Account
                
                print("Account:\nf= \(resp.firstName)\nl= \(resp.lastName)\ne= \(resp.email)\np= \(resp.password)", terminator: "\n")
                
                self.firstNameField.text = "\(resp.firstName) "
                self.lastNameField.text = "\(resp.lastName) "
                self.emailField.text = "\(resp.email) "
                self.passwordField.text = "\(resp.password) "
        })
    }
    
    @IBAction func getAccount(sender: AnyObject)
    {
        var service : ServiceSpotcheckApi? = nil
        if service == nil
        {
            service = ServiceSpotcheckApi()
            service?.retryEnabled = true
        }
        
        let email : String = emailField.text!
        let password : String = passwordField.text!
        
        let query : QuerySpotcheckApi = QuerySpotcheckApi.authenticateAccount(email,password) as QuerySpotcheckApi
        
        service!.executeQuery(query, completionHandler:
            { (ticket: GTLServiceTicket!, object: AnyObject!, error: NSError!) -> Void in
                
                print("Results: \(object) ", terminator: "\n\n\n")
                
                let resp = object as Account
                
                if (resp.firstName == nil)
                {
                    resp.firstName = "failed"
                }
                
                print("Account:\nf= \(resp.firstName)\nl= \(resp.lastName)\ne= \(resp.email)\np= \(resp.password)", terminator: "\n")
                
                self.firstNameField.text = "\(resp.firstName) "
                self.lastNameField.text = "\(resp.lastName) "
                self.emailField.text = "\(resp.email) "
                self.passwordField.text = "\(resp.password) "
        })
    }
    
}

