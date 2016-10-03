import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.Timer;

public class BeynondStarController {
	
	private Model model = new Model();
	private View view = new View(model);
	private RepaintController repaintController = new RepaintController(view);
	private Coordinate shipCoordinate;
	private HitBox shipHitBox;
	private Coordinate shipUL;
	private Coordinate shipLR;
	private int lastSpawn = 0;
	private int gameStatus;
	
	Random rn = new Random();

	public BeynondStarController(){
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(850, 730);
		view.setVisible(true);
		shipCoordinate = new Coordinate(325, 600);
		shipUL = new Coordinate(325, 600);
		shipLR = new Coordinate(325+64, 600+128);
		shipHitBox = new HitBox(shipUL, shipLR);
		view.passShipHitBox(shipHitBox);
		new Timer(10, repaintController).start();
		gameStatus = 0;
	}
	
	public void startTitle(){
		model.resetGameOverStatus();
		//System.out.println("Start Title");
		int keyResult;
		int endLoop = 0;
		while(endLoop == 0){
			//System.out.println("A");
			keyResult = view.getKey();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(keyResult == 3){
				//System.out.println("Start Game");
				gameStatus = 1;
				view.updateGameStatus(gameStatus);
				startGame();
				endLoop = 1;
			}
		}
	}
	
	public void startGame(){
		
		//System.out.println("HEY");
		//model.setScore(100);
		//model.setHighScore(1000);
		//model.setShips(7);
		//model.setHealth(9);
		//System.out.println("StartGAME HERE");
		
		Ship ship2 = new Ship();
		int x2;
		int y2;
		
		int x;
		int y;
		int keyResult;
		int stop = 0;
		
		while(stop == 0){
			keyResult = view.getKey();
			
			//x = shipCoordinate.getX();
			//y = shipCoordinate.getY();
			//view.passShipHitBox(shipHitBox);
			
			x2 = ship2.getCoordX();
			y2 = ship2.getCoordY();
			
			view.passShip(ship2);
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(keyResult == -1 && ship2.getCoordX()>0){
				//x = x - 1;
				//shipCoordinate.updateCoordinate(x,y);
				//shipUL.updateCoordinate(x, y);
				//shipLR.updateCoordinate(x+64, y+128);
				
				x2 = x2 - 1;
				ship2.setCoordxy(x2, y2);
				ship2.updateHitBox();
				
				//System.out.println("ShipL X: " + shipCoordinate.getX() + " Ship Y: " + shipCoordinate.getY());
				//System.out.println("UL X: " + x + "UL Y: " + y);
				
				//view.moveShip(shipCoordinate);
				//shipHitBox.setCoord(shipUL, shipLR);
				stop = 0;
			}
			
			if(keyResult == 1 && ship2.getCoordX()<722){
				//x = x + 1;
				//shipCoordinate.updateCoordinate(x,y);
				//shipUL.updateCoordinate(x, y);
				//shipLR.updateCoordinate(x+64, y+128);
				
				x2 = x2 + 1;
				ship2.setCoordxy(x2, y2);
				ship2.updateHitBox();
				
				//System.out.println("ShipR X: " + shipCoordinate.getX() + " Ship Y: " + shipCoordinate.getY());
				//System.out.println("UL X: " + x + "UL Y: " + y);
				
				//view.moveShip(shipCoordinate);
				//shipHitBox.setCoord(shipUL, shipLR);
				stop = 0;
			}
			
			if(keyResult == 2){
				keyResult = 0;
				try {
					Thread.sleep(185);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Coordinate bulletStart = new Coordinate((shipCoordinate.getX()+53), shipCoordinate.getY()- 11);
				//Coordinate bulletUL = new Coordinate((shipCoordinate.getX()+53), shipCoordinate.getY()-11);
				//Coordinate bulletLR = new Coordinate((shipCoordinate.getX()+53+20), shipCoordinate.getY()-11+20);
				
				Coordinate bulletStart = new Coordinate((ship2.getCoordX()+53), ship2.getCoordY()- 11);
				Coordinate bulletUL = new Coordinate((ship2.getCoordX()+53), ship2.getCoordY()-11);
				Coordinate bulletLR = new Coordinate((ship2.getCoordX()+53+20), ship2.getCoordY()-11+20);
				
				HitBox bulletBox = new HitBox(bulletUL, bulletLR);
				PaintBullet temp = new PaintBullet(bulletStart, bulletBox);
				view.addBullet(temp);
				stop = 0;
			}
			spawnEnemy();
			if(model.getGameOverStatus() == 0){
				gameStatus = 0;
				view.updateGameStatus(gameStatus);
				view.resetArrays();
				view.resetHUD();
				startTitle();
			}
		}
	}
	
	public void spawnEnemy(){
		int enemySpawn = rn.nextInt(110);
		int enemyX = rn.nextInt(750);
		int enemyType = rn.nextInt(3);
		
		int baseSpeed = 1;
		int baseHealth = 1;
		
		//IF Score is greater than some value
		//Increase Health/Speed.
		//System.out.println(model.currentHighScore());
		if(model.currentScore() > 1000){
			baseSpeed = baseSpeed + 1;
			baseHealth = baseHealth + 1;
			//System.out.println("A");
		}
		if(model.currentScore() > 2000){
			baseSpeed = baseSpeed + 1;
			baseHealth = baseHealth + 1;
			//System.out.println("B");
		}
		if(model.currentScore() > 5000){
			baseSpeed = baseSpeed + 1;
			baseHealth = baseHealth + 1;
			//System.out.println("C");
		}
		
		if( lastSpawn > 800){
			if(enemyType == 0){
			Enemy en = new Enemy(baseHealth+1,baseSpeed);
			Coordinate enemyStart = new Coordinate(enemyX, -128);
			Coordinate enemyUL = new Coordinate(enemyX, -128);
			Coordinate enemyLR = new Coordinate(enemyX+64, -128+128);
			HitBox enemyBox = new HitBox(enemyUL, enemyLR);
			PaintEnemy enemy = new PaintEnemy(enemyStart, enemyBox, en);
			view.addEnemy(enemy);
			lastSpawn = 0;
			}
			if(enemyType == 1){
				Enemy en = new Enemy(baseHealth,baseSpeed);
				Coordinate enemyStart = new Coordinate(enemyX, -64);
				Coordinate enemyUL = new Coordinate(enemyX, -64);
				Coordinate enemyLR = new Coordinate(enemyX+64, -64-64);
				HitBox enemyBox = new HitBox(enemyUL, enemyLR);
				Asteroid a = new Asteroid(enemyStart, enemyBox, en);
				view.addAsteroid(a);
				lastSpawn = 0;
			}
			if(enemyType == 2){
				Enemy en = new Enemy(baseHealth+2,baseSpeed);
				Coordinate enemyStart = new Coordinate(enemyX, -120);
				Coordinate enemyUL = new Coordinate(enemyX, -120);
				Coordinate enemyLR = new Coordinate(enemyX+115, -120+120);
				HitBox enemyBox = new HitBox(enemyUL, enemyLR);
				UFO u = new UFO(enemyStart, enemyBox, en);
				view.addUFO(u);
				lastSpawn = 0;
			}
		}
		if(lastSpawn >= 901){
			lastSpawn = 0;
		}
		lastSpawn++;
	}
	
}