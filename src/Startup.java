import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Startup implements Screen{
	private static final Color BACKGROUND_COLOR = Color.CORNFLOWERBLUE;
	private Group root; 
	private ImageView logo;
	private ImageView rules;
	private Text start;
	private static final String logoFilename = "logo.jpg";
	private static final String rulesFilename = "rules.jpg";
	
	private int status;
	public Startup(){
		status = Screen.NEEDS_SETUP;
	}
	public int getStatus(){
		return status;
	}
	public void setStatus(int newStatus){
		status = newStatus;
	}
	public Scene initialize(){
		//Instantiate root Group and Scene
		root = new Group();
		Scene scene = new Scene(root, Driver.SCREEN_BASE, Driver.SCREEN_HEIGHT, BACKGROUND_COLOR);
		
		//Instantiate ImageViews and set locations
		logo = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(logoFilename)));
		//Note to self: getFitWidth() and getFitHeight() do not work as expected until you set them manually
		logo.setFitWidth(480 / 2);
		logo.setFitHeight(230 / 2);
		logo.setX(Driver.SCREEN_BASE / 2 - logo.getFitWidth() / 2);
		logo.setY(Driver.SCREEN_HEIGHT / 15);
		
		rules = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(rulesFilename)));
		rules.setFitWidth(600 / 2);
		rules.setFitHeight(600 / 2);
		rules.setX(Driver.SCREEN_BASE / 2 - rules.getFitWidth() / 2);
		rules.setY(Driver.SCREEN_HEIGHT / 2.5);
		
		//Set text and set locations/properties
		start = new Text(0, Driver.SCREEN_HEIGHT / 3, "Press Space Bar to Start");
    	start.setFont(Font.font("Chiller", 35));
    	start.setWrappingWidth(Driver.SCREEN_BASE);
    	start.setTextAlignment(TextAlignment.CENTER);
    	start.setFill(Color.BLACK);
    	//start.setX(Driver.SCREEN_BASE / 2 - start.getWrappingWidth() / 2);
    	
    	
    	//Add to root group
    	root.getChildren().addAll(logo, rules, start);
    	
    	//Change status
    	status = Screen.RUNNING;
    	
		//Listen for keyboard input (S to Start)
    	scene.setOnKeyPressed(e -> keyboardInput(e.getCode()));
    	//Return scene
    	return scene;
	}
	public void step(){
		//Can make start text blink later if want
		//System.out.println("Stepping!");
	}
	private void keyboardInput(KeyCode code){
		if(code == KeyCode.SPACE){
			System.out.println("Pressed start");
			status = Screen.WON;
		}
	}
}
