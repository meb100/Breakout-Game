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
	//private ArrayList<GameObject> shapes; 
	private final Paint BACKGROUND_COLOR = Color.GREY;
	private final int SCREEN_BASE;
	private final int SCREEN_HEIGHT;
	public Level(int n, Stage s, /*ArrayList<GameObject> sh,*/ int b, int h){
		levelNum = n;
		stage = s;
		//shapes = sh;
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
		/*
		//Instantiate GameObjects (yes, even the ball and paddle are different instances in between levels)
		shapes.add(new Ball());
		shapes.add(new BlockGrid(levelNum));
		//Instantiate associated JavaFX shapes (BlockGrid will know how to generate right pattern of blocks)
		for(GameObject obj : shapes){
			obj.instantiateJavaFXShape();
		}
		//Group ordering
		for(GameObject obj : shapes){
			objectCollection.getChildren().add(obj);
		}
		*/
		//Add keyboard feature setup (cheat codes) here later
		//Return
		return scene;
	}

}
