
public class Ship {
	private Coordinate coord;
	private HitBox hitBox;
	private int health;
	
	public Ship(){
		this.coord = new Coordinate(325, 600); //whatever the starting point is
		this.hitBox = new HitBox(new Coordinate(325,600), new Coordinate(325+64,600+128)); //insert default here
		this.health = 3; //or whatever max health is 
	}
	
	public int getHealth(){
		return this.health; 
	}
	
	public void decHealth(int damage){
		this.health-=damage;
	}
	
	public Coordinate getCoord(){
		return this.coord;
	}
	public HitBox getHitBox(){
		return this.hitBox;
	}
	
	public void updateHitBox() {
		Coordinate tempCoord1, tempCoord2;
		tempCoord1 = new Coordinate(coord.x, coord.y);
		tempCoord2 = new Coordinate(coord.x+70, coord.y+140);
		hitBox.setCoord(tempCoord1, tempCoord2);
	}
	
	public void setCoord(Coordinate c1) {
		coord = c1;
	}
	
	public int getCoordX(){
		return coord.getX();
	}
	
	public int getCoordY(){
		return coord.getY();
	}
	
	public void setCoordxy(int x, int y){
		coord.setX(x);
		coord.setY(y);
	}
}
