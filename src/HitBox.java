
public class HitBox {
	private Coordinate upperLeft;
	private Coordinate lowerRight;
	
	public HitBox(Coordinate up, Coordinate low) {
		upperLeft = up;
		lowerRight = low;
	}
	
	public boolean detectCollision3(HitBox other) {
		boolean withinXBounds, withinYBounds;
		withinXBounds = (other.upperLeft.x >= this.upperLeft.x 
				&& other.upperLeft.x <= this.lowerRight.x)
				|| (other.lowerRight.x >= this.upperLeft.x
				&& other.lowerRight.x <= this.lowerRight.x);
		withinYBounds = (other.upperLeft.y >= this.upperLeft.y 
				&& other.upperLeft.y <= this.lowerRight.y)
				|| (other.lowerRight.y >= this.upperLeft.y
				&& other.lowerRight.y >= this.lowerRight.y);
		if (withinXBounds && withinYBounds)
		{
			return true;
		}
		return false;
	}
	
	public boolean detectCollision(HitBox other){
		
		if( this.upperLeft.getY() < other.lowerRight.getY() && this.upperLeft.getX() >= other.upperLeft.getX()
				&& this.upperLeft.getX() <= other.lowerRight.getX()){
			return true;
		}
		if( this.upperLeft.getY() < other.lowerRight.getY() && this.lowerRight.getX() >= other.upperLeft.getX()
				&& this.lowerRight.getX() <= other.lowerRight.getX()){
			return true;
		}
		return false;
	}
	
	public boolean detectCollision2(HitBox other){
		
		if(this.lowerRight.getY() > other.upperLeft.getY() && this.upperLeft.getX() <= other.upperLeft.getX()
				&& this.lowerRight.getX() >= other.upperLeft.getX()){
			return true;
		}
		if(this.lowerRight.getY() > other.upperLeft.getY() && this.upperLeft.getX() <= other.lowerRight.getX()
				&& this.lowerRight.getX() >= other.lowerRight.getX()){
			return true;
		}
		
		return false;
	}
	
	public void setCoord(Coordinate a1, Coordinate a2) {
		upperLeft = a1;
		lowerRight = a2;
	}
	
	public int getULX(){
		return upperLeft.getX();
	}
	
	public int getULY(){
		return upperLeft.getY();
	}
	
	public int getLRX(){
		return lowerRight.getX();
	}
	
	public int getLRY(){
		return lowerRight.getY();
	}
}