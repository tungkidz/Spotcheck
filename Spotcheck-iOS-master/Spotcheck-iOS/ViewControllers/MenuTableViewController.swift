//
//  MenuTableViewController.swift
//  Spotcheck-iOS
//
//  Created by Matthew Reiss on 5/22/16.
//  Copyright © 2016 Spotcheck. All rights reserved.
//

import UIKit
import MapKit

protocol MenuTableViewControllerDelegate
{
    func menuItemSelected(menuItem: MenuItem)
}

class MenuTableViewController: UITableViewController
{
    var menuItems: Array<MenuItem>!
    var delegate: MenuTableViewControllerDelegate?
    
    @IBOutlet var mapView: MKMapView!
    @IBOutlet var menuView: UITableView!
    
    struct TableView
    {
        struct CellIdentifiers
        {
            static let MenuItemCell = "MenuItemCell"
        }
    }
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        menuView.reloadData()
    }
    
    /**
     Sets the default menu item selection
     */
    func setDefaultSelection()
    {
        let index: NSIndexPath = NSIndexPath(forRow: 0, inSection: 0);
        menuView.selectRowAtIndexPath(index, animated: true, scrollPosition:  UITableViewScrollPosition.None)
    }
    
    /**
     Sets the default menu item selection
     */
    func setSelection(selectionIndex: Int)
    {
        let index: NSIndexPath = NSIndexPath(forRow: selectionIndex, inSection: 0);
        menuView.selectRowAtIndexPath(index, animated: true, scrollPosition:  UITableViewScrollPosition.None)
    }
    
    /**
     Centers on a location on a map
    */
    func centerMapOnLocation(location: CLLocation)
    {
        let regionRadius: CLLocationDistance = 1000
        let coordinateRegion = MKCoordinateRegionMakeWithDistance(location.coordinate,
                                                                  regionRadius * 2.0, regionRadius * 2.0)
        mapView.setRegion(coordinateRegion, animated: true)
    }
    
    
    // MARK: - Table View Data Source
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int
    {
        return 1
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return menuItems.count
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCellWithIdentifier(TableView.CellIdentifiers.MenuItemCell, forIndexPath: indexPath) as! MenuItemCell
        cell.configureForMenuItem(menuItems[indexPath.row])
        return cell
    }
    
    
    // Mark: Table View Delegate
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath)
    {
        let selectedMenuItem = menuItems[indexPath.row]
        delegate?.menuItemSelected(selectedMenuItem)
    }
}


class MenuItemCell: UITableViewCell
{
    @IBOutlet var menuItemImageView: UIImageView!
    @IBOutlet var menuItemNameLabel: UILabel!
    
    func configureForMenuItem(menuItem: MenuItem)
    {
        menuItemImageView.image = menuItem.image
        menuItemNameLabel.text = menuItem.title
    }
}

