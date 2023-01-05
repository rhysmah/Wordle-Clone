package com.example.wordleclonerebuild;

import java.util.Random;

/**
 * Creates a game word for Wordle.
 *
 * @author Mahannah
 * @version 5 January 2023
 */
public class GameWord implements Updatable {

    private static final Random RANDOM = new Random();
    private String gameWord;
    private String[] gameWordLetters;

    /**
     * Creates an object of type GameWord.
     */
    public GameWord() {
        gameWord = "";
    }

    /**
     * Selects a random word as the game word and separates the game word into an array of letters
     */
    public void setGameWord() {
        gameWord = selectRandomWord();
        gameWordLetters = separateIntoLetters();
    }

    /**
     * Returns the game word.
     *
     * @return the gameWord (String).
     */
    public String getGameWord() {
        return gameWord;
    }

    /**
     * Returns the game word letters.
     *
     * @return the gameWordLetters (a String array).
     */
    public String[] getGameWordLetters() {
        return gameWordLetters;
    }

    /**
     * Replaces the character (String) at a specific index position.
     *
     * @param indexPosition the index (int) position of the character to be changed.
     * @param character the character (String) to be added to the specified index position.
     */
    @Override
    public void updateLetterAtIndexPosition(final String character, final int indexPosition) {
        gameWordLetters[indexPosition] = character.toUpperCase();
    }

    /*
     * Splits the gameWord into an array of letters (single-character Strings).
     */
    private String[] separateIntoLetters() {
        return gameWord.toUpperCase().split("");
    }

    /*
     * Selects a word a random from a list of words.
     */
    private String selectRandomWord() {
        return WordList.WORDS[RANDOM.nextInt(WordList.WORDS.length - 1)];
    }
}
