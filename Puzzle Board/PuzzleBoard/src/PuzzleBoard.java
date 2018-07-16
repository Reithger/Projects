//Mac Clevinger
//Comp 1731 - Assignment 3
//A program to allow the user to play a simple puzzle game of moving squares in a grid into a winning sequence.
//In addition, once the user has won, an animation will play, and if the puzzle piece is in the right spot an orb will appear to show them
//that they have placed the piece in the correct spot, a brief, ecstatic moment of pure rightness in world besot by madness before the
//sheer necessity of progress forces them to tear this shining example of perfection asunder, force it into the harsh cold of an unfair
//world until the fateful day comes that it may be permitted to return to its place of perfection, having learned now that it is not in
//your being in a place of peaceful rest but in you and your brethren, your fellow sister and all of humankind finding their place of peace,
//the knowledge that not only are you in comfort but all are in comfort that you can truly find harmony with where you are.

import java.awt.event.*;
import java.util.*;
import java.io.*;
import acm.program.*;
import acm.graphics.*;
import acm.io.*;
import java.awt.Color;
import java.awt.Font;								//Imports to make everything work

public class PuzzleBoard extends GraphicsProgram{
	private static final int PUZZLESIZE = 4;		//Size of puzzle board x & y
	private static final int TILESIZE = 128;		//Length of side of each image 
	private static final int XCORNER = 50;			//Corner coordinate of X total image
	private static final int YCORNER = 50;			//Corner coordinate of Y total image
	private static final int[][] WINNINGBOARD = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};	//Winning configuration
	
	private GImage[] images;						//An array to hold the images of the pieces of the board; loaded in from Data folder
	private int[][] state;							//An array to hold the positions of the pieces on the board
	private volatile boolean isSolved;				//A boolean to check for whether the use has won the game or not.
													//Volatile is used to make sure that changes to variables across threads are consistent; conversation with Rob Allen led to this addition.
	
	private int numClicks;							//For the bonus part, this records how many moves it took the user to win.
	
	public static void main(String[] args){			//Main method to run the program and fix the filePath so it runs properly.
		PuzzleBoard puzGame = new PuzzleBoard();				//Makes an object of the class to run the rest of the program.
		puzGame.start(args);
	}
	
	public void run(){								//An applett's 'main' method to make things happen
		setSize(650, 700);							//Adjust size of the screen on start-up
		readInImages();								//Calls method to assign images to GImage array images[]
		try {										//Eclipse told me to add the try{} catch{} so that it would run; likely a side-effect of how I read the file.
			initializePuzzle();						//Calls method to read a file that describes the composition of the board.
		} catch (IOException e) {
			e.printStackTrace();
		}
		drawGridAndTiles();							//Draws the board that the user will play with.
		addMouseListeners();						//Allows for the program to detect mouse clicking, opens up user input.
		while(true){								//This will be checking for a victory scenario permanently
		  if(isSolved)								//If the puzzle is solved, run the method winScreen and end the while(true) loop.
			try {									//This is made possible by multi-threading, where this is happening while 
				winScreen();
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}}
	}
	
	public void readInImages(){								//Method to assign images from Data folder to images[] array
		images = new GImage[PUZZLESIZE*PUZZLESIZE];			//Sets size of images[] by the size of the puzzle squared; it's a square board.
		for(int i = 1; i < images.length; i++){				//Loop through images[] to assign it images based on the value of 'i'.
			if (i > 9)
			  images[i] = new GImage("data/num" + i + ".jpg");
			else
			  images[i] = new GImage("data/num0" + i + ".jpg");
			images[i].setSize(TILESIZE, TILESIZE);			//Set size of image to make work in our program.
		}
	}
	
	public void initializePuzzle() throws FileNotFoundException{	//Reads a file to describe how to compose the board of the puzzle
		state = new int[PUZZLESIZE][PUZZLESIZE];		//Set size of state[][], a representation of the displayed board.
		isSolved = false;								//Defaults value of isSolved to false.
		numClicks = 0;
		IODialog dialog = new IODialog();				//To ask the user for the location of the file to read
		String locFile = dialog.readLine("Give the file path of your puzzle board.");
		Scanner s = new Scanner(new File(locFile));	//Scanner to read the file and assign its values into state
		for(int i = 0; i < state.length; i++){
			for(int j = 0; j < state.length; j++){
				state[i][j] = Integer.parseInt(s.next());	//Takes values in file and converts to integer, as we know what the file will look like.
			}
		}
		s.close();										//Close scanner, because apparently we're supposed to.
	}
	
	public void drawGridAndTiles(){					//Draws the board; lines and images assigned to the board. Changes based on later input.
		GLine gridDraw;								//Create GLine object for later use.
		for(int i = 0; i < PUZZLESIZE+1; i++){		//Loop that draws the grid of the board based on a lot of experimental values and relations between them.
			gridDraw = new GLine(XCORNER, YCORNER + i*(TILESIZE+1), XCORNER + PUZZLESIZE * (TILESIZE)+ PUZZLESIZE, YCORNER + i*(TILESIZE+1));	//Horizontal lines
			add(gridDraw);	//Adds the Horizontal Line
			gridDraw = new GLine(XCORNER + i*(TILESIZE+1), YCORNER, XCORNER + i * (TILESIZE+1), YCORNER + PUZZLESIZE * (TILESIZE)+ PUZZLESIZE);	//Vertical lines
			add(gridDraw);	//Adds the Vertical Line
		}
		for(int i = 0; i < PUZZLESIZE; i++){	//Loops through the values in images, based on values stored in changing state[][], and places them correctly.
			for(int j = 0; j < PUZZLESIZE; j++){
				if(state[i][j] != 0){
				  images[state[i][j]].setLocation(XCORNER + j * (TILESIZE+1) + 1, YCORNER + i * (TILESIZE+1) + 1);
				  add(images[state[i][j]]);}
			}
		}
	}
	
	public void mouseClicked(MouseEvent e){		//When user clicks on the board, move pieces and check for victory, or do nothing.
		int x = e.getX();						//Get location of the user's click
		int y = e.getY();
		for(int i = 0; i < PUZZLESIZE+1; i++){	//Quick check for if the user clicked out of bounds or otherwise illegally.
			if(x == XCORNER + i*(TILESIZE+1) || y == YCORNER + i * (TILESIZE+1))
				return;}
		if(x <= XCORNER || x >= XCORNER + PUZZLESIZE * (TILESIZE)+ PUZZLESIZE || y <= YCORNER || y >= YCORNER + PUZZLESIZE * (TILESIZE)+ PUZZLESIZE)
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
		drawGridAndTiles();	//Redraw it with adjusted values
		checkForWinner();	//Check states[][] against the WINNINGBOARD[][] constant
		
}
	
	public void checkForWinner(){					//Simple method that compares current states[][] board to WINNINGBOARD constant
		boolean passTrue = true;
		for(int i = 0; i < PUZZLESIZE; i++){
			for(int j = 0; j < PUZZLESIZE; j++){
				if(WINNINGBOARD[i][j] != state[i][j])	//If ever a position is different, tell the program not to go to win-state.
					passTrue = false;
				else{									//Adds a circle on to the puzzle pieces that are in the correct spot.
					GOval confirmation = new GOval(XCORNER + j * (TILESIZE+1) + 1, YCORNER + i * (TILESIZE+1)+1, TILESIZE/(PUZZLESIZE*PUZZLESIZE), TILESIZE/(PUZZLESIZE*PUZZLESIZE));
					confirmation.setColor(colorSwitch(i + j));
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
			    blankScreen.get(j).setColor(colorGrab(j));	//Uses method colorGrab to change the colors used in the circles
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
	
	public Color colorGrab(int local){	//Used to switch up the color of the circles in winScreen
		switch(local % 6){				//Value of which circle in the list is being used looping through values of 0 to 5
		case 0: return Color.BLACK;
		case 1: return Color.BLUE;
		case 2: return Color.GREEN;
		case 3: return Color.ORANGE;
		case 4: return Color.RED;
		case 5: return Color.PINK;
		default: return Color.BLACK;
		}
	}
	
	public Color colorSwitch(int local){	//Used for swapping the color of the 'this is in the right spot!' circles
		switch(local % 4){
		case 0: return Color.GREEN;
		case 1: return Color.YELLOW;
		case 2: return Color.BLUE;
		case 3: return Color.MAGENTA;
		default: return Color.BLACK;
		}
	}
}
