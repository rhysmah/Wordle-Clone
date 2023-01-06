package com.example.wordleclonerebuild;

/**
 * Represents a general written word.
 *
 * @author Mahannah
 * @version 6 January 2023
 */
public abstract class Word {

    private String[] wordLetters;

    /**
     * Creates an object of type word.
     */
    public Word() {}

    /**
     * Returns an array of letters.
     *
     * @return an array of letters (String[]).
     */
    public String[] getLetters() {
        return wordLetters;
    }
}
