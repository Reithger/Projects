import java.awt.event.*;
import java.util.*;
import acm.program.*;
import acm.graphics.*;
import acm.io.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;								//Imports to make everything work
import java.awt.Graphics2D;

public class Draw extends GraphicsProgram{
	public static void main(String[] args){
		Draw obj = new Draw();
		obj.start(args);
	}

	private static final int TILESIZE = 128;		//Length of side of each image 
	private static final int XCORNER = 50;			//Corner coordinate of X total image
	private static final int YCORNER = 50;			//Corner coordinate of Y total image
	
	private int boardSize;
	private int mode;
	private int setting;
	private int xFirst;
	private int yFirst;
	private int numClicks;
	private GImage[] images;
	private BufferedImage usrImg;
	private int[][] state;
	private boolean isSolved;
	private int[][] winningBoard;
	
	public void run(){
		initialize();
		addMouseListeners();
		while(true){
		System.out.println("Here");
		  if(isSolved)
			try {
				winScreen();
				break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void initialize(){
		xFirst = -1;
		yFirst = -1;
		IODialog dialog = new IODialog();
		boardSize = dialog.readInt("How large a puzzleboard do you wish to play on? (Number of squares in height/width)");
		images = new GImage[boardSize*boardSize];
		GRect rect;
		rect = new GRect(XCORNER/ 8, XCORNER/8, 7.0 / 9.0 * XCORNER, 7.0 / 9.0 * XCORNER);
		rect.setColor(Color.green);
		rect.setFilled(true);
		add(rect);
		GOval button = new GOval(XCORNER *3.0/16.0, XCORNER * 3.0/16.0, 5.0 * XCORNER / 8.0, 5.0*XCORNER /8.0);
		button.setFillColor(Color.red);
		button.setFilled(true);
		add(button);
		add(new GLabel(" <-- Start the game here after drawing!", XCORNER, XCORNER / 2));
		GLabel reset = new GLabel("Reset", XCORNER/6, (boardSize+1) * TILESIZE - YCORNER);
		add(reset);
		add(new GRect(XCORNER/6 - 4, (boardSize+1) * TILESIZE - YCORNER - reset.getHeight(), reset.getWidth() + 10, reset.getHeight() + 5));
		for(int i = 1; i < 5; i++){
		  rect = new GRect(XCORNER/4.0, XCORNER * i + XCORNER/4.0, XCORNER /2.0, XCORNER /2.0);
		  add(rect);
		  switch(i){
		  case 1: Line(XCORNER/4.0, XCORNER/4.0 + XCORNER * i, XCORNER/4.0+XCORNER/2.0, XCORNER/4.0+XCORNER/2.0+XCORNER * i); 
		  		  break;
		  case 2: Rectangle((int)(3.0/8.0*XCORNER), 5.0/16.0*XCORNER, XCORNER*i + XCORNER *5.0 /16.0); 
		  		  break;
		  case 3: Circle((int)(3.0 / 8.0 * XCORNER), 5.0/16.0*XCORNER, 5.0/16.0*XCORNER + XCORNER * i);
		  		  break;
		  case 4: superShape(XCORNER/8, XCORNER/2.0, XCORNER/2.0 + XCORNER * i); 
		  	      break;
		  }
		  mode = 0;
		  setting = 0;
		}
		GLine gridDraw;								//Create GLine object for later use.
		for(int i = 0; i < boardSize+1; i++){		//Loop that draws the grid of the board based on a lot of experimental values and relations between them.
			gridDraw = new GLine(XCORNER, YCORNER + i*(TILESIZE+1), XCORNER + boardSize * (TILESIZE)+ boardSize, YCORNER + i*(TILESIZE+1));	//Horizontal lines
			add(gridDraw);	//Adds the Horizontal Line
			gridDraw = new GLine(XCORNER + i*(TILESIZE+1), YCORNER, XCORNER + i * (TILESIZE+1), YCORNER + boardSize * (TILESIZE)+ boardSize);	//Vertical lines
			add(gridDraw);	//Adds the Vertical Line
		}
		setSize(boardSize * TILESIZE + 2 * XCORNER, (int)(((double)boardSize + .5) * TILESIZE + 2 * YCORNER));
		usrImg = new BufferedImage(boardSize * TILESIZE + 2 * XCORNER, (int)(((double)boardSize + .5) * TILESIZE + 2 * YCORNER), BufferedImage.TYPE_INT_RGB);
		state = new int[boardSize][boardSize];
		numClicks = 0;
		isSolved = false;
		winningBoard = new int[boardSize][boardSize];
		for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++){
				if(i == boardSize - 1 && j == boardSize - 1)
				  winningBoard[i][j] = 0;
				else
				  winningBoard[i][j] = i * boardSize + j + 1;
				System.out.print(winningBoard[i][j] + " ");
			}
		}
	}
	
	public void mouseClicked(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		switch(mode){
		case 0:
			int temp = setting;
			if(x > XCORNER/8 && x < XCORNER / 8 + XCORNER * 7.0 /9.0 && y > XCORNER / 8 && y < XCORNER / 8 + XCORNER * 7.0 / 9.0){
				mode = 1;
				makeBoard();
				break;
			}
			for(int i = 0; i < 4; i++){				
				if(x > XCORNER / 4.0 && x < XCORNER / 4.0 + XCORNER/2.0 && y > XCORNER / 4.0 + XCORNER * (i+1) && y < XCORNER / 4.0 + XCORNER * (i+1) + XCORNER / 2.0){
					setting = i;
					break;}
			}
			GLabel reset = new GLabel("Reset");
			if(x > XCORNER / 6 - 4 && x < XCORNER / 6 + reset.getWidth() + 6 && y > (boardSize+1) * TILESIZE - YCORNER - reset.getHeight() && y < (boardSize+1) * TILESIZE - YCORNER - reset.getHeight() + reset.getHeight() + 5){
				removeAll();
				initialize();
				break;
			}
			if (setting != temp)
			  break;
			
			if(xFirst == -1){
			  xFirst = x;
			  yFirst = y;
			  break;
			}
			int size = (int)Math.sqrt(Math.pow(x - xFirst, 2) + Math.pow(y - yFirst, 2));
			switch(setting){
			case 0: Line(xFirst, yFirst, x, y); xFirst = -1; yFirst = -1; break;
			case 1: Rectangle(size, xFirst, yFirst); xFirst = -1; yFirst = -1; break;
			case 2: Circle(size, xFirst, yFirst); xFirst = -1; yFirst = -1; break;
			case 3: superShape(size, xFirst, yFirst); xFirst = -1; yFirst = -1; break;
			}
			break;
			
		case 1:
			for(int i = 0; i < boardSize+1; i++){	//Quick check for if the user clicked out of bounds or otherwise illegally.
				if(x == XCORNER + i*(TILESIZE+1) || y == YCORNER + i * (TILESIZE+1))
					return;}
			if(x <= XCORNER || x >= XCORNER + boardSize * (TILESIZE)+ boardSize || y <= YCORNER || y >= YCORNER + boardSize * (TILESIZE)+ boardSize)
				return;
			
			int xPt = 0, yPt = 0, xPos, yPos;	//Some variables for derived locations and positions in state[][] array
			
			for(int i = 0; i < state.length; i++){		//First, finds the location of the 0 spot where the board can move in to.
				for(int j = 0; j < state.length; j++){
					if (state[i][j] == 0){
						xPt = i;
						yPt = j;}}}
			
			xPos = XCORNER + yPt * TILESIZE;			//Finds the top left corner of this 0-block in pixel terms for comparison to user's click location.
			yPos = YCORNER + xPt * TILESIZE;
			int tempHold = 0;							//tempHold is just for doing a switch-around and making sure values aren't references.
			
			if(x < xPos && x > xPos - TILESIZE && y > yPos && y < yPos + TILESIZE){	//Checks to see if user click was in the left block to 0
				tempHold = new Integer(state[xPt][yPt-1]);	//Copy value
				state[xPt][yPt] = tempHold;					//Set old blank spot to value
				state[xPt][yPt-1] = 0;}						//Set clicked-on block to 0
			else if(x > xPos + TILESIZE && x < xPos + (2*TILESIZE) && y > yPos && y < yPos + TILESIZE){	//Checks to see if user click was in the right block to 0
				tempHold = new Integer(state[xPt][yPt+1]);	//repeat
				state[xPt][yPt] = tempHold;
				state[xPt][yPt+1] = 0;}
			else if(y < yPos && y > yPos - TILESIZE && x > xPos && x < xPos + TILESIZE){	//Checks to see if user click was in the above block to 0
				tempHold = new Integer(state[xPt-1][yPt]);	//repeat
				state[xPt][yPt] = tempHold;
				state[xPt-1][yPt] = 0;}
			else if(y > yPos + TILESIZE && y < yPos + 2*TILESIZE && x > xPos && x < xPos + TILESIZE){	//Checks to see if user click was in the below block to 0
				tempHold = new Integer(state[xPt+1][yPt]);	//repeat
				state[xPt][yPt] = tempHold;
				state[xPt+1][yPt] = 0;}
			numClicks++;	//count number of clicks for win scenario
			removeAll();	//Clean the board off of the screen
			drawGrid();
			addImg();
			checkForWinner();	//Check states[][] against the WINNINGBOARD[][] constant
			System.out.println(isSolved);
			break;
		}
	}

	public void makeBoard(){
		Graphics2D g2 = usrImg.createGraphics();
		this.paint(g2);		
		removeAll();		
		add(new GImage(usrImg));
		usrImg = usrImg.getSubimage(XCORNER+1, YCORNER+1, boardSize * (TILESIZE+1), boardSize * (TILESIZE+1));
		for(int i = 1; i < boardSize*boardSize; i++){
			  images[i] = new GImage(usrImg.getSubimage((TILESIZE+1)*((i-1)%boardSize), (TILESIZE+1) * ((i-1)/boardSize), TILESIZE, TILESIZE));
		}
		Random rand = new Random();
		ArrayList<Integer> order = new ArrayList<Integer>();
		int num;
		while(order.size() < boardSize*boardSize){
		  num = rand.nextInt(boardSize * boardSize);
		  if(order.indexOf(num) == -1)
			order.add(num);
		}
		for(int i = 0; i < state.length; i++){
		  for(int j = 0; j < state.length; j++){
			  state[j][i] = order.get(i*state.length + j);
		  }
		}
		System.out.println(Arrays.toString(state));
		removeAll();
		drawGrid();
		addImg();

	}
	
	public void drawGrid(){
		GLine gridDraw;								//Create GLine object for later use.
		for(int i = 0; i < boardSize+1; i++){		//Loop that draws the grid of the board based on a lot of experimental values and relations between them.
			gridDraw = new GLine(XCORNER, YCORNER + i*(TILESIZE+1), XCORNER + boardSize * (TILESIZE)+ boardSize, YCORNER + i*(TILESIZE+1));	//Horizontal lines
			add(gridDraw);	//Adds the Horizontal Line
			gridDraw = new GLine(XCORNER + i*(TILESIZE+1), YCORNER, XCORNER + i * (TILESIZE+1), YCORNER + boardSize * (TILESIZE)+ boardSize);	//Vertical lines
			add(gridDraw);	//Adds the Vertical Line
		}
	}

	public void addImg(){
		for(int i = 0; i < boardSize; i++){
			  for(int j = 0; j < boardSize; j++){
				if(state[i][j] != 0)
				  add(images[state[i][j]], XCORNER + j * (TILESIZE+1) + 1, YCORNER + i * (TILESIZE+1) + 1);
			  }
			}
	}
	
	public void checkForWinner(){					//Simple method that compares current states[][] board to WINNINGBOARD constant
		boolean passTrue = true;
		for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++){
				if(winningBoard[i][j] != state[i][j])	//If ever a position is different, tell the program not to go to win-state.
					passTrue = false;
				else{									//Adds a circle on to the puzzle pieces that are in the correct spot.
					GOval confirmation = new GOval(XCORNER + j * (TILESIZE+1) + 1, YCORNER + i * (TILESIZE+1)+1, TILESIZE/(boardSize*boardSize), TILESIZE/(boardSize*boardSize));
					confirmation.setColor(randColor());
					confirmation.setFilled(true);
					add(confirmation);
				}}}
		if (passTrue)
		  isSolved = true;							//If it makes it through the loop, then it must be a winning board.
	}
	
	public void winScreen() throws InterruptedException{		//Custom method to show the user a fun thing if they win.
		List<GOval> blankScreen = new ArrayList<GOval>();		//Makes a list of GOvals to alter their size in animation
		int startSize = 10;										//Circles appear as this size initially
		double rtOfChg = 5;									//How quickly the circles get larger
		Random rand = new Random();								//Random object for locational randomness.
		for(int i = 0; i < 180; i++){							//Loops with Thread.sleep for animation of growing circles
		    if(blankScreen.size() < 30)							//Only draw ten circles total
			  blankScreen.add(new GOval(rand.nextInt(getWidth()), rand.nextInt(getHeight()), startSize, startSize));	//Randomize location of circles, same start size
		    for(int j = 0; j < blankScreen.size(); j++){	//Loops through growing List to add Ovals of changing size based on relative size of circles, growing.
			    blankScreen.set(j, new GOval(blankScreen.get(j).getX()- rtOfChg/2, blankScreen.get(j).getY()- rtOfChg/2, blankScreen.get(j).getHeight()+rtOfChg, blankScreen.get(j).getHeight()+rtOfChg));
			    blankScreen.get(j).setFilled(false);
			    blankScreen.get(j).setColor(randColor());	//Uses method colorGrab to change the colors used in the circles
			    add(blankScreen.get(j));
		    }
		  Thread.sleep(1000/60);}	//60 fps
		String[] toPrint = {"Congratulations! ", "You have made whole ", "that which was broken ", "in only " + numClicks + " moves! ", "- - - - - - - - - - - - - - -",
							"That's pretty cool. ", "These games aren't easy. ", "You should try again ", "with a new board! ", "Have a beautiful day. "};
		//An array of messages to print, so that the lines skip as it draws with GLabel with relative values and stuff.
		GLabel winMessage = new GLabel("", XCORNER, YCORNER);	//Default GLabel
		int printCnt = 0;										//Counter to make sure it doesn't exceed the array, is also position in toPrint[] array.
		rtOfChg = 15;											//Speed of each letter being printed; smaller is faster.
		for(int i = 0; i < 600; i++){							//Arbitrary maximum value of i.
			if(printCnt == toPrint.length-1 && toPrint[printCnt].length() == winMessage.getLabel().length())	//If final label is drawn, break from for{} loop
				break;
			if (i <= rtOfChg * toPrint.length && printCnt < toPrint.length){	//If values would be illegal/breaking, then don't do the thing.
			  if(toPrint[printCnt].length() == winMessage.getLabel().length() && printCnt+1 < toPrint.length){	//If label is fully drawn, switch to next word in array.
				printCnt++;
			    winMessage = new GLabel("", XCORNER, YCORNER * (printCnt+1));}
			  //The label draws by taking the i-value, reset by position of printCnt, divided by total value of rtOfChg as a ratio of how much of the string to show.
			  winMessage.setLabel(toPrint[printCnt].substring(0, 1+(int)((i - printCnt * rtOfChg) / rtOfChg * toPrint[printCnt].length())) + "|");
			  winMessage.setVisible(true);
			  winMessage.setColor(Color.YELLOW);
			  winMessage.setFont(new Font("Serif", Font.BOLD , 48));
			  add(winMessage);}
			
			Thread.sleep(1000/60); //60 fps
		}
	}
	
	public void superShape(int size, double xLoc, double yLoc){
		GTurtle turt = new GTurtle(xLoc, yLoc);
		Random rand = new Random();
		turt.hideTurtle();
		turt.penDown();
		turt.setColor(randColor());
		turt.setSpeed(1);
		add(turt);
	    int first = rand.nextInt(80)+ 20;
	    int second = rand.nextInt(6)+6;
	    for(int i = 0; i < first; i++){
	      for(int j = 0; j < second; j++){
	    	turt.forward(size/2);
	    	turt.left(360.0/second);}
	      turt.left(360.0/first);}
	      }
	    
	public void Circle(int size, double xLoc, double yLoc){
		GOval oval = new GOval(xLoc, yLoc, size, size);
		oval.setFilled(true);
		Color choose = randColor();		//More colors
		oval.setColor(choose);
		oval.setFillColor(choose);
		add(oval);
		
	}
	
	public void Rectangle(int size, double xLoc, double yLoc){
		GRect rect = new GRect(xLoc, yLoc, size, size);
		rect.setFilled(true);
		rect.setColor(randColor()); 
		add(rect);
	}
	
	public void Line(double xLocA, double yLocA, double xLocB, double yLocB){
		GLine line = new GLine(xLocA, yLocA, xLocB, yLocB);
		line.setColor(randColor());
		add(line);
	}
	
	public Color randColor(){
		Random rand = new Random();
		int num = rand.nextInt(8);
		switch(num){
		case 0: return Color.black;
		case 1: return Color.blue;
		case 2: return Color.yellow;
		case 3: return Color.red;
		case 4: return Color.green;
		case 5: return Color.cyan;
		case 6: return Color.pink;
		case 7: return Color.orange;
		default: return Color.green;
		}
	}
}
