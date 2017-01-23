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
 * Abstract class extending GameObjects to the many types of Blocks present on screen.
 * @author Matthew Barbano
 *
 */
public abstract class Block extends GameObject{
	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEIGHT = 15;
	
	public Block(double initialX, double initialY, String imageFilename){
		super(initialX, initialY, BLOCK_WIDTH, BLOCK_HEIGHT, imageFilename);
	}
	/**
	 * Makes block disappear (and other appropriate effects occur) when collision detected.
	 * @param group
	 * @param scorebar
	 * @param paddle
	 * @param ball
	 * @param grid
	 * @param r
	 * @param c
	 */
	public abstract void collisionWithBall(Group group, Scorebar scorebar, Paddle paddle, Ball ball, BlockGrid grid, int r, int c);
	/**
	 * Helper method for collisionWithBall called by Block's subclasses.
	 * @param group
	 * @param grid
	 * @param r
	 * @param c
	 */
	public void makeBlockDisappear(Group group, BlockGrid grid, int r, int c){
		group.getChildren().remove(getJavaFXShape());
		grid.setBlock(null, r, c);
	}
}
