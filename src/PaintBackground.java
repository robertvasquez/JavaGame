import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaintBackground extends JPanel{
	
	private int firstStars = 1000;
	private int otherStars = 1;
	
	public boolean start = true;
	private boolean initialStars = false;
	
	private ArrayList<Stars> stars = new ArrayList<Stars>();
	private PaintShip ship;
	private PaintBullet bullet;
	private PaintEnemy enemy;
	private Asteroid a;
	private UFO u;
	private View view;
	private HitBox shipHitBox;
	private ArrayList<PaintBullet> bullets = new ArrayList<PaintBullet>();
	private ArrayList<PaintEnemy> enemies = new ArrayList<PaintEnemy>();
	private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	private ArrayList<UFO> ufos = new ArrayList<UFO>();
	Random rn = new Random();
	ShowMenu sm = new ShowMenu();
	
	private Ship ship2;
	
	int hit;
	int bulletHitIndex;
	int enemyHit;
	int enemyHitIndex;
	int asteroidHit;
	int asteroidHitIndex;
	int ufoHit;
	int ufoHitIndex;
	
	int checkBullet;
	int checkEnemy;
	int checkAsteroid;
	int checkUfo;
	
	int gameStatus;
	
	public PaintBackground(View v){
		view = v;
		ship = new PaintShip(new Coordinate(325,600));
	}
	
	public void paintComponent(Graphics g) {
		Random rn = new Random();
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Dimension size = this.getSize();
		
		gameStatus = view.getGameStatus();
		
		if (!initialStars){
			for(int i = 0; i<=firstStars;i++){
				int x = rn.nextInt(size.width)+1;
				int y = rn.nextInt(size.height)+1;
				Stars star = new Stars(x,y);
				stars.add(star);
				g.fillRect(x, y, 1, 1);
				initialStars = true;
			}	
		} else {
			for (int i = 0; i<=otherStars;i++){
				int x = rn.nextInt(size.width)+1;
				int y = rn.nextInt(size.height-(size.height-5))+1;
				Stars star = new Stars(x,y);
				stars.add(star);
				
			}
			for (int i = 0;i<stars.size();i++){
				stars.get(i).setY(stars.get(i).getY()+1);
				if (stars.get(i).getY()>size.height)
					stars.remove(i);
				g.fillRect(stars.get(i).getX(), stars.get(i).getY(), 1, 1);
			}
		}
		
		//Testing ShowMenu
		if( gameStatus == 0){
			sm.drawTitle(g);
		}
		//End Testing ShowMenu
		
		//g.drawImage(ship.getImage(), ship.getCoord().x, ship.getCoord().y, null);
		
		if(gameStatus == 1){
		
		g.drawImage(ship.getImage(), ship2.getCoordX(), ship2.getCoordY(), null);
		
		bullets = view.getBullets();
		for (int i = 0; i<bullets.size(); i++){
			bullet = bullets.get(i);
			checkBullet = 0;
			g.drawImage(bullets.get(i).getImage(), bullets.get(i).getCoord().x, bullets.get(i).getCoord().y, null);
			if(bullet.getCoord().getY() < -10){
				view.removeBullet(bullets.get(i));
				checkBullet = 1;
			}
			if(checkBullet == 0){
				updateBullet(bullet);
			}
		}
		
		enemies = view.getEnemies();
		for (int i = 0; i<enemies.size(); i++){
			enemy = enemies.get(i);
			checkEnemy = 0;
			g.drawImage(enemies.get(i).getImage(), enemies.get(i).getCoord().x, enemies.get(i).getCoord().y, null);
			if(enemy.getCoord().getY() > 1500){
				view.removeEnemy(enemies.get(i));
				checkEnemy = 1;
			}
			if(checkEnemy == 0){
				if(enemies.get(i).checkHit2(ship2.getHitBox()) == 1){
					view.removeEnemy(enemies.get(i));
					view.subtractHealth(2);
					checkEnemy = 1;
				}
			}
			if( checkEnemy == 0){
				updateEnemy(enemy);
			}
		}
		
		asteroids = view.getAsteroids();
		for (int i = 0; i<asteroids.size(); i++){
			a = asteroids.get(i);
			checkAsteroid = 0;
			g.drawImage(asteroids.get(i).getImage(), asteroids.get(i).getCoord().x, asteroids.get(i).getCoord().y, null);
			if(a.getCoord().getY() > 1500){
				view.removeAsteroid(asteroids.get(i));
				checkAsteroid = 1;
			}
			if(checkAsteroid == 0){
				if(asteroids.get(i).checkHit2(ship2.getHitBox()) == 1){
					view.removeAsteroid(asteroids.get(i));
					view.subtractHealth(3);
					checkAsteroid = 1;
				}
			}
			if(checkAsteroid == 0){
				updateAsteroid(a);
			}
		}
		
		ufos = view.getUfos();
		for (int i = 0; i<ufos.size(); i++){
			u = ufos.get(i);
			checkUfo = 0;
			g.drawImage(ufos.get(i).getImage(), ufos.get(i).getCoord().x, ufos.get(i).getCoord().y, null);
			if(u.getCoord().getY() > 1500){
				view.removeUFO(ufos.get(i));
				checkUfo = 1;
			}
			if(checkUfo == 0){
				if(ufos.get(i).checkHit2(ship2.getHitBox()) == 1){
					view.removeUFO(ufos.get(i));
					view.subtractHealth(1);
					checkUfo = 1;
				}
			}
			if(checkUfo == 0){
				updateUfo(u);
			}
		}
		
		hit = 0;
		bulletHitIndex = 0;
		for( int i = 0; i<bullets.size(); i++){
			enemyHit = 0;
			enemyHitIndex = 0;
			for(int j = 0; j<enemies.size(); j++){
				int hitResult = bullets.get(i).checkHit(enemies.get(j).getHitbox());
				if(hitResult == 1){
					hit = 1;
					bulletHitIndex = i;
					enemies.get(j).damageEnemy();
					if(enemies.get(j).getEnemyHealth() == 0){
						enemyHit = 1;
						enemyHitIndex = j;
						view.addEnemyShipScore();
					}
				}
			}
			if(enemyHit == 1){
				view.removeEnemy(enemies.get(enemyHitIndex));
			}
		}
		if(hit == 1){
			view.removeBullet(bullets.get(bulletHitIndex));
		}
		
		hit = 0;
		bulletHitIndex = 0;
		for( int i = 0; i<bullets.size(); i++){
			asteroidHit = 0;
			asteroidHitIndex = 0;
			for(int j = 0; j<asteroids.size(); j++){
				int hitResult = bullets.get(i).checkHit(asteroids.get(j).getHitbox());
				if(hitResult == 1){
					hit = 1;
					bulletHitIndex = i;
					asteroids.get(j).damageEnemy();
					if(asteroids.get(j).getEnemyHealth() == 0){
						asteroidHit = 1;
						asteroidHitIndex = j;

						view.addAsteriodScore();
					}
				}
			}
			if(asteroidHit == 1){
				view.removeAsteroid(asteroids.get(asteroidHitIndex));
			}
		}
		if(hit == 1){
			view.removeBullet(bullets.get(bulletHitIndex));
		}
		
		hit = 0;
		bulletHitIndex = 0;
		for( int i = 0; i<bullets.size(); i++){
			ufoHit = 0;
			ufoHitIndex = 0;
			for(int j = 0; j<ufos.size(); j++){
				int hitResult = bullets.get(i).checkHit(ufos.get(j).getHitbox());
				if(hitResult == 1){
					hit = 1;
					bulletHitIndex = i;
					ufos.get(j).damageEnemy();
					if(ufos.get(j).getEnemyHealth() == 0){
						ufoHit = 1;
						ufoHitIndex = j;

						view.addUfoScore();
					}
				}
			}
			if(ufoHit == 1){
				view.removeUFO(ufos.get(ufoHitIndex));
			}
		}
		if(hit == 1){
			view.removeBullet(bullets.get(bulletHitIndex));
		}
		
		}
		
		
	}
	
	public void updateShip(Coordinate c){
		ship.setCoord(c.getX(),c.getY());
	}
	
	public void updateBullet(PaintBullet bullet){
		int y = bullet.getCoord().getY();
		int x = bullet.getCoord().getX();
		
		Coordinate tempUL;
		Coordinate tempLR;
		
		y = y-3;
		bullet.getCoord().setY(y);
		
		tempUL = new Coordinate(x, y);
		tempLR = new Coordinate(x+12, y+20);
		
		bullet.updateHitbox(tempUL, tempLR);
		
	}
	
	public void updateEnemy(PaintEnemy e){
		int y = e.getCoord().getY();
		int x = e.getCoord().getX();
		
		Coordinate tempUL;
		Coordinate tempLR;
		
		y = y + e.getEnemySpeed();
		e.getCoord().setY(y);
		
		tempUL = new Coordinate(x, y);
		tempLR = new Coordinate(x+64, y+128);
		
		e.updateHitbox(tempUL, tempLR);
	}
	
	public void updateAsteroid(Asteroid a){
		int y = a.getCoord().getY();
		int x = a.getCoord().getX();
		
		Coordinate tempUL;
		Coordinate tempLR;
		
		y = y + a.getEnemySpeed();
		a.getCoord().setY(y);
		
		int directionY = rn.nextInt(3);
		if(directionY == 0){
			x = x + a.getEnemySpeed();
		}
		if(directionY == 1 || directionY == 2){
			x = x - a.getEnemySpeed();
		}
		a.getCoord().setX(x);
		
		tempUL = new Coordinate(x, y);
		tempLR = new Coordinate(x+64, y+64);
		
		a.updateHitbox(tempUL, tempLR);
	}
	
	public void updateUfo(UFO u){
		int y = u.getCoord().getY();
		int x = u.getCoord().getX();
		
		Coordinate tempUL;
		Coordinate tempLR;
		
		x = x + u.getEnemySpeed();
		y = y + u.getEnemySpeed();
		u.getCoord().setX(x);
		u.getCoord().setY(y);
		
		tempUL = new Coordinate(x, y);
		tempLR = new Coordinate(x+115, y+120);
		
		u.updateHitbox(tempUL, tempLR);
	}
	
	public void printEnemyCoordinates(PaintEnemy e){
		System.out.println("ENEMY X: " + e.getCoord().getX() + " ENEMY Y: " + e.getCoord().getY());
		e.printULCoordinates();
		e.printLRCoordinates();
	}
	
	public void getShipHitBox(HitBox s){
		shipHitBox = s;
	}
	
	public void getShipObject(Ship s){
		ship2 = s;
	}
	
}
