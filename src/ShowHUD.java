
public class ShowHUD {
	
	private int highScore;
	private int score;
	private int ships;
	private int health;
	
	private int gameOver;
	
	public ShowHUD(int hs, int sc, int s, int h){
		highScore = hs;
		score = sc;
		ships = s;
		health = h;
		gameOver = 1;
	}

	public void updateHighScore(int hs){
		highScore = hs;
	}
	
	public void updateScore(int sc){
		score = score + sc;
	}
	
	public void updateScore2(int sc){
		score = sc;
	}
	
	public void updateShips(int s){
		ships = ships - s;
	}
	
	public void updateShips2(int s){
		ships = s;
	}
	
	public void updateHealth(int h){
		health = health - h;
		if(health <= 0 ){
			health = 3;
			ships--;
			if(ships == 0){
				gameOver = 0;
			}
		}
	}
	
	public void updateHealth2(int h){
		health = h;
	}
	
	public int getGameOver(){
		return gameOver;
	}
	
	public void setGameOver(){
		gameOver = 1;
	}
	
	public int getHighScore(){
		return highScore;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getShips(){
		return ships;
	}
	
	public int getHealth(){
		return health;
	}
}
