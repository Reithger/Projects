package entities;
import java.util.*;
import java.awt.*;

public class Character extends Player{

	private String[] NAME_LIST_FIRST = {"Sam", "Jordan", "Aerin", "Jamie", "Morgan", "Taylor", "Billie", "Cameron", "Robin", "Leo", "Fran", "Bailey",
										"Dakota", "Devin", "Drew", "Ashton", "Jesse", "Kelsey", "Logan", "Mackenzie", "Marley", "Mason", "Quinn",
										"Reagan", "Rowan", "Ryan", "Shawn", "Ashley", "Casey", "Chris", "Clem", "Connie", "Denny", "Gail", "Izzy"};
	private String[] NAME_LIST_LAST = {"Jones", "Myers", "Seamus", "Moroaner", "Douglas", "Brown", "Miller", "Stewart", "Sanchez", "Davis", "Perez",
									   "Martinez", "Thompson", "Robinson", "Clark", "Campbell", "Alexander", "Foster", "Patterson", "Baker", "Kelly",
									   "Lewis", "Lee", "Walker", "Watson", "Russel", "Bryant", "Carter", "Green", "Hayes", "Diaz", "Taylor", "Garcia"};
	
	private double[][] bias;
	private int boundX;
	private int boundY;
	
	public Character(int x, int y, String imageLoc, int newRanking, String newTitle, String filePath){
		super(x, y, imageLoc, filePath);
		boundX = x;
		boundY = y;
		fillIdent();
		fillBias();
		Random rand = new Random();
		setName(NAME_LIST_FIRST[rand.nextInt(NAME_LIST_FIRST.length)] + " " + NAME_LIST_LAST[rand.nextInt(NAME_LIST_LAST.length)]);
		adjustVisual();
		assignRanking(newRanking);
		assignTitle(newTitle);
	}
	
	public Character(int x, int y, String name, String title, String imageLoc, String filePath){
		super(x, y, imageLoc, filePath);
		setName(name);
		assignTitle(title);
	}
	
	private void fillIdent(){
		Random rand = new Random();
		setState(0, randomizeSexGender(getRank(), rand));	//sex
		setState(1, randomizeSexGender(getRank(), rand));	//gender
		setState(2, randomizeRace(getRank(), rand));	//race
		setState(3, randomizeSexuality(getRank(), rand));	//sexuality
		setState(4, randomizeDisability(getRank(), rand));	//disability
		setState(5, randomizeEconomicSocial(getRank(), rand));	//economic social
		int randHair = rand.nextInt(10);
		int randShoe = rand.nextInt(10);
		for(int i = 0; i < randHair; i++)
		  cycleHair();
		for(int i = 0; i < randShoe; i++)
		  cycleShoe();
	}
	
	private void fillBias(){
		int[] count = {3, 3, 5, 6, 7, 3, 1};
		bias = new double[7][];			//sex gender race sexuality disability socio-economic disparity(sex/gender)
		for(int i = 0; i < bias.length; i++){
			bias[i] = new double[count[i]];
			for(int j = 0; j < bias[i].length; j++){
				bias[i][j] = 50;
			}
		}
		boolean[][] iden = getIdentity();
		Random rand = new Random();
		for(int i = 0; i < iden.length; i++){
			for(int j = 0; j < iden[i].length; j++){
				if(iden[i][j]){
					bias[i][j] += 10 + rand.nextInt(10);
				}
				else{
					bias[i][j] -= 10 + rand.nextInt(10);
				}
			}
		}
	}

	public int getHomeX(){
		return boundX;
	}
	
	public int getHomeY(){
		return boundY;
	}
	
	public void setHomeX(int in){
		boundX = in;
	}
	
	public void setHomeY(int in){
		boundY = in;
	}
	
	public double processResult(boolean[][] iden, double score){
		double base = 1.0;
		for(int i = 0; i < iden.length; i++){
			for(int j = 0; j < iden[i].length; j++){
				if(iden[i][j]){
					base *= bias[i][j]/50.0;
				}
			}
		}
		return base * score * (5 - getRank());
	}
	
	private int randomizeSexGender(int rank, Random rand){
		int hold = rand.nextInt(100);
		switch(rank){
		    case 0: return multiItemProbability(hold, 72, 97);		//president
		    case 1: return multiItemProbability(hold, 67, 97);		//dean
		    case 2: return multiItemProbability(hold, 67, 97);		//department head
		    case 3:	return multiItemProbability(hold, 67, 97);  	//researcher
		    case 4:	return multiItemProbability(hold, 47, 97);		//research assistant
			default: return 0;
		}
	}
	
	private int randomizeRace(int rank, Random rand){
		int hold = rand.nextInt(100);
		switch(rank){
		    case 0: return multiItemProbability(hold, 86, 90, 94, 96);		//president
		    case 1: return multiItemProbability(hold, 80, 84, 88, 98);		//dean
		    case 2: return multiItemProbability(hold, 80, 84, 88, 98);		//department head
		    case 3:	return multiItemProbability(hold, 72, 75, 80, 98);  	//researcher
		    case 4:	return multiItemProbability(hold, 47, 49, 61, 98);		//research assistant
			default: return 0;
		}
	}
	
	private int randomizeSexuality(int rank, Random rand){
		int hold = rand.nextInt(100);
		switch(rank){
			default: return multiItemProbability(hold, 80, 88, 92, 94, 96);
		}
	}
	
	private int randomizeDisability(int rank, Random rand){
		int hold = rand.nextInt(100);
		switch(rank){
		    case 0: return multiItemProbability(hold, 74, 81, 84, 85, 91, 97);		//president
		    case 1: return multiItemProbability(hold, 90, 92, 93, 94, 96, 98);		//dean
		    case 2: return multiItemProbability(hold, 90, 92, 93, 94, 96, 98);		//department head
		    case 3:	return multiItemProbability(hold, 92, 93, 94, 95, 96, 97);  	//researcher
		    case 4:	return multiItemProbability(hold, 92, 93, 94, 95, 96, 97);		//research assistant
			default: return 0;
		}
	}
	
	private int randomizeEconomicSocial(int rank, Random rand){
		int hold = rand.nextInt(100);
		switch(rank){
		    case 0: return multiItemProbability(hold, 15, 44);		//president
		    case 1: return multiItemProbability(hold, 18, 47);		//dean
		    case 2: return multiItemProbability(hold, 20, 50);		//department head
		    case 3:	return multiItemProbability(hold, 23, 56);  	//researcher
		    case 4:	return multiItemProbability(hold, 25, 60);		//research assistant
			default: return 0;
		}
	}
	
	private int multiItemProbability(int calc, int ... chance){
		for(int i = 0; i < chance.length; i++){
			if(calc < chance[i]){
				return i;
			}
		}
		return chance.length-1;
	}
}
