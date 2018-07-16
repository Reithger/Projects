/*
 * Mac Clevinger
 * April 10, 2018
 * Assignment 3
 * Class: MandelbrotViewer
 * 
 * This class displays the Mandelbrot Set to the user, making use of the Complex class to calculate
 * the Mandelbrot Set.
 * 
 * Additional Features: Permits the user to interact with the Mandelbrot image, moving around the 
 * coordinate plane and zooming in/out. It also permits the user to view variations of the Mandelbrot
 * sequence that are artistically interesting, and save the image they are viewing to their file system.
 */

/**
 * The MandelbrotViewer program calculates the Mandelbrot Sequence and graphically displays its results in
 * the complex plane.
 * 
 * Additional Features: Permits the user to interact with the Mandelbrot image, moving around the 
 * coordinate plane and zooming in/out. It also permits the user to view variations of the Mandelbrot
 * sequence that are artistically interesting, and save the image they are viewing to their file system.
 * 
 * @author Mac Clevinger
 * @version 2.0
 * @since 2018-04-03
 */

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.*;
import java.util.*;

public class MandelbrotViewer extends Application{
																/** Constant for the X component to start (Real value) */
	private static final double START_CORNER_REAL = -2.3;		/** Constant for the Y component to start (Complex value) */	
	private static final double START_CORNER_COMPLEX = 1.8;		/** Constant for the total distance moved from one end of the screen to the other */
	private static final double SIDE_LENGTH = 3.6;			/** Constant for the number of iterations of the Mandelbrot sequence performed */		
	private final int MANDELBROT_ITERATIONS = 200;			/** Constant for the limiting value for rejecting within the Mandelbrot sequence*/ 
	private final double MANDELBROT_FAIL_VALUE = 2;			/** Constant for the size of the screen (Width and Height; square sized) */
	private final int SIZE_SCREEN = 600;					/** Constant for the rate at which zoomIn/Out should change the boundaries of the screen.*/
	private final double ZOOM_SCALE_VALUE = .80;			/** Constant for the rate at which the directional commands should move relative to screen size*/
	private final double MOVEMENT_SCALE_VALUE = 10;			
													/** Instance Variable for what the current Complex value of the top-left corner is.*/
	private static double cornerLoc_Complex;		/** Instance Variable for what the current Real value of the top-left corner is.*/
	private static double cornerLoc_Real;			/** Instance Variable for how long, in the coordinate system, each side length is.*/
	private static double sideLengthRunning;		/** Instance Variable for the object containing the image, so that it can be updated.*/
	private ImageView theSet;						/** Simple toggling value that cycles through different variations of the Mandelbrot Set.*/
	private int state;								
	
	/**
	 * The main method simply calls launch to have the start() method occur.
	 * 
	 * @param args This is the typical main method entry.
	 */
	public static void main(String[] args){
		cornerLoc_Complex = START_CORNER_COMPLEX;	//Constant values are assigned to variables that may change, but need to remember what they originall were.
		cornerLoc_Real = START_CORNER_REAL;
		sideLengthRunning = SIDE_LENGTH;
		launch(args);
	}

	/**
	 * The start method uses the JavaFX library to display the results of
	 * calculating the Color values of points in the complex plane according
	 * to the Mandelbrot Sequence.  
	 * 
	 * In addition, it also implements numerous UI elements that allow the user
	 * to move around the Mandelbrot image, zoom in/out on it, reset to a default state,
	 * calculate variations of the Mandelbrot set that are interesting to look at, and save
	 * them to your file system.
	 * 
	 * @param theStage This is a provided parameter to which objects interact to display graphics.
	 */
	
