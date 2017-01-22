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

public interface Screen {
	public static final int NEEDS_SETUP = 0;
	public static final int RUNNING = 1;
	public static final int WON = 2;
	public static final int LOST = 3;   //add status field to both subclass
	
	public int getStatus();
	public void setStatus(int newStatus);
	public Scene initialize();
	public void step();
}
