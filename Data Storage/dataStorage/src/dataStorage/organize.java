package dataStorage;

import java.awt.*;

import acm.io.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.*;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class organize extends GraphicsProgram{

	public static final int INTROX = 450;
	public static final int INTROY = 200;
	public static final int GENERATEX = 700;
	public static final int GENERATEY = 450;
	public static final int ACCESSX = 1000;
	public static final int ACCESSY = 600;
	public static final int TUTX = 725;
	public static final int TUTY = 525;
	public static final String[] TUTORIALHELP = new String[]{
			"Intro Menu:",
			"This menu gives you access to Generate and Access, as well as the tutorials for all parts of this program.",
			"Generate is used to create a new organizational scheme to store data into (create and name Categories, etc.)",
			"Generate Menu:",
			"In Generate, you will need to choose a file location on your computer to save a folder that will hold the data.",
			"You can add new Categories and SubCategories by selecting [Primary] or [Secondary] respectively. A Category will",
			"display normally in Access, while a SubCategory only displays when you examine a file in Access. Use Add Term after",
			"choosing [Primary] or [Secondary]. You can also Remove Term if you make a mistake. When you are done, select Submit Form",
			"to name your file and continue to Access. You will be able to edit these Categories later.",
			"Access Menu:",
			"If you do not arrive at Access from Generate, then when you select Access from Intro, you will need to find the [master.txt]",
			"file that was saved into the folder you named in Generate. Submit that file, and the Access screen will be displayed.",
			"In Access, you may [Add] new data, wherein you supply the contents to each Category/Subcategory that you provided in Generate.",
			"You may also [Edit] a file by supplying its index (to the left of the data item) and reviewing each aspect of the data.",
			"[Remove] will entirely remove a row of data as described by its index. [Sort] allows you to choose which Category to sort by",
			"alpha-numerically. [Search] will look for a term in the currently sorted column; if you search again and leave the field empty,",
			"it will return to normal. [Switch] will open the file browser to select a new organizational scheme (or [master file]).",
			"If you have over 20 rows of data, you can use the [Prev] and [Next] buttons to flip through pages of data.",
			"In addition, you can use [Add Category], [Remove Category], and [Edit Category] along the bottom of the screen to adjust the",
			"Categories and Subcategories you set down in Generate. If you [Click] on a row, it will open a new window to display all of its",
			"information, including the otherwise invisible Subcategories.",
			"",
			"While you can find the created files in your file explorer alongside the master.txt, please do not alter their contents.",
			"If you come across any crashes, glitches, design concerns, or have an idea for how to make this better, let me know at",
			"www.menagerieofmiscellaneous.weebly.com",
			"Enjoy!"
	};
	
	private int state;
	private int accPage;
	private int primSecState;
	private int numPerPage;
	private int sortColumn;
	private boolean typing;
	private String toSearch;
	private File master;
	private String fileLocation;
	private ArrayList<GRect> buttons;
	private ArrayList<String> Categories;
	private ArrayList<String> subCategories;
	private ArrayList<ArrayList<String>> Display;
	
	public static void main(String[] args){
		organize name = new organize();
		name.start(args);
	}
	
	/* 
		More tutorial. - UPDATE AS NEEDED
		Primary/Secondary Category options; Primary shows on main page, Secondary only in detailed window option. - DONE
		Add/Edit/Remove Category Listings with update to all sub-files. - DONE
		
		Save images in files.
		Customize screen size, number of files on-screen, font size. -
	*/
	
	//Note: Ctrl-Shift-KeyPad/ Collapses all, Ctrl-Shift-Keypad* open.
	
	public void run(){	//Start the program into the main screen of choosing Generate or Access options.
		initialize();
		initIntro();
		addMouseListeners();
		addKeyListeners();
	}
	
	public void initialize(){	//Method to do first-running variable settings.
		state = 0;
	}
	
	public void initIntro(){	//Changes the program window into the beginning mode for the Intro screen.
		buttons = new ArrayList<GRect>();
		buttons.clear();
		int buffer = 20;
		setSize(INTROX+17 + buffer*2, INTROY+60 + buffer*2 + 5);
		removeAll();
		String[] terms = new String[]{"Generate", "Access", "Create a new organizational file", "Select a master file to view data", "g", "a", "?", ""};
		for(int i = 0; i < 2; i++){
		  displayText(buffer, buffer + INTROY/2*i, INTROX, INTROY/3, terms[i]);
		  displayText(buffer, INTROY/2*i + buffer + INTROY/4, INTROX, INTROY/3, terms[i+2]);	
		  displayText(buffer, buffer + INTROY/2*i, INTROX/16, INTROY/8, terms[i+4]);	
		  displayText(buffer, buffer + INTROY+INTROY/2*i, INTROX/16, INTROY/8, terms[i+6]);
		}
		buttons.add(new GRect(buffer, buffer, INTROX, INTROY/2));
		buttons.add(new GRect(buffer, INTROY/2 + buffer, INTROX, INTROY/2));
		GRect rect = new GRect(buffer,buffer,INTROX, INTROY);
		GLine line = new GLine(buffer, INTROY/2+buffer, INTROX + buffer, INTROY/2 + buffer);
		add(rect);
		add(line);
	}
	
	public void initGenerate(){	//Changes the program window into the beginning mode for the Generate screen.
		Categories = new ArrayList<String>();
		subCategories = new ArrayList<String>();
		primSecState = 0;
		drawGenerate();
	}
	
	public void initAccess(){	//Changes the program window into the beginning mode for the Access screen.
		buttons.clear();
		ArrayList<ArrayList<String>> pullFromMaster = new ArrayList<ArrayList<String>>();
		Categories = new ArrayList<String>();
		subCategories = new ArrayList<String>();
		Display = new ArrayList<ArrayList<String>>();
		accPage = 1;
		sortColumn = 0;
		numPerPage = 22;	//Actual displayed file is amount - 2; 1 for category headings, 1 thrown away.
		toSearch = "";
		if(fileLocation == null){
			  JFileChooser chooser = new JFileChooser();
			  chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
			  chooser.showSaveDialog( null );
			  fileLocation = chooser.getSelectedFile().getAbsolutePath();
			}
		master = new File(fileLocation);
		pullFromMaster = pullMultiLineContents(master);
		if(pullFromMaster.size() == 1){
			String temp = "";
			for(int i = 0; i < pullFromMaster.get(0).size(); i++){
				temp += pullFromMaster.get(0).get(i)+ "~";
			}
			temp += "\n";
			editFile(temp, master);
			pullFromMaster = pullMultiLineContents(master);
		}
		Categories = new ArrayList<String>(pullFromMaster.get(0));
		if(pullFromMaster.size() != 1)
		  subCategories = new ArrayList<String>(pullFromMaster.get(1));
		else
		  subCategories = new ArrayList<String>();
		int trueSize = master.getParentFile().listFiles().length;
		if(Integer.parseInt(Categories.get(Categories.size()-1)) != trueSize-1){
		  String replacement = "";
		  for(int i = 0; i < Categories.size()-1; i++){
			  replacement += Categories.get(i) + "~";
		  }
		  replacement += trueSize + "\n";
		  for(int i = 0; i < subCategories.size(); i++){
			  replacement += subCategories.get(i) + "~";
		  }
		  editFile(replacement, master);
		}
		int foundFiles = 0, currPlace = 0;
		while(foundFiles != trueSize-1){		//CHANGE THIS - trueSize can not be how many files are in the area; search through for ??? Figure it out.
			File test = new File(master.getParent() + "//" + currPlace + ".txt");
			if(test.exists()){
			  currPlace++;
			  foundFiles++;
			}
			else{
			  fixFileGap(currPlace);
			}
		}
		  
		fillDisplay();
		removeAll();
		setSize(ACCESSX + 17, ACCESSY + 60);
		drawAccess();
	}

	public void mouseClicked(MouseEvent e){	//Method for user click calls three different methods based on which Intro/Generate/Access you're in
		switch(state){
		  case 0: mousePressIntro(e); break;
		  case 1: mousePressGenerate(e); break;
		  case 2: mousePressAccess(e); break;
		  default: break;
		}
	}
	
	public void keyPressed(KeyEvent e){		//Switch{} case: for directing user Key Presses in Intro/Generate/Access
		switch(state){
		  case 0: keyPressIntro(e); break;
		  case 1: keyPressGenerate(e); break;
		  case 2: keyPressAccess(e); break;
		  default: break;
		}
	}
	
	public void keyPressIntro(KeyEvent e){	//If user does a Key Press in Intro screen, what do?
		switch(e.getKeyChar()){ 
		  case 'g': state = 1; stateChange(); break;
		  case 'a': state = 2; stateChange(); break;
		  case '?':	showTutorial(); break; 
		  default: break;
		}
	}
	
	public void keyPressGenerate(KeyEvent e){	//If user does a Key Press in Generate screen, what do?
		switch(e.getKeyChar()){
		  case '?': generateSelect(); break;
		  case '+': generateAdd(); break;
		  case '-': generateRemove(); break;
		  case '*': generateSubmit(); break;
		  default: break;
		}
		drawGenerate();
	}
	
	public void keyPressAccess(KeyEvent e){		//If user does a Key Press in Access screen, what do?
		switch(e.getKeyChar()){
		  case '+': accessAdd(); break;
		  case '*': accessEdit(); break;
		  case '-': accessRemove(); break;
		  case '^': accessSort(); break;
		  case '?': accessSearch(); break;
		  case '/': accessSwitch(); break;
		  case '<': accessPrev(); break;
		  case '>': accessNext(); break;
		  default: break;
		}
		drawAccess();
	}
	
	public void mousePressIntro(MouseEvent e){	//Simple matter of clicking one of two options at the Intro screen to transfer to Generate/Access
		for(int i = 0; i < buttons.size(); i++){
			if(clickObject(buttons.get(i), e)){
				state = i + 1;
				break;
			}
		}
		stateChange();
	}
	
	public void mousePressGenerate(MouseEvent e){	//What happens when you mouse click during the Generate screen.
		int genState = -1;
		for(int i = 0; i < buttons.size(); i++){
			if(clickObject(buttons.get(i), e)){
			  genState = i;
			  break;}
		}
		switch(genState){
		case 0:		
		  generateSelect();
	      break;
		case 1:	
		  generateAdd();
		  break;
		case 2:
		  generateRemove(); 
		  break;
		case 3:
		  generateSubmit();
		  return;
		case 4:
		  generateBack();
		  return;
		case 5:
		  primSecState = 0;
		  break;
		case 6:
		  primSecState = 1;
		  break;
		default:  
		  break;
		}
		drawGenerate();
	}

	public void generateSelect(){	//If user input causes Select option, allow user to select a new File Location through the window explorer.
	    JFileChooser chooser = new JFileChooser();
	    chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
	    chooser.showSaveDialog( null );
	    fileLocation = chooser.getSelectedFile().getAbsolutePath();
	}
	
	public void generateAdd(){		//If user input causes Add option, allow user to add terms to the Categories Array List for saving in Master.
		IODialog dialogStr = new IODialog();
		if(primSecState == 0)
		  Categories.add(dialogStr.readLine("Enter term:"));
		else
		  subCategories.add(dialogStr.readLine("Enter term:"));
	}
	
	public void generateRemove(){	//If user input causes Remove option, allow user to remove terms from the display by numerical indexing.
		IODialog dialogInt = new IODialog();
		int toRemove = dialogInt.readInt("Submit the number (1 at top, " + Categories.size() + " at bottom) to be removed") - 1;
		if(primSecState == 0 && toRemove < Categories.size())
		  Categories.remove(toRemove);
		else if(toRemove < subCategories.size())
		  subCategories.remove(toRemove);
		else
		  dialogInt.readLine("That value does not exist. Please check which category is selected and try again.");
	}
	
	public void generateSubmit(){	//If user input causes Submit option, take Categories and create a Master file at the given File Location. Move to Access.
		if(fileLocation == null){
		  IODialog dialogBrk = new IODialog();
		  dialogBrk.readLine("No file path given; please Select File Location. Press OK to continue.");
		  return;}
		IODialog dialogGive = new IODialog();
		String name = dialogGive.readLine("Please name your folder");
		String toCopyPrim = "", toCopySec = "";
		for(int i = 0; i < Categories.size(); i++)
		  toCopyPrim += Categories.get(i) + "~";
		toCopyPrim += "0";
		for(int i = 0; i < subCategories.size(); i++)
		  toCopySec += subCategories.get(i) + "~";
		  try{
			File fold = new File(fileLocation + "//" + name);
			fold.mkdir();
			File f = new File(fold.getAbsolutePath() + "//master.txt");
			FileOutputStream stream = new FileOutputStream(f);
			OutputStreamWriter writer = new OutputStreamWriter(stream);
			Writer w = new BufferedWriter(writer);
			w.write(toCopyPrim + "\n" + toCopySec);
			w.close();
		  } catch(IOException e13){
			System.err.println("Problem writing to the file");
		  }
		state = 2;
		fileLocation = fileLocation + "//" + name + "//master.txt";
		stateChange();
	}
	
	public void generateBack(){		//If user input causes Back option, remove the user to the Intro screen.
		removeAll();
		fileLocation = null;
		state = 0;
		initIntro();
	}
	
	public void mousePressAccess(MouseEvent e){		//What happens when you mouse click during the Access screen.
		int accState = -1;
		master = new File(fileLocation);
		for(int i = 0; i < buttons.size(); i++){
		  if(clickObject(buttons.get(i), e)){
			  accState = i;}}
		switch(accState){
		  case 0:					//Add files according to naming convention; update master to reflect number of files
			accessAdd();
		    break;
		  case 1:					//Edit a requested file by confirming each point of information; in future, make it possible to specify?
			accessEdit();
			break;
		  case 2:					//Remove a file - Needs to update all numbering and the master file
			accessRemove();
			break;
		  case 3:							//Sort the Display arrayList alphabetically or numerically based on one column from Categories.
			accessSort();
			break;
		  case 4: 						//Search the Display arrayList for terms, display based on relevance.	THE NEXT ONE TO CODE!
			accessSearch();
			break;
		  case 5:			  				//Change file directory.
			accessSwitch();
			return;
		  case 6:	//Next page
			accessPrev();
			break;
		  case 7:	//Previous page
			accessNext();
			break;
		  case 8:	//Return to Intro
			accessBack();
			return;
		  case 9:	//Add Category
			  accessAddCat();
			  break;
		  case 10:	//Remove Category
			  accessRemCat();
			  break;
		  case 11:	//Edit Category
			  accessEditCat();
			  break;
		  default: break;
		}
		if(e.getX() > ACCESSX/30 && e.getX() < ACCESSX - ACCESSX/30 && e.getY() > ACCESSY/5 && e.getY() < ACCESSY*4/5){
		  int displayVal = (int)((e.getY() - ACCESSY/5.0) / (ACCESSY* 3.0 / 5.0 / (numPerPage-1)));
		  if(displayVal != 0)
			displayInfoWindow(displayVal - 1 + (accPage-1)*(numPerPage-2));
		}
		drawAccess();
	}

	public void accessAdd(){	//If user input causes Add option, allow user to submit items for a new item for each category.
		IODialog dialog = new IODialog();
		String putInFile = "", replaceMaster = "", temp = "";
	    for(int i = 0; i < Categories.size()-1; i++){
	    	temp = dialog.readLine("What is the: " + Categories.get(i) + " information for this file?") + "~";
	    	while(temp.equals("~"))
	    	  temp = dialog.readLine("Please do not leave the field empty. What is the: " + Categories.get(i) + " information for this file?") + "~";
	    	putInFile += temp;
	    	replaceMaster += Categories.get(i) + "~";
	    }
	    putInFile += Categories.get(Categories.size()-1) + "\n";
	    replaceMaster += (Integer.parseInt(Categories.get(Categories.size()-1))+1) + "\n";
	    for(int i = 0; i < subCategories.size(); i++){
	    	temp = dialog.readLine("What is the: " + subCategories.get(i) + " information for this file?") + "~";
	    	while(temp.equals("~"))
	    	  temp = dialog.readLine("Please do not leave the field empty. What is the: " + subCategories.get(i) + " information for this file?") + "~";
	    	putInFile += temp;
	    	replaceMaster += subCategories.get(i) + "~";
	    }
	    addFile(putInFile, replaceMaster);
		Categories = new ArrayList<String>(pullMultiLineContents(master).get(0));
		if(pullMultiLineContents(master).size() != 1)
		  subCategories = new ArrayList<String>(pullMultiLineContents(master).get(1));
		else
		  subCategories = new ArrayList<String>();
	    fillDisplay();
	    removeAll();
	}
	
	public void accessEdit(){	//If user input causes Edit option, allow user to review all information of given file information and replace if wanted.
		IODialog dialog = new IODialog();
		int page = (dialog.readInt("Which file would you like to edit? Values are beside the file information.")) - 1;
		page = actualFileIndex(page);
		String replaceFile = "", toAdd;
		String fileToGrab = master.getParent() + "//" + Integer.toString(page) + ".txt";
		File f = new File(fileToGrab);
		ArrayList<ArrayList<String>> originalFile = pullMultiLineContents(f);
		for(int i = 0; i < Categories.size()-1; i++){
			toAdd = dialog.readLine("What would you like to replace: '" + originalFile.get(0).get(i) + "' with? Leave the space blank if no changes are wished.");
			if(toAdd.length() < 1)
			  replaceFile += originalFile.get(0).get(i) + "~";
			else
			  replaceFile += toAdd + "~";
			toAdd = null;
		}
		replaceFile += originalFile.get(0).get(originalFile.get(0).size()-1) + "\n";
		for(int i = 0; i < subCategories.size(); i++){
			toAdd = dialog.readLine("What would you like to replace: '" + originalFile.get(1).get(i) + "' with? Leave the space blank if no changes are wished.");
			if(toAdd.length() < 1)
			  replaceFile += originalFile.get(1).get(i) + "~";
			else
			  replaceFile += toAdd + "~";
			toAdd = null;
		}
		editFile(replaceFile, f);
		fillDisplay();
		removeAll();
	}
	
	public void accessRemove(){	//If user input causes Remove option, allow user to select numerical value of file to be removed.
		IODialog dialog = new IODialog();
		int toRemove = (dialog.readInt("Which file would you like to delete? Numerical value of the file is beside the file information.")) - 1;
		toRemove = actualFileIndex(toRemove);
		ArrayList<ArrayList<String>> confirmContent = pullMultiLineContents(new File(master.getParent() + "//" + toRemove + ".txt"));
		String confirm = dialog.readLine("Please confirm you wish to delete the file beginning with " + confirmContent.get(0).get(0) + ". Type anything and submit to confirm, or leave the field blank to not delete the file.");
		if(confirm.equals(""))
		  return;
		fixFileGap(toRemove);
		fillDisplay();
		removeAll();
	}
	
	public void accessSort(){	//If user input causes Sort option, allow user to select numerical value of which category to rearrange the data by.
		IODialog dialog = new IODialog();
		if(Categories.size() == 0)
		  accessSwitch();
		int newSort = dialog.readInt("Which category of information would you like to sort by? Enter its numeric value; leftmost column is 1, rightmost " + Integer.toString(Categories.size()-1) + ".");
		while(newSort > Categories.size()-1 || newSort < 0)
		  newSort = dialog.readInt("Illegal value submitted. Please enter a value between 1 and " + Integer.toString(Categories.size()-1));
		sortColumn = newSort - 1;
		fillDisplay();
		removeAll();
	}
	
	public void accessSearch(){	//If user input causes Search option, allow user to input a String and only display items that match the String.
		IODialog dialog = new IODialog();
		toSearch = dialog.readLine("What term would you like to search for?");
		fillDisplay();
		removeAll();
	}
	
	public void accessSwitch(){	//If user input causes Switch option, allow user to navigate file system and select new Master file.
		fileLocation = null;
		Display.clear();
		removeAll();
		drawAccess();
		initAccess();
	}
	
	public void accessPrev(){	//If user input causes Prev option, allow user to reduce value of accPage to show different items from file.
		if(accPage > 1){
		  accPage--;
		  removeAll();
		  drawAccess();}
	}
	
	public void accessNext(){	//If user input causes Next option, allow user to increase value of accPage to show different items from file.
		if(Integer.parseInt(Categories.get(Categories.size()-1)) > (numPerPage-2) * accPage){
		  accPage++;
		  removeAll();
		  drawAccess();}
	}
	
	public void accessBack(){	//If user input causes Back option, allow user to go back to the Intro screen.
		removeAll();
		fileLocation = null;
		state = 0;
		initIntro();
	}

	public void accessAddCat(){
		IODialog dialog = new IODialog();
		int subCat = dialog.readInt("If you are adding a new Category, submit '0'; if you are adding a new Sub-Category, submit '1'");
		while(subCat != 0 && subCat != 1){
			subCat = dialog.readInt("Please submit a '0' if you wish to add a new Category, or a '1' if you are adding a new Sub-Category.");
		}
		String placer = dialog.readLine("Type the name of the Category listing that you want your new Category to come after; type 'First' to put it at the beginning.");
		int indCat = -1;
		if(placer.equals("First"))
			indCat = 0;
		else{
		  if(subCat == 0){
			for(int i = 0; i < Categories.size(); i++){
				if(placer.equals(Categories.get(i)))
					indCat = i+1;
			}}
		  else{
			for(int i = 0; i < subCategories.size(); i++){
				if(placer.equals(subCategories.get(i)))
					indCat = i+1;}	  
		  }
		}
		if (indCat == -1){
			dialog.readLine("The Category given was not found; please try again.");
			return;
		}
		placer = dialog.readLine("Please type the name of the new Category. Leave this space blank if you do not want to add a new Category Listing.");
		if(placer.equals("")){
			return;
		}
		ArrayList<ArrayList<String>> masterContents = pullMultiLineContents(master);
		String toRep = "";
		for(int i = 0; i < masterContents.size(); i++){
			for(int j = 0; j < masterContents.get(i).size(); j++){
				if(i == subCat && j == indCat)
					toRep += placer + "~";
				toRep += masterContents.get(i).get(j) + "~";
			}
			if(i == 0)
			  toRep += "\n";
		}
		if(masterContents.get(1).size() == 0)
			toRep += placer + "~";
		editFile(toRep, master);
		placer = dialog.readLine("Please give a default value to set all data items as for this Category. You may leave this blank.");
		for(int i = 0; i < Integer.parseInt(Categories.get(Categories.size()-1)); i++){
			toRep = "";
			masterContents = pullMultiLineContents(new File(master.getParent() + "//" + (i) + ".txt"));
			for(int j = 0; j < masterContents.size(); j++){
				for(int k = 0; k < masterContents.get(j).size(); k++){
					if(j == subCat && k == indCat)
						toRep += placer + "~";
					toRep += masterContents.get(j).get(k) + "~";
				}
				if(masterContents.get(j).size() == 0)
					toRep += placer + "~";
				if(j == 0)
				  toRep += "\n";
			}
			editFile(toRep, new File(master.getParent() + "//" + (i) + ".txt"));
		}
		initAccess();
	}
	
	public void accessRemCat(){
		IODialog dialog = new IODialog();
		int subCat = dialog.readInt("If you are removing a Category, submit '0'; if you are removing a Sub-Category, submit '1'");
		String placer = dialog.readLine("Please give the exact name of the Category you would like to remove.");
		String toRep = "";
		ArrayList<ArrayList<String>> contents = pullMultiLineContents(master);
		int remCatInd = -1;
		for(int i = 0; i < contents.size(); i++){
			for(int j = 0; j < contents.get(i).size(); j++){
				if(i == subCat && contents.get(i).get(j).equals(placer)){
					remCatInd = j;
				}
				else{
					toRep += contents.get(i).get(j) + "~";
				}
			}
			if(i == 0)
			  toRep += "\n";
		}
		editFile(toRep, master);
		for(int i = 0; i < Integer.parseInt(Categories.get(Categories.size()-1)); i++){
			toRep = "";
			contents = pullMultiLineContents(new File(master.getParent() + "//" + (i) + ".txt"));
			for(int j = 0; j < contents.size(); j++){
				for(int k = 0; k < contents.get(j).size(); k++){
					if(j == subCat && k == remCatInd)
					  continue;
					else if(!contents.get(j).get(k).equals(""))
					  toRep += contents.get(j).get(k) + "~";
				}
				if(j == 0)
				  toRep += "\n";
			}
			editFile(toRep, new File(master.getParent() + "//" + (i) + ".txt"));
		}
		initAccess();
	}
	
	public void accessEditCat(){
		IODialog dialog = new IODialog();
		int subCat = dialog.readInt("If you would like to edit a Category, submit '0'; if you would like to edit a Sub-Category, submit '1'");
		while(subCat != 0 && subCat != 1){
			subCat = dialog.readInt("Please submit '0' to edit a Category, or '1' to edit a Sub-Category.");
		}
		String placer = dialog.readLine("Please give the exact name of the Category that you would like to change the name of.");
		String replace = dialog.readLine("What is the new name of the Category you would like?");
		String toRep = "";
		ArrayList<ArrayList<String>> contents = pullMultiLineContents(master);
		for(int i = 0; i < contents.size(); i++){
			for(int j = 0; j < contents.get(i).size(); j++){
				if(i == subCat && contents.get(i).get(j).equals(placer))
					toRep += replace + "~";
				else
					toRep += contents.get(i).get(j) + "~";
			}
			toRep += "\n";
		}
		editFile(toRep, master);
		initAccess();
	}
	
	public void addFile(String putInFile, String replaceMaster){	//One string to build the file, the other is an edit to the master to reflect new number of files.
	    File f = new File(master.getParent() + "//" + Categories.get(Categories.size()-1) + ".txt");
	    editFile(putInFile, f);
	    editFile(replaceMaster, master);
	}
	
	public void editFile(String replaceFile, File toEdit){		//Given a String to replace the contents of a given File, edits the contents of the given file.
		try{
			FileOutputStream stream = new FileOutputStream(toEdit);
			OutputStreamWriter write = new OutputStreamWriter(stream);
			Writer w = new BufferedWriter(write);
			w.write(replaceFile);
			w.close();
		} catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public ArrayList<String> pullContents(File f){		//Returns an ArrayList holding the String information of any given file, separated by "~"
		Scanner sc;
		String[] toReturn = new String[0];
		ArrayList<String> returnList = new ArrayList<String>();
		try {
			sc = new Scanner(f);
			toReturn = sc.nextLine().split("~");
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < toReturn.length; i++)
		  returnList.add(toReturn[i]);
		return returnList;
	}
	
	public ArrayList<ArrayList<String>> pullMultiLineContents(File f){	//Returns an ArrayList holding the ArrayLists holding String information of the file.
		Scanner sc;
		String[] toReturn;
		ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
		ArrayList<String> oneLine = new ArrayList<String>();
		try{
			sc = new Scanner(f);
			while(sc.hasNextLine()){
			  toReturn = sc.nextLine().split("~");
			  for(int i = 0; i < toReturn.length; i++)
				oneLine.add(toReturn[i]);
			  returnList.add(new ArrayList<String>(oneLine));
			  oneLine.clear();
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		if(returnList.size() == 1)
			returnList.add(new ArrayList<String>());
		return(returnList);
	}
	
	public void fixFileGap(int fileNum){		//If user moves/deletes files, this closes the hole and deletes the off-hanging file.
		int numFile = Integer.parseInt(Categories.get(Categories.size()-1)) - 1;
		String replaceFile = "";
		for(int i = fileNum; i < numFile; i++){
			replaceFile = "";
			ArrayList<ArrayList<String>> fileCont = pullMultiLineContents(new File(master.getParent() + "//" + (i+1) + ".txt"));
			for(int j = 0; j < Categories.size()-1; j++)
			  replaceFile += fileCont.get(0).get(j) + "~";
			replaceFile += (Integer.parseInt(fileCont.get(0).get(fileCont.get(0).size()-1))-1) + "\n";
			for(int j = 1; j < fileCont.size(); j++){
			  for(int k = 0; k < fileCont.get(j).size(); k++){
				  replaceFile += fileCont.get(j).get(k);
			  }
			  replaceFile += "\n";
			}
			editFile(replaceFile, new File(master.getParent() + "//" + i + ".txt"));
		}
		File removal = new File(master.getParent() + "//" + numFile + ".txt");
		removal.delete();
		String replaceMaster = "";
		for(int i = 0; i < Categories.size()-1; i++){
			replaceMaster += Categories.get(i) +"~";
		}
		replaceMaster += (Integer.parseInt(Categories.get(Categories.size()-1))-1) + "\n";
		for(int i = 0; i < subCategories.size(); i++){
			replaceMaster += subCategories.get(i) + "~";
		}
		editFile(replaceMaster, master);
		Categories = new ArrayList<String>(pullMultiLineContents(master).get(0));
		if(pullMultiLineContents(master).size() != 1)
		  subCategories = new ArrayList<String>(pullMultiLineContents(master).get(1));
		else
		  subCategories = new ArrayList<String>();
	}
	
	public void drawGenerate(){					//Draws the Generate screen.
		removeAll();
		int boxW = 120;
		int boxH = 60;
		buttons.clear();
		setSize(GENERATEX + 17, GENERATEY + 60);
		String[] terms = new String[]{"Select File~Location", "Add~Term", "Remove~Term", "Submit~Form", "Prev~Screen"};
		String[] keyTerms = new String[]{"?", "+", "-", "*", " "};
		for(int i = 0; i < terms.length; i++){		//Creates buttons for user
		  GRect rect = new GRect((GENERATEX/2 - boxW)/8, GENERATEY/12 + ((double)i/terms.length)*(GENERATEY - boxH - GENERATEY/12), boxW, boxH); 
		  add(rect);
		  buttons.add(rect);
		  displayText((int)rect.getX(), (int)rect.getY(), boxW, boxH, terms[i]);
		  displayText((int)rect.getX() - boxW/32, (int)rect.getY() + boxH - boxH/2, boxW/4, boxH/2, keyTerms[i]);
		}
		GRect rect = new GRect(GENERATEX/3 - boxW/3, GENERATEY/12 + boxH, GENERATEX/4, GENERATEY - GENERATEY/6 - boxH*3/2);	//Shows the Primary Categories
		add(rect);
		buttons.add(rect);
		displayText((int)rect.getX(), (int)rect.getY() - boxH, (int)rect.getWidth(), (int)boxH, "Primary");
		String temp = "";
		for(int i = 0; i < Categories.size(); i++){
			temp += Categories.get(i) + "~";
		}
		displayText((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight(), temp);
		if(primSecState == 0)
		  add(new GRect(rect.getX(), rect.getY() - boxH, rect.getWidth(), boxH));
		rect = new GRect(GENERATEX/3 + GENERATEX /3 - boxW/3, GENERATEY/12 + boxH, GENERATEX/4, GENERATEY-GENERATEY/6 - boxH*3/2);	//Shows the Secondary Categories
		add(rect);
		buttons.add(rect);
		displayText((int)rect.getX(), (int)rect.getY() - boxH, (int)rect.getWidth(), (int)boxH, "Secondary");
		temp = "";
		for(int i = 0; i < subCategories.size(); i++){		//Shows the submitted terms in Categories
			temp += subCategories.get(i) + "~";
		}
		displayText((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight(), temp);
		if(primSecState == 1)
		  add(new GRect(rect.getX(), rect.getY() - boxH, rect.getWidth(), boxH));
	}
	
	public void drawAccess(){					//Draws the Access page with updated information for user convenience	-- termsBot[] work/buttons needs redesign.
		String[] terms = new String[]{"Add", "Edit", "Remove", "Sort", "Search", "Switch", "Prev", "Next"};
		String[] termsBot = new String[]{"Intro~Screen", "Add~Category", "Remove~Category", "Edit~Category"};
		String[] keyTerms = new String[]{"+", "*", "-", "^", "?", "/", "<", ">"};
		buttons.clear();
		int boxW = 90;
		int boxH = 35;
		GRect rect;
		GLabel label;
		for(int i = 0; i < terms.length; i++){	//Top row buttons
			rect = new GRect(ACCESSX/30 + ((double)i/terms.length) * (ACCESSX - ACCESSX/30), ACCESSY/30, boxW, boxH);
			buttons.add(rect);
			add(rect);
			displayText((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight(), terms[i]);
			displayText((int)rect.getX() + boxW/4, (int)rect.getY() + boxH *5 / 4, boxW/2, boxH*2/3, keyTerms[i]);
		}
		for(int i = 0; i < termsBot.length; i++){	//Bottom row buttons
		  rect = new GRect(ACCESSX/30 + ((double)i/terms.length) * (ACCESSX - ACCESSX/30), ACCESSY*4/5 + boxH/2, boxW, boxH);
		  add(rect);
		  buttons.add(rect);
		  displayText((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight(), termsBot[i]);
		}
		displayText((ACCESSX-boxW)/2, ACCESSY/30 + boxH * 3 / 2, boxW, boxH, accPage + "/" + ((Display.size()-1)/20+1));
		if(!toSearch.equals(""))
			displayText(ACCESSX/2 + boxW*3/2, ACCESSY-ACCESSY/30 - boxH * 2, boxW*4, boxH, "Search Term: '" + toSearch +"'.");
		Font font = new Font("Serif", Font.BOLD, 12);
		int spacing = (ACCESSX-ACCESSX/20)/(Categories.size()-1);
		for(int i = 0; i < Categories.size()-1; i++){		//List of categories
		  rect = new GRect(ACCESSX/40, ACCESSY/5, i*spacing, ACCESSY*3/5);
		  add(rect);
		  label = new GLabel(Categories.get(i), 0, ACCESSY/5 + font.getSize());
		  label.setFont(font);
		  label.setLocation(ACCESSX/40 + i*spacing + (spacing-label.getWidth())/2, label.getY());
		  add(label);
		}
		for(int i = 0; i < numPerPage; i++){				//The lines that make the grid for the page
		  rect = new GRect(ACCESSX/40, ACCESSY/5, ACCESSX-ACCESSX/20, i*(ACCESSY*3/5)/(numPerPage-1));
		  add(rect);
		}
		for(int i = (accPage-1)*(numPerPage-2); i < (accPage * (numPerPage-2)); i++){	//Displaying the file information
		  if(i > Display.size()-1)
		    break;
		  label = new GLabel(Integer.toString(i+1), 0, 0);
		  label.setFont(font);
		  label.setLocation(ACCESSX/40 - label.getWidth() - 5, ACCESSY/5 + ((i%(numPerPage-2))+2)*(ACCESSY*3/5)/(numPerPage-1) - 5);
		  add(label);
		  for(int j = 0; j < Categories.size()-1; j++){
			  label = new GLabel(Display.get(i).get(j), ACCESSX/40 + spacing*j + 5, ACCESSY/5 + ((i%(numPerPage-2))+2)*(ACCESSY*3/5)/(numPerPage-1) - 5);
			  label.setFont(font);
			  if(label.getWidth() > (ACCESSX-ACCESSX/20)/(Categories.size()-1)){
			    while(label.getWidth() > (ACCESSX-ACCESSX/20)/(Categories.size()-1) - font.getSize()){
				  label.setLabel(label.getLabel().substring(0, label.getLabel().length()-3));}
			    label.setLabel(label.getLabel() + "...");}
			  add(label);
		  }
		}
	}
	
	public void stateChange(){					//Calls initializing methods based on what state the program is in.
		switch(state){
		  case 0: initIntro(); break;
		  case 1: initGenerate(); break;
		  case 2: initAccess(); break;
		  default: break;
		}
	}
	
	public void fillDisplay(){					//Fills arrayList Display to show the user
		Display.clear();
		ArrayList<String> toDisplay = new ArrayList<String>();
		master = new File(fileLocation);
		for(int i = 0; i < Integer.parseInt(Categories.get(Categories.size()-1)); i++){	//Loops through all files to display to user and puts contents in Display
		  toDisplay = new ArrayList<String>(pullMultiLineContents(new File(master.getParent() + "//" + i + ".txt")).get(0));			
		  if(toDisplay.get(sortColumn).indexOf(toSearch) != -1)
		    Display.add(new ArrayList<String>(toDisplay));}
		sortDisplay();
	}

	public void sortDisplay(){					//The really important one for user convenience
		ArrayList<ArrayList<String>> sorted = new ArrayList<ArrayList<String>>();
		ArrayList<String> single = new ArrayList<String>();
		for(int i = 0; i < Display.size(); i++)
			single.add(Display.get(i).get(sortColumn));
		Collections.sort(single);
		int location = 0;
		for(int i = 0; i < single.size(); i++){
		  for(int j = 0; j < single.size(); j++){
		    if(single.get(i).equals(Display.get(j).get(sortColumn))){
		      location = j;}
		  }
		  sorted.add(new ArrayList<String>(Display.get(location)));
	      Display.get(location).set(sortColumn, "!@#$%^&*()");
		}
		
		Display = new ArrayList<ArrayList<String>>(sorted);
	}
	
	public void displayText(int x, int y, int width, int height, String message){  //It sodding works. Displays text and resizes for space; uses '~' for line breaking.
		GLabel label;
		Font font = new Font("Serif", Font.BOLD, 2);
		String[] terms = message.split("~");
		int longest = 0;
		for(int i = 0; i < terms.length; i++){
		  if(terms[i].length() > terms[longest].length())
			longest = i;}
		label = new GLabel(terms[longest]);
		while(label.getWidth() < width - (width - label.getWidth())/2 && label.getHeight() * terms.length < height - (height - ((int)label.getHeight() * terms.length))/2){
			font = new Font("Serif", Font.BOLD, font.getSize() + 1);
			label.setFont(font);
		}
		font = new Font("Serif" , Font.BOLD, font.getSize() - 2);
		label.setFont(font);
		int totHeight = (int)((height - (label.getHeight() * terms.length))/2) + (int)label.getAscent();	//Note: .getAscent() and .getHeight() are different; use the former.
		for(int i = 0; i < terms.length; i++){
		  label = new GLabel(terms[i], 0, 0);
		  label.setFont(font);
		  label.setLocation(x + (width - label.getWidth())/2, y + totHeight);
		  totHeight += label.getHeight();
		  add(label);}
	}

	public void typeSpace(int x, int y, int width, int height, String typed){		//Should offer whitespace for user to enter a lot of text.
		add(new GRect(x, y, width, height));
		
	}

	public void displayTextGrand(int x, int y, int width, int height, String message){	//Displays text without needing a '~' for line breaking
		
	}

	public void displayInfoWindow(int displayIndex){	//If user clicks on an item, opens window to display all info.
		ArrayList<ArrayList<String>> toFillWindow = pullMultiLineContents(new File(master.getParent() + "\\" + actualFileIndex(displayIndex) + ".txt"));
		if(toFillWindow.size() == 1){
			String temp = "";
			for(int i = 0; i < toFillWindow.get(0).size(); i++)
			  temp += toFillWindow.get(0).get(i) + "~";
			temp += "\n";
			editFile(temp, new File(master.getParent() + "\\" + actualFileIndex(displayIndex) + ".txt"));
			toFillWindow = pullMultiLineContents(new File(master.getParent() + "\\" + actualFileIndex(displayIndex) + ".txt"));
		}
		Font font = new Font("Serif", Font.BOLD, 16);
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame windowFile = new JFrame("Opened: " + (displayIndex+1));
		windowFile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowFile.setLayout(new FlowLayout());
		JLabel label = new JLabel("<html>");
		for(int i = 0; i < toFillWindow.get(0).size() - 1; i++)
			label = new JLabel(label.getText() + Categories.get(i) + ": " + toFillWindow.get(0).get(i) + "<br> ");
		if(toFillWindow.size() != 1){
		for(int i = 0; i < toFillWindow.get(1).size(); i++)
			label = new JLabel(label.getText() + subCategories.get(i) + ": " + toFillWindow.get(1).get(i) + "<br> ");}
		label = new JLabel(label.getText() + "<html>", JLabel.LEFT);
		label.setFont(font);
		windowFile.add(label);
		windowFile.pack();
		windowFile.setLocationRelativeTo(null);
		windowFile.setVisible(true);
	}
	
	public void showTutorial(){
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame tut = new JFrame("Help");
		tut.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tut.setLayout(new FlowLayout());
		tut.pack();
		Font font = new Font("Serif", Font.BOLD, 13);
		JLabel label = new JLabel("<html><div style = 'text-align: center;'>");
		for(int i = 0; i < TUTORIALHELP.length; i++){
			label = new JLabel(label.getText() + TUTORIALHELP[i] + "<br>");
		}
		label = new JLabel(label.getText() + "</div><html>", JLabel.CENTER);
		label.setFont(font);
		tut.add(label);
		tut.setSize(TUTX, TUTY);
		tut.setLocationRelativeTo(null);
		tut.setVisible(true);
	}
	
	/* public void newWindow(){			//Testing the opening of a new window
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame can = new JFrame("A Title");	//Makes the window and assigns it a title
		can.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//Tells what to do when user clicks the red X to close it; this disposes. Only removes local window.
		//can.getContentPane().add(new JLabel("This is a Label."));	//Example of adding a component to the window; however, only one can be in there?
		//can.getContentPane().add(new JLabel("This is another Label."));	//However, the window only displays the last JLabel added to it.
	//	can.pack();							//Maximizes space efficiency of your window; alters size and placement of objects. Don't use, usually.
		can.setLayout(new FlowLayout());	//Enables multiple objects to appear together.
		can.setSize(250,250);				//Sets width/height; .pack() makes this ignored for efficient space usage.
		can.setBounds(50,50,250,250);		//Set's pixel location and size; location ignored if .setLocationRelativeTo(null) is used. Want centered anyways.
		can.setLocationRelativeTo(null);	//Centers the window; must come after .pack(); only consequence is misplacement. Place after size is set.
		JLabel label1 = new JLabel("This is a label.", JLabel.RIGHT);		//Make a JLabel, give it String and optional location.
		JLabel label2 = new JLabel("This is also a label.", JLabel.LEFT);	//Each line will display at least one Label; more if it fits. Otherwise skip line.
		can.add(label1); can.add(label2);		//[JFrame Name].add([JObject Name]);
		
		can.setVisible(true);				//Makes it appear to the user.
	}
	*/
	
	public int actualFileIndex(int given){	//Just shortcuts the process of extracting the real file number from Display.
		return Integer.parseInt(Display.get(given).get(Categories.size()-1));
	}
	
	public boolean clickObject(GObject obj, MouseEvent e){		//Works like a charm; does user click on an object?
		if(e.getX() > obj.getX() && e.getX() < obj.getX() + obj.getWidth() && e.getY() >  obj.getY() && e.getY() < obj.getY() + obj.getHeight())
		  return true;
		else
		  return false;
	}

}
