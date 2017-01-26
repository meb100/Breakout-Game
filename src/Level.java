//This entire file is part of my masterpiece.
//MATTHEW BARBANO
//IMPORTANT: This class was < 200 lines before I began the masterpiece. Even though it is > 200 now,
//most is due to the Javadoc commenting. I also modified one line in Driver.java to call my modified Level constructor appropriately.

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class specifies the details of each level's screen and implements
 * the Screen interface. It assumes that the level number is a positive integer.
 * Its dependencies are: Ball.java, BlockGrid.java, Paddle.java, and Scorebar.java (in
 * this project) and the Group and Scene classes in JavaFX. Here is an example of
 * how to use it:
 * <pre>
 * {@code
 *  Level level = new Level(2);
 *  level.initialize();
 *  for(int stepNumber = 1; stepNumber <= 10000; stepNumber++)
 *  		level.step();
 *  }
 * @author Matthew Barbano
 * This class demonstrates good design principles because it uses constants instead
 * of magic values, as with SLOW_MULTIPLIER, etc. All of its variables are private instance.
 * Repeated code has been eliminated through helpers such as locationHasBlock() and gameObjectsIntersect(),
 * and previously verbose method bodies have been divided into descriptively-named calls to helper methods
 * (see initialize() and setup() bodies). This class also demonstrates inheritance principles since it implements Screen.
 */
public class Level implements Screen{
	public static final Paint BACKGROUND_COLOR = Color.GREY;
	
	private static final int SLOW_MULTIPLIER = 1;
	private static final int MEDIUM_MULTIPLIER = 2;
	private static final int FAST_MULTIPLIER = 3;

	private Group rootGroup;
	private Ball ball;
	private BlockGrid blockGrid;
	private Paddle paddle;
	private Scorebar scorebar;
	private Scene scene;
	private int status;
	private int levelNumber;
	
