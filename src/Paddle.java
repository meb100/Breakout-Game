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

public class Paddle implements GameObject{
	private int vel; //just x direction!
	private ImageView imageView;
	private final String imageFilename = "HotPlate.jpg"; //https://www.nist.gov/laboratories/tools-instruments/corning-pc-420-stirrer-hot-plate
	public static final int INITIAL_WIDTH = 70;
	
	public Paddle(double initX, double initY, int initVel){
		vel = initVel;
		imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilename)));
		setWidth(INITIAL_WIDTH);
		setHeight(20);
		setX(initX);
		setY(initY);
	}
	@Override
	public ImageView getJavaFXShape(){
		return imageView;
	}

	public double getX(){
		return imageView.getX();
	}
	public double getY(){
		return imageView.getY();
	}

	public void setX(double newX){
		imageView.setX(newX);
	}
	public void setY(double newY){
		imageView.setY(newY);
	}

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
	
	//Velocity getters and selectors
	public int getVelocity(){
		return vel;
	}
	public void setVelocity(int v){
		vel = v;
	}
	
	//Movement methods
	public void moveRight(int screen_width){
		if(getX() + getWidth() < screen_width){
			setX(getX() + vel);
		}
	}
	public void moveLeft(){
		if(getX() > 0){
			setX(getX() - vel);
		}
	}
}