	public void start(Stage theStage){
		theStage.setTitle("Mandelbrot");
		
		Group root = new Group();
		Scene theScene = new Scene(root);
		theSet = new ImageView();							//Used approach is to make an ImageView object and assign it a WritableImage
		renderScene();										//Assigns an image to the instance variable theSet; an ImageView object
		GridPane pane = new GridPane();						//For UI and placement of buttons beside the image, several GridPanes
		GridPane subPane = new GridPane();
		GridPane secPane = new GridPane();
		
		setLocInGrid(subPane, 0, 1, 1, 1);					//Helper method written to ease the assigning of x, y location in a grid and width/height
		
		setLocInGrid(secPane, 3, 0, 3, 3);
		
		subPane.getColumnConstraints().add(new ColumnConstraints(50));	//Defines the spacing for a grid in how large its regions should be
		subPane.getColumnConstraints().add(new ColumnConstraints(50));	//Two formats, one for columns and one for rows.
		subPane.getColumnConstraints().add(new ColumnConstraints(50));	//subPane is made up of the up/down/left/right buttons (3x3 grid)
		subPane.getRowConstraints().add(new RowConstraints(50));
		subPane.getRowConstraints().add(new RowConstraints(50));
		subPane.getRowConstraints().add(new RowConstraints(50));
		subPane.getRowConstraints().add(new RowConstraints(50));
		subPane.getRowConstraints().add(new RowConstraints(50));
		
		secPane.getRowConstraints().add(new RowConstraints(50));		//secPane is composed of the rest of the buttons(8x1 grid, very tall)
		secPane.getRowConstraints().add(new RowConstraints(50));
		secPane.getRowConstraints().add(new RowConstraints(50));
		secPane.getRowConstraints().add(new RowConstraints(50));
		secPane.getRowConstraints().add(new RowConstraints(50));
		secPane.getRowConstraints().add(new RowConstraints(50));
		secPane.getRowConstraints().add(new RowConstraints(50));
		secPane.getRowConstraints().add(new RowConstraints(50));
		
		pane.getColumnConstraints().add(new ColumnConstraints(600));	//The primary pane object holds the other two GridPanes, relegating them to beside the image.
		pane.getColumnConstraints().add(new ColumnConstraints(60));
		
		setLocInGrid(theSet, 0, 0, 1, 1);
		
		Button up = new Button("Up");							//Creates the buttons, assigns them actions when pressed, then formats/places them in a gridpane
		Button right = new Button("Right");						//These four handle the movement around the coordinate system
		Button left = new Button("Left");						//Moving distance is a ratio of the current size of the region being looked at
		Button down = new Button("Down");
		
		up.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				cornerLoc_Complex += sideLengthRunning/MOVEMENT_SCALE_VALUE;
				renderScene();
			  }
		});
		down.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				cornerLoc_Complex -= sideLengthRunning/MOVEMENT_SCALE_VALUE;
				renderScene();
			  }
		});
		left.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				cornerLoc_Real -= sideLengthRunning/MOVEMENT_SCALE_VALUE;
				renderScene();
			  }
		});
		right.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				cornerLoc_Real += sideLengthRunning/MOVEMENT_SCALE_VALUE;
				renderScene();
			  }
		});
		
		up.setMinWidth(50);
		up.setMinHeight(50);
		right.setMinWidth(50);
		right.setMinHeight(50);
		left.setMinWidth(50);
		left.setMinHeight(50);
		down.setMinWidth(50);
		down.setMinHeight(50);
		
		setLocInGrid(up, 0, 1, 1, 1);
		setLocInGrid(down, 2, 1, 1, 1);
		setLocInGrid(left, 1, 0, 1, 1);
		setLocInGrid(right, 1, 2, 1, 1);

		Button zoomIn = new Button("Zoom In");				//These buttons handle the expansion/shrinking of the coordinate system
		Button zoomOut = new Button("Zoom Out");			//The sideLength is changed mutliplicatively, adjusting the real/complex corner to stay centered
		
		zoomIn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				cornerLoc_Real += (1.0 - ZOOM_SCALE_VALUE) * sideLengthRunning / 2.0;
				cornerLoc_Complex -= (1.0 - ZOOM_SCALE_VALUE) * sideLengthRunning / 2.0;
				sideLengthRunning *= ZOOM_SCALE_VALUE;
				renderScene();
			  }
		});
		
		zoomOut.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				cornerLoc_Real -= (1.0 - ZOOM_SCALE_VALUE) * sideLengthRunning / 2.0;
				cornerLoc_Complex += (1.0 - ZOOM_SCALE_VALUE) * sideLengthRunning / 2.0;
				sideLengthRunning /= ZOOM_SCALE_VALUE;
				renderScene();
			  }
		});
		
		zoomIn.setMinWidth(150);
		zoomIn.setMinHeight(50);
		zoomOut.setMinWidth(150);
		zoomOut.setMinHeight(50);
		
		setLocInGrid(zoomIn, 1, 0, 1, 1);
		setLocInGrid(zoomOut, 2, 0, 1, 1);
		
		Button reset = new Button("Reset");		//This button resets the values to their defaults as defined by constant values
		
		reset.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				cornerLoc_Complex = START_CORNER_COMPLEX;
				cornerLoc_Real = START_CORNER_REAL;
				sideLengthRunning = SIDE_LENGTH;
				state = 0;
				renderScene();
			  }
		});
		
		reset.setMinWidth(150);
		reset.setMinHeight(50);
		
		setLocInGrid(reset, 4, 0, 1, 1);
		
		Button alternate = new Button("Variation 1");			//This button switches through the alternate methods of calculating the Mandelbrot sequence
		
		alternate.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				state = (state + 1) % 3;
				alternate.setText(alternate.getText().substring(0, alternate.getText().length()-1) + (state + 1));
				renderScene();
			  }
		});
		
		alternate.setMinWidth(150);
		alternate.setMinHeight(50);
		
		setLocInGrid(alternate, 6, 0, 1, 1);
		
		Button printOut = new Button("Print Image");		//This button allows the user to save the image they are looking at, with a randomly generated name
		
		printOut.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				  Random rand = new Random();	
				  try{
					JFileChooser choose = new JFileChooser();  		//User selects the print-out location, name is randomized for convenience of mass-printing
				    choose.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
					choose.showSaveDialog(null);
			        ImageIO.write(SwingFXUtils.fromFXImage(theSet.getImage(), null), "png", new File(choose.getSelectedFile().getAbsolutePath() + "/" + rand.nextInt(999999) + ".jpg"));
				  } catch(Exception ex){ System.out.println("Error - Save Function Malfunction"); ex.printStackTrace();};
			  }
		});
		
		printOut.setMinWidth(150);
		printOut.setMinHeight(50);
		
		setLocInGrid(printOut, 7, 0, 1, 1);
		
		Button exitApplication = new Button("Exit");
		
		exitApplication.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				System.exit(0);
			}
		});
		
		exitApplication.setMinWidth(150);
		exitApplication.setMinHeight(50);
		
		setLocInGrid(exitApplication, 8, 0, 1, 1);
		
		subPane.getChildren().add(up);				//All buttons are added to their corresponding GridPanes
		subPane.getChildren().add(right);			//Then those GridPanes are added to the 'root' GridPane
		subPane.getChildren().add(left);			//Then that GridPane is added to the root, the scene is set up, and all is displayed
		subPane.getChildren().add(down);
		secPane.getChildren().add(zoomIn);
		secPane.getChildren().add(zoomOut);
		secPane.getChildren().add(reset);
		secPane.getChildren().add(alternate);		//Any alterations to the Mandelbrot image after this is performed straight to the object
		secPane.getChildren().add(printOut);		//kept as an instance variable, so any change is immediately reflected as its reference is
		secPane.getChildren().add(exitApplication);
		subPane.getChildren().add(secPane);			//maintained.
		
		pane.getChildren().add(theSet);
		pane.getChildren().add(subPane);
		
		root.getChildren().add(pane);
		theStage.setScene(theScene);
		theStage.show();
	}
	
	/**
	 * The renderScene method isolates the portion of code that recalculates the color values for
	 * each pixel in the WritableImage object that displays the Mandelbrot set. It calls on doIterations to
	 * receive a value immediately passed to pickColor, which is then assigned to the pixel; this is done
	 * for every pixel in the WritableImage via a PixelWriter.
	 * 
	 */
	
	private void renderScene(){
		WritableImage mandelbrot = new WritableImage(SIZE_SCREEN, SIZE_SCREEN);		//We draw onto the WritableImage by getting its PixelWriter
		PixelWriter draw = mandelbrot.getPixelWriter();				//Via the PixelWriter, we can access each already-defined pixel
		for(int i = 0; i < mandelbrot.getWidth(); i++){				//Each pixel can be set to a color derived from the Mandelbrot Sequence values
			for(int j = 0; j < mandelbrot.getHeight(); j++){		//The passed arguments correspond to the pixel's location on the screen with scale described by constant values
				draw.setColor(i, j, pickColor(doIterations(new Complex(cornerLoc_Real + (double)i * sideLengthRunning / (double)mandelbrot.getWidth() , cornerLoc_Complex - (double)j  * sideLengthRunning / (double)mandelbrot.getHeight()))));
			}
		}
		theSet.setImage(mandelbrot);		//The rest is adding the image to the ImageView, adding that to the root Group, finalizing our Stage, and displaying.
	}
	
	/**
	 * This helper method automates the process of defining the row, column, width, and height that
	 * an Object possesses when added to a GridPane.
	 * 
	 * @param a	This parameter is a stand in for any objects that may be added to the GridPane
	 * @param rowLoc	This parameter defines which row the object will be placed in in the GridPane
	 * @param colLoc	This parameter defines which column the object will be placed in in the GridPane
	 * @param rowDist	This parameter defines how many rows the object will fill in the GridPane
	 * @param colDist	This parameter defines how many columns the object will fill in the GridPane
	 */
	
	private void setLocInGrid(Node a, int rowLoc, int colLoc, int rowDist, int colDist){
		GridPane.setRowIndex(a, rowLoc);
		GridPane.setColumnIndex(a, colLoc);
		GridPane.setColumnSpan(a, colDist);
		GridPane.setRowSpan(a, rowDist);
	}

	/**
	 * This method performs 200 iterations of the Mandelbrot Sequence to a 
	 * starting Complex number as supplied to it as an argument. Returns
	 * the index that fails the Mandelbrot Sequence, or 200 in the event that
	 * it succeeds.
	 * 
	 * @param c This parameter is a Complex object that is used to calculate the Mandelbrot Sequence
	 * @return Returns an integer value that corresponds to when the Mandelbrot Sequence failed, or 200 if it succeeded.
	 */
	
	private int doIterations(Complex c){
		Complex copyC = new Complex(c);
		int iter = MANDELBROT_ITERATIONS;				//This is the success value; if not changed, then know that it passed.
		for(int i = 0; i < MANDELBROT_ITERATIONS; i++){
			if(copyC.modulus() > MANDELBROT_FAIL_VALUE){
				iter = i;
				break;
			}
			switch(state){
			case 0: copyC.multiply(copyC); break;		//Performs Mandelbrot Sequence if still within legal boundary
			case 1: copyC.multiplyVar1(copyC); break;
			case 2: copyC.multiplyVar2(copyC); break;
			default: copyC.multiply(copyC); break;
			}
			copyC.add(c);
		}
		return iter;
	}
	
	/**
	 * This method uses a given integer, expectedly from the doIterations method, that is
	 * used to calculate a Color which is returned to the caller; this Color is related
	 * to displaying the Mandelbrot Sequence.
	 * 
	 * @param numIter This parameter is used to decide whether the returned color should be Black (too far away) or
	 * a calculated color corresponding to the submitted value.
	 * @return This method returns a Color describing whether a coordinate is in or how near to being in the Mandelbrot Sequence.
	 */
	
	private Color pickColor(int numIter){
		if(numIter == MANDELBROT_ITERATIONS)
			return new Color(0, 0, 0, 1);		//It's just Black, the other color below uses input to derive a Color that just happens to look pretty nice. 
		return new Color(numIter/MANDELBROT_ITERATIONS, numIter/(2.0 * MANDELBROT_ITERATIONS), numIter/(MANDELBROT_ITERATIONS / 2.0) < 1 ? numIter/(MANDELBROT_ITERATIONS / 2.0) : 1, 1);
	}
	
}
