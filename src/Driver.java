import java.util.Arrays;

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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application {
	//Picture constants
	public static final int SCREEN_BASE = 400;
	public static final int SCREEN_HEIGHT = 600;
	//Animation timing constants
	public static final int FRAME_DELAY_MILLISECONDS = 15;
	//Level-related constants/variables
	public static final int NUM_LEVELS = 3;
	public static Screen[] screens = new Screen[NUM_LEVELS + 1];
	private int currentScreenNumber;  //0-indexed
	
	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		//Instantiate Startup and Level objects, set current screen number
		screens[0] = new Startup();
		for(int level = 1; level <= NUM_LEVELS; level++){
			screens[level] = new Level(level, SCREEN_BASE, SCREEN_HEIGHT, FRAME_DELAY_MILLISECONDS);
		}
		currentScreenNumber = 0;
		
		//Do standard setup (some taken from CS 308 Lab_Bounce)
		Scene scene = new Scene(new Group(), SCREEN_BASE, SCREEN_HEIGHT, Color.YELLOW); //blank dummy scene
		stage.setScene(scene);
		stage.setTitle("Breakout: Laboratory Edition");
		stage.show();
		
		KeyFrame f = new KeyFrame(Duration.millis(FRAME_DELAY_MILLISECONDS), e -> step(stage));
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE); //will probably change when add more levels - need to research!
		t.getKeyFrames().add(f);
		t.play();
	}
	private void step(Stage stage){
		if(screens[currentScreenNumber].getStatus() == Screen.NEEDS_SETUP){
			stage.setScene(screens[currentScreenNumber].initialize());
		}
		else if(screens[currentScreenNumber].getStatus() == Screen.RUNNING){
			screens[currentScreenNumber].step();
		}
		else if(screens[currentScreenNumber].getStatus() == Screen.WON){
			if(currentScreenNumber == NUM_LEVELS){   //NUM_LEVELS = (NUM_LEVELS + 1) - 1 = NUM_SCREENS - 1
				resetGame();
				System.out.println("Won game");
			}
			else{
				currentScreenNumber++;
				System.out.println("Won level");
			}
		}
		else if(screens[currentScreenNumber].getStatus() == Screen.LOST){
			resetGame();
			System.out.println("Lost level");
		}
		else{
			System.out.println("Error");
		}
	}
	private void resetGame(){
		for(Screen screenToReset : screens){
			screenToReset.setStatus(Screen.NEEDS_SETUP);
		}
		currentScreenNumber = 0;
	}
}
