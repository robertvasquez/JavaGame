import java.util.Random;

public class Enemy {
	
	private int health;
	int speed;
	
	public Enemy(int health, int s) {
		this.health = health;
		speed = s;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void decHealth(int damage) {
		health -= damage;
	}
	
	public int getSpeed(){
		return speed;
	}
	
}