import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MSDSBlock extends PowerupBlock{
	public static final String IMAGE_FILENAME = "msds.jpg";
	
	public MSDSBlock(double initialX, double initialY){
		super(initialX, initialY, IMAGE_FILENAME);
	}
	
	public void collisionWithBall(Group group, Scorebar scorebar, Paddle paddle, Ball ball, BlockGrid grid, int r, int c) {
		makeBlockDisappear(group, grid, r, c);
		
		paddle.setWidth(Paddle.INITIAL_WIDTH*2);
		if(paddle.getX() + paddle.getWidth() > Driver.SCREEN_BASE){
			paddle.setX(Driver.SCREEN_BASE - paddle.getWidth());
		}
	}
}
