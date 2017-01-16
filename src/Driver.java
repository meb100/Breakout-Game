import java.util.ArrayList;

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
	public static final int numLevels = 3;
	public static Level[] levels = new Level[numLevels];
	
	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Startup and Level classes know when to terminate themselves
		//Startup startup = new Startup(primaryStage, SCREEN_BASE, SCREEN_HEIGHT);
		//startup.run();
		for(int i = 0; i < numLevels; i++){
			levels[i] = new Level(i + 1, primaryStage, SCREEN_BASE, SCREEN_HEIGHT); //i is level number
			levels[i].run();
		}
	}
}
