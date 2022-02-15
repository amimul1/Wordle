/*
* AUTHOR: Amimul Ehsan Zoha
* FILE: Wordle.java
* ASSIGNMENT: Programming Assignment 1 - Wordle
* COURSE: CSc 335;  Spring 2022
* 
* PURPOSE: This program implements a simple version, a text 
* based UI of the popular game wordle by making use of the MVC
* architecture. MVC architecture is the model-view- controller 
* architecture when we have something to model (in this case the 
* wordle game) and user interface that displays and interacts with that
* model (the view). The controller is the code that manipulates the model
* in response to actions from the view. This file is the view part of 
* MVC and this displays the game actions and progress to the user in text
* form. It is sufficiently abstracted so that we can change it later 
* to other forms of view for example GUI
*
*/
// import statements 
package view;
import model.WordleModel;
import controller.WordleController;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Wordle {
	//contants are declared 
	public static final int wordLength = 5;
	public static final int maxGuesses = 6;
	public static final int totalLetters = 26;
	private static String decision;
	
	/*
	 * The main method runs the logic of the game by looping and asking the user
	 * to make guesses. It calls methods from the controller to get the data but 
	 * it does not manipulate the progress data itself. In this way, it is 
	 * abstracted which is good for future use and edits. 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner userInputObj = new Scanner(System.in);
		decision = "yes";
		//loop used in case the user wants to play again
		while (playAgain(decision)) {
			WordleController controller = new WordleController(new WordleModel());
			// this loop runs until a single game is over
			while(!controller.isGameOver()) {
				System.out.println("Enter a guess: ");
				//a while loop here to get a valid guess
				String userGuess = userInputObj.nextLine();
				while(!isAlphaa(userGuess)) {
					System.out.println("Try a new word! Guess must be contain only alphabets");
					userGuess = userInputObj.nextLine();	
				}
				while (userGuess.length()!= 5) {
					System.out.println("Try a new word! Guess must be a 5 letter word");
					userGuess = userInputObj.nextLine();	
				}
				controller.makeGuess(userGuess);
				printProgress(controller.getProgress());
				printGuessCharUpdate(controller.getGuessedCharacters());
				//if the game is over, the answer word is displayed
				if (controller.isGameOver()) {
					System.out.println("Good game! The word was " + controller.getAnswer());
					System.out.println("Would you like to play again? yes/no");
					decision = userInputObj.nextLine();
					playAgain (decision);		
				}
			}	
		}
		
	}
	/*
	* this method prints out the current progress of the game
	* @param [][],curProgress- the current progress of the game
	* in a 2d array
	* @return void
	*/
	private static void printProgress(Character[][] curProgress) {
		for(int i = 0; i<maxGuesses; i++ ) {
			for(int j=0; j<wordLength; j++ ) {
				if (curProgress[i][j] != null ) {
					System.out.print(curProgress[i][j]);	
				}
				else {
					System.out.print("_");
				}	
			}
			System.out.print("\n");
		}
	}
	
	/*
	* this method prints out the all the letters except the 
	* already guessed ones
	* @param [], guessedCharacters- the letters update
	* @return void
	*/
	private static void printGuessCharUpdate(Character[] guessedCharacters){
		for (int i = 0; i < totalLetters; i++) {
			if (guessedCharacters[i] == null) {
				//we print the letters by accessing their ASCII number
				System.out.print(Character.toString((char)(i+65)) + ' ');
			}
			else {
				System.out.print("_ ");
			}
		}
		System.out.println();
	}
	/*
	* this method returns true if the user wants to play 
	* again, or false otherwise
	* @param String, decision - yes or no
	* @return boolean, if the user wants to play 
	*/
	private static boolean playAgain (String decision) {
		if(decision.toLowerCase().equals("yes")){
			return true;
		}
		if (decision.toLowerCase().equals("no")) {
			return false;
		}
		return false;
	}
	/*
	* this method returns true if the user guess is 
	* alphabetic  or false otherwise
	* @param String, user guess
	* @return boolean, if the user guess is alpha
	*/
	private static boolean isAlphaa(String guess)
    {
        if (guess == null) {
            return false;
        }
 
        for (int i = 0; i < guess.length(); i++)
        {
            char c = guess.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }
 
}
