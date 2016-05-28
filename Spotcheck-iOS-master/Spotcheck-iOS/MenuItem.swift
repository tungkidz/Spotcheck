//
//  MenuItem.swift
//  Spotcheck-iOS
//
//  Created by Matthew Reiss on 5/22/16.
//  Copyright © 2016 Spotcheck. All rights reserved.
//

import UIKit

/**
 Represents an object for the menu.
 */
class MenuItem
{
    
    let title: String
    let image: UIImage?
    
    init(title: String, image: UIImage?)
    {
        self.title = title
        self.image = image
    }
    
    /**
     Creates an Array of menuItems to display in the menu.
     The title's must match a ViewController's prefix. (eg ProfileViewController)
     or "Log out" must match the if statement in ContainerViewController.swift
    */
    class func allItems() -> Array<MenuItem>
    {
        return [ MenuItem(title: "Profile", image: UIImage(named: "profile_black_48px.png")),
                 MenuItem(title: "Explore", image: UIImage(named: "public_black_48px.png")),
                 MenuItem(title: "Message", image: UIImage(named: "message_black_48px.png")),
                 MenuItem(title: "Capture", image: UIImage(named: "camera_black_48px.png")),
                 MenuItem(title: "Log out", image: UIImage(named: "exit_black_48px.png")) ]
    }
}