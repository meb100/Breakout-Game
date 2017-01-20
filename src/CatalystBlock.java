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

public class CatalystBlock extends PowerupBlock{
	public final String imageFilename = "catalyst.jpg";

	public CatalystBlock(double initX, double initY, double w, double h){
		//Rethink location (i.e. class) of constructor when refactoring
		imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilename)));
		imageView.setFitWidth(w);
		imageView.setFitHeight(h);
		imageView.setX(initX);
		imageView.setY(initY);
	}
	
	@Override
	public void collisionWithBall(Group group, Paddle paddle, Ball ball, BlockGrid grid, int r, int c) {
		//Disappears
		group.getChildren().remove(getJavaFXShape());
		grid.setBlock(null, r, c);
		//Quadruple both x and y velocity components of Ball
		ball.setXVel(Ball.INITIAL_XVEL * 4);
		ball.setYVel(Ball.INITIAL_YVEL * 4);
	}
	
}
