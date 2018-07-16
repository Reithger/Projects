import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Mechanics {

	//Next time, do not use GridBagConstraints; control the actual X, Y coordinates to make this so much easier.
	
	
	//Just some helper methods of JFrame posing and such. All data will be stored in Presentation, but the construction
	//of JFrames and posing of lettering will be here.
	
	
	//Need a method that takes in a long string and breaks it into discrete lines to write.
	

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Dimension userScreen = tk.getScreenSize();
	private static int quizCounter;
	
	public final static Font buttonFont = new Font("Serif", Font.BOLD, 32);
	public final static Font premiseLabelFont = new Font("Serif", Font.BOLD, 14);
	public final static Font questionFont = new Font("Serif", Font.BOLD, 20);
	public final static int sizeX = 1066;
	public final static int sizeY = 630;
	public final static int[] lineBreaks = new int[]{};	//How many lines of strings for each section?
	
	public static JFrame Splash(String showMe, String title, String change){	//User loads up the app, they see this to start at their own readiness.
		JFrame splash = new JFrame(title);
		JButton begin = new JButton(showMe);
		begin.setFont(buttonFont);
		begin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				splash.setTitle(change);
			}
		});
		splash.setLayout(new GridBagLayout());
		composeComponent(splash, begin, 0, 0, 1, 1, GridBagConstraints.SOUTHEAST, GridBagConstraints.EAST, new Insets(50,50,50,50));
		splash.setSize(sizeX/2, sizeY/2);
		splash.setLocationRelativeTo(null);
		splash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return splash;
	}
	
	public static void IntroPremise(JFrame screen, String describePremise, String newTitle){	//First use is explaining how it's a magazine test, second is Feminist review of tropes.
		screen.setSize(sizeX, sizeY);
		screen.setLocationRelativeTo(null);
		screen.getContentPane().removeAll();
		screen.revalidate();
		screen.repaint();
		JButton startQuiz = new JButton("<html>Continue<br><html>");
		JLabel[] toLoop = splitString(describePremise, screen.getWidth(), questionFont);
		startQuiz.setFont(buttonFont);
		startQuiz.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				screen.setTitle("");
			}
		});
		for(int i = 0; i < toLoop.length; i++){
			if(i == 0)
				composeComponent(screen, toLoop[i], 0, i, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(100,25,0,0));
			else
				composeComponent(screen, toLoop[i], 0, i, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,25,0,0));
		}
		composeComponent(screen, startQuiz, 0, toLoop.length, 1, 1, GridBagConstraints.SOUTHEAST, GridBagConstraints.EAST, new Insets(50,50,50,50));
	}
	
	public static void Loading(JFrame screen, String fileName, double time) throws AWTException{	//Loading bar animation with different text for each genre of test and eventually Feminist literature.
		JFrame loadingBar = new JFrame(fileName);
		screen.setTitle(fileName);
		loadingBar.setSize(userScreen.width/3, userScreen.width/30 + 10);
		loadingBar.setLocationRelativeTo(null);
		loadingBar.repaint();
		loadingBar.setVisible(true);
		Robot rob = new Robot();
		Graphics g = loadingBar.getContentPane().getGraphics();
		int split = 20;
		int defaultWait = 125;
		for(int i = 0; i < split; i++){
		    g.drawRect(userScreen.width / 3 / split * i + 5 * i, 4, userScreen.width/3 / split, userScreen.width/75-5);
		}
		loadingBar.repaint();
		rob.delay(defaultWait);
		for(int i = 0; i < split; i++){
			g.setColor(Color.GREEN);
			for(int j = 0; j < split; j++){
				if(j < i)
					g.fillRect(userScreen.width / 3 / split * j + 5 * j, 4, userScreen.width/3 / split, userScreen.width/75-5);
				else
				    g.drawRect(userScreen.width / 3 / split * j + 5 * j, 4, userScreen.width/3 / split, userScreen.width/75-5);
			}
			rob.delay((int)(time/(split+1) * 1000));
			g.setColor(Color.WHITE);
			g.fillRect(0,0,loadingBar.getWidth(), loadingBar.getHeight());
		}
		rob.delay(defaultWait);
		loadingBar.setVisible(false);
	}
	
	public static int QuestionnaireOverall(JFrame screen, String[] questions, String[] responses){	//Overall body that handles a set of 10 questions and returns an answer
		screen.getContentPane().removeAll();
		screen.revalidate();
		screen.repaint();
		quizCounter = 0;
		String baseTitle = screen.getTitle();
		screen.setTitle(baseTitle.substring(0, baseTitle.length()-4) + "_q1" + baseTitle.substring(baseTitle.length()-4));
		for(int i = 0; i < questions.length; i++){
			String[] answerPack = new String[responses.length/questions.length];
			for(int j = 0; j < answerPack.length; j++){
				answerPack[j] = responses[answerPack.length*i + j];
			}
			screen.getContentPane().removeAll();
			screen.revalidate();
			screen.repaint();
			SingleQuestionScreen(screen, questions[i], answerPack);
			baseTitle = screen.getTitle();
			screen.setVisible(true);
			while(screen.getTitle().equals(baseTitle)){System.out.print("");};
		}
		return quizCounter;
	}
	
	public static void SingleQuestionScreen(JFrame screen, String question, String[] answers){	//Individual question with: the questions and 4 answers, returns values pushing male/female
		JLabel questionLbl = new JLabel(question, JLabel.LEFT);
		questionLbl.setFont(questionFont);
		questionLbl.setSize(screen.getWidth(), screen.getHeight()/2);
		JButton[] Answers = new JButton[answers.length];
		for(int i = 0; i < Answers.length; i++){
			Answers[i] = new JButton(answers[i]);
			Answers[i].setFont(questionFont);
			if(i < 2){
			  Answers[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					  quizCounter++;
					  String baseTitle = screen.getTitle();
					  if(question.substring(0,2).equals("Q9"))
						  screen.setTitle(baseTitle.substring(0,baseTitle.length()-5) + "10" + baseTitle.substring(baseTitle.length()-4));
					  else if(question.substring(0,3).equals("Q10"))
						  screen.setTitle(baseTitle.substring(0,baseTitle.length()-8) + baseTitle.substring(baseTitle.length()-4));
					  else
						  screen.setTitle(baseTitle.substring(0,baseTitle.length()-5) + (Character.getNumericValue(baseTitle.charAt(baseTitle.length()-5))+1) + baseTitle.substring(baseTitle.length()-4));
					  //Some way to continue the process
				}
			});}
			else{
			  Answers[i].addActionListener(new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  String baseTitle = screen.getTitle();
					  if(question.substring(0,2).equals("Q9"))
						  screen.setTitle(baseTitle.substring(0,baseTitle.length()-5) + "10" + baseTitle.substring(baseTitle.length()-4));
					  else if(question.substring(0,3).equals("Q10"))
						  screen.setTitle(baseTitle.substring(0,baseTitle.length()-8) + baseTitle.substring(baseTitle.length()-4));
					  else
						  screen.setTitle(baseTitle.substring(0,baseTitle.length()-5) + (Character.getNumericValue(baseTitle.charAt(baseTitle.length()-5))+1) + baseTitle.substring(baseTitle.length()-4));
				  }
			  });}
		}
		composeComponent(screen, questionLbl, 0, 0, 3, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(100,75,100,0));
		int[] randomOrder = randomizeOrder(answers.length);
		for(int j = 0; j < Answers.length; j++){
			int i = randomOrder[j];
			composeComponent(screen, Answers[i], 0, 3+j, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,10,10,10));
		}
		screen.setVisible(true);
	}
	
	public static void FinalResult(JFrame screen, String gender, String flavour){	//A triumphant voice screaming of the cleverness of the test to say if you're a boy or a girl.
		screen.getContentPane().removeAll();
		screen.revalidate();
		screen.repaint();
		JLabel label = new JLabel("You have been found to be: " + gender + "!", JLabel.CENTER);
		JLabel descript = new JLabel(flavour, JLabel.CENTER);
		descript.setFont(questionFont);
		label.setFont(questionFont);
		JButton carryOn = new JButton("Continue");
		carryOn.setFont(questionFont);
		carryOn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				screen.setTitle("");
				screen.getContentPane().removeAll();
			}
		});
		composeComponent(screen, label, 0, 0, 1, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(50,50,50,50));
		composeComponent(screen, descript, 0, 1, 1, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(50,50,50,50));
		composeComponent(screen, carryOn, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(50,50,50,50));
		screen.setVisible(true);
	}
	
	public static void AlteredQuestionnaireOverall(JFrame screen, String[] questions, String[] responses, String[] commentsAns){	//Second pass through to review ideas of gender
		screen.getContentPane().removeAll();
		screen.revalidate();
		screen.repaint();
		String baseTitle = screen.getTitle();
		screen.setTitle(baseTitle.substring(0, baseTitle.length()-4) + "_q1" + baseTitle.substring(baseTitle.length()-4));
		for(int i = 0; i < questions.length; i++){
			String[] answerPack = new String[responses.length/questions.length];
			String[] commentPack = new String[responses.length/questions.length];
			for(int j = 0; j < answerPack.length; j++){
				answerPack[j] = responses[answerPack.length*i + j];
				commentPack[j] = commentsAns[answerPack.length*i + j];
			}
			screen.getContentPane().removeAll();
			screen.revalidate();
			screen.repaint();
			AlteredSingleQuestionScreen(screen, questions[i], answerPack, commentPack);
			baseTitle = screen.getTitle();
			screen.setVisible(true);
			while(screen.getTitle().equals(baseTitle)){System.out.print("");};
		}
	}
	
	public static void AlteredSingleQuestionScreen(JFrame screen, String question, String[] answers, String[] comments){	//Individual comment on questions in each time period
		JLabel questionLbl = new JLabel(question, JLabel.LEFT);
		questionLbl.setFont(questionFont);
		questionLbl.setSize(screen.getWidth(), screen.getHeight()/2);
		JButton[] Answers = new JButton[answers.length];
		JButton carryOn = new JButton("Continue");
		carryOn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				  String baseTitle = screen.getTitle();
				  if(question.substring(0,2).equals("Q9"))
					  screen.setTitle(baseTitle.substring(0,baseTitle.length()-5) + "10" + baseTitle.substring(baseTitle.length()-4));
				  else if(question.substring(0,3).equals("Q10"))
					  screen.setTitle(baseTitle.substring(0,baseTitle.length()-8) + "_comment_male" + baseTitle.substring(baseTitle.length()-4));
				  else
					  screen.setTitle(baseTitle.substring(0,baseTitle.length()-5) + (Character.getNumericValue(baseTitle.charAt(baseTitle.length()-5))+1) + baseTitle.substring(baseTitle.length()-4));
			}
		});
		carryOn.setFont(questionFont);
		for(int i = 0; i < Answers.length; i++){
			Answers[i] = new JButton(answers[i]);
			final String toInsert = comments[i];
			Answers[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					displayComment(toInsert);
				}
			});
		}
		composeComponent(screen, questionLbl, 0, 0, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(100,75,100,50));
		composeComponent(screen, carryOn, 0, 1, 2, 2, GridBagConstraints.NORTHEAST, GridBagConstraints.NORTHEAST, new Insets(50,50,50,50));
		int[] randomOrder = randomizeOrder(answers.length);
		for(int j = 0; j < Answers.length; j++){
			int i = randomOrder[j];
			Answers[i].setFont(questionFont);
			composeComponent(screen, Answers[i], 0, 4+j, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,10,10,10));
		}
		screen.setVisible(true);
	}
	
	public static void displayComment(String comment){
		JFrame popUp = new JFrame();
		popUp.setSize(sizeX*4/9, sizeY/4);
		JButton closeScreen = new JButton("Close");
		JLabel[] labels = splitString(comment, popUp.getWidth(), closeScreen.getFont());
		closeScreen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				popUp.setVisible(false);
			}
		});
		popUp.setLocationRelativeTo(null);
		popUp.setLayout(new GridBagLayout());
		for(int i = 0; i < labels.length; i++){
			if(i == 0)
				composeComponent(popUp, labels[i], 0, i, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(25,10,0,10));
			else
				composeComponent(popUp, labels[i], 0, i, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,10,0,10));
		}
		composeComponent(popUp, closeScreen, 0, labels.length, 1, 1, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(10,10,10,10));
		popUp.setVisible(true);
	}
	
	public static void AlteredFinalResult(JFrame screen, String gender, String comment){
		screen.getContentPane().removeAll();
		screen.revalidate();
		screen.repaint();
		JLabel label = new JLabel("The place of the " + gender + ":");
		label.setFont(questionFont);
		JLabel[] commentary = splitString(comment, screen.getWidth(), questionFont);
		JButton carryOn = new JButton("Continue");
		carryOn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				screen.setTitle(screen.getTitle().substring(0, screen.getTitle().length()-8) + "female" + screen.getTitle().substring(screen.getTitle().length()-4));
			}
		});
		carryOn.setFont(questionFont);
		for(int i = -1; i < commentary.length; i++){
		  if(i == -1)
			composeComponent(screen, label, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(75,75,20,50));
		  else
		    composeComponent(screen, commentary[i], 0, 1+i, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,25,0,0));
		}
		composeComponent(screen, carryOn, 0, commentary.length+1, 1, 1, GridBagConstraints.SOUTHEAST, GridBagConstraints.EAST, new Insets(50,50,50,50));
		screen.setVisible(true);
	}
	
	public static void FinalFinalResult(JFrame screen, String ghender){	//Final screen that says gender isn't simple and not something to rate on a checklist.
		screen.getContentPane().removeAll();
		screen.revalidate();
		screen.repaint();
		JButton endProgram = new JButton("Close");
		JLabel[] allLines = splitString(ghender, sizeX, questionFont);
		endProgram.setFont(questionFont);
		endProgram.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			  screen.setTitle("");
			}
		});
		for(int i = 0; i < allLines.length; i++){
			if(i == 0)
				composeComponent(screen, allLines[i], 0, i, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(25,0,0,0));
			else
				composeComponent(screen, allLines[i], 0, i, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0));
		}
		composeComponent(screen, endProgram, 0, allLines.length, 1, 1, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(15,15,15,15));
		screen.setVisible(true);
	}
	
	public static void composeComponent(Container screen, Component toAdd, int gridX, int gridY, int gridWidth, int gridHeight, int anchor, int fill, Insets border){
		GridBagConstraints gridBagConstraints = new GridBagConstraints(gridX, gridY, gridWidth, gridHeight, 1.0, 1.0, anchor, fill, border, 0, 0);
		screen.add(toAdd, gridBagConstraints);
	}
	
	public static int dealWithResult(int n){
		if(n < 5)
			return 0;
		else
			return 1;
	}

	public static JLabel[] splitString(String arbitrary, int width, Font usedfont){
		String[] hold = arbitrary.split(" ");
		int maxWidth;
		if(width == sizeX)
		    maxWidth = 2 * width / usedfont.getSize();
		else
			maxWidth = 9 * width/usedfont.getSize() / 5;
		JLabel[] toReturn = new JLabel[arbitrary.length()/maxWidth + 1];
		String forward = "";
		int counter = 0;
		for(int i = 0; i < hold.length; i++){
			if(forward.length() + hold[i].length() >= maxWidth){
				JLabel label = new JLabel(forward, JLabel.CENTER);
				label.setFont(usedfont);
				forward = "";
				toReturn[counter] = label;
				counter++;
			}
			forward += hold[i] + " ";
		}
		JLabel label = new JLabel(forward, JLabel.CENTER);
		label.setFont(usedfont);
		System.out.println(counter + " " + toReturn.length);
		toReturn[counter] = label;
		if(toReturn[toReturn.length-1] == null){
			toReturn[toReturn.length-1] = new JLabel("");
			toReturn[toReturn.length-1].setFont(usedfont);
		}
		return toReturn;
	}
	
	public static int[] randomizeOrder(int size){
		Random rand = new Random();
		int[] toReturn = new int[size];
		for(int i = 0; i < toReturn.length; i++)
			toReturn[i] = -1;
		int hold;
		for(int i = 0; i < size; i++){
			hold = rand.nextInt(size);
			if(indexOf(toReturn, hold) == -1)
				toReturn[i] = hold;
			else{
				while(indexOf(toReturn, hold) != -1)
					hold = rand.nextInt(size);
				toReturn[i] = hold;
			}
		}
		return toReturn;
	}
	
	public static int indexOf(int[] arr, int key){
		for(int i = 0; i < arr.length; i++)
			if(arr[i] == key)
				return i;
		return -1;
	}
	
}
