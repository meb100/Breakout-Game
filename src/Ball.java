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

public class Ball implements GameObject {
	public static final double INITIAL_X = 200.0;
	public static final double INITIAL_Y = 450.0;
	public static final int INITIAL_XVEL = 200;
	public static final int INITIAL_YVEL = 100;
	private int xVel;
	private int yVel;
	private final String imageFilename = "StirBar.jpg";
	private ImageView imageView; //Just use imageView's x and y to keep track of position
	public Ball(){
		xVel = INITIAL_XVEL;
		yVel = INITIAL_YVEL;
		imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilename)));
		setWidth(25);
		setHeight(7);
		setX(INITIAL_X);
		setY(INITIAL_Y);
	}
	//Accessors and modifiers
	public double getX(){
		return imageView.getX();
	}
	public double getY(){
		return imageView.getY();
	}
	public int getXVel(){
		return xVel;
	}
	public int getYVel(){
		return yVel;
	}
	public void setX(double newX){
		imageView.setX(newX);
	}
	public void setY(double newY){
		imageView.setY(newY);
	}
	public void setXVel(int newXVel){
		xVel = newXVel;
	}
	public void setYVel(int newYVel){
		yVel = newYVel;
	}
	public ImageView getJavaFXShape(){
		return imageView;
	}
	//For animation (implemented from GameObject)
	//What does a Ball need to know how to do?
	//Update position based on speed and time elapsed (as do Paddle, but not Bricks)
	//Bounce off walls (ONLY Ball)
	//How to handle collisions (design decision): Check if there is a collision in Level class, then if so, run
	//appropriate collision method in classes of ALL affected GameObjects
	//Collision with Paddle
	//Collision with Block
	//Collision with LabEquipment
	public void updateLocation(Scorebar scorebar, double delay_sec, int width, int height){ //also deal with wall collisions here
		//Update position
		setX(getX() + xVel*delay_sec);
		setY(getY() + yVel*delay_sec);
		//Collisions with walls
		//right wall
		
        if(beyondRightWall(width)){
        	xVel *= -1;
        	/*
        	System.out.println("Right");
            System.out.println("x = " + getX() + " y = " + getY());
            */
        }
        //left wall
        if(beyondLeftWall()){
        	xVel *= -1;
        	/*
        	System.out.println("Left");
            System.out.println("x = " + getX() + " y = " + getY());
            */
        }
        //top wall
        if(beyondTopWall()){
        	yVel *= -1;
        	/*
        	System.out.println("Top");
            System.out.println("x = " + getX() + " y = " + getY());
            */
        }
        //bottom wall
        if(beyondBottomWall(height)){
        	yVel *= -1;
        	/*
        	System.out.println("Bottom");
            System.out.println("x = " + getX() + " y = " + getY());
            */
        	//Reset ball location/velocity
        	setX(INITIAL_X);
        	setY(INITIAL_Y);
        	setXVel(INITIAL_XVEL);
        	setYVel(INITIAL_YVEL);
        	//Decrement lives
        	scorebar.decrementLivesLeft();
        }
	}
	private boolean beyondRightWall(int width){
		return getX() + getWidth() >= width;
	}
	private boolean beyondLeftWall(){
		return getX() <= 0;
	}
	private boolean beyondTopWall(){
		return getY() <= 0;
	}
	private boolean beyondBottomWall(int height){
		return getY() + getHeight() >= height;
	}
	public void collisionWithPaddle(Paddle paddle){  //Perhaps come back at end to deal with edge cases of hitting paddle from sides other than top
		bounceOff(paddle);
	}
	public void collisionWithBlock(Block block){
		bounceOff(block);
	}
	private void bounceOff(GameObject otherShape){
		String sideHit = whichSideHit(otherShape);
		if(sideHit.equals("Left")){
			xVel = - Math.abs(xVel);
		}
		else if(sideHit.equals("Right")){
			xVel = Math.abs(xVel);
		}
		else if(sideHit.equals("Top")){
			yVel = - Math.abs(yVel);
		}
		else{
			yVel = Math.abs(yVel);
		}
	}
	private String whichSideHit(GameObject otherShape){  //returns "Top", "Bottom", "Left","Right", assumes width and height of otherShape are larger than those of this, and than an intersection has occurred
		String sideHit = ""; //Top and Bottom take precedence over Left and Right
		
		if(getTopY(otherShape) <= getTopY(this)
				&& getBottomY(otherShape) >= getBottomY(this)
				&& getLeftX(otherShape) + otherShape.getWidth() / 4 >= getRightX(this)){
			System.out.println("Left");
			sideHit = "Left";
		}
		if(getTopY(otherShape) <= getTopY(this)
				&& getBottomY(otherShape) >= getBottomY(this)
				&& getRightX(otherShape) - otherShape.getWidth() / 4 <= getLeftX(this)){
			System.out.println("Right");
			sideHit = "Right";
		}
		if(getLeftX(otherShape) <= getLeftX(this) 
				&& getRightX(otherShape) >= getRightX(this)
				&& getBottomY(otherShape) - otherShape.getHeight() / 4 <= getTopY(this)){
			System.out.println("Bottom");
			sideHit = "Bottom";
		}
		if(getLeftX(otherShape) <= getLeftX(this)
				&& getRightX(otherShape) >= getRightX(this)
				&& getTopY(otherShape) + otherShape.getHeight() / 4 >= getBottomY(this)){
			System.out.println("Top");
			sideHit = "Top";
		}
		return sideHit;
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
	public void collisionWithLabEquipment(){
		return;
	}
	//make sure get/set fitWidth is OK, rather than method in ExampleBounce.java
	@Override
	public double getWidth() {
		return imageView.getFitWidth();
	}
	@Override
	public double getHeight() {
 		return imageView.getFitHeight();
	}
	@Override
	public void setWidth(double newWidth) {
		imageView.setFitWidth(newWidth);
	}
	@Override
	public void setHeight(double newHeight) {
		imageView.setFitHeight(newHeight);
	}
}
