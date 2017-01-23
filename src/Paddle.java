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

public class Paddle extends GameObject{
	public static final double INITIAL_X = 200.0;
	public static final double INITIAL_Y = 525.0;
	public static final int INITIAL_VELOCITY = 5;
	public static final int INITIAL_WIDTH = 70;
	public static final int INITIAL_HEIGHT = 20;
	public static final String IMAGE_FILENAME = "HotPlate.jpg";
	
	private int velocity;

	public Paddle(){
		super(INITIAL_X, INITIAL_Y, INITIAL_WIDTH, INITIAL_HEIGHT, IMAGE_FILENAME);
		velocity = INITIAL_VELOCITY;
	}
	
	public int getVelocity(){
		return velocity;
	}
	public void setVelocity(int newVelocity){
		velocity = newVelocity;
	}
	
	public void moveRight(int multiplier){
		if(getX() + getWidth() < Driver.SCREEN_BASE){
			setX(getX() + multiplier*velocity);
		}
	}
	public void moveLeft(int multiplier){
		if(getX() > 0){
			setX(getX() - multiplier*velocity);
		}
	}
}
