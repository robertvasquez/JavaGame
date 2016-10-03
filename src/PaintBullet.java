import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PaintBullet {
	private Coordinate coord;
	private HitBox hitbox;
	private BufferedImage bulletImg;
	
	public PaintBullet(Coordinate coord, HitBox h){
		this.coord = coord;
		hitbox = h;
		try {
			bulletImg = ImageIO.read(new File("bullet2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCoord(Coordinate coord){
		this.coord = coord;
	}
	
	public Coordinate getCoord(){
		return coord;
	}
	public BufferedImage getImage(){
		return bulletImg;
	}
	
	public void updateHitbox(Coordinate ul, Coordinate lr){
		hitbox.setCoord(ul, lr);
	}
	
	public HitBox getHitbox(){
		return hitbox;
	}
	
	public int checkHit(HitBox h){
		if(hitbox.detectCollision(h) == true){
			return 1;
		}
		return 0;
	}
	
	public void printULCoordinates(){
		System.out.println("UL X: " + hitbox.getULX() + " UL Y: " + hitbox.getULY());
	}
	
	public void printLRCoordinates(){
		System.out.println("LR X: " + hitbox.getLRX() + " LR Y: " + hitbox.getLRY());
	}
	
}