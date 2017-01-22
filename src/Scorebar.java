import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Scorebar class is designed to draw, update, and store data relevant to the 
 * scorebar at the bottom of the screen of each level. Assumptions include that LEVEL_NUMBER
 * and livesLeft are positive integers, and dependencies include the Font, HBox, and ArrayList
 * classes, and the "burn.jpg" image. Here is an example of how to use it:
 * <pre>
 * {@code
 * Scorebar scorebar = new Scorebar(2);
 * scorebar.decrementLivesLeft();
 * scorebar.incrementScore(5);
 * scorebar.drawScorebar();
 * }
 * </pre>
 * @author Matthew Barbano
 *
 */
public class Scorebar {
	public static final Font FONT = Font.font("Chiller", 25);
	public static final int TOTAL_LIVES = 5;
	public static final String BURN_FILENAME = "burn.jpg";
	public static final String BACKGROUND_COLOR_CSS = "-fx-background-color: CORNFLOWERBLUE";
	
	public final int LEVEL_NUMBER; 
	
	private int score;
	private int livesLeft;
	private HBox outerBox;

	/**
	 * Sets LEVEL_NUMBER to levelNumberArg, score to 0, livesLeft to TOTAL_LIVES. Sets up outerBox
	 * and draws scorebar in this state. The parameter levelNumberArg must be a positive integer.
	 * @param levelNumberArg Current level number
	 */
	public Scorebar(int levelNumberArg){
		LEVEL_NUMBER = levelNumberArg;
		
		score = 0;
		livesLeft = TOTAL_LIVES;
		outerBox = createHBox();
		
		drawScorebar();
	}
	
	/**
	 * Gets the value of the score variable.
	 * @return Current score
	 */
	public int getScore(){
		return score;
	}
	/**
	 * Gets the value of the livesLeft variable.
	 * @return livesLeft
	 */
	public int getLivesLeft(){
		return livesLeft;
	}
	/**
	 * Increments the value of the score variable by increment.
	 * @param increment Amount to add to score variable
	 */
	public void incrementScore(int increment){
		score += increment;
	}
	/**
	 * Decrements the value of the livesLeft variable by 1.
	 */
	public void decrementLivesLeft(){
		livesLeft--;
	}
	/**
	 * Sets the value of the livesLeft variable to newLives. The parameter newLives
	 * must be a positive integer.
	 * @param newLives The new value of livesLeft
	 */
	public void setLivesLeft(int newLives){
		livesLeft = newLives;
	}
	/**
	 * Gets the HBox variable outerBox. Name is getJavaFXShape() to stay consistent
	 * with GameObjects.
	 * @return The HBox outerBox variable holding the main HBox of this Scorebar 
	 */
	public HBox getJavaFXShape(){
		return outerBox;
	}
	/**
	 * Draws the scorebar to the bottom left corner of the current scene. First clears the
	 * current scorebar, then updates with new text. Refactored to call 4 private helper methods. 
	 */
	public void drawScorebar(){
		clearScorebar();
		addScore();
		addLives();
		addLevel();
	}
	
	/**
	 * Draws and returns an HBox for the scorebar. Sets the style to BACKGROUND_COLOR_CSS, padding
	 * and spacing to appropriate values, and location of the bottom left corner of the screen.
	 * @return The created HBox
	 */
	private HBox createHBox(){
		HBox box = new HBox();
		
		box.setStyle(BACKGROUND_COLOR_CSS);
		box.setPadding(new Insets(5,5,5,5));
		box.setSpacing(20);
		box.relocate(0, Driver.SCREEN_HEIGHT - 40);
		return box;
	}
	
	/**
	 * Helper method to remove all children from scorebar. Used by drawScorebar() in first step of
	 * updating scorebar. Part of refactoring to make drawScorebar() more readable. 
	 */
	private void clearScorebar(){
		outerBox.getChildren().setAll(new ArrayList<>());
	}
	/**
	 * Helper method to add the current score to scorebar. Used by drawScorebar() in second step of
	 * updating scorebar. Part of refactoring to make drawScorebar() more readable. 
	 */
	private void addScore(){
		Text scoreText = new Text(score + "");
		scoreText.setFont(FONT);
		outerBox.getChildren().add(scoreText);
	}
	/**
	 * Helper method to add the BURN_FILENAME image and number of lives to scorebar. Used by drawScorebar() in third step of
	 * updating scorebar. Part of refactoring to make drawScorebar() more readable. 
	 */
	private void addLives(){
		HBox livesBox = new HBox();
		livesBox.setStyle(BACKGROUND_COLOR_CSS);
		livesBox.setSpacing(2);
		
		ImageView burn = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(BURN_FILENAME)));
		burn.setFitWidth(Driver.SCREEN_HEIGHT / 25);
		burn.setFitHeight(Driver.SCREEN_HEIGHT / 25);
		livesBox.getChildren().add(burn);
		
		Text livesText = new Text(livesLeft + "/" + TOTAL_LIVES);
		livesText.setFont(FONT);
		livesBox.getChildren().add(livesText);
		outerBox.getChildren().add(livesBox);
	}
	/**
	 * Helper method to add current level number to scorebar. Used by drawScorebar() in fourth (final) step of
	 * updating scorebar. Part of refactoring to make drawScorebar() more readable. 
	 */
	private void addLevel(){
		Text levelText = new Text("#" + LEVEL_NUMBER);
		levelText.setFont(FONT);
		outerBox.getChildren().add(levelText);
	}
}