	/**Constructor which sets levelNumber as status. Does not set up GameObjects. Assumes levelNumberToSet is positive integer.
	 * @param levelNumberToSet The level number of this Level*/
	public Level(int levelNumberToSet){
		levelNumber = levelNumberToSet;
		status = Screen.NEEDS_SETUP;
	}
	/**Returns the value of status.
	 * @return Value of status*/
	public int getStatus(){
		return status;
	}
	/**Sets the value of status.
	 * @param newStatus The new value of status*/
	public void setStatus(int newStatus){
		status = newStatus;
	}
	/**Returns true if location (r, c) is not null in blockGrid. Abbreviations "r" and "c" are
	 * used due to use of "dr" and "dc" in HClBlock.java (see Analysis).
	 * @param r Row in blockGrid
	 * @param c Column in blockGrid
	 * @return If the location has a block*/
	private boolean locationHasBlock(int r, int c) {
		return blockGrid.getBlock(r, c) != null;
	}
	/**Sets up the Scene - for example, instantiating GameObjects, adding them
	 * to a Group, and listening to keyboard input. It sets the value of status
	 * to Screen.RUNNING
	 * @return The scene set up*/
	public Scene initialize(){
		instantiateJavaFXObjects();
		instantiateGameObjects();
		
		addGameObjectsToGroup();
		
		setStatus(Screen.RUNNING);
		setupKeyboardInput();        
		
		return scene;
	}
	/**Instantiates rootGroup and scene.*/
	private void instantiateJavaFXObjects(){
		rootGroup = new Group();
		scene = new Scene(rootGroup, Driver.SCREEN_BASE, Driver.SCREEN_HEIGHT, BACKGROUND_COLOR); 
	}
	/**Instantiates ball, blockGrid, paddle, and scorebar.*/
	private void instantiateGameObjects(){
		ball = new Ball();
		blockGrid = new BlockGrid(levelNumber);
		paddle = new Paddle();
		scorebar = new Scorebar(levelNumber);
	}
	/**Adds ball, paddle, all of the blocks in blockGrid, and scorebar to rootGroup. Note that the addToGroup()
	 * helper was not called for adding scorebar because Scorebar is not in the GameObject hierarchy (GameObject has HBox as main JavaFX Node, but wanted all GameObjects
	 * to have an ImageView - changing ImageView to Node just for Scorebar would be too extreme).*/
	private void addGameObjectsToGroup(){
		addToGroup(ball);
		addToGroup(paddle);
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){
				if(locationHasBlock(r, c))
					addToGroup(blockGrid.getBlock(r, c));
			}
		}
		rootGroup.getChildren().add(scorebar.getJavaFXShape()); 
	}
	/**Adds a single GameObject to rootGroup.
	 * @param objectToBeAdded The object that will be added to rootGroup*/
	private void addToGroup(GameObject objectToBeAdded){   
		rootGroup.getChildren().add(objectToBeAdded.getJavaFXShape());
	}
	/**Sets up keyboard input during initialization.*/
	private void setupKeyboardInput(){
		scene.setOnKeyPressed(keyPressedEvent -> keyboardInput(keyPressedEvent.getCode()));
	}
	/**Updates locations, checks for collisions, updates scorebar, and ends the level in needed on each animation step.*/
	public void step(){
		ball.updateLocation(scorebar);
		updateCollisions();
		scorebar.drawScorebar();
		endLevelIfAppropriate();
	}
	/**Checks for collision and updates appropriately.*/
	private void updateCollisions(){
		updateBallPaddleCollisions();
		updateBallBlockCollisions();
	}
	/**Checks for collisions between ball and paddle.*/
	private void updateBallPaddleCollisions(){
		if(gameObjectsIntersect(ball, paddle)){
			ball.collisionWithPaddle(paddle);
		}
	}
	/**Checks for collisions between ball and any block in blockGrid.*/
	private void updateBallBlockCollisions(){
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){
				if(ballBlockCollisionOccurred(r, c)){
					incrementScoreByBlockType(r, c);
					updateGameObjectsFromBallBlockCollision(r, c);
				}
			}
		}
	}
	/**Returns true if a ball and block at (r, c) in blockGrid have collided. See Analysis for r, c abbreviation justification.
	 * @param r Row
	 * @param c Column
	 * @return Whether a collision has occurred there*/
	private boolean ballBlockCollisionOccurred(int r, int c){
		return locationHasBlock(r, c) && gameObjectsIntersect(ball, blockGrid.getBlock(r, c));
	}
	/**Increments score by 1 points for GlasswareBlocks and 3 for PowerupBlocks at (r, c) in blockGrid. If given more refactoring time, would modify GameObject inheritance hierarchy and use polymorphism
	 * to eliminate if-then tree, but beyond scope of masterpiece assignment.
	 * @param r Row
	 * @param c Column*/
	private void incrementScoreByBlockType(int r, int c){
		if(blockGrid.getBlock(r, c) instanceof GlasswareBlock){
			scorebar.incrementScore(1);
		}
		else if(blockGrid.getBlock(r, c) instanceof PowerupBlock){
			scorebar.incrementScore(3);
		}
	}
	/**Calls appropriate ball and block collisionWithBall() methods for the block at (r, c) in blockGrid.
	 * @param r Row
	 * @param c Column*/
	private void updateGameObjectsFromBallBlockCollision(int r, int c) {
		ball.collisionWithBlock(blockGrid.getBlock(r,c));
		blockGrid.getBlock(r, c).collisionWithBall(rootGroup, scorebar, paddle, ball, blockGrid, r, c);
	}
	/**Handles whether to end the level on a game over or winning setup.*/
	private void endLevelIfAppropriate(){
		handleGameOver();
		handleWonLevel();
	}
	/**Checks if there are no more lives left and if so sets status to Screen.LOST*/
	private void handleGameOver(){
		if(scorebar.getLivesLeft() <= 0){
			setStatus(Screen.LOST);
		}
	}
	/**Checks if grid is clear and if so, sets status to Screen.WON*/
	private void handleWonLevel(){
		if(gridIsClear()){
			setStatus(Screen.WON);
		}
	}
	/**Returns true if all blocks are null in blockGrid
	 * @return If grid is clear*/
	private boolean gridIsClear(){
		boolean allBlocksNull = true;
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){
				if(locationHasBlock(r, c)){
					allBlocksNull = false;
				}
			}
		}
		return allBlocksNull;
	}
	/**Returns true if object1 and object2 have collided
	 * @param object1 First object
	 * @param object2 Second object
	 * @return If object1 and object2 have collided */
	private boolean gameObjectsIntersect(GameObject object1, GameObject object2){
		return object1.getJavaFXShape().getBoundsInParent().intersects(object2.getJavaFXShape().getBoundsInParent());
	}
	/**Handles keyboard update of paddle movement at all three speeds.
	 * @param keyCode*/
	private void updatePaddleMovement(KeyCode keyCode){
		if(keyCode == KeyCode.RIGHT){
			movePaddleRight(SLOW_MULTIPLIER);
		}
		else if(keyCode == KeyCode.LEFT){
			movePaddleLeft(SLOW_MULTIPLIER);
		}
		else if(keyCode == KeyCode.D){
			movePaddleRight(MEDIUM_MULTIPLIER);
		}
		else if(keyCode == KeyCode.A){
			movePaddleLeft(MEDIUM_MULTIPLIER);
		}
		else if(keyCode == KeyCode.E){
			movePaddleRight(FAST_MULTIPLIER);
		}
		else if(keyCode == KeyCode.Q){
			movePaddleLeft(FAST_MULTIPLIER);
		}
	}
	/**Moves paddle right - added helper in case want to add more code under each condition of if-then tree from which this method is called.
	 * @param multiplier*/
	private void movePaddleRight(int multiplier){
		paddle.moveRight(multiplier);
	}
	/**Moves paddle left - added helper in case want to add more code under each condition of if-then tree from which this method is called. 
	 * @param multiplier*/
	private void movePaddleLeft(int multiplier){
		paddle.moveLeft(multiplier);
	}
	/**Removes all blocks from blockGrid.*/
	private void removeAllGlasswareAndPowerupBlocks(){
		for(int r = 0; r < blockGrid.getRows(); r++){
			for(int c = 0; c < blockGrid.getCols(); c++){    //Add a line here if ever implement LabEquipment blocks 
				blockGrid.setBlock(null, r, c);
			}
		}
	}
	/**Updates cheat keys for jumping between levels. @param keyCode*/
	private void updateJumpToLevelKeys(KeyCode keyCode){
		if(keyCode == KeyCode.DIGIT1){
			setStatus(Screen.JUMP_TO_LEVEL_1);
		}
		else if(keyCode == KeyCode.DIGIT2){
			setStatus(Screen.JUMP_TO_LEVEL_2);
		}
		else if(keyCode == KeyCode.DIGIT3){
			setStatus(Screen.JUMP_TO_LEVEL_3);
		}
	}
	/**Updates all cheat keys. 
	 * @param keyCode*/
	private void updateCheatKeys(KeyCode keyCode){
		if(keyCode == KeyCode.B){
			scorebar.setLivesLeft(Scorebar.TOTAL_LIVES);
		}
		else if(keyCode == KeyCode.C){
			removeAllGlasswareAndPowerupBlocks();
		}
		updateJumpToLevelKeys(keyCode);
	}
	/**Handles all keyboard input - both paddle movement and cheat keys. @param keyCode*/
	private void keyboardInput(KeyCode keyCode){
		updatePaddleMovement(keyCode);
		updateCheatKeys(keyCode);
	}
}
