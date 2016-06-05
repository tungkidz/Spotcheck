
class ProfileViewController: MainViewController
{
    override func viewDidLoad()
    {
        super.viewDidLoad()
        
    }
    
    @IBAction func addButtonClicked(sender: AnyObject)
    {
        super.delegate?.updateMenuSelection("Capture")
    }
    
}
