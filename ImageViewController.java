import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.SepiaTone;

public class ImageViewController 
{
	private static int xValue = 0;
	private static double cValue = 0.0;
	private static double bValue = 0.0;
	private static double sValue = 0.0;
	private static double spValue = 0.0f;
	
	private int hValue = 300;
	private int wValue = 300;
	private int xlayout = 250;
	private int ylayout = 200;	//zooming
	
	private Image [] image = new Image[5]; //array of image
	private static int indexValue;
    // Private fields for components 
    @FXML
    private ImageView myImage;

    @FXML
    private ToggleGroup myToggleGroup;

    @FXML
    private RadioButton dogRadioButton;

    @FXML
    private RadioButton catRadioButton;
    
    @FXML
    private Button rotateButton;
    
    @FXML
    private Button zoomInButton;
    @FXML
    private Button zoomOutButton;
    
    @FXML
    private Stage stage;
    @FXML
    private Stage secondStage;
    @FXML
    private Label greetingLabel;
    //@FXML
   // private ListView listview;
  
    
    
    
    // Private fields for the dog and cat images
    private Image dogImage;
    private Image catImage;
    private Image goldenImage;
    private Image rabbitImage;
    private Image penguinImage;

    // InitiXalize method
    public void initialize() 
    {
      // Load the dog and cat images
      dogImage = new Image("Dog.jpg");
      catImage = new Image("Cat.jpg");
      goldenImage = new Image("golden.jpg");
      rabbitImage = new Image("rabbit.jpg");
      penguinImage = new Image ("penguin.jpg");
      image[0] = dogImage;
      image[1] = catImage;
      image[2] = goldenImage;
      image[3] = rabbitImage;
      image[4] = penguinImage;
    }
    //reset image method
    public void resetImage(){
    	xValue = 0 ;
    	myImage.setRotate(xValue);
    	hValue = 300;
    	myImage.setFitHeight(hValue);
    	wValue = 300;
    	myImage.setFitWidth(300);
    	xlayout = 250;
    	myImage.setLayoutX(xlayout);
    	ylayout = 200;
    	myImage.setLayoutY(ylayout);
    	cValue = 0.0;
    	bValue = 0.0;
    	sValue = 0.0;
    	spValue =0.0f;
    	ColorAdjust colourAd = new ColorAdjust();
    	SepiaTone sepiaTone = new SepiaTone();
    	colourAd.setContrast(cValue);
    	colourAd.setContrast(bValue);
    	colourAd.setSaturation(sValue);
    	sepiaTone.setLevel(spValue);
    	myImage.setEffect(colourAd);
    	
    }
    
    public void resetButtonListener(){
    	resetImage();
    	
    }
    // Event listener for the dogRadioButton
   /*public void dogRadioButtonListener() 
    {
      if (dogRadioButton.isSelected())
         myImage.setImage(dogImage);
      		hValue = 300;
  			wValue = 300;
  			xlayout = 250;
  			ylayout = 200;
  			resetImage();
      	 myImage.setImage(image[0]);
      	ColorAdjust colourAd = new ColorAdjust();
      	colourAd.setContrast(0.0);
    	myImage.setEffect(colourAd);
    }

    // Event listener for the catRadioButtonListener
    public void catRadioButtonListener() 
    {
      if (catRadioButton.isSelected())
         myImage.setImage(catImage);
      	 hValue = 300;
      	 wValue = 300;
      	 xlayout = 250;
      	 ylayout = 200;
      	 
      	resetImage();
      	 myImage.setImage(image[1]);
      	ColorAdjust colourAd = new ColorAdjust();
      	colourAd.setContrast(0.0);
    	myImage.setEffect(colourAd);
      	 
    }*/
    
    public void rotateButtonListener()
    {
    xValue += 90;
    myImage.setRotate(xValue);
    }
    
    public void zoomInButtonListener(){
    	xlayout -=20;
    	ylayout -=20;
    	hValue +=50;
    	wValue +=50;
    	myImage.setFitWidth(wValue);
    	myImage.setFitHeight(hValue);
    	myImage.setLayoutX(xlayout);
    	myImage.setLayoutY(ylayout);	
    }
    
