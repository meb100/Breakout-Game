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

public abstract class Block extends GameObject{
	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEIGHT = 15;
	
	public Block(double initialX, double initialY, String imageFilename){
		super(initialX, initialY, BLOCK_WIDTH, BLOCK_HEIGHT, imageFilename);
	}
	public abstract void collisionWithBall(Group group, Scorebar scorebar, Paddle paddle, Ball ball, BlockGrid grid, int r, int c);
	public void makeBlockDisappear(Group group, BlockGrid grid, int r, int c){
		group.getChildren().remove(getJavaFXShape());
		grid.setBlock(null, r, c);
	}
}
