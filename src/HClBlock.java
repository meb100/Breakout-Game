import java.util.logging.Level;

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
	public static final String IMAGE_FILENAME = "hcl.jpg";

	public HClBlock(double initialX, double initialY){
		super(initialX, initialY, IMAGE_FILENAME);
	}
	
	public void collisionWithBall(Group group, Scorebar scorebar, Paddle paddle, Ball ball, BlockGrid grid, int r, int c) {
		//Allowing abbreviations for r, c, dr, and dc naming (here and elsewhere) since only single letters make sense with d (delta) notation 
		for(int dr = -1; dr <= 1; dr++)
			for(int dc = -1; dc <= 1; dc++){
				if(r + dr >= 0 && r + dr < grid.getRows() && c + dc >= 0 && c + dc < grid.getCols() && grid.getBlock(r + dr, c + dc) != null){
					//Updates scorebar for 8 surrounding blocks (GridBlock class updates for this)
					updateScorebarSurroundingBlock(grid, scorebar, r, dr, c, dc);
					//Remove all 9 blocks, including this
					grid.getBlock(r + dr, c + dc).makeBlockDisappear(group, grid, r + dr, c + dc);
				}
			}
	}
	private void updateScorebarSurroundingBlock(BlockGrid grid, Scorebar scorebar, int r, int dr, int c, int dc){
		if(dr != 0 || dc != 0){
			if(grid.getBlock(r + dr, c + dc) instanceof GlasswareBlock){
				scorebar.incrementScore(1);
			}
			else if(grid.getBlock(r + dr, c + dc) instanceof PowerupBlock){
				scorebar.incrementScore(3);
			}
		}
	}
}
