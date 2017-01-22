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

public class Scorebar {
	private int score;
	private int livesLeft;
	private int levelNumber;
	private HBox box;
	public static final int TOTAL_LIVES = 5;
	public Scorebar(int l){
		score = 0;
		livesLeft = TOTAL_LIVES;
		levelNumber = l;
		box = createHBox();
		drawScorebar();
		//background = new Rectangle(0, 0, Driver.SCREEN_BASE, Driver.SCREEN_HEIGHT / 10);
		//background.setFill(Color.BLUE);
	}
	public int getScore(){
		return score;
	}
	public int getLivesLeft(){
		return livesLeft;
	}
	public int getLevelNumber(){
		return levelNumber;
	}
	public void incrementScore(int increment){
		score += increment;
	}
	public void decrementLivesLeft(){
		livesLeft--;
	}
	public void setLevelNumber(int l){
		levelNumber = l;
	}
	public void setLivesLeft(int newLives){
		livesLeft = newLives;
	}
	public HBox getJavaFXShape(){
		return box;
	}
	private HBox createHBox(){
		HBox h = new HBox();
		h.setStyle("-fx-background-color: CORNFLOWERBLUE");
		h.setPadding(new Insets(5,5,5,5));
		h.setSpacing(20);
		h.relocate(0, Driver.SCREEN_HEIGHT - 40);   //Change 40 if can figure out Node method for it
		return h;
	}
	public void drawScorebar(){
		//Remove previous text
		box.getChildren().setAll(new ArrayList<>());  //removes all children and adds all elements of the AL (which is empty) --> so removes all children
		//Add new text
		Text scoreText = new Text(score + "");
		scoreText.setFont(Font.font("Chiller", 25));
		box.getChildren().add(scoreText);
		
		HBox livesBox = new HBox();
		livesBox.setStyle("-fx-background-color: CORNFLOWERBLUE");
		livesBox.setSpacing(2);
		ImageView burn = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("burn.jpg")));
		burn.setFitWidth(Driver.SCREEN_HEIGHT / 25);
		burn.setFitHeight(Driver.SCREEN_HEIGHT / 25);
		livesBox.getChildren().add(burn);
		Text livesText = new Text(livesLeft + "/" + TOTAL_LIVES);
		livesText.setFont(Font.font("Chiller", 25));
		livesBox.getChildren().add(livesText);
		box.getChildren().add(livesBox);
		
		Text levelText = new Text("#" + levelNumber);
		levelText.setFont(Font.font("Chiller", 25));
		box.getChildren().add(levelText);
	}
	
	/**
	 * 1)	An icon whose color indicates whether that level piece of lab equipment
		is in storage
		2)	Area with powerup icons indicating whether they are in storage
		3)	Score
		4)	A number next to a fire icon indicating number of lives left
		5)	The level number
	 */
}
