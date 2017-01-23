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
 * Class for GlasswareBlocks (regular blocks).
 * @author Matthew Barbano
 *
 */
public class GlasswareBlock extends Block{
	public static final String IMAGE_FILENAME = "glassware.jpg";
	
	public GlasswareBlock(double x, double y){
		super(x, y, IMAGE_FILENAME);
	}

	public void collisionWithBall(Group group, Scorebar scorebar, Paddle paddle, Ball ball, BlockGrid grid, int r, int c) {
		makeBlockDisappear(group, grid, r, c);
	}
}
