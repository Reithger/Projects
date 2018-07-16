import java.awt.event.*;
import java.util.*;
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.math.*;
import java.awt.Font;								//Imports to make everything work

public class PlayGame extends GraphicsProgram{

	private static final int bitSize = 8;			//Constants for screen size and #-bit count/size of images drawn by #-bit count.
	private static final int playerSize = 6;
	private static final int SCREEN_X = 1500;
	private static final int SCREEN_Y = 750;
	
	private Entity player;			//Class variables for a lot of things.
	private ArrayList<Character> heldKey;
	private ArrayList<moveOval> shotsFired;
	private int shotSpeed;
													/*
	Change edge-detection to a GRect/Object array to compare against for future obstructions that aren't in a rectangle.
	Scrolling screen as you move along the path? Fix the white flashing (maybe reduce frame-rate, adjust speed)
	Make the image for the player character(s), give special ability.
	Add enemy (new class?) to fight; health, movement pattern, custom image. If new class, make generic and change player-entity to that instead of local variable.
	Give an objective: defeat practice monster, protect left wall from on-coming horde, once scrolling the screen assault at a rapid pace (bullet hell)
													*/
	
	public static void main(String[] args){
		PlayGame main = new PlayGame();
		main.start(args);
	}
	
	public void run(){							  	//Begin the program, while(true) to run things at 60 fps
		setSize(SCREEN_X + 17,SCREEN_Y + 59);
		initialize();
		drawCharacter();
		addKeyListeners();
		addMouseListeners();
		while(true){
			try {
				runningMethods();
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void initialize(){						//Base values that can change later on, so not constants but defaulted.
		player = new Entity(SCREEN_X/2 - playerSize*bitSize/2, SCREEN_Y/2 - playerSize*bitSize/2, "Art/test9.png", bitSize * playerSize, bitSize * playerSize);
		heldKey = new ArrayList<Character>();
		shotsFired = new ArrayList<moveOval>();
		shotSpeed = 15;
	}
	
	public void drawCharacter(){					//Adds entities to screen (currently player only)
		remove(player);
		add(player);
	}
	
	public void runningMethods(){					//Groups together all methods that run every frame(called in while(true))
		//Back-end methods
		momentumKey();					//Check for changes to momentum from user input
		adjMomentum();					//Change user location/momentum
		stayInBounds();					//Check objects to ensure they're legally placed/can't leave boundaries.
		//Run-time drawing
		removeAll();					//Clear the screen
		drawWalls();					//Add top/bottom borders and possible barriers.
		drawCharacter();				//Add character player
		moveShots();					//Projectile motion and drawing them.
	}

	public void mousePressed(MouseEvent e){			//If user clicks, add shot to array and get angle of fire. Lets player shoot.
		int yLoc = (int)player.getY();
		int xLoc = (int)player.getX();
		double theta = 180.0/Math.PI*Math.asin((yLoc+bitSize*playerSize/2.0 - e.getY())/(Math.sqrt(Math.pow(Math.abs(xLoc + playerSize*bitSize/2.0 - e.getX()), 2)+Math.pow(Math.abs(yLoc+playerSize*bitSize/2.0 - e.getY()),2))));
		if(e.getX() < xLoc + bitSize*playerSize/2.0)
		  theta = 180 - theta;
		shotsFired.add(new moveOval(xLoc+bitSize*playerSize/2, yLoc+bitSize*playerSize/2, bitSize*playerSize/2, bitSize*playerSize/2, theta, shotSpeed, true));
	}

	public void keyPressed(KeyEvent e){				//If user uses WASD, move the character-entity in that direction until let go.
		switch(e.getKeyChar()){
		  case 'w': if(heldKey.indexOf('w') == -1) heldKey.add('w'); break;
		  case 'a': if(heldKey.indexOf('a') == -1) heldKey.add('a'); break;
		  case 's': if(heldKey.indexOf('s') == -1) heldKey.add('s'); break;
		  case 'd': if(heldKey.indexOf('d') == -1) heldKey.add('d'); break;
		  default: break;
		}
	}
	
	public void keyReleased(KeyEvent e){			//If user stops using WASD, stop moving character-entity in that direction.
		switch(e.getKeyChar()){
		  case 'w': if(heldKey.indexOf('w') != -1) heldKey.remove(heldKey.indexOf('w')); break;
		  case 'a': if(heldKey.indexOf('a') != -1) heldKey.remove(heldKey.indexOf('a')); break;
		  case 's': if(heldKey.indexOf('s') != -1) heldKey.remove(heldKey.indexOf('s')); break;
		  case 'd': if(heldKey.indexOf('d') != -1) heldKey.remove(heldKey.indexOf('d')); break;
		  default: break;
		}
	}
	
	public void moveShots(){						//All shots still active need to move every frame. Part of runningMethods()
		for(int i = 0; i < shotsFired.size(); i++){
			shotsFired.get(i).movePolar(shotsFired.get(i).getSpeed(),  shotsFired.get(i).getTheta());
			shotsFired.get(i).setFilled(true);
			shotsFired.get(i).setColor(Color.RED);
			shotsFired.get(i).setFillColor(Color.RED);
			add(shotsFired.get(i));
		}
	}
	
	public void drawWalls(){						//Adds the bordering and background walls; level will likely affect color. Change design if eye-sore.
		drawWall(0,(int)(bitSize*playerSize*1.5), SCREEN_Y, SCREEN_X, bitSize*8, bitSize*6, bitSize/8, Color.PINK);
		drawWall(0,0, (int)(bitSize*playerSize*1.5), SCREEN_X, 3, bitSize*2, bitSize/4, Color.GRAY);
		drawWall(0,SCREEN_Y-(int)(bitSize*playerSize*1.5), (int)(bitSize*playerSize*1.5), SCREEN_X, 3, bitSize*2, bitSize/4, Color.GRAY);
	}
	
	public void drawWall(int x, int y, int height, int length, int rows, int columns, int thick, Color fill){	//Shortcut to draw an image given parameters.
		GRect rect;
		rect = new GRect(x, y, length, height);
		rect.setFilled(true);
		rect.setFillColor(fill);
		add(rect);
		for(int i = 0; i <= rows; i++){
			rect = new GRect(x, y + height/rows*i, length, thick);
			rect.setFilled(true);
			rect.setFillColor(Color.BLACK);
			rect.setColor(Color.BLACK);
			add(rect);
			if(i != rows){
			  for(int j = 0; j <= columns; j++){
				if(i%2 == 1 && j == columns)
				  break;
				rect = new GRect(x + length/columns*j + (i%2)*length/columns/2, y + thick + height/rows*i, thick, height/(rows));
				rect.setFilled(true);
				rect.setFillColor(Color.BLACK);
				rect.setColor(Color.BLACK);
				add(rect);  
			}}
		}
	}
	
	public void stayInBounds(){						//Keeps all entities on the screen, or removes them if they leave the screen.
		if(player.getX() < 0)
		  player.move(-(player.getLatMom()-.1), 0);
		if(player.getY() < bitSize*playerSize*1.5)
		  player.move(0, -(player.getLonMom()-.1));
		if(player.getX() + bitSize*playerSize > SCREEN_X)
		  player.move(-(player.getLatMom()+.1), 0);
		if(player.getY() + bitSize * playerSize > SCREEN_Y - bitSize*playerSize*1.5)
		  player.move(0, -(player.getLatMom()+.1));
		
		for(int i = 0; i < shotsFired.size(); i++){
			if(shotsFired.get(i).getX()+shotsFired.get(i).getWidth() > SCREEN_X)
			  shotsFired.remove(i);
			else if(shotsFired.get(i).getY() + shotsFired.get(i).getWidth() > SCREEN_Y - bitSize*playerSize*1.5){
			  if(shotsFired.get(i).getBounce() < 3){
			    shotsFired.get(i).setTheta(360 - shotsFired.get(i).getTheta());
			    shotsFired.get(i).setBounce(shotsFired.get(i).getBounce() + 1);}
			  else{
				shotsFired.remove(i);}
			  }
			else if(shotsFired.get(i).getX() < 0)
			  shotsFired.remove(i);
			else if(shotsFired.get(i).getY() < bitSize*playerSize*1.5)
			  if(shotsFired.get(i).getBounce() < 3){
				shotsFired.get(i).setTheta(360 - shotsFired.get(i).getTheta());
				shotsFired.get(i).setBounce(shotsFired.get(i).getBounce() + 1);}
			  else{
				shotsFired.remove(i);}
		}
	}
	
	public void momentumKey(){						//Check if user is moving with WASD to affect momentum of player-entity. 
		if(heldKey.indexOf('w') != -1)
		  player.setLonMom(player.getLonMom() - 1);
		if(heldKey.indexOf('a') != -1)
		  player.setLatMom(player.getLatMom() - 1);
	    if(heldKey.indexOf('s') != -1)
	      player.setLonMom(player.getLonMom() + 1);
	    if(heldKey.indexOf('d') != -1)
	      player.setLatMom(player.getLatMom() + 1);
	}
	
	public void adjMomentum(){						//Cap momentum, slow down momentum, and affect location of player-entity.
		if(player.getLonMom() > 5)
		  player.setLonMom(5);
		if(player.getLonMom() < -5)
		  player.setLonMom(-5);
		if(player.getLatMom() > 5)
		  player.setLatMom(5);
		if(player.getLatMom() < -5)
		  player.setLatMom(-5);
		if(player.getLonMom() > 0.0)
		  player.setLonMom(player.getLonMom() - .1);
		if(player.getLonMom() < 0.0)
		  player.setLonMom(player.getLonMom() + .1);
		if(player.getLatMom() > 0.0)
		  player.setLatMom(player.getLatMom() - .1);
		if(player.getLatMom() < 0.0)
		  player.setLatMom(player.getLatMom() + .1);
		player.move(player.getLatMom(), player.getLonMom());
	}
}

class Entity extends GImage{
	private double latMom;
	private double lonMom;
	
	public Entity(int x, int y, String filePath, int size, int size2){
		super(filePath, x, y);
		setSize(size, size2);
		setLatMom(0);
		setLonMom(0);
	}
	
	public void setLatMom(double given){
		latMom = given;
	}
	
	public double getLatMom(){
		return latMom;
	}
	
	public void setLonMom(double given){
		lonMom = given;
	}
	
	public double getLonMom(){
		return lonMom;
	}
}

class moveOval extends GOval{						//Class for shotsFired[] objects; holds degree and speed of shot.
	private double theta;
	private double speed; 
	private int bounce;
	private boolean ally;
	
	public moveOval(int x, int y, int size, int size2, double degree, double givSpeed, boolean flag){		//Constructor of moveOval object
		super (x, y, size, size2);
		setTheta(degree);
		setSpeed(givSpeed);
		setBounce(0);
		setAlly(flag);
	}
	
	public void setTheta(double degree){		//Set angle for object
		theta = degree;
	}
	
	public double getTheta(){					//Get the angle of the object
		return theta;
	}

	public void setSpeed(double inPut){			//Set speed for object
		speed = inPut;
	}
	
	public double getSpeed(){					//Get the speed of the object
		return speed;
	}
	
	public void setBounce(int value){			//Set # of bounces for object
		bounce = value;
	}
	
	public int getBounce(){						//Get # of bounces for object
		return bounce;
	}
	
	public void setAlly(boolean inPut){			//Set whether the shot should hurt player/enemy
		ally = inPut;
	}
	
	public boolean getAlly(){					//Get whether the shot should hurt player/enemy
		return ally;
	}
}