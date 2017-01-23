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

public abstract class GameObject {
	private ImageView imageView;
	
	public GameObject(double initialX, double initialY, int width, int height, String imageFilename){
		setJavaFXShape(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilename))));
		setX(initialX);
		setY(initialY);
		setWidth(width);
		setHeight(height);
	}
	public ImageView getJavaFXShape(){
		return imageView;
	}
	public void setJavaFXShape(ImageView newImageView){
		imageView = newImageView;
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
	public double getHeight() {
 		return imageView.getFitHeight();
	}
	public void setWidth(double newWidth) {
		imageView.setFitWidth(newWidth);
	}
	public void setHeight(double newHeight) {
		imageView.setFitHeight(newHeight);
	}
}
