import java.awt.event.*;
import java.util.*;
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.Font;								//Imports to make everything work

//Move around by mouse clicking/holding, randomly spawning orbs bounce around arena of two colors. One color increases your size, one decreases,
//when you get one you swap colors to grab. Goal is to get large enough to expand the arena (you shrink, color changes.) Records how many grabbed, 
//how many arenas moved through. (If size is reduced enough, you lose.) Limitation on how orbs spawn, eventually all are one color and you can't swap around.
//In 'final' arena, would need every orb with no screw-ups to win.

public class playGame extends GraphicsProgram{

	private Color BorderCol;
	private moveOval oval;
	private int size;
	private int sizeOval;
	private int sizeOrb;
	private int speed;
	private int level;
	private int numOrb;
	private int score;
	private int rndScr;
	List<moveOval> hldOvl;
	
	public static void main(String[] args){
		playGame run = new playGame();
		run.start(args);
	}
	
	public void run(){
		size = 700;
		setSize(size,size+size/12);
		initialize();
		addMouseListeners();
		while(true){
		  try {
			runGame();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void initialize(){
		removeAll();
		sizeOval = size/20;
		sizeOrb = size/20;
		speed = 3;
		level = 0;
		numOrb = 2;
		hldOvl = new ArrayList<moveOval>();
		BorderCol = borderSwap();
		score = 0;
		addBorder();
		oval = new moveOval(size/2 - sizeOval/2, size/2 - sizeOval/2, sizeOval, sizeOval,0);
		oval.setFilled(true);
		oval.setFillColor(colorSwitch(score));
		oval.setColor(Color.BLACK);
		add(oval);
		GLabel label = new GLabel("Click and hold the mouse to move your ball around!", size/5.0, size/5.0);
		label.setFont(new Font("Serif", Font.BOLD, 18));
		add(label);
		label = new GLabel("Move your ball on top of the same-colored balls to speed up!", size/5.0, size/5.0 * 2.0);
		label.setFont(new Font("Serif", Font.BOLD, 18));
		add(label);
		label = new GLabel("Touching a different color will shrink you and slow you down.", size/5.0, size/ 5.0 * 3.0);
		label.setFont(new Font("Serif", Font.BOLD, 18));
		add(label);
		label = new GLabel("You can only make four mistakes in a level, so be careful!", size/5.0, size/5.0 * 3.6);
		label.setFont(new Font("Serif", Font.BOLD, 18));
		add(label);
	}
	
	public void mouseDragged(MouseEvent e){
		if(level == -1){
			initialize();
		}
		int x = e.getX();
		int y = e.getY();
		int changeX = 0, changeY = 0;
		if(x > oval.getX() + sizeOval/2 && oval.getX() + sizeOval + speed < 9.0/10.0*size)
		  changeX = 1;
		else if(x < oval.getX() + sizeOval/2 && oval.getX() - speed > 1.0/10.0*size)
		  changeX = -1;
		if(y > oval.getY() + sizeOval/2 && oval.getY() + sizeOval + speed < 9.0/10.0*size)
		  changeY = 1;
		else if (y < oval.getY() + sizeOval/2 && oval.getY() - speed > 1.0/10.0*size)
		  changeY = -1;
		remove(oval);
		oval = new moveOval((int)(oval.getX() + changeX * speed), (int)(oval.getY() + changeY * speed), sizeOval, sizeOval,0);
		oval.setFilled(true);
		oval.setFillColor(colorSwitch(score));
		oval.setColor(BorderCol);
	    add(oval);
//		oval.movePolar(speed, 180.0/Math.PI*Math.atan((double)(y - (oval.getY() + sizeOval/2.0))/(double)(x - (oval.getX() + sizeOval/2.0))));
	}
	
	public void runGame() throws InterruptedException{
		switch(level){
		case -1: break;
		case 0:
			if(hldOvl.size() == 0){
				rndScr = 0;
				for(int i = 0; i < numOrb; i++){
				  moveOval add = new moveOval(size/2 - sizeOrb/2, size/4 + size/2*i, sizeOrb, sizeOrb,0);
				  add.setColor(colorSwitch(i));
				  add.setFilled(true);
				  add.setFillColor(colorSwitch(i));
				  hldOvl.add(add);
				}
			}
			for(int i = 0; i < hldOvl.size(); i++){
			  remove(hldOvl.get(i));
			  detectCollision(i);
			  add(hldOvl.get(i));}
			if(rndScr == 2){
				level = 1;
				numOrb = 8;
				sizeOval = size/20;
				hldOvl.clear();
				removeAll();
				addBorder();
				break;
			}
			checkLose();
			break;
		default:
			if(hldOvl.size() == 0){
				generateOrbs();
			}
			moveOrbs();
			checkLose();
			checkNextRound();
			break;
		}
		Thread.sleep(1000/60);
	}
	
	public Color borderSwap(){
		switch(level%8){
		case 0: return Color.PINK;
		case 1: return Color.BLUE;
		case 2: return Color.YELLOW;
		case 3: return Color.GREEN;
		case 4: return Color.ORANGE;
		case 5: return Color.RED;
		case 6: return Color.MAGENTA;
		case 7: return Color.GRAY;
		default: return Color.BLACK;
		}
	}
	
	public void checkLose() throws InterruptedException{
		for(int i = 0; i < hldOvl.size(); i++){
			if((int)hldOvl.get(i).getY() != 0)
				return;
		}
		GLabel label = new GLabel("Game Over! You made it to level " + level + " and had a score of " + score + ".", size/5.0, size/5.0);
		label.setFont(new Font("Serif", Font.BOLD , 18));
		add(label);
		Thread.sleep(2000);
		label = new GLabel("Click anywhere to start a new game.", size/5.0, size * 2.0 / 5.0);
		label.setFont(new Font("Sefit", Font.BOLD, 18));
		add(label);
		level = -1;
	}
	
	public void checkNextRound(){
		if(rndScr == numOrb - 4){
			level++;
			numOrb += 2;
			sizeOval = size/20;
			removeAll();
			addBorder();
			hldOvl.clear();
		}
	}
	
	public void moveOrbs(){
		for(int i = 0; i < hldOvl.size(); i++){
			remove(hldOvl.get(i));
			detectCollision(i);
			hldOvl.get(i).movePolar(speed,  hldOvl.get(i).getTheta());
			add(hldOvl.get(i));
		}
	}
	
	public void generateOrbs() throws InterruptedException{
		rndScr = 0;
		for(int i = 0; i < numOrb; i++){
		  moveOval add;
		  Random rand = new Random();
		  switch(i%4){
		    case 0: add = new moveOval(size/5 + ((i/4)%4)*sizeOrb, size/5 + ((i/4)%4)*sizeOrb, sizeOrb, sizeOrb, (double)rand.nextInt(360)); break;	//up
		    case 1: add = new moveOval(size - size/5 - ((i/4)%4)*sizeOrb, size/5 + ((i/4)%4)*sizeOrb, sizeOrb, sizeOrb, (double)rand.nextInt(360)); break;	//left
		    case 2: add = new moveOval(size - size/5 - ((i/4)%4)*sizeOrb, size - size/5 - ((i/4)%4)*sizeOrb, sizeOrb, sizeOrb, (double)rand.nextInt(360)); break;	//down 
		    case 3: add = new moveOval(size/5 + ((i/4)%4)*sizeOrb, size - size/5 - ((i/4)%4)*sizeOrb, sizeOrb, sizeOrb, (double)rand.nextInt(360)); break;	//right
		    default: add = new moveOval(0,0,0,0,0); break;
		  }
		  add.setColor(colorSwitch(i));
		  add.setFilled(true);
		  add.setFillColor(colorSwitch(i));
		  hldOvl.add(add);
		}
		for(int i = 0; i < hldOvl.size(); i++){
			add(hldOvl.get(i));
			Thread.sleep(250);
		}
	}
	
	public void detectCollision(int i){
		  if(Math.sqrt(Math.pow(Math.abs(oval.getX() - hldOvl.get(i).getX()),2) + Math.pow(Math.abs(oval.getY() - hldOvl.get(i).getY()),2)) < (sizeOval + sizeOrb)/2){
			if(hldOvl.get(i).getFillColor().equals(oval.getFillColor())){
			  score++;
			  rndScr++;
			  sizeOval += size/20.0 /numOrb;
			  if(speed + 1 != 11)
			    speed++;
			  hldOvl.set(i,new moveOval(0,0,0,0,0));}
			else{
			  sizeOval -= size/20.0 / numOrb;
			  if(speed - 1 != 2)
			    speed--;
			  hldOvl.set(i, new moveOval(0,0,0,0,0));
			}
		  }
		  if(hldOvl.get(i).getX() + sizeOrb >= size - size/10)
			  hldOvl.get(i).setTheta(180-hldOvl.get(i).getTheta());
		  if(hldOvl.get(i).getY() + sizeOrb >= size - size/10)
			  hldOvl.get(i).setTheta(360-hldOvl.get(i).getTheta());
		  if(hldOvl.get(i).getX() <= size/10)
			  hldOvl.get(i).setTheta(180-hldOvl.get(i).getTheta());
		  if(hldOvl.get(i).getY() <= size/10)
			  hldOvl.get(i).setTheta(360 - hldOvl.get(i).getTheta());
	}
	
	public Color colorSwitch(int i){
		switch(i%2){
		case 0: return Color.BLACK;
		case 1: return borderSwap();
		default: return null;
		}
	}
	
	public void addBorder(){
		BorderCol = borderSwap();
		border(0,0, size/10, size*.4, BorderCol);
		border(0, size/5 + size*.4, size/10, size*.4, BorderCol);
		border(size - size/10, 0, size / 10, size * .4, BorderCol);
		border(size - size/10, size/5 + size * .4, size / 10, size * .4, BorderCol);
		border(0,0, size * .4, size / 10, BorderCol);
		border(size / 5 + size*.4, 0, size * .4, size / 10, BorderCol);
		border(0, size - size / 10, size * .4, size / 10, BorderCol);
		border(size / 5 + size * .4, size - size / 10, size * .4, size / 10, BorderCol);
	}
	
	public void border(double x1, double y1, double width, double height, Color col){
		GRect rect = new GRect(x1, y1, x1 + width, y1 + height);
		rect.setFilled(true);
		rect.setColor(Color.BLACK);
		rect.setFillColor(col);
		add(rect);
	}
}

class moveOval extends GOval{
	private double theta;
	
	public moveOval(int x, int y, int size, int size2, double degree){
		super (x, y, size, size2);
		setTheta(degree);
	}
	
	public void setTheta(double degree){
		theta = degree;
	}
	
	public double getTheta(){
		return theta;
	}
}