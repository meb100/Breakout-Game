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
 * The Screen class stores constants for the variable setup in Screen's implementing subclasses.
 * It also holds methods dictating the game loop implemented by its subclasses.
 * @author Matthew Barbano
 *
 */

public interface Screen {
	/**
	 * This method returns the value of the field status.
	 * @return Value of status field
	 */
	public ScreenStatus getStatus();
	/**
	 * This method sets the value of the field status to newStatus.
	 * @param newStatus The new value of status
	 */
	public void setStatus(ScreenStatus newStatus);
	/**
	 * Sets up the Scene - for example, instantiating GameObjects, adding them
	 * to a Group, and listening to keyboard input. It sets the value of status
	 * to Screen.RUNNING
	 * @return The scene set up
	 */
	public Scene initialize();
	/**
	 * Runs a step of the animation - for example, updating the location of the Ball
	 * (Stir Bar) and handling collisions. Sets the value of status to Screen.WON or
	 * Screen.LOST as appropriate.
	 */
	public void step();
}
