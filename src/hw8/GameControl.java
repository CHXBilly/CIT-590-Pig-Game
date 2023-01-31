package hw8;

import java.util.Random;
import java.util.Scanner;

public class GameControl {
	
	/**
	 * Computer player
	 */
	Computer computer;
	
	/**
	 * Human player
	 */
	Human human;
	
	/**
	 * A random number generator to be used for returning random dice result (1-6) for both computer and human player
	 */
	Random random = new Random();
	
	/**
	 * The main method just creates a GameControl object and calls its run method
	 * @param args not used
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println("Welcome to Pig!");
		
		GameControl gc = new GameControl();
		while (true) {
			gc.run(sc);
			System.out.println("--------------------");
			System.out.println("Do you want to play again?");
			
			boolean check = gc.askYesNo(sc);
			if (!check) {
				System.out.println("Goodbye!");
				sc.close();
				break;
			}
		}
	}
	
	/**
     * Calls methods to perform each of the following actions:
     * - Ask user to input the name for human player, and print welcome with the name
     * - Create the players (one human and one computer)
     * - Control the play of the game
     * - Print the final results
	 * @param sc to use for getting user input
	 */
	public void run(Scanner sc) {
		System.out.println("Please input your name");
		String username = sc.next();
		System.out.println("Welcome " + username);
		createPlayers(username);
		
		while (true) {
			computer.move(human, random);
			human.move(computer, random, sc);
			boolean check = checkWinningStatus();
			if (check) {
				printResults();
				printWinner();
				break;
			}
		}
		

		
	}
	
	/**
     * Creates one human player with the given humanName, and one computer player with a name.
     * @param humanName for human player
     */
	public void createPlayers(String humanName) {
		this.human = new Human(humanName);//create player with the given humanName
		this.computer = new Computer();
	}
	
	/**
     * Checks if a winning status has been achieved
     * @return true if one player has won the game
     */
	public boolean checkWinningStatus() {
		boolean answer;
		//if they all have the same score its always false
		if (human.getScore()==computer.getScore()) {
			answer=false;
		}
		else if (human.getScore()>49 || computer.getScore()>49) {
			answer=true;
		}
		else {
			answer=false;
		}
		return answer;
		
	}
	
	/**
	 * Prints the final scores of the human player and computer player
	 */
	public void printResults() {
		System.out.println("Human gets "+human.getScore());
		System.out.println("Computer gets "+computer.getScore());

	}
	
	/**
     * Determines who won the game, and prints the results
     */
	public void printWinner() {
		if (human.getScore()>49) {
			System.out.println("Human wins");
		}
		else {
			System.out.println("Computer wins");
		}

	}
	
	/**
	 * If the user responds a string starting with "y" or "Y", the function returns True. 
	 * If the  user responds a string starting with "n" or "N", the function returns False. 
	 * Any other response will cause the question to be repeated until the user provides an acceptable response. 
	 * @param sc to use for getting user input
	 * @return true if user responds with "y" or "Y"
	 */
	public boolean askYesNo(Scanner sc) {
		boolean answer;//create the boolean variable
		while (true) {
		    String input = sc.next();
		    char firstletter=input.charAt(0);//look at the first letter of user input
		    
		    if (firstletter=='y' || firstletter=='Y') {
		    	answer=true;
		        break;
		    }
		    else if (firstletter=='n' || firstletter=='N') {
		    	answer=false;
		        break;
		    }
		    System.out.println("Wrong input try it again");
		}
		return answer;

		
	}
	
}
