import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

public class View extends JFrame {
	
	private int highScore;
	private int score;
	private int ships;
	private int health;
	private int gameStatus;
	
	private Model model;
	
	private JLabel scoreDisplay;
	private JLabel shipHealthDisplay;
	
	private PaintBackground bg;
	private keyboardManager k = new keyboardManager();
	
	private ArrayList<PaintBullet> bulletsOnScreen = new ArrayList<PaintBullet>();
	private ArrayList<PaintEnemy> enemyOnScreen = new ArrayList<PaintEnemy>();
	private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	private ArrayList<UFO> ufos = new ArrayList<UFO>();
	
	public View(Model m){
		super("Beyond the Stars");
		model = m;
		bg = new PaintBackground(this);
		this.addKeyListener(k);
		drawBackGround();
	}
	
	public void moveShip(Coordinate c){
		bg.updateShip(c);
	}
	
	public void drawBackGround(){
		add(bg, BorderLayout.CENTER);
		bg.setBackground(Color.BLACK);
		bg.setLayout(new BorderLayout());
		scoreDisplay = new JLabel("Score: " + model.getCurrentScore() + "            High Score: " + model.getCurrentHighScore(),SwingConstants.CENTER);
		scoreDisplay.setForeground(Color.GREEN);
        scoreDisplay.setOpaque(false);
        bg.add(scoreDisplay,BorderLayout.NORTH);
        shipHealthDisplay = new JLabel("Ships: " + model.getCurrentShips() + "   Health: " + model.getCurrentHealth(), SwingConstants.LEFT);
        shipHealthDisplay.setForeground(Color.GREEN);
        shipHealthDisplay.setOpaque(false);
        bg.add(shipHealthDisplay,BorderLayout.SOUTH);
	}
	
	public void updateHUD(){
		scoreDisplay.setText("Score: " + model.getCurrentScore() + "            High Score: " + model.getCurrentHighScore());
		shipHealthDisplay.setText("Ships: " + model.getCurrentShips() + "   Health: " + model.getCurrentHealth());
	}
	
	public int getKey(){
		return k.getResult();
	}
	
	public void addBullet(PaintBullet bullet){
		bulletsOnScreen.add(bullet);
	}
	
	public void removeBullet(PaintBullet bullet){
		bulletsOnScreen.remove(bullet);
	}
	
	public void addEnemy(PaintEnemy enemy){
		enemyOnScreen.add(enemy);
	}
	
	public void removeEnemy(PaintEnemy enemy){
		enemyOnScreen.remove(enemy);
	}
	
	public void addAsteroid(Asteroid a){
		asteroids.add(a);
	}
	
	public void removeAsteroid(Asteroid a){
		asteroids.remove(a);
	}
	
	public void addUFO(UFO u){
		ufos.add(u);
	}
	
	public void removeUFO(UFO u){
		ufos.remove(u);
	}
	
	public ArrayList<PaintBullet> getBullets(){
		return bulletsOnScreen;
	}
	
	public ArrayList<PaintEnemy> getEnemies(){
		return enemyOnScreen;
	}
	
	public ArrayList<Asteroid> getAsteroids(){
		return asteroids;
	}
	
	public ArrayList<UFO> getUfos(){
		return ufos;
	}
	
	public void numberOfBullets(){
		System.out.println(bulletsOnScreen.size());
	}
	
	public void addEnemyShipScore(){
		int hs = model.getCurrentHighScore();
		//System.out.println("OLD HIGH SCORE: " + hs);
		model.setScore(100);
		int ns = model.getCurrentScore();
		//System.out.println("SCORE AFTER ADDED: " + ns);
		if(hs < ns){
			//System.out.println(   "HIGH SCORE: " + hs + " SCORE: " + ns);
			model.setHighScore(ns);
		}
	}
	
	public void addAsteriodScore(){
		int hs = model.getCurrentHighScore();
		//System.out.println("OLD HIGH SCORE: " + hs);
		model.setScore(50);
		int ns = model.getCurrentScore();
		//System.out.println("SCORE AFTER ADDED: " + ns);
		if(hs < ns){
			//System.out.println(   "HIGH SCORE: " + hs + " SCORE: " + ns);
			model.setHighScore(ns);
		}
	}
	
	public void addUfoScore(){
		int hs = model.getCurrentHighScore();
		//System.out.println("OLD HIGH SCORE: " + hs);
		model.setScore(150);
		int ns = model.getCurrentScore();
		//System.out.println("SCORE AFTER ADDED: " + ns);
		if(hs < ns){
			//System.out.println(   "HIGH SCORE: " + hs + " SCORE: " + ns);
			model.setHighScore(ns);
		}
	}
	
	public void subtractHealth(int h){
		model.setHealth(h);
	}
	
	public void passShipHitBox(HitBox s){
		bg.getShipHitBox(s);
	}
	
	public void passShip(Ship s){
		bg.getShipObject(s);
	}
	
	public int getGameStatus(){
		return gameStatus;
	}
	
	public void updateGameStatus(int gs){
		//System.out.println("UPDATE GAMESTATUS WITH: " + gs);
		gameStatus = gs;
	}
	
	public void resetArrays(){
		bulletsOnScreen.clear();
		enemyOnScreen.clear();
		asteroids.clear();
		ufos.clear();
	}
	
	public void resetHUD(){
		model.setScore2(0);
		model.setShips2(4);
		model.setHealth2(3);
	}
	
	
}
