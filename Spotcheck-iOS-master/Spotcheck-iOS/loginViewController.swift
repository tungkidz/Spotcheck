  //
//  loginViewController.swift
//  Spotcheck-iOS
//
//  Created by evo baby on 4/21/16.
//  Copyright © 2016 Spotcheck. All rights reserved.
//

import UIKit

class loginViewController: UIViewController {
    
    //made referencing outlet
    
   
    @IBOutlet weak var loginEmailText: UITextField!
   
   
    @IBOutlet weak var loginPasswordText: UITextField!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //loginViewController.viewDidLayoutSubviews()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
   
     //authentication code
    @IBAction func loginButtonTapped(sender:UIButton)
    {
        
        var service : ServiceSpotcheckApi? = nil
        if service == nil
        {
            service = ServiceSpotcheckApi()
            service?.retryEnabled = true
            print("service not on")
        }
        
        let email : String = loginEmailText.text!
        let password : String = loginPasswordText.text!
        
      
        
        let query : QuerySpotcheckApi = QuerySpotcheckApi.authenticateAccount(email,password) as QuerySpotcheckApi
        
        service!.executeQuery(query, completionHandler:
            { (ticket: GTLServiceTicket!, object: AnyObject!, error: NSError!) -> Void in
                
                print("Results: \(object) ", terminator: "\n\n\n")
                
                let resp = object as! Account
                print(resp)
                
                if (resp.firstName == nil)
                {
                    resp.firstName = "failed"
                    print("failed")
                   // print("failed")
                }
                else {
                   self.performSegueWithIdentifier("loginSegue", sender: self)
                }
                
                print("Account:\nf= \(resp.firstName)\nl= \(resp.lastName)\ne= \(resp.email)\np= \(resp.password)", terminator: "\n")
                
               // self.firstNameField.text = "\(resp.firstName) "
               // self.lastNameField.text = "\(resp.lastName) "
               // self.loginEmailText.text = "\(resp.email) "
                //self.loginPasswordText.text = "\(resp.password) "
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
