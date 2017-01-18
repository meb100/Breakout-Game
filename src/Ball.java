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
	private int xVel;
	private int yVel;
	private final String imageFilename = "StirBar.jpg";
	private ImageView imageView; //Just use imageView's x and y to keep track of position
	public Ball(double initX, double initY, int initXVel, int initYVel){
		xVel = initXVel;
		yVel = initYVel;
		imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilename)));
		imageView.setFitWidth(50);
		imageView.setFitHeight(15);
		imageView.setX(initX);
		imageView.setY(initY);
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
	public void updateLocation(double delay_sec, int width, int height){ //also deal with wall collisions here
		//Update position
		setX(getX() + xVel*delay_sec);
		setY(getY() + yVel*delay_sec);
		//Collisions with walls
        if(getX() + getWidth() >= width)
        	xVel *= -1;
        //left wall
        if(getX() <= 0)
        	xVel *= -1;
        //top wall
        if(getY() + getHeight() >= height)
        	yVel *= -1;
        //bottom wall
        if(getY() <= 0)
        	yVel *= -1;
	}
	public void collisionWithPaddle(){
		return;
	}
	public void collisionWithBlock(){
		return;
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
