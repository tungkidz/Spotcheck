import UIKit
import QuartzCore

// Determines if the menu is expanded or collapsed
enum SlideOutState
{
    case Collapsed
    case Expanded
}

class ContainerViewController: UIViewController
{
    // Set instance variables
    var menuSelection:String = "Profile"
    let mainPanelExpandedOffset: CGFloat = 60
    var mainViewController: MainViewController!
    var menuViewController: MenuTableViewController?
    var currentState: SlideOutState = .Collapsed
    {
        didSet
        {
            let shouldShowShadow = currentState != .Collapsed
            showShadowForViewController(shouldShowShadow)
        }
    }
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        initialize()
        
        if Data.model.user.userId == nil
        {
            displayError()
            logOut()
        }
    }
    
    /**
     Initializes the child views within the container.
    */
    func initialize()
    {
        // Set main view based on selected menu item
        mainViewController = UIStoryboard.mainViewController(menuSelection)
        mainViewController.delegate = self
        view.addSubview(mainViewController.view)
        addChildViewController(mainViewController)
        mainViewController.didMoveToParentViewController(self)
        
        // Set main view to reckognize tough gestures to pull out the menu
        let panGestureRecognizer = UIPanGestureRecognizer(target: self, action: #selector(ContainerViewController.handlePanGesture(_:)))
        mainViewController.view.addGestureRecognizer(panGestureRecognizer)
    }
    
    func displayError()
    {
        let alertView = UIAlertController(title: "User Authentication Problem",
                                          message: "Cannot authenticate user credentials, Please log in again." as String, preferredStyle:.Alert)
        let okAction = UIAlertAction(title: "Okay", style: .Default, handler: nil)
        alertView.addAction(okAction)
        self.presentViewController(alertView, animated: true, completion: nil)
    }
    
    func logOut()
    {
        NSUserDefaults.standardUserDefaults().setBool(false, forKey: "hasAutoLoginKey")
        NSUserDefaults.standardUserDefaults().synchronize()
        Data.model.user = User()
        performSegueWithIdentifier("logoutSeque", sender: self)
    }
    
}

// MARK: MainViewController delegate

extension ContainerViewController: MainViewControllerDelegate
{
    /**
     Initializes a the menu
     */
    func initializeMenu()
    {
        if (menuViewController != nil) { return }
        menuViewController = UIStoryboard.menuViewController()
        menuViewController!.menuItems = MenuItem.allItems()
        menuViewController!.delegate = self
        view.insertSubview(menuViewController!.view, atIndex: 0)
        addChildViewController(menuViewController!)
        menuViewController!.didMoveToParentViewController(self)
        menuViewController!.setDefaultSelection()
    }
    
    /**
     Updates the menu selection to transition between views
     
     Currently only used for add button in profile view
    */
    func updateMenuSelection(selection: String)
    {
        if (menuViewController == nil)
        {
            initializeMenu()
        }
        
        // naive implementation to set menu selection to capture
        // used when add button clicked in profile
        menuViewController!.setSelection(3)
        self.menuSelection = selection
        
        // Remove the old screen
        self.mainViewController!.view.removeFromSuperview()
        self.mainViewController = nil;
        
        // Reset main view
        self.initialize()
    }
    
    /**
     Toggles the menu on and off
    */
    func toggleMenuPanel()
    {
        if (menuViewController == nil)
        {
            initializeMenu()
        }
        animateMenuPanel(currentState != .Expanded)
    }
    
    /**
     Collopses the menu if expanded
    */
    func collapseMenuPanels()
    {
        if (currentState == .Expanded)
        {
            toggleMenuPanel()
        }
    }
    
    /**
     Animates the menu in/out of the screen
    */
    func animateMenuPanel(shouldExpand: Bool)
    {
        if (shouldExpand)
        {
            currentState = .Expanded
            animateCenterPanelXPosition(CGRectGetWidth(mainViewController.view.frame) - mainPanelExpandedOffset)
        } else
        {
            animateCenterPanelXPosition(0) { finished in
                self.currentState = .Collapsed
            }
        }
    }
    
    /**
     Actual animation of the menu
    */
    func animateCenterPanelXPosition(targetPosition: CGFloat, completion: ((Bool) -> Void)! = nil)
    {
        UIView.animateWithDuration(0.5, delay: 0, usingSpringWithDamping: 0.8, initialSpringVelocity: 0, options: .CurveEaseInOut, animations: {
            self.mainViewController.view.frame.origin.x = targetPosition
            }, completion: completion)
    }
    
    /**
     Applies a shadow to the main view when the menu is shown
    */
    func showShadowForViewController(shouldShowShadow: Bool) {
        if (shouldShowShadow) {
            mainViewController.view.layer.shadowOpacity = 0.5
        } else {
            mainViewController.view.layer.shadowOpacity = 0.0
        }
    }
    
    
}


// MARK: MenuTableViewController delegate

extension ContainerViewController: MenuTableViewControllerDelegate
{
    /**
     Sets the main view to the screen selected in the menu
    */
    func menuItemSelected(menuItem: MenuItem)
    {
        animateMenuPanel(false)
        menuSelection = menuItem.title
        
        // Check if log out was selected
        if (menuSelection.containsString("Log out"))
        {
            logOut()
            return
        }
        
        // Remove the old screen
        self.mainViewController!.view.removeFromSuperview()
        self.mainViewController = nil;
        
        // Reset main view
        self.initialize()
    }
}


// MARK: UIGestureRecognizer delegate

extension ContainerViewController: UIGestureRecognizerDelegate
{
    // MARK: Gesture recognizer
    
    func handlePanGesture(recognizer: UIPanGestureRecognizer) {
        let gestureIsDraggingFromLeftToRight = (recognizer.velocityInView(view).x > 0)
        switch(recognizer.state)
        {
        case .Began:
            if (currentState == .Collapsed && gestureIsDraggingFromLeftToRight)
            {
                initializeMenu()
                showShadowForViewController(true)
            }
        case .Changed:
            if (currentState == .Collapsed && gestureIsDraggingFromLeftToRight || currentState == .Expanded)
            {
                recognizer.view!.center.x = recognizer.view!.center.x + recognizer.translationInView(view).x
                recognizer.setTranslation(CGPointZero, inView: view)
            }
        case .Ended:
            if (menuViewController != nil )
            {
                // animate the side panel open or closed based on whether the view has moved more or less than halfway
                let hasMovedGreaterThanHalfway = recognizer.view!.center.x > view.bounds.size.width
                animateMenuPanel(hasMovedGreaterThanHalfway)
            }
        default:
            break
        }
    }
    
}

// Class extension to retrieve views from UIStoryboard

private extension UIStoryboard
{
    class func mainStoryboard() -> UIStoryboard
    {
        return UIStoryboard(name: "Main", bundle: NSBundle.mainBundle())
    }
    
    class func menuViewController() -> MenuTableViewController?
    {
        return mainStoryboard().instantiateViewControllerWithIdentifier("MenuTableViewController") as? MenuTableViewController
    }
    
    /**
     Dynamically sets the main screen based on the inputed string.
     You must set the view's id to match the string value stored in menuItem.swift
    */
    class func mainViewController(screen:String) -> MainViewController?
    {
        return mainStoryboard().instantiateViewControllerWithIdentifier(screen + "ViewController") as? MainViewController
    }
    
}