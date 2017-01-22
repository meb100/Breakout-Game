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

public class Level implements Screen{
	private int levelNum;
	private final Paint BACKGROUND_COLOR = Color.GREY;
	private final int SCREEN_BASE;
	private final int SCREEN_HEIGHT;
	public final int FRAME_DELAY_MILLISECONDS;
	public Group objectCollection;
	
	public Ball ball;
	//public GlasswareBlock block;
	public BlockGrid blockGrid;
	public Paddle paddle;
	public Scorebar scorebar;
	//Add more GameObjects here!
	
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
		
		//Instantiate GameObjects (yes, even the ball and paddle are different instances in between levels)
		//Note constructor automatically instantiates ImageView/Rectangle/Circle/etc JavaFX Shapes
		ball = new Ball();
		//block = new GlasswareBlock(50.0, 50.0);
		blockGrid = new BlockGrid(levelNum, SCREEN_BASE, SCREEN_HEIGHT, 50.0, 15.0, 5.0);
		paddle = new Paddle(200.0, 525.0, 5);
		scorebar = new Scorebar(levelNum);
		//Group ordering
		objectCollection.getChildren().add(ball.getJavaFXShape());
		//objectCollection.getChildren().add(block.getJavaFXShape());
		objectCollection.getChildren().add(paddle.getJavaFXShape());
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){
				if(blockGrid.getBlock(r, c) != null)
					objectCollection.getChildren().add(blockGrid.getBlock(r, c).getJavaFXShape());
			}
		}
		objectCollection.getChildren().add(scorebar.getJavaFXShape());
		
        //Change status
        status = Screen.RUNNING;
		
		//Keyboard feature setup (cheat codes, paddle movement)
        scene.setOnKeyPressed(e -> keyboardInput(e.getCode()));
        
		//Return
		return scene;
	}
	public void step(){
		final double FRAME_DELAY_SECONDS = FRAME_DELAY_MILLISECONDS / 1000.0;
		//Update positions of Ball and Paddle - Also takes care of ball-wall collisions, including bottom wall
		ball.updateLocation(scorebar, FRAME_DELAY_SECONDS, SCREEN_BASE, SCREEN_HEIGHT);
		//Take care of collisions
		//Ball-Paddle
		if(gameObjectsIntersect(ball, paddle)){
			ball.collisionWithPaddle(paddle);
		}
		//Ball-Block
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){
				//Short circuiting
				if(blockGrid.getBlock(r, c) != null && gameObjectsIntersect(ball, blockGrid.getBlock(r, c))){
					//Update Scorebar - make sure to do BEFORE remove blocks!
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
		//If game over, stop the animation and erase scene
		if(scorebar.getLivesLeft() <= 0){
			status = Screen.LOST;
		}
		//If won level, stop the animation and erase scene
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
			paddle.moveRight(SCREEN_BASE);
		}
		else if(code == KeyCode.LEFT){
			paddle.moveLeft();
		}
		//Cheat keys
		//Restore all lives (burns)
		else if(code == KeyCode.B){
			scorebar.setLivesLeft(Scorebar.TOTAL_LIVES);
		}
		//Clear all blocks except for chemical blocks (to focus on lab equipment) - if no chemical blocks, jumps to next level
		else if(code == KeyCode.C){
			for(int r = 0; r < blockGrid.getRows(); r++){
				for(int c = 0; c < blockGrid.getCols(); c++){
					if(blockGrid.getBlock(r, c) instanceof GlasswareBlock || blockGrid.getBlock(r, c) instanceof PowerupBlock)
						blockGrid.setBlock(null, r, c);
				}
			}
		}
		//Jump to level 1
		else if(code == KeyCode.DIGIT1){
			status = Screen.JUMP_TO_LEVEL_1;
		}
		//Jump to level 2
		else if(code == KeyCode.DIGIT2){
			status = Screen.JUMP_TO_LEVEL_2;
		}
		//Jump to level 3
		else if(code == KeyCode.DIGIT3){
			status = Screen.JUMP_TO_LEVEL_3;
		}
	}
}
