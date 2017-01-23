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

public class Ball extends GameObject {
	public static final double INITIAL_X = 200.0;
	public static final double INITIAL_Y = 450.0;
	public static final int INITIAL_X_VELOCITY = 200;
	public static final int INITIAL_Y_VELOCITY = -100;
	public static final int IMAGE_WIDTH = 25;
	public static final int IMAGE_HEIGHT = 7;
	public static final String IMAGE_FILENAME = "StirBar.jpg";
	public static final double COLLISION_INTERIOR_THRESHOLD = 0.25;
	
	private int xVelocity;
	private int yVelocity;
	
	public Ball(){
		super(INITIAL_X, INITIAL_Y, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_FILENAME);
		
		xVelocity = INITIAL_X_VELOCITY;
		yVelocity = INITIAL_Y_VELOCITY;
	}
	

	public int getXVelocity(){
		return xVelocity;
	}
	public int getYVelocity(){
		return yVelocity;
	}
	public void setXVelocity(int newXVel){
		xVelocity = newXVel;
	}
	public void setYVelocity(int newYVel){
		yVelocity = newYVel;
	}

	
	public void updateLocation(Scorebar scorebar){
		incrementCoordinates();
		checkWallCollisions(scorebar);
	}
	private void incrementCoordinates(){
		setX(getX() + xVelocity*Driver.FRAME_DELAY_MILLISECONDS/1000);
		setY(getY() + yVelocity*Driver.FRAME_DELAY_MILLISECONDS/1000);
	}
	private void checkWallCollisions(Scorebar scorebar){
        if(beyondRightWall()){
        	xVelocity *= -1;
        }
        if(beyondLeftWall()){
        	xVelocity *= -1;
        }
        if(beyondTopWall()){
        	yVelocity *= -1;
        }
        if(beyondBottomWall()){
        	resetAfterBottomWall(scorebar);
        }
	}
	private boolean beyondRightWall(){
		return getX() + getWidth() >= Driver.SCREEN_BASE;
	}
	private boolean beyondLeftWall(){
		return getX() <= 0;
	}
	private boolean beyondTopWall(){
		return getY() <= 0;
	}
	private boolean beyondBottomWall(){
		return getY() + getHeight() >= Driver.SCREEN_HEIGHT;
	}
	private void resetAfterBottomWall(Scorebar scorebar){
    	setX(INITIAL_X);
    	setY(INITIAL_Y);
    	setXVelocity(INITIAL_X_VELOCITY);
    	setYVelocity(INITIAL_Y_VELOCITY);
    	
    	scorebar.decrementLivesLeft();
	}

	public void collisionWithPaddle(Paddle paddle){
		bounceOff(paddle);
	}
	public void collisionWithBlock(Block block){
		bounceOff(block);
	}
	private void bounceOff(GameObject otherShape){
		String sideHit = whichSideHit(otherShape);
		if(sideHit.equals("Left")){
			xVelocity = - Math.abs(xVelocity);
		}
		else if(sideHit.equals("Right")){
			xVelocity = Math.abs(xVelocity);
		}
		else if(sideHit.equals("Top")){
			yVelocity = - Math.abs(yVelocity);
		}
		else{
			yVelocity = Math.abs(yVelocity);
		}
	}
	/**
	 * During a ball-paddle or ball-block collision, determines which 
	 * side of the paddle/block the ball hit. Uses criteria of whether ball
	 * hit within vertical or horizontal range of rectangle edges, then distinguishes
	 * between top/bottom and left/right with COLLISION_INTERIOR_THRESHOLD. Assumes otherShape's
	 * dimensions (width, height, and area) are larger than this's dimensions, and that this and
	 * otherShape intersect. In general, "Top" and "Bottom" take precedence over
	 * "Left" and "Right".
	 * @param otherShape The paddle/block intersecting with this
	 * @return "Top", "Bottom", "Left", or "Right" indicating where the collision occurred.
	 */
	private String whichSideHit(GameObject otherShape){  
		String sideHit = ""; 
		
		if(withinRectangleHorizontally(otherShape)
				&& getLeftX(otherShape) + otherShape.getWidth() * COLLISION_INTERIOR_THRESHOLD >= getRightX(this)){
			sideHit = "Left";
		}
		if(withinRectangleHorizontally(otherShape)
				&& getRightX(otherShape) - otherShape.getWidth() * COLLISION_INTERIOR_THRESHOLD <= getLeftX(this)){
			sideHit = "Right";
		}
		if(withinRectangleVertically(otherShape)
				&& getBottomY(otherShape) - otherShape.getHeight() * COLLISION_INTERIOR_THRESHOLD <= getTopY(this)){
			sideHit = "Bottom";
		}
		if(withinRectangleVertically(otherShape)
				&& getTopY(otherShape) + otherShape.getHeight() * COLLISION_INTERIOR_THRESHOLD >= getBottomY(this)){
			sideHit = "Top";
		}
		return sideHit;
	}
	private boolean withinRectangleHorizontally(GameObject otherShape){
		return getTopY(otherShape) <= getTopY(this) && getBottomY(otherShape) >= getBottomY(this);
	}
	private boolean withinRectangleVertically(GameObject otherShape){
		return getLeftX(otherShape) <= getLeftX(this) && getRightX(otherShape) >= getRightX(this);
	}
	private double getLeftX(GameObject shape){
		return shape.getX();
	}
	private double getRightX(GameObject shape){
		return shape.getX() + shape.getWidth();
	}
	private double getTopY(GameObject shape){
		return shape.getY();
	}
	private double getBottomY(GameObject shape){
		return shape.getY() + shape.getHeight();
	}
}
