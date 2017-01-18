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

public class Level {
	private int levelNum;
	private Stage stage;
	private final Paint BACKGROUND_COLOR = Color.GREY;
	private final int SCREEN_BASE;
	private final int SCREEN_HEIGHT;
	public final int FRAME_DELAY_MILLISECONDS;
	
	public Ball ball;
	//public GlasswareBlock block;
	public BlockGrid blockGrid;
	//Add more GameObjects here!
	
	public Level(int n, Stage s, int b, int h, int d){
		levelNum = n;
		stage = s;
		SCREEN_BASE = b;
		SCREEN_HEIGHT = h;
		FRAME_DELAY_MILLISECONDS = d;
	}
	public void run(){
		//This method taken from Dr Duvall's ExampleBounce.java shell
		Scene scene = initializeGameObjects();
		stage.setScene(scene);
		stage.setTitle("Breakout: Laboratory Edition. Level " + levelNum);
		stage.show();
		
		KeyFrame f = new KeyFrame(Duration.millis(FRAME_DELAY_MILLISECONDS), e -> step());
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE); //will probably change when add more levels - need to research!
		t.getKeyFrames().add(f);
		t.play();
	}
	private Scene initializeGameObjects(){
		Group objectCollection = new Group();
		Scene scene = new Scene(objectCollection, SCREEN_BASE, SCREEN_HEIGHT, BACKGROUND_COLOR); 
		
		//Instantiate GameObjects (yes, even the ball and paddle are different instances in between levels)
		//Note constructor automatically instantiates ImageView/Rectangle/Circle/etc JavaFX Shapes
		ball = new Ball(200.0, 200.0, 150, 150);
		//block = new GlasswareBlock(50.0, 50.0);
		blockGrid = new BlockGrid(levelNum, SCREEN_BASE, SCREEN_HEIGHT, 50.0, 15.0, 5.0);
		//Group ordering
		objectCollection.getChildren().add(ball.getJavaFXShape());
		//objectCollection.getChildren().add(block.getJavaFXShape());
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){
				if(blockGrid.getBlock(r, c) != null)
					objectCollection.getChildren().add(blockGrid.getBlock(r, c).getJavaFXShape());
			}
		}
		//Now all the GameObjects ARE drawn in their initial positions
		//Add keyboard feature setup (cheat codes) here later
		//Return
		return scene;
	}
	private void step(){
		final double FRAME_DELAY_SECONDS = FRAME_DELAY_MILLISECONDS / 1000.0;
		//Update positions of Ball and Paddle
		ball.updateLocation(FRAME_DELAY_SECONDS, SCREEN_BASE, SCREEN_HEIGHT);
		//Take care of collisions
	}

}
