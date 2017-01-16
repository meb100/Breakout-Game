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
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private final String imageFilename = "StirBar.jpg";
	private ImageView imageView;
	public Ball(int initX, int initY, int initXVel, int initYVel){
		x = initX;
		y = initY;
		xVel = initXVel;
		yVel = initYVel;
		imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilename)));
	}
	//Accessors and modifiers
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getXVel(){
		return xVel;
	}
	public int getYVel(){
		return yVel;
	}
	public void setX(int newX){
		x = newX;
	}
	public void setY(int newY){
		y = newY;
	}
	public void setXVel(int newXVel){
		x = newXVel;
	}
	public void setYVel(int newYVel){
		y = newYVel;
	}
	public ImageView getJavaFXShape(){
		return imageView;
	}
	//For animation (implemented from GameObject)
	public void step(){
		return;
	}
}
