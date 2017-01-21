import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MSDSBlock extends PowerupBlock{ //inherits all PowerupBlock methods (and others from interfaces higher up)
	public final String imageFilename = "msds.jpg";
	public MSDSBlock(double initX, double initY, double w, double h){
			//Rethink location (i.e. class) of constructor when refactoring
			imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilename)));
			imageView.setFitWidth(w);
			imageView.setFitHeight(h);
			imageView.setX(initX);
			imageView.setY(initY);
	}
	public void collisionWithBall(Group group, Scorebar scorebar, Paddle paddle, Ball ball, BlockGrid grid, int r, int c) {
		//Disappears
		group.getChildren().remove(getJavaFXShape());
		grid.setBlock(null, r, c);
		//Doubles size of paddle
		paddle.setWidth(Paddle.INITIAL_WIDTH*2);
		if(paddle.getX() + paddle.getWidth() > Driver.SCREEN_BASE){ //Consider doing this for global static CONSTANTS! Go back and refactor...
			paddle.setX(Driver.SCREEN_BASE - paddle.getWidth());
		}
	}
}
