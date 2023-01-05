package com.example.wordleclonerebuild;

import java.util.Arrays;

public class PlayerWord implements Updatable {

    private static final int LETTERS_PER_WORD    = 5;
    private static final String VALID_CHARACTERS = "^[a-zA-Z]*$";

    private final String[] playerWordLetters;

    public PlayerWord() {
        this.playerWordLetters = new String[LETTERS_PER_WORD];
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
     * Returns the player word as an array letters.
     *
     * @return the player words as an array (String[]).
     */
    public String[] getPlayerWordLetters() {
        return playerWordLetters;
    }

    /*
     * Checks if the player word is valid.
     */
    public boolean validWord() {
        String word = String.join("", playerWordLetters);
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
