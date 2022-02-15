/*
* AUTHOR: Amimul Ehsan Zoha
* FILE: WordleController.java
* ASSIGNMENT: Programming Assignment 1 - Wordle
* COURSE: CSc 335;  Spring 2022
* 
* PURPOSE: This program implements a simple version, a text 
* based UI of the popular game wordle by making use of the MVC
* architecture. This class is the controller class of the MVC 
* architecture. This class is used to query the model based on 
* the input from the user that comes through the view.
*/
package controller;
import model.WordleModel;

public class WordleController {
	//declaration of fields and contants
	private WordleModel model;
	private int guessNumber;
	private String curGuess;
	public static final int wordLength = 5;
	public static final int maxGuesses = 6;
	public static final int totalLetters = 26;
	
	
	/*
	 * 2d character array to track guesses that have already been made, and the blank spaces 
	 * for future guesses.
	 * We chose to store it in the controller though that was a somewhat arbitrary choice. 
	 * It could (and maybe even should)
	 * be placed in the model as well.
	 */
	private Character[][] progress;
	
	// the constructor of the wordle controller class
	public WordleController (WordleModel model) {
		this.model = model;
		//Initialization of private instance variables
		guessNumber  = 0;
		progress = new Character[maxGuesses][wordLength];	
		curGuess = "";
	} 
	/*
	* this method prints returns true if the game is over,
	* false otherwise
	* @param no parameters
	* @return boolean, returns true if the game is over,
	*/
	public boolean isGameOver() {
		
		// game is over if right word is guessed
		if (curGuess.toUpperCase().equals(getAnswer().toUpperCase())) {
			return true;
		}
		// game is over if the guesses number pass 6
		if (guessNumber >= maxGuesses) {
			return true;
		}
		return false;
	}
	/*
	* this method performs any work necessary when a guess occurs
	* The methods in model are called to make the necessary changes 
	* of the game
	* @param String, the guess word
	* @return void
	*/
	public void makeGuess(String guess) {
		curGuess = guess;
		model.updateGuessedCharacters(guess);
		Character[] correctLettersWrongPlacesResult = model.correctLettersWrongPlaces(guess);
		Character[] correctLettersCorrectPlacesResult = model.correctLettersCorrectPlaces(guess);
		// loop to compare the letters of guess and answer
		for(int i = 0; i < wordLength; i++ ) {
			if(correctLettersWrongPlacesResult[i] != null) {
				progress[guessNumber][i] = correctLettersWrongPlacesResult[i];
			}
			if(correctLettersCorrectPlacesResult[i] != null){//else if o hote pare
				progress[guessNumber][i] = correctLettersCorrectPlacesResult[i];	
			}	
		}
		//guess number is increased by one after each guess
		guessNumber +=1;
	}
	/*
	* this method returns the answer by calling method from model class
	* @param no parameters
	* @return String, the answer.
	*/
	public String getAnswer() {
		/* Returns the answer or 'hidden word'. */
		return model.getAnswer();
	}
	
	/* This methods returns the progress the user has made thus far. 
	 * This also includes blank spaces (underscores) for guesses to come. 
	 * @return [][], the progress (current)
	 */
	public Character[][] getProgress() {
	
		return progress;
		
		
	}
	// the below comment is provided by instructor, I am keeping it for help in the 
	// future edits of next projects.
	/*
	 * Note that this method returns an array of characters that have been guessed thus far. This is NOT what
	 * the view will eventually display. The view is interested in displaying the characters that have NOT been
	 * guessed yet in order to help the user form new guesses. But that is not OUR problem in the controller (and 
	 * in the model for that matter). If the view wants to display the characters that way, then it is up to the view
	 * to transform this data to its own needs.
	 * 
	 * It is just as likely that another view will want to display the guessed characters instead of those that haven't been guessed.
	 * Which is why it is simply the controller's job to present a clean interface, not perfectly format all of the data the way a 
	 * particular view wants it.
	 * 
	 * If curious, some might argue that it is the controller's job to 'interpret' the data from the model and put it in
	 * such a form/way so that the view can easily display it. This is reasonable but I would argue it fits more in the
	 * MVVM - Model, View, ViewModel architecture.
	 */
	public Character[] getGuessedCharacters() {
		/* Return the guessed characters from the model. */
		return model.getGuessedCharacters();
	}
	

}
