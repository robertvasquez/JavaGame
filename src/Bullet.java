
public class Bullet {
	private Coordinate coord;
	private HitBox hitBox;
	private int damage;
	private boolean bulletType;
	int speed;
	
	public Bullet(Coordinate coord, HitBox hitbox, boolean type){
		this.coord = coord;
		this.hitBox = hitBox;
		this.bulletType = type;
		damage = 5;//need a number for it
		speed = 3;
	}
	public Coordinate getCoord(){ return this.coord; }
	public HitBox getHitBox(){ return this.hitBox; }
	public int getDamage(){ return this.damage; }
	
	public void updateHitBox() {
		Coordinate tempCoord1, tempCoord2;
		tempCoord1 = new Coordinate(coord.x, coord.y);
		tempCoord2 = new Coordinate(coord.x+12, coord.y+20);
		hitBox.setCoord(tempCoord1, tempCoord2);
	}
	
	public void moveBullet() {
		coord.y-=speed;
		updateHitBox();
	}
}