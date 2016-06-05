
class CaptureViewController: MainViewController, UINavigationControllerDelegate, UIImagePickerControllerDelegate
{
    @IBOutlet var name: UITextField!
    @IBOutlet var image: UIImageView!
    let imagePicker = UIImagePickerController()
    
    @IBAction func selectButtonClicked(sender: AnyObject)
    {
        imagePicker.allowsEditing = false
        imagePicker.sourceType = .PhotoLibrary
        
        presentViewController(imagePicker, animated: true, completion: nil)
    }
    
    @IBAction func submitButtonClicked(sender: AnyObject)
    {
        //send to api
        let spotForm: SpotForm = SpotForm()
        //spotForm.photo = iUIImageJPEGRepresentation(image.image)
        spotForm.name = name.text
        spotForm.profileId = Data.model.user.profileId
        
        
        // Set up Api service
        var service : ServiceSpotcheckApi? = nil
        if service == nil
        {
            service = ServiceSpotcheckApi()
            service?.retryEnabled = true
        }
        
        // Call the Api service
        /*
        let query : QuerySpotcheckApi = QuerySpotcheckApi.createSpot(spotForm) as QuerySpotcheckApi
        service!.executeQuery(query, completionHandler:
            { (ticket: GTLServiceTicket!, object: AnyObject!, error: NSError!) -> Void in
                
                // Get response from Api service
                let resp = object as! Spot
                
                print("spotid = " + resp.spotId.stringValue)
        })
 */
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        imagePicker.delegate = self
    }
    
    // MARK: - UIImagePickerControllerDelegate Methods
    
    func imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : AnyObject]) {
        if let pickedImage = info[UIImagePickerControllerOriginalImage] as? UIImage
        {
            image.contentMode = .ScaleAspectFit
            image.image = pickedImage
        }
        
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    func imagePickerControllerDidCancel(picker: UIImagePickerController) {
        dismissViewControllerAnimated(true, completion: nil)
    }
}
