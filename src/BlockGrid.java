public class BlockGrid {
	private Block[][] grid;
	private double spacing;
	private double blockWidth;
	private double blockHeight;
	private int rows;
	private int cols;
	
	public BlockGrid(int level, int screen_width, int screen_height, double b_w, double b_h, double sp){
	    spacing = sp;
	    blockWidth = b_w;
	    blockHeight = b_h;
	    
	    cols = (int)(screen_width / (spacing + blockWidth));

		
		if(level == 1){
			rows = 8;
			grid = new Block[rows][cols];
			
			double xPos = spacing;
			double yPos = spacing;
			for(int r = 0; r < rows; r++){
				for(int c = 0; c < cols; c++){
					if(r % 4 == 1 || r % 4 == 3){
						if(c != 0 && c!= cols-1)
							grid[r][c] = new GlasswareBlock(xPos, yPos, blockWidth, blockHeight);
					}
					if(r % 4 == 2){
						if(c == 1 || c == cols-2)
							grid[r][c] = new GlasswareBlock(xPos, yPos, blockWidth, blockHeight);
					}
					//For powerup blocks
					if(r == 3 && c == 2){
						grid[r][c] = new CatalystBlock(xPos, yPos, blockWidth, blockHeight);
					}
					if(r == 6 && c == 5){
						grid[r][c] = new MSDSBlock(xPos, yPos, blockWidth, blockHeight);
					}
					
					xPos += (spacing + blockWidth);
				}
				xPos = spacing;
				yPos += (spacing + blockHeight);
			}
		}
		else if(level == 2){
			rows = 12;
			grid = new Block[rows][cols];
			
			double xPos = spacing;
			double yPos = spacing;
			for(int r = 0; r < rows; r++){
				for(int c = 0; c < cols; c++){
					if(r % 2 == 1 || c % 2 == 1){
						grid[r][c] = new GlasswareBlock(xPos, yPos, blockWidth, blockHeight);
					}
					//Powerup blocks
					if(r == 5 && c == 2){
						grid[5][2] = new HClBlock(xPos, yPos, blockWidth, blockHeight);
					}
					if(r == 1 && c == 1){
						grid[1][1] = new CatalystBlock(xPos, yPos, blockWidth, blockHeight);
					}
					if(r == 1 && c == 11){
						grid[1][11] = new HClBlock(xPos, yPos, blockWidth, blockHeight);
					}
					xPos += (spacing + blockWidth);
				}
				xPos = spacing;
				yPos += (spacing + blockHeight);
			}
		}
		else if(level == 3){
			rows = 20;
			grid = new Block[rows][cols];
			
			//Make HCl blocks every 10th possible block space
			int blockNum = 1;
			double xPos = spacing;
			double yPos = spacing;
			for(int r = 0; r < rows; r++){
				for(int c = 0; c < cols; c++){
					if(blockNum % 10 == 0){
						grid[r][c] = new HClBlock(xPos, yPos, blockWidth, blockHeight);
					}
					else if(blockNum % 2 == 0){
						grid[r][c] = new GlasswareBlock(xPos, yPos, blockWidth, blockHeight); //note the constructor of GlasswareBlock already draws in on screen! No need to call drawSelf()
					}
					xPos += (spacing + blockWidth);
					blockNum++;
				}
				xPos = spacing;
				yPos += (spacing + blockHeight);
			}
		}
	}
	public int getRows(){
		return rows;
	}
	public int getCols(){
		return cols;
	}
	//yes, r and c are ALWAYS 0-indexed
	public Block getBlock(int r, int c){
		return grid[r][c];
	}
	public void setBlock(Block b, int r, int c){ //can use to set an entry to null (test out)
		grid[r][c] = b;
		//Place it appropriately on screen (likely not drawn in correct location in grid at start)
		if(b != null){
		b.setX(((r + 1) * (spacing + blockWidth)) + spacing);
		b.setY(((c + 1) * (spacing + blockWidth)) + spacing);
		}
	}
	/*//No need - setBlock() takes an already-drawn Block, and places it appropriately
	public void drawSelf(){  //draws block grid from current contents of "grid" matrix, call when update 
		
	}
	*/
	//Should know how to:
	//Draw self (complex) - do in terms of WIDTH and HEIGHT
	//Get a specific block
	//Modify a specific block
}
