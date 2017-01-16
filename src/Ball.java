public class Ball implements GameObject {
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private String imageFilename = "StirBar.jpg";
	public Ball(int initX, int initY, int initXVel, int initYVel){
		x = initX;
		y = initY;
		xVel = initXVel;
		yVel = initYVel;
	}
	//Accessors and modifiers
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getXVel(){
		return xVel;
	}
	public int getYVel(){
		return yVel;
	}
	public void setX(int newX){
		x = newX;
	}
	public void setY(int newY){
		y = newY;
	}
	public void setXVel(int newXVel){
		x = newXVel;
	}
	public void setYVel(int newYVel){
		y = newYVel;
	}
	//Initializing (implemented from GameObject)
	public Image instantiateJavaFXShape(){
		
	}
	//For animation (implemented from GameObject)
	public void step(){
		
	}
}
