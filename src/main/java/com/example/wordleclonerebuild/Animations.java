package com.example.wordleclonerebuild;

import javafx.animation.ScaleTransition;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * A class static methods for animating the Wordle board.
 *
 * @author Mahannah
 * @version 4 January 2023
 */
public final class Animations {

    private static final int WIGGLE_ANIMATION_IN_MS = 100;
    private static final int FLIP_ANIMATION_IN_MS   = 250;

    /*
     * Contains only public static methods.
     */
    private Animations() {}

    private static Animation createLetterFlipAnimation(final Label letterBox, final int startPosition, final int endPosition) {
        ScaleTransition flipAnimation = new ScaleTransition(Duration.millis(FLIP_ANIMATION_IN_MS), letterBox);
        flipAnimation.setFromY(startPosition);
        flipAnimation.setToY(endPosition);
        return flipAnimation;
    }

    private static Animation createWiggleAnimation(final Label letterBox) {
        TranslateTransition wiggleAnimation = new TranslateTransition(Duration.millis(WIGGLE_ANIMATION_IN_MS), letterBox);
        wiggleAnimation.setToX(10);
        wiggleAnimation.setAutoReverse(true);
        wiggleAnimation.setCycleCount(4);
        return wiggleAnimation;
    }

    public static void playWiggleAnimation(final Label letterBox) {
        Animation wiggleAnimation = createWiggleAnimation(letterBox);
        wiggleAnimation.play();
    }

    public static void playFlipAnimation(final Label letterBox) {
        Animation animationOne = createLetterFlipAnimation(letterBox, 1, 0);
        Animation animationTwo = createLetterFlipAnimation(letterBox, 0, 1);
        SequentialTransition sequentialAnimations = new SequentialTransition(animationOne, animationTwo);
        sequentialAnimations.play();
    }
}
