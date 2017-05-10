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

/**
 * Concrete class for Catalyst Blocks. Increases ball's speed when hit.
 * @author Matthew Barbano
 *
 */
public class CatalystBlock extends PowerupBlock{
	public static final String IMAGE_FILENAME = "catalyst.jpg";
	
	public CatalystBlock(double initialX, double initialY){
		super(initialX, initialY, IMAGE_FILENAME);
	}
	
	public void collisionWithBall(Group group, Scorebar scorebar, Paddle paddle, Ball ball, BlockGrid grid, int r, int c) {
		makeBlockDisappear(group, grid, r, c);
		
		ball.setXVelocity((int)(Ball.INITIAL_X_VELOCITY * 2.75));
		ball.setYVelocity((int)(Ball.INITIAL_Y_VELOCITY * 2.75));
	}
	
}
