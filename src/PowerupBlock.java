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

public abstract class PowerupBlock implements Block{ //and since Block interface extends GameObject interface, must implement GameObject methods too!
	public ImageView imageView;
	
	//No constructor - see subclasses
	//From Block interface
	@Override
	public abstract void collisionWithBall(Group group, Paddle paddle, Ball ball, BlockGrid grid, int r, int c); //Paddle only for MSDS block, consider refactoring

	//Rest from GameObject interface - consider implementing these all in GameObject interface and make it a class!
	@Override
	public ImageView getJavaFXShape() {
		return imageView;
	}

	@Override
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