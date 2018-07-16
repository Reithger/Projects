import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.util.*;

public class dodgeWall extends GraphicsProgram{
    
	public static final int screenX = 1200;
	public static final int screenY = 500;
	public static final int[] difficulty = {2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6};
	public static final int[] levSpd = {15, 15, 12, 12, 12, 10, 10, 10, 10, 10, 10, 8, 8, 8};
	public static final int[] gap = {300, 300, 350, 340, 330, 370, 360, 350, 340, 380, 370, 400, 390, 380};
	public static final Color[] playCol = {Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, Color.BLACK};
	public static final Color[] wallCol = {Color.RED, Color.ORANGE, Color.MAGENTA, Color.GREEN, Color.BLUE, Color.BLACK, Color.GRAY, Color.LIGHT_GRAY, Color.PINK};
	public static final int size = screenX / 40;
	public static final int wallSpd = 6;
	
	private int numGap;
	private int toMove;
	private int curLoc;
	private int level;
	private int lives;
	private GRect player;
	private GOval[] markers;
	private ArrayList<GRect> wall;
	private boolean play;
	
	public void debug(){
		System.out.println(player.getX() + " " + player.getY() + " " + numGap + " " + toMove + " " + curLoc + " " + getPos(toMove) + " " + getPos(curLoc) + " " + level + " " + lives);
	}
	
	public static void main(String[] args){
		dodgeWall run = new dodgeWall();
		run.start(args);
	}
	
	public void run(){
		setSize(screenX + 17, screenY + 20 + 59);
		initialize();
		addKeyListeners();
		while(true){
		  gameActions();
		  try {
			Thread.sleep(1000/60);
		 }catch (InterruptedException e) {
			e.printStackTrace();
		  }
		}
	}
	
	public void initialize(){
		level = 0;
		numGap = difficulty[level];
		curLoc = numGap/2;
		lives = 3;
		toMove = curLoc;
		play = true;
		markers = new GOval[0];
		player = new GRect(screenX/10, getPos(curLoc), size, size);
		player.setColor(playCol[lives]);
		player.setFilled(true);
		wall = new ArrayList<GRect>();
		add(player);
		addBorder();
	}
	
	//Create gaps in walls that move towards the left side - DONE
	//Move your entity with 'w'/'a' to line up with holes in the walls - DONE
	//Change the speed of the game as user progresses - DONE
	//If hit by wall, turn it glaring color and use one of several lives - DONE
	//Level progression of number of wall gaps/speed - DONE
	//Affect image to better let user observe how much to move for oncoming holes. - DONE
	//Pre-program a series of patterns that fit into any number of positions - DONE, ADD MORE
	//Also have random bits, why not. - DONE
	//Game over screen w/ score
	
	public void gameActions(){
		if(play){
		  if(lives <= 0){
			gameOver();
			return;}
		remove(player);
		int speed = levSpd[level];
		if(toMove != curLoc){
		  if(player.getY() == getPos(toMove))
			curLoc = toMove;
		  else{
			if(player.getY() - getPos(toMove) > 0)
			  player.move(0, -speed);
			else
			  player.move(0, speed);
		  }
		  if(Math.abs(player.getY() - getPos(toMove)) < speed+1){
			player = new GRect(player.getX(), getPos(toMove), size, size);
			if(lives >= 0)
			  player.setColor(playCol[lives]);
			else
			  player.setColor(Color.white);
			player.setFilled(true);
		  }}
		  else{
			player = new GRect(player.getX(), player.getY(), size, size);
			player.setFilled(true);
			if(lives >= 0)
			  player.setColor(playCol[lives]);
			else
			  player.setColor(Color.white);
		  }
		
		hitDetection();
		add(player);
		addMark();
		addWalls();
		if(wall.size() == 0){
		  if(level + 1 < gap.length)
		    level++;
		  if(lives + 1 < 3)
		    lives++;
		  createLevel();
		  addBorder();
		}
		numGap = difficulty[level];
		debug();
		}
		else{
		  
		}
	}
	
	public void keyPressed(KeyEvent e){
	  if(play){
		switch(e.getKeyChar()){
		  case 'w':
		  case KeyEvent.VK_UP: if (toMove - 1 >= 0 && curLoc >= toMove) toMove--; System.out.println("up"); break;
		  case 's':
		  case KeyEvent.VK_DOWN: if (toMove + 1 < numGap && curLoc <= toMove) toMove++; System.out.println("down"); break;
		  case '1': if (level + 1 < difficulty.length) level++; break;
		  case '2': if (level - 1 > 0) level--; break;
		}
		System.out.println(toMove + " " + curLoc + " " + player.getColor());}
	  else{
	    char inPut = e.getKeyChar();
	    if(Character.isDigit(inPut) == true && Character.getNumericValue(inPut) <= level && Character.getNumericValue(inPut) != 0){
	    	removeAll();
	    	initialize();
	    	wall.clear();
	    	level = Character.getNumericValue(inPut) - 1;
	    }
	  }
	}
	
	public void addMark(){
		for(int i = 0; i < markers.length; i++)
		  remove(markers[i]);
		markers = new GOval[numGap];
		for(int i = 0; i < markers.length; i++){
		  markers[i] = new GOval(screenX / 10 + size*7/17, getPos(i) + size*7/16, size/8, size/8);
		  markers[i].setFilled(true);
		  markers[i].setColor(Color.BLACK);
		  add(markers[i]);
		}
	}
	
