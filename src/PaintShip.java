import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PaintShip {
	private Coordinate coord;
	private BufferedImage shipImg;
	
	public PaintShip(Coordinate coord){
		this.coord = coord;
		try {
			shipImg = ImageIO.read(new File("Ship3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCoord(int xValue, int yValue){
		coord.setX(xValue);
		coord.setY(yValue);
	}
	
	public Coordinate getCoord(){
		return coord;
	}
	public BufferedImage getImage(){
		return shipImg;
	}
}
