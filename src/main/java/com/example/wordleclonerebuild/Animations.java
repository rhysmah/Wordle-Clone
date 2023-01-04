package com.example.wordleclonerebuild;

import javafx.animation.TranslateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Animation;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * A class static methods for animating the Wordle board.
 *
 * @author Mahannah
 * @version 4 January 2023
 */
public final class Animations {

    private static final int WIGGLE_ANIMATION_DURATION_IN_MS = 100;
    private static final int FLIP_ANIMATION_DURATION_IN_MS   = 250;

    /*
     * The background of the player guess letters will change based on certain conditions.
     */
    enum Colors {
        GREEN("#32CD32"),
        YELLOW("#FFD700");

        private final String colorValue;

        Colors(final String colorValue) {
            this.colorValue = colorValue;
        }

        public String getColorValue() {
            return colorValue;
        }
    }

    /*
     * Contains only public static methods.
     */
    private Animations() {
    }

    /**
     * Plays a wiggle animation on the specified object.
     *
     * @param letterBox the object to be animated.
     */
    public static void playWiggleAnimation(final Label letterBox) {
        Animation wiggleAnimation = createWiggleAnimation(letterBox);
        wiggleAnimation.play();
    }

    /**
     * Plays a flip animation on the specified object.
     *
     * @param letterBox the object to be animated.
     */
    public static void playFlipAnimation(final Label letterBox, final boolean letterPosition) {
        Animation animationOne = createFlipAnimation(letterBox, 1, 0);
        Animation animationTwo = createFlipAnimation(letterBox, 0, 1);

        animationOne.play();
        animationOne.setOnFinished(actionEvent -> {
            if (letterPosition) {
                updateContainerColor(letterBox, Colors.GREEN.getColorValue());
            } else {
                updateContainerColor(letterBox, Colors.YELLOW.getColorValue());
            }
            animationTwo.play();
        });
    }

    private static void updateContainerColor(final Label letterBox, final String color) {
        letterBox.setStyle("-fx-background-color:" + color);
    }

    /*
     * A flip animation that gives the illusion the object's rotating on its y-axis.
     */
    private static Animation createFlipAnimation(final Label letterBox, final int startPosition, final int endPosition) {
        ScaleTransition flip = new ScaleTransition(Duration.millis(FLIP_ANIMATION_DURATION_IN_MS), letterBox);
        flip.setFromY(startPosition);
        flip.setToY(endPosition);
        return flip;
    }

    /*
     * Makes the object wiggle back and forth.
     */
    private static Animation createWiggleAnimation(final Label letterBox) {
        TranslateTransition wiggle = new TranslateTransition(Duration.millis(WIGGLE_ANIMATION_DURATION_IN_MS), letterBox);
        wiggle.setToX(10);
        wiggle.setAutoReverse(true);
        wiggle.setCycleCount(4);
        return wiggle;
    }
}
