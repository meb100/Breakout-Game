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

import java.util.ArrayList;

public class Level {
	private int levelNum;
	private Stage stage;
	private ArrayList<GameObject> shapes = new ArrayList<>(); 
	private final Paint BACKGROUND_COLOR = Color.GREY;
	private final int SCREEN_BASE;
	private final int SCREEN_HEIGHT;
	public Level(int n, Stage s, int b, int h){
		levelNum = n;
		stage = s;
		SCREEN_BASE = b;
		SCREEN_HEIGHT = h;
	}
	public void run(){
		Scene scene = initializeGameObjects();
		stage.setScene(scene);
		stage.setTitle("Breakout: Laboratory Edition. Level " + levelNum);
		stage.show();
		
		
	}
	private Scene initializeGameObjects(){
		Group objectCollection = new Group();
		Scene scene = new Scene(objectCollection, SCREEN_BASE, SCREEN_HEIGHT, BACKGROUND_COLOR); 
		
		//Instantiate GameObjects (yes, even the ball and paddle are different instances in between levels)
		//Note constructor automatically instantiates ImageView/Rectangle/Circle/etc JavaFX Shapes
		shapes.add(new Ball(200, 200, 60, 60));
		//shapes.add(new BlockGrid(levelNum));
		//Group ordering
		for(GameObject obj : shapes){
			objectCollection.getChildren().add(obj.getJavaFXShape());
		}
		//Now all the GameObjects ARE drawn in their intial positions
		//Add keyboard feature setup (cheat codes) here later
		//Return
		return scene;
	}

}
