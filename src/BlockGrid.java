import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BlockGrid {
	public static final double BLOCK_SPACING = 5.0;
	public static final double BLOCK_WIDTH = 50.0;
	public static final double BLOCK_HEIGHT = 15.0;
	public static final int TOTAL_COLS = (int)(Driver.SCREEN_BASE / (BLOCK_SPACING + BLOCK_WIDTH));
	public static final String POWERUP_LOCATIONS_FILENAME = "PowerupLocations.txt";
	
	private int totalRows;
	private Block[][] grid;
	
	public BlockGrid(int level){
		totalRows = 4*level + 4;
		grid = new Block[totalRows][TOTAL_COLS];
		
		generateGrid(level);
	}
	
	public int getRows(){
		return totalRows;
	}
	public int getCols(){
		return TOTAL_COLS;
	}
	
	public Block getBlock(int r, int c){
		return grid[r][c];
	}
	public void setBlock(Block blockToSet, int r, int c){
		grid[r][c] = blockToSet;
		
		//Since this method can be used to set a block to null, this condition must be checked here
		if(blockToSet != null){
			blockToSet.setX(((r + 1) * (BLOCK_SPACING + BLOCK_WIDTH)) + BLOCK_SPACING);
			blockToSet.setY(((c + 1) * (BLOCK_SPACING + BLOCK_WIDTH)) + BLOCK_SPACING);
		}
	}
	
	private void generateGrid(int level){
		int blockPositionNum = 1;
		double xPos = BLOCK_SPACING;
		double yPos = BLOCK_SPACING;
		for(int r = 0; r < totalRows; r++){
			for(int c = 0; c < TOTAL_COLS; c++){
				if(level == 1){
					generateLevel1Block(xPos, yPos, r, c, blockPositionNum);
				}
				else if(level == 2){
					generateLevel2Block(xPos, yPos, r, c, blockPositionNum);
				}
				else if(level == 3){
					generateLevel3Block(xPos, yPos, r, c, blockPositionNum);
				}
				blockPositionNum++;
				xPos += (BLOCK_SPACING + BLOCK_WIDTH);
			}
			xPos = BLOCK_SPACING;
			yPos += (BLOCK_SPACING + BLOCK_HEIGHT);
		}
	}
	
	private void generateLevel1Block(double xPos, double yPos, int r, int c, int blockPositionNum){
		if((r % 4 == 1 || r % 4 == 3) && (c != 0 && c!= TOTAL_COLS-1)){
				grid[r][c] = new GlasswareBlock(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT);
		}
		if((r % 4 == 2) && (c == 1 || c == TOTAL_COLS-2)){
				grid[r][c] = new GlasswareBlock(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT);
		}
		if(blockPositionNum % 20 == 0){
			grid[r][c] = new CatalystBlock(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT);
		}
	}
	
	private void generateLevel2Block(double xPos, double yPos, int r, int c, int blockPositionNum){
		if(r % 2 == 1 || c % 2 == 1){
			grid[r][c] = new GlasswareBlock(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT);
		}
		if(blockPositionNum % 15 == 0){
			grid[r][c] = new MSDSBlock(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT);
		}
		if(blockPositionNum % 10 == 0){
			grid[r][c] = new HClBlock(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT);
		}
	}
	
	private void generateLevel3Block(double xPos, double yPos, int r, int c, int blockPositionNum){
		if(blockPositionNum % 2 == 0){
			grid[r][c] = new GlasswareBlock(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT); //note the constructor of GlasswareBlock already draws in on screen! No need to call drawSelf()
		}		
		if(blockPositionNum % 10 == 0){
			grid[r][c] = new HClBlock(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT);
		}
	}
}
