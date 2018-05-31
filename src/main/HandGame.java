package main;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class HandGame {

	static boolean flagValidate = true;
	static boolean flagEndGame = false;
	public static void main(String [] argm) {
		// set role play = true;
		Player player = new Player();
		//first time 
		player.setRolePlay(true);		
		System.out.println("Welcome to the game");
		
		while(!flagEndGame) {
			Scanner scan = new Scanner(System.in);		
			String handHuman = new String();
			
			//input hand
			System.out.println("Role :: " + player.isRolePlay());
			while(flagValidate) {
				if(player.isRolePlay()) {
					System.out.println("You are the predictor , what is your input ?");
				}else {
					System.out.println("AI are the predictor , what is your input ?");
				}		
				
				//validate format
				handHuman = scan.nextLine();
				if(validateInput(handHuman,player.isRolePlay())) {
					player.setShowhand(handHuman);
					flagValidate = false;
				}else {
					System.out.println("try again");
				}
				
			}
			
			// simulate AI
			String handAI = simulateAI(!player.isRolePlay());
			System.out.println("AI :: " + handAI);
			
			// result win or no win
			boolean challengeResult = challenge(player.getShowhand(),handAI,player.isRolePlay());
			
			if(challengeResult) {
				if(player.isRolePlay()) {
					System.out.println("You WIN !!!");
				}else {
					System.out.println("AI WIN !!!");
				}				
				System.out.println("Bye!");
				flagEndGame = true;
			}else {
				//next turn
				player.setRolePlay(!player.isRolePlay());	
				flagEndGame = false;
				flagValidate = true;
			}
		}
	}
	
	public static boolean validateInput(String handHuman,boolean role) {
		//role play
		if(role) {
			//str length = 3
			if(handHuman.length() == 3 && handHuman.matches("[CO]{2}[01234]{1}")) {
				return true;
			}
		}
		//role not play
		if(!role) {
			//str length = 2
			if(handHuman.length() == 2 && handHuman.matches("[CO]{2}")) {
				return true;
			}
		}
		
		return false;
	}
	
	public static String simulateAI(boolean role) {
		String result = new String();
		//imposible case CO4,CO0,OC4,OC0 OO1 CC3
		//hard AI
		
		//not play
		if(!role) {
			result = randomHand();
			return result;
		}
		
		//play
		if(role) {
			result = randomHand();
			Random rand = new Random();
			
			//hard AI
			
			Integer pdt = rand.nextInt(4);
			return result+pdt.toString();
		}
		return "";
	}
	
	public static String randomHand() {
		String result = new String();
		Random rand = new Random();
		int  n = rand.nextInt(3);
		//System.out.println(n);
		switch (n) {
			case 0:
				result = "OO";
				break;
			
			case 1:
				result = "CO";	
				break;
						
			case 2:
				result = "OC";
				break;
				
			case 3:
				result = "CC";
				break;
		}
		
		return result;
		
	}
	
	//optional HARD AI
	public static Integer randomDigit(boolean enableHard,String handAI) {
		//set default hand
		HashMap<String, Integer> handsmap = new HashMap<>();
		handsmap.put("C", 0);
		handsmap.put("O", 1);
		
		//flag random
		boolean flagPass = false;
		
		Integer result = new Integer("0");
		Random rand = new Random();
		
		//calculate hand
		String [] aiHand = handAI.split("");
		Integer countHand = handsmap.get(aiHand[0]) + handsmap.get(aiHand[1]);
		Integer maxLimit = 4 - countHand;
		
		//logic is more hand and limit max(4)
		while(!flagPass) {
			int  randomdigi = rand.nextInt(3);
			if(randomdigi >= countHand && randomdigi <= maxLimit) {
				flagPass = true;
				result = randomdigi;
			}
		}
		System.out.println(result);
		return result;
	}
	
	public static boolean challenge(String human ,String ai,boolean humanRole) {
		Integer handPredictor = 0;
		Integer countHand = 0;
		HashMap<String, Integer> handsmap = new HashMap<>();
		handsmap.put("C", 0);
		handsmap.put("O", 1);
		
		//human play
		if(humanRole) {
			//spilt hand and pdt
			String [] humanHand = human.split(""); // [3]
			String [] aiHand = ai.split(""); // [2]
			
			handPredictor = Integer.valueOf(humanHand[2]);			
			countHand = handsmap.get(humanHand[0]) + handsmap.get(humanHand[1]) + handsmap.get(aiHand[0]) + handsmap.get(aiHand[1]);
				
		}
		// ai play
		else {
			
			//spilt hand and pdt
			String [] humanHand = human.split(""); // [2]
			String [] aiHand = ai.split(""); // [3]
			
			handPredictor = Integer.valueOf(aiHand[2]);
			countHand = handsmap.get(humanHand[0]) + handsmap.get(humanHand[1]) + handsmap.get(aiHand[0]) + handsmap.get(aiHand[1]);
		}
		
		
		if(handPredictor == countHand) {
			return true;
		}
		return false;
	}
}

//System.out.println(handsmap.get(humanHand[0]));
//System.out.println(handsmap.get(humanHand[1]));
//System.out.println(handsmap.get(aiHand[0]));
//System.out.println(handsmap.get(aiHand[1]));
