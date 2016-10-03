
public class Coordinate {
	public int x;
	public int y;
	
	public keyboardManager kk;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void addKeyListener(keyboardManager k){
		kk=k;
	}
	
	public void updateCoordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int xValue){
		x = xValue;
	}
	
	public void setY(int yValue){
		y = yValue;
	}
	
}