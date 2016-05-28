//
//  ViewController.swift
//  Spotcheck-iOS
//
//  Created by Matthew Reiss on 04/09/16.
//  Copyright © 2016 Matthew Reiss. All rights reserved.
//

import UIKit


@objc
protocol MainViewControllerDelegate
{
    optional func toggleMenuPanel()
    optional func collapseMenuPanels()
}

/**
 Controls the main view
 */
class MainViewController: UIViewController
{
    var delegate: MainViewControllerDelegate?
    
    @IBOutlet var screenLabel: UILabel!
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
    }
    
    @IBAction func MenuClicked(sender: AnyObject) {
        delegate?.toggleMenuPanel?()
    }
    
    
}