	public void addBorder(){
		GRect rect = new GRect(0, 0, screenX + 17, screenY / 14);
		rect.setFilled(true);
		rect.sendToFront();
		add(rect);
		rect = new GRect(0, screenY - screenY / 28, screenX + 17, screenY / 10);
		rect.setFilled(true);
		rect.sendToFront();
		add(rect);
	}
	
	public void genWall(int gapSpot){
		int val = screenX + gap[level] * wall.size() / 2;
		GRect rect = new GRect(val, screenY/14+1, size, getPos(gapSpot) - 50 / (level + 1) - screenY/14-1);
		rect.setFilled(true);
		rect.setColor(wallCol[(level-1)%wallCol.length]);
		rect.sendToBack();
		wall.add(rect);
		rect = new GRect(val, getPos(gapSpot) + size + 50 / (level + 1), size, screenY - getPos(gapSpot) - 50 / (level + 1) - screenY / 10 + 2);
		rect.setFilled(true);
		rect.setColor(wallCol[(level-1)%wallCol.length]);
		rect.sendToBack();
		wall.add(rect);
		System.out.println(screenY + " " +  getPos(gapSpot) + " " + size);
	}
	
	public void createLevel(){
		Random rand = new Random();
		for(int i = 0; i < (level+1) * 5; i++){
			switch(rand.nextInt(5)){
			case 0: pattern1(); break;
			case 1: pattern2(); break;
			case 2: pattern4(); break; 
			case 3:
			case 4: pattern3(); break;
			default: break;
			}
		}
		
		for(int i = 0; i < wall.size(); i++)
		  System.out.println(wall.get(i).getX() + " " + wall.get(i).getY() + " " + wall.get(i).getHeight());
	}
	
	public void hitDetection(){
		if(wall.size() == 0)
		  return;
		int top;
		int bot;
	    for(int i = 0; i < wall.size()-1; i+=2){
	      top = i;
	      bot = i + 1;
		  if(wall.get(top).getX() < player.getX() + size && wall.get(top).getX() + size > player.getX() && (player.getY() < wall.get(top).getHeight() || player.getY()+size > wall.get(bot).getY())){
			 lives--;
			 System.out.println(player.getX() + " " + player.getY() + " " + wall.get(top).getX() + " " + wall.get(top).getHeight() + " " + wall.get(bot).getY() + " " + (wall.get(top).getX() < player.getX() + size) + " " +  (wall.get(top).getX() + size > player.getX()) + " " + (player.getY() < wall.get(top).getHeight()) + " " + (player.getY()+size > wall.get(bot).getY()));
			 remove(wall.get(top));
			 remove(wall.get(bot));
			 wall.remove(bot);
			 wall.remove(top);
		  }
	    }
	    
	}
	
	public void gameOver(){
		removeAll();
		play = false;
		displayText(size, size, screenX - size, screenY - size, "Game Over!~ You made it to level " + level + "!~ You can restart at any completed level.~ Press 1 through " + level + " to choose a level.~");
	}
	
	public void displayText(int x, int y, int width, int height, String message){
		GLabel label;
		Font font = new Font("Serif", Font.BOLD, 12);
		String[] terms = message.split("~");
		int longest = 0;
		for(int i = 0; i < terms.length; i++){
		  if(terms[i].length() > terms[longest].length())
			longest = i;}
		label = new GLabel(terms[longest]);
		while(label.getWidth() < width && label.getHeight() * terms.length < height){
			font = new Font("Serif", Font.BOLD, font.getSize() + 1);
			label.setFont(font);
		}
		font = new Font("Serif" , Font.BOLD, font.getSize() - 1);
		int totHeight = (height - ((int)label.getHeight() * 4))/2;
		for(int i = 0; i < terms.length; i++){
			label = new GLabel(terms[i], 0, 0);
			label.setFont(font);
			label.setFont(font);
			totHeight += label.getHeight();
			label.setLocation(x + (width - label.getWidth())/2, y + totHeight);
			add(label);
		}
	}
	
	public void pattern1(){
		for(int i = 0; i < numGap; i++)
		  genWall(i);
	}
	
	public void pattern2(){
		for(int i = numGap-1; i >= 0; i--)
		  genWall(i);
	}
	
	public void pattern3(){
		Random rand = new Random();
		for(int i = 0; i < numGap; i++)
			genWall(rand.nextInt(numGap));
	}
	
	public void pattern4(){
		System.out.println("pattern4");
		for(int i = 0; i < numGap - 2; i++){
			genWall(i);
			genWall(i+2);
		}
		for(int i = numGap - 1; i > 1; i--){
			genWall(i);
			genWall(i-2);
		}
	}
	
	public void addWalls(){
		for(int i = 0; i < wall.size(); i++){
		  remove(wall.get(i));
		  wall.get(i).move(-wallSpd, 0);
		  add(wall.get(i));
		}
		for(int i = 0; i < wall.size(); i++){
		  if(wall.get(i).getX() < -size)
			wall.remove(i);
		}
	}
	
	public int getPos(int loc){
		if(loc == 0)
		  return screenY / 7;
		else
		  return screenY / 7 + screenY * 5 / 7 * loc / (numGap-1);
	}
}