    public void zoomOutButtonListener(){
    	xlayout +=20;
    	ylayout +=20;
    	hValue -=50;
    	wValue -=50;
    	if(hValue > 0 && wValue > 0){
    	myImage.setFitWidth(wValue);
    	myImage.setFitHeight(hValue);
    	myImage.setLayoutX(xlayout);
    	myImage.setLayoutY(ylayout);}
    	else
    	{ hValue = 300;
    	  wValue = 300;
    	  xlayout = 250;
    	  ylayout = 200;	
    		myImage.setFitWidth(wValue);
        	myImage.setFitHeight(hValue);
        	myImage.setLayoutX(xlayout);
        	myImage.setLayoutY(ylayout);}
    	}
    
    
    public void previousButtonListener(){
    	if (indexValue != 0){
    	
    	indexValue--;
    	}
    	else{
    		indexValue=image.length-1;
    	}
    	myImage.setImage(image[indexValue]);
    	resetImage();
    	if(image[indexValue] == null){	
    	
    	}
    }
    public void nextButtonListener(){
    	if(indexValue != image.length-1){
    	indexValue++;
    	}
    	else{
    		indexValue=0;
    	}
    	myImage.setImage(image[indexValue]);
    	resetImage();
    
    	
    }
  //Contrast adding adjustment
    public void contrastAddingButtonListener(){
    	ColorAdjust colourAd = new ColorAdjust();
    	if(cValue < 1.0){
    		cValue += 0.1;
    	colourAd.setContrast(cValue);
    	colourAd.setBrightness(bValue);
    	colourAd.setSaturation(sValue);
    	myImage.setEffect(colourAd);}
    	else {
    		cValue = 1.0;
    		colourAd.setContrast(cValue);
    		colourAd.setBrightness(bValue);
        	colourAd.setSaturation(sValue);
    	}
    	
    	
    	}
   //Contrast reducing adjustment
    public void contrastReducingButtonListener(){
    	ColorAdjust colourAd = new ColorAdjust();
    	if(cValue >-1.0){
    		cValue -=0.1;
    		colourAd.setContrast(cValue);
        	colourAd.setBrightness(bValue);
        	myImage.setEffect(colourAd);
        	
    	}
    	else {
    		cValue = -0.9;
    		colourAd.setContrast(cValue);
    		colourAd.setBrightness(bValue);
        	colourAd.setSaturation(sValue);}
    	
    }
    	
    
  //Brightness adding adjustment
    public void brightnessAddingButtonListener(){
    	ColorAdjust colourAd = new ColorAdjust();
    	if(bValue < 1.0){
    	bValue +=0.1;
    	colourAd.setBrightness(bValue);
    	colourAd.setContrast(cValue);
    	colourAd.setSaturation(sValue);
    	myImage.setEffect(colourAd);}
    	else{
    		bValue = 1.0;
    		colourAd.setBrightness(bValue);
        	colourAd.setContrast(cValue);
        	colourAd.setSaturation(sValue);
    	}
    	
    }
   
    //Brightness reducing adjustment
    public void brightnessReducingButtonListener(){
    	ColorAdjust colourAd = new ColorAdjust();
    	if(bValue > -1.0){
    	bValue -=0.1;
    	colourAd.setBrightness(bValue);
    	colourAd.setContrast(cValue);
    	colourAd.setSaturation(sValue);
    	myImage.setEffect(colourAd);}
    	else{
    		bValue = -0.9;
    		colourAd.setBrightness(bValue);
        	colourAd.setContrast(cValue);
        	colourAd.setSaturation(sValue);
    	}
    }
    
    // Saturation adding adjustment
    public void saturationAddingButtonListener(){
    	ColorAdjust colourAd = new ColorAdjust();
    	if(sValue < 1.0){
    	sValue += 0.1;
    	colourAd.setSaturation(sValue);
    	colourAd.setBrightness(bValue);
    	colourAd.setContrast(cValue);
    	myImage.setEffect(colourAd);}
    	else{
    		sValue = 1.0;
    		colourAd.setSaturation(sValue);
        	colourAd.setBrightness(bValue);
        	colourAd.setContrast(cValue);
    	}
    }
    //Saturation reducing adjustment
    public void saturationReducingButtonListener(){
    	ColorAdjust colourAd = new ColorAdjust();
    	if(sValue > -1.0){
    	sValue -= 0.1;
    	colourAd.setSaturation(sValue);
    	colourAd.setBrightness(bValue);
    	colourAd.setContrast(cValue);
    	myImage.setEffect(colourAd);}
    	else{
    		sValue = -0.9;
    		colourAd.setSaturation(sValue);
        	colourAd.setBrightness(bValue);
        	colourAd.setContrast(cValue);
    	}
    }
    //sepiaTone effect
    public void SepiaTone(){
    	SepiaTone sepiaTone = new SepiaTone();
    	spValue = 0.7f;
    	sepiaTone.setLevel(spValue);
    	myImage.setEffect(sepiaTone);
    }
    public void openFile(){
    	FileChooser fileChoosed = new FileChooser();
    	fileChoosed.setTitle("Open");
    	
    	fileChoosed.setInitialDirectory(new File(System.getProperty("user.home")));
    	fileChoosed.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter("All Images", "*.*"),
    			new FileChooser.ExtensionFilter("PNG", "*.png"),
    			new FileChooser.ExtensionFilter("JPG", "*.jpg")
    			);
    	File file = fileChoosed.showOpenDialog(stage);
    	
    	if(file != null){
    		System.out.println("File choosed : " + file);
    	}
    	
    	String path = file.getAbsolutePath();
    	
    	try{
    		InputStream input = new FileInputStream(path);
    		Image image = new Image(input);
    		myImage.setImage(image);
    	}catch (FileNotFoundException ex){
    		Logger.getLogger(ImageView.class.getName()).log(Level.SEVERE,null,ex);
    	}
    	
    }
    
    //saving file
   public void saveFile(){
    	FileChooser fileChoosed = new FileChooser();
    	fileChoosed.setTitle("Save Image");
    	File file = fileChoosed.showSaveDialog(secondStage);
    	if (file != null){
    		try {
    			ImageIO.write(SwingFXUtils.fromFXImage(myImage.getImage(), null), "png", file);
    		}catch (IOException ex){
    			Logger.getLogger(ImageView.class.getName()).log(Level.SEVERE, null, ex);
    		}
    	}
    }
    public void doExit(){
		Platform.exit();
	}
    
    
   
}
