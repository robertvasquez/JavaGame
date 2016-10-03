public class Model {
	
	private ShowHUD hud = null;
	private int highScore;
	private int score;
	private int ships;
	private int health;
	
	public Model(){
		hud = new ShowHUD(0,0,4,3);
		setValues();
	}
	
	public void setValues(){
		highScore = hud.getHighScore();
		score = hud.getScore();
		ships = hud.getShips();
		health = hud.getHealth();
	}
	
	public int currentHighScore(){
		return highScore;
	}
	
	public int currentScore(){
		return score;
	}
	
	public int currentShips(){
		return ships;
	}
	
	public int currentHealth(){
		return health;
	}
	
	public void setScore(int s){
		hud.updateScore(s);
	}
	
	public void setScore2(int s){
		hud.updateScore2(s);
	}
	
	public void setHighScore(int hs){
		hud.updateHighScore(hs);
	}
	
	public void setShips(int s){
		hud.updateShips(s);
	}
	
	public void setShips2(int s){
		hud.updateShips2(s);
	}
	
	public void setHealth(int h){
		hud.updateHealth(h);
	}
	
	public void setHealth2(int h){
		hud.updateHealth2(h);
	}
	
	public int getCurrentScore(){
		return hud.getScore();
	}
	
	public int getCurrentHighScore(){
		highScore = hud.getHighScore();
		return highScore;
	}
	
	public int getCurrentShips(){
		ships = hud.getShips();
		return ships;
	}
	
	public int getCurrentHealth(){
		health = hud.getHealth();
		return health;
	}
	
	public int getGameOverStatus(){
		return hud.getGameOver();
	}
	
	public void resetGameOverStatus(){
		hud.setGameOver();
	}
	
}