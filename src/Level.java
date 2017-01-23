import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class implements each level screen, as specified by the levelNum variable.
 * @author Matthew Barbano
 *
 */
public class Level implements Screen{
	private int levelNum;
	private final Paint BACKGROUND_COLOR = Color.GREY;
	private final int SCREEN_BASE;
	private final int SCREEN_HEIGHT;
	public final int FRAME_DELAY_MILLISECONDS;
	private Group objectCollection;
	
	private Ball ball;
	private BlockGrid blockGrid;
	private Paddle paddle;
	private Scorebar scorebar;
	
	private int status;
	
	public Level(int n,int b, int h, int d){
		levelNum = n;
		SCREEN_BASE = b;
		SCREEN_HEIGHT = h;
		FRAME_DELAY_MILLISECONDS = d;
		status = Screen.NEEDS_SETUP;
	}
	public int getStatus(){
		return status;
	}
	public void setStatus(int newStatus){
		status = newStatus;
	}
	public Scene initialize(){
		objectCollection = new Group();
		Scene scene = new Scene(objectCollection, SCREEN_BASE, SCREEN_HEIGHT, BACKGROUND_COLOR); 
		
		ball = new Ball();
		blockGrid = new BlockGrid(levelNum);
		paddle = new Paddle();
		scorebar = new Scorebar(levelNum);
		
		objectCollection.getChildren().add(ball.getJavaFXShape());
		objectCollection.getChildren().add(paddle.getJavaFXShape());
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){
				if(blockGrid.getBlock(r, c) != null)
					objectCollection.getChildren().add(blockGrid.getBlock(r, c).getJavaFXShape());
			}
		}
		objectCollection.getChildren().add(scorebar.getJavaFXShape());
		
        status = Screen.RUNNING;
        scene.setOnKeyPressed(e -> keyboardInput(e.getCode()));
        
		return scene;
	}
	public void step(){
		ball.updateLocation(scorebar);
		
		//Ball-Paddle Collision
		if(gameObjectsIntersect(ball, paddle)){
			ball.collisionWithPaddle(paddle);
		}
		
		//Ball-Block Collision
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){
				if(blockGrid.getBlock(r, c) != null && gameObjectsIntersect(ball, blockGrid.getBlock(r, c))){
					//Update scorebar - important to do before remove blocks
					if(blockGrid.getBlock(r, c) instanceof GlasswareBlock){
						scorebar.incrementScore(1);
					}
					else if(blockGrid.getBlock(r, c) instanceof PowerupBlock){
						scorebar.incrementScore(3);
					}
					//Run Ball and BlockGrid methods
					ball.collisionWithBlock(blockGrid.getBlock(r,c));
					blockGrid.getBlock(r, c).collisionWithBall(objectCollection, scorebar, paddle, ball, blockGrid, r, c);
				}
			}
		}
		
		//Update Scorebar
		scorebar.drawScorebar();
		
		//Ending level
		if(scorebar.getLivesLeft() <= 0){
			status = Screen.LOST;
		}
		boolean allBlocksNull = true;
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){
				if(blockGrid.getBlock(r, c) != null){
					allBlocksNull = false;
				}
			}
		}
		if(allBlocksNull){
			status = Screen.WON;
		}
	}
	private boolean gameObjectsIntersect(GameObject obj1, GameObject obj2){
		return obj1.getJavaFXShape().getBoundsInParent().intersects(obj2.getJavaFXShape().getBoundsInParent());
	}
	private void keyboardInput(KeyCode code){
		if(code == KeyCode.RIGHT){
			paddle.moveRight(1);
		}
		else if(code == KeyCode.LEFT){
			paddle.moveLeft(1);
		}
		else if(code == KeyCode.D){
			paddle.moveRight(2);
		}
		else if(code == KeyCode.A){
			paddle.moveLeft(2);
		}
		else if(code == KeyCode.E){
			paddle.moveRight(3);
		}
		else if(code == KeyCode.Q){
			paddle.moveLeft(3);
		}
		//Cheat keys
		else if(code == KeyCode.B){
			scorebar.setLivesLeft(Scorebar.TOTAL_LIVES);
		}
		else if(code == KeyCode.C){
			for(int r = 0; r < blockGrid.getRows(); r++){
				for(int c = 0; c < blockGrid.getCols(); c++){
					if(blockGrid.getBlock(r, c) instanceof GlasswareBlock || blockGrid.getBlock(r, c) instanceof PowerupBlock)
						blockGrid.setBlock(null, r, c);
				}
			}
		}
		else if(code == KeyCode.DIGIT1){
			status = Screen.JUMP_TO_LEVEL_1;
		}
		else if(code == KeyCode.DIGIT2){
			status = Screen.JUMP_TO_LEVEL_2;
		}
		else if(code == KeyCode.DIGIT3){
			status = Screen.JUMP_TO_LEVEL_3;
		}
	}
}
