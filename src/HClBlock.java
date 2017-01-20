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

public class HClBlock extends PowerupBlock{
	public final String imageFilename = "hcl.jpg";

	public HClBlock(double initX, double initY, double w, double h){
		//Rethink location (i.e. class) of constructor when refactoring
		imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilename)));
		imageView.setFitWidth(w);
		imageView.setFitHeight(h);
		imageView.setX(initX);
		imageView.setY(initY);
	}
	
	@Override
	public void collisionWithBall(Group group, Paddle paddle, Ball ball, BlockGrid grid, int r, int c) {
		//Clear 8 surrounding blocks and hit block as well - be sure to consider edges of grid!
		for(int dr = -1; dr <= 1; dr++)
			for(int dc = -1; dc <= 1; dc++){
				if(r + dr >= 0 && r + dr < grid.getRows() && c + dc >= 0 && c + dc < grid.getCols() && grid.getBlock(r + dr, c + dc) != null){
					group.getChildren().remove(grid.getBlock(r+dr, c+dc).getJavaFXShape());
					grid.setBlock(null, r + dr, c + dc);   //Consider making a removeBlock() method for these 2 lines in BlockGrid class
				}
			}
	}
	
}
