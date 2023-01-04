package com.example.wordleclonerebuild;

import java.util.Arrays;
import java.util.Scanner;

public class PlayerWord implements Updatable {

    private static final int LETTERS_PER_WORD    = 5;
    private static final String VALID_CHARACTERS = "^[a-zA-Z]*$";

    private static final Scanner SCAN = new Scanner(System.in);

    private String[] playerWordLetters;
    private String playerWord;

    public PlayerWord() {
        this.playerWord        = "";
        this.playerWordLetters = new String[LETTERS_PER_WORD];
    }

    /**
     * Asks the player for a word. If the word is a valid length, contains valid characters,
     * and is in the word list, then it's assigned to playerWord.
     */
    public void askForWord() {
        String guess = SCAN.next().toUpperCase();
        if (validWord(guess)) {
            playerWord = guess;
        }
        separateIntoLetters(playerWord);
    }

    /**
     * Replaces the character (String) at a specific index position.
     *
     * @param indexPosition the index (int) position of the character to be changed.
     * @param character the character (String) to be added to the specified index position.
     */
    @Override
    public void replaceLetterAtIndexPosition(final String character, final int indexPosition) {
        playerWordLetters[indexPosition] = character.toUpperCase();
    }

    /**
     * Returns the player word
     *
     * @return the player word (String).
     */
    public String getPlayerWord() {
        return playerWord;
    }

    /**
     * Returns the player word as an array letters.
     *
     * @return the player words as an array (String[]).
     */
    public String[] getPlayerWordLetters() {
        return playerWordLetters;
    }

    /*
     * Breaks validated player guess into an array of letters.
     */
    private void separateIntoLetters(final String word) {
        playerWordLetters = word.toUpperCase().split("");
    }

    /*
     * Checks if the player word is valid.
     */
    private boolean validWord(final String word) {
        return containsValidCharacters(word) && isValidLength(word) && isInWordList(word);
    }

    /*
     * Checks that the player word contains valid characters.
     */
    private boolean containsValidCharacters(final String word) {
        return word.matches(VALID_CHARACTERS);
    }

    /*
     * Checks that the player word is the correct length.
     */
    private boolean isValidLength(final String word) {
        return word.length() == LETTERS_PER_WORD;
    }

    /*
     * Checks that the player word is in the word list.
     */
    private boolean isInWordList(final String word) {
        return Arrays.asList(WordList.WORDS).contains(word);
    }
}
