package com.example.wordleclonerebuild;

import java.util.Random;

/**
 * Creates a game word for Wordle.
 *
 * @author Mahannah
 * @version 3 January 2023
 */
public class GameWord {

    private static final Random RANDOM = new Random();
    private final String gameWord;
    private final String[] gameWordLetters;

    /**
     * Creates an object of type GameWord.
     */
    public GameWord() {
        gameWord = selectRandomWord(WordList.WORDS);
        gameWordLetters = getGameWordLetters();
    }

    /**
     * Returns the gameWord.
     *
     * @return the gameWord (String).
     */
    public String getGameWord() {
        return gameWord;
    }

    /**
     * Replaces the character (String) at a specific index position.
     *
     * @param index the index (int) position of the character to be changed.
     * @param character the character (String) to be added to the specified index position.
     */
    public void setGameWordLetter(final int index, final String character) {
        gameWordLetters[index] = character;
    }

    /*
     * Splits the gameWord into an array of letters (single-character Strings).
     */
    private String[] getGameWordLetters() {
        return gameWord.split("");
    }

    /*
     * Selects a word a random from a list of words.
     */
    private String selectRandomWord(final String[] listOfWords) {
        if (listOfWords.length == 0) {
            return "";
        }
        return listOfWords[RANDOM.nextInt(WordList.WORDS.length - 1)];
    }
}
