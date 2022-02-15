/*
* AUTHOR: Amimul Ehsan Zoha
* FILE: WordleModel.java
* ASSIGNMENT: Programming Assignment 1 - Wordle
* COURSE: CSc 335;  Spring 2022
* 
* PURPOSE: This program implements a simple version, a text 
* based UI of the popular game wordle by making use of the MVC
* architecture. This class is the model class of the MVC 
* architecture. This model class (named WordleModel) stores 
* the representation of the hidden word, which letters have been guessed 
* and responds to guesses about that word.
*/
//import statements 
package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordleModel {
	//private fields
	private String answer;
	private Character[] guessedCharacters;
	private Scanner inDictionaryFile;
	private  Random random_method;
	
	
	private static final int wordLength = 5;
	private static final int totalLetters = 26;
	
	private String selectRandomAnswer(Scanner dictionaryFile) {
		
		List<String> probableAnswerList = new ArrayList<String>();
		//preparing the answers word lists from the "Dictionary.txt" file
	    while (inDictionaryFile.hasNext()) {
	      String word = inDictionaryFile.next();
	      probableAnswerList.add(word);
	    }
	    //generating a random index to choose a random answer
	    int index = random_method.nextInt(probableAnswerList.size());
	    String randomAnswer = probableAnswerList.get(index);
	    return randomAnswer;
	}
	
	
	// the constructor of the wordle controller class
	public WordleModel() throws FileNotFoundException {
		
		//Initialization of private instance variables
		guessedCharacters = new Character[totalLetters];
		inDictionaryFile = new Scanner(new File("Dictionary.txt"));
		random_method = new Random();
		answer = selectRandomAnswer(inDictionaryFile);
		
		
	}
	/* 
	 * This method returns an array of characters. Each index 
	 * in the result array that corresponds to an index in the guess that
	 * held an incorrect letter (a letter not in the answer) should be 
	 * set to null. Each index that is in the guess AND in the answer in that 
	 * same index should also be null. Only a character which is in the guess and the answer,
	 * but in different indices should be set at the index in the result array. 
	 * Ex: answer: MAYBE
	 * guess: MEATS
	 * would return an array:
	 * [null, 'e', 'a', null, null]
	 * @param String, the guess word
	 * @return [], the resulting array
	 */
	public Character[] correctLettersWrongPlaces(String guess) {

		
		guess = guess.toLowerCase(); answer = answer.toLowerCase();
		Character[] correctLettersWrongPlacesResult = new Character[wordLength]; 
		int i = 0;
		// a while to test each of the letter is in both 
		// the guess and the word but in wrong positions
		while (i<wordLength) {
			if (answer.contains("" + guess.charAt(i))){
				if (answer.charAt(i) == guess.charAt(i)) {
					correctLettersWrongPlacesResult[i]=null;
				}
				else {
					correctLettersWrongPlacesResult[i] = guess.charAt(i);
				}	
			}
			i++;
		}
		return correctLettersWrongPlacesResult;
		
	}
	/*
	 * This method is similar to above except now all indices in the result array 
	 * are null except those in the correct indices. Ex:
	 * answer: MAYBE
	 * guess: MEATS
	 * would return an array:
	 * ['M', null, null, null, null]
	 * @param String, the guess word
	 * @return [], the resulting array
	 */
	public Character[] correctLettersCorrectPlaces(String guess) {

		guess = guess.toUpperCase(); answer = answer.toUpperCase();
		Character[] correctLettersCorrectPlacesResult = new Character[5];
		int i = 0;
		while (i<5) {
			// a while to test each of the letter is in both 
			// the guess and the word but in same positions
			if (answer.contains("" + guess.charAt(i))){
				if (answer.charAt(i) == guess.charAt(i)) {
					correctLettersCorrectPlacesResult[i] = guess.charAt(i);
				}
				else {
					correctLettersCorrectPlacesResult[i] = null;
				}	
			}
			i++;
		}
		return correctLettersCorrectPlacesResult;
	}
	/*
	* this method returns the answer word
	* again, or false otherwise
	* @param no parameters
	* @return String, the answer
	*/
	public String getAnswer() {
		
		return answer;
	}
	/*
	* this method updates the guessed characters
	* For instance, if the user's first guess is SHELF, 
    * your guessed characters array previous had every index
    * set to null. After the guess, then the array would have certain indices set like below:
    * ['S' - 'A'] = 'S'
	* ['H' - 'A'] = 'H'
	* ['E' - 'A'] = 'E'
	* ['L' - 'A'] = 'L'
	* ['F' - 'A'] = 'F'
	* @param String, the user guess word
	* @return void
	*/
	public void updateGuessedCharacters(String guess) {
		guess = guess.toUpperCase();
		for (int i = 0; i <wordLength; i++) {
			guessedCharacters[guess.charAt(i) - 'A'] = guess.charAt(i);
			}
	}
	/*
	* this method returns the guessed characters in form 
	* of an array
	* @param no parameters
	* @return [], guessed characters array.
	*/
	public Character[] getGuessedCharacters() {
		return guessedCharacters;
	}	
}