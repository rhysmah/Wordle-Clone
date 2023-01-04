package com.example.wordleclonerebuild;

import java.util.Random;

/**
 * Creates a game word for Wordle.
 *
 * @author Mahannah
 * @version 3 January 2023
 */
public class GameWord implements Updatable {

    private static final Random RANDOM = new Random();
    private final String gameWord;
    private final String[] gameWordLetters;

    /**
     * Creates an object of type GameWord.
     */
    public GameWord() {
        gameWord = selectRandomWord();
        gameWordLetters = separateIntoLetters();
    }

    /**
     * Returns the gameWord.
     *
     * @return the gameWord (String).
     */
    public String getGameWord() {
        return gameWord;
    }

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
    public void replaceLetterAtIndexPosition(final String character, final int indexPosition) {
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
