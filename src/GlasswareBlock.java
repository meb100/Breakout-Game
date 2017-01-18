import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GlasswareBlock implements Block{ //and since Block interface extends GameObject interface, must implement GameObject methods too!
	public ImageView imageView;
	public final String imageFilename = "glassware.jpg"; //https://www.amazon.com/Corning-1000-600-Graduated-Graduation-Interval/dp/B004DGIII8
	
	public GlasswareBlock(double initX, double initY, double w, double h){
		imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilename)));
		imageView.setFitWidth(w);
		imageView.setFitHeight(h);
		imageView.setX(initX);
		imageView.setY(initY);
	}
	//From Block interface
	@Override
	public void collisionWithBall() {
		// TODO Auto-generated method stub
	}

	//Rest from GameObject interface - consider implementing these all in GameObject interface and make it a class!
	@Override
	public ImageView getJavaFXShape() {
		return imageView;
	}

	@Override
	public double getX(){
		return imageView.getX();
	}
	public double getY(){
		return imageView.getY();
	}

	public void setX(double newX){
		imageView.setX(newX);
	}
	public void setY(double newY){
		imageView.setY(newY);
	}
	@Override
	public double getWidth() {
		return imageView.getFitWidth();
	}
	@Override
	public double getHeight() {
		return imageView.getFitHeight();
	}
	@Override
	public void setWidth(double newWidth) {
		imageView.setFitWidth(newWidth);
	}
	@Override
	public void setHeight(double newHeight) {
		imageView.setFitHeight(newHeight);
	}
	
}
