package com.example.wordleclonerebuild;

import java.util.Arrays;

/**
 * Represents the win condition array for a Wordle game.
 * @author Mahannah
 * @version 4 January 2023
 */
public class GameWinCondition implements Comparable {

    private final Boolean[] solution;

    /**
     * Creates an object of type winCondition.
     */
    public GameWinCondition() {
        this.solution = new Boolean[] {false, false, false, false, false};
    }

    /**
     * Updates the solution array with a specified Boolean value at the specified index.
     *
     * @param index the index position that's updated.
     * @param value the value to be added to the specified index position.
     */
    public void updateWinCondition(final int index, final boolean value) {
        solution[index] = value;
    }

    /**
     * Checks if the two specified letters are equivalent.
     *
     * @param firstLetter the first letter to be compared.
     * @param secondLetter the second letter to be compared.
     * @return true if the letters are equivalent, else false.
     */
    @Override
    public boolean lettersAreEqual(final String firstLetter, final String secondLetter) {
        return (firstLetter.equals(secondLetter));
    }

    /**
     * Checks if the specified letter is in the specified word.
     *
     * @param letter the letter (String) to be looked for in the word.
     * @param word the word to be evaluated.
     * @return true if the letter is in the word, else false.
     */
    @Override
    public boolean letterIsInWord(final String letter, final String word) {
        String[] lettersInWord = word.split("");
        return Arrays.asList(lettersInWord).contains(letter);
    }

    /**
     * Checks if the win condition is met -- i.e., the array contains only "true" values.
     *
     * @return true if the array contains all "true" values, else false.
     */
    public Boolean satisfied() {
        return Arrays.stream(solution).allMatch(booleanValue -> booleanValue);
    }
}
