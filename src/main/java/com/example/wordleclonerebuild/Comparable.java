package com.example.wordleclonerebuild;

public interface Comparable {
    boolean lettersAreEqual(final String playerLetter, final String gameLetter);
    boolean letterIsInWord(final String letter, final Updatable[] word);
}
