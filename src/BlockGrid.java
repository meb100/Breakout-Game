public class BlockGrid {
	private Block[][] grid;
	private double spacing;
	private double block_width;
	private double block_height;
	private int rows;
	private int cols;
	public BlockGrid(int level, int screen_width, int screen_height, double b_w, double b_h, double sp){
	    spacing = sp;
	    block_width = b_w;
	    block_height = b_h;
	    
	    cols = (int)(screen_width / (spacing + block_width));
	    int rowsForFullScreen = (int)(screen_height / (spacing + block_height));
	    rows = rowsForFullScreen - (17-level); //change 17 to function of width later - need to play with this formula
		grid = new Block[rows][cols];
		
		if(level == 1){
			
		}
		else if(level == 2){
			
		}
		else{
			//Make HCl blocks every 10th possible block space
			int blockNum = 1;
			double xPos = spacing;
			double yPos = spacing;
			for(int r = 0; r < rows; r++){
				for(int c = 0; c < cols; c++){
					if(blockNum % 10 == 0){
						grid[r][c] = new HClBlock(xPos, yPos, block_width, block_height);
					}
					else if(blockNum % 2 == 0){
						grid[r][c] = new GlasswareBlock(xPos, yPos, block_width, block_height); //note the constructor of GlasswareBlock already draws in on screen! No need to call drawSelf()
					}
					xPos += (spacing + block_width);
					blockNum++;
				}
				xPos = spacing;
				yPos += (spacing + block_height);
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
		b.setX(((r + 1) * (spacing + block_width)) + spacing);
		b.setY(((c + 1) * (spacing + block_width)) + spacing);
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
