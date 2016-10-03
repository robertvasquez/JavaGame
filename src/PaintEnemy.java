import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PaintEnemy {
	private Coordinate coord;
	private HitBox hitbox;
	private BufferedImage enemyImg;
	
	private Enemy enemy;
	
	public PaintEnemy(Coordinate coord, HitBox h, Enemy en){
		this.coord = coord;
		hitbox = h;
		enemy = en;
		try {
			enemyImg = ImageIO.read(new File("enemy1.png"));
		} catch (IOException e) {
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
		return enemyImg;
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
	
	public int checkHit2(HitBox h){
		if(hitbox.detectCollision2(h) == true){
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
	
	public void damageEnemy(){
		enemy.decHealth(1);
	}
	
	public int getEnemyHealth(){
		return enemy.getHealth();
	}
	
	public int getEnemySpeed(){
		return enemy.getSpeed();
	}
	
}