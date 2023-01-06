package com.example.wordleclonerebuild;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

import java.util.Arrays;

/**
 * Controls the main game screen.
 *
 * @author Mahannah
 * @version 6 January 2023
 */
public class WordleController {

    private static final String DEFAULT_LETTERBOX_COLOR     = "-fx-background-color: #DCDCDC";
    private static final String DEFAULT_LETTERBOX_CHARACTER = "";
    private static final int    NUM_ROWS_PER_GAMEBOARD      = 6;
    private static final int    NUM_LETTERS_PER_ROW         = 5;

    private final Updatable    gameWord;
    private final Updatable    playerWord;
    private final WinCondition WinCondition;

    @FXML private Label letter1_1;
    @FXML private Label letter1_2;
    @FXML private Label letter1_3;
    @FXML private Label letter1_4;
    @FXML private Label letter1_5;

    @FXML private Label letter2_1;
    @FXML private Label letter2_2;
    @FXML private Label letter2_3;
    @FXML private Label letter2_4;
    @FXML private Label letter2_5;

    @FXML private Label letter3_1;
    @FXML private Label letter3_2;
    @FXML private Label letter3_3;
    @FXML private Label letter3_4;
    @FXML private Label letter3_5;

    @FXML private Label letter4_1;
    @FXML private Label letter4_2;
    @FXML private Label letter4_3;
    @FXML private Label letter4_4;
    @FXML private Label letter4_5;

    @FXML private Label letter5_1;
    @FXML private Label letter5_2;
    @FXML private Label letter5_3;
    @FXML private Label letter5_4;
    @FXML private Label letter5_5;

    @FXML private Label letter6_1;
    @FXML private Label letter6_2;
    @FXML private Label letter6_3;
    @FXML private Label letter6_4;
    @FXML private Label letter6_5;

    @FXML private Button startGame;
    @FXML private Button quitGame;

    private Label[][] gameBoard;

    private int currentLetterIndex;
    private int currentRowIndex;

    /**
     * Controls the object of type GameBoardApplication.
     */
    public WordleController() {
        this.currentLetterIndex = 0;
        this.currentRowIndex    = 0;
        this.WinCondition       = new WinCondition();
        this.playerWord         = new PlayerWord();
        this.gameWord           = new GameWord();
    }

    /**
     * Initializes the gameboard and buttons.
     */
    public final void initializeGameScreenControls() {
        initializeGameBoardButtons();
        initializeGameBoard();

        GameWord gameWordObject = (GameWord) gameWord;
        gameWordObject.setGameWord();
    }

    /**
     * Resets the gameboard. Erases all letters and resets the letter and row indices.
     */
    public void onPlayAgainButtonClick() {
        clearGameBoard();

        GameWord gameWordObject = (GameWord) gameWord;
        gameWordObject.setGameWord();

        currentLetterIndex = 0;
        currentRowIndex = 0;
    }

    /**
     * Quits the application.
     */
    public void onQuitGameButtonClick() {
        Platform.exit();
    }

    /**
     * Checks if the player entered a valid word; if not, the player must enter a new word.
     * <p>
     * If the player word is valid, it's compared against the game word. After each turn,
     * the game checks if the player (1) guessed the correct word or (2) ran out of turns.
     * <p>
     * If either end-game condition is met, the game ends, and a window pops up telling
     * the player if they won or lost.
     */
    protected void enterKeyPushed() {

        PlayerWord playerWordObject = (PlayerWord) playerWord;
        GameWord gameWordObject     = (GameWord) gameWord;

        if (playerWordObject.notValidWord()) {
            for (Label letterBox : gameBoard[currentRowIndex]) {
                Animations.playWiggleAnimation(letterBox);
            }
        } else {
            String[] remainingLettersInWord = gameWordObject.getLetters();

            checkForAndAnimateGreenAndGreyLetters(playerWordObject, gameWordObject, remainingLettersInWord);
            checkForAndAnimateYellowLetters(playerWordObject, gameWordObject, remainingLettersInWord);

            currentRowIndex++;
            currentLetterIndex = 0;
        }
        checkEndGameConditions();
    }

    /*
     * Checks if the player guessed the correct word or ran out of turns.
     */
    private void checkEndGameConditions() {

        GameWord gameWordObject     = (GameWord) gameWord;

        if (noRowsRemaining()) {
            PopUpWindow.display("You lose!", "The word was " + gameWordObject.getGameWord());
        }
        if (WinCondition.satisfied()) {
            PopUpWindow.display("You win!", "The word was indeed " + gameWordObject.getGameWord());
        }
    }

    /*
     * Checks the following:
     * (1) If the player's guess had letters in the exact same position as the game word, the letterbox turns green.
     * (2) If the player's guess contains letters that are NOT in the game word, the letterbox stays grey.
     */
    private void checkForAndAnimateGreenAndGreyLetters(PlayerWord playerWordObject, GameWord gameWordObject, String[] remainingLettersInWord) {
        for (int index = 0; index < NUM_LETTERS_PER_ROW; index++) {

            if (WinCondition.lettersAreEqual(playerWordObject.getLetters()[index], gameWordObject.getLetters()[index])) {
                remainingLettersInWord[index] = "";
                Animations.playFlipAnimation(gameBoard[currentRowIndex][index], Animations.Colors.GREEN);
                WinCondition.updateWinCondition(index, true);

            } else {
                Animations.playFlipAnimation(gameBoard[currentRowIndex][index], Animations.Colors.GREY);
            }
        }
    }

    /*
     * Checks if the player's guess had letters in the game word, but in the wrong position. If so, those
     * letterboxes turn yellow.
     */
    private void checkForAndAnimateYellowLetters(PlayerWord playerWordObject, GameWord gameWordObject, String[] remainingLettersInWord) {
        for (int index = 0; index < NUM_LETTERS_PER_ROW; index++) {

            if (Arrays.asList(remainingLettersInWord).contains(playerWordObject.getLetters()[index])
                    && !playerWordObject.getLetters()[index].equals(gameWordObject.getLetters()[index])) {

                int letterToRemove = Arrays.asList(remainingLettersInWord).indexOf(playerWordObject.getLetters()[index]);
                remainingLettersInWord[letterToRemove] = "";
                Animations.playFlipAnimation(gameBoard[currentRowIndex][index], Animations.Colors.YELLOW);
            }
        }
    }

    /**
     * Adds a letter to the gameboard.
     *
     * @param letter the letter to be added to the gameboard.
     */
    protected void letterKeyPushed(final String letter) {
        if (currentLetterIndex < NUM_LETTERS_PER_ROW) {
            gameBoard[currentRowIndex][currentLetterIndex].setText(letter);
            playerWord.updateLetterAtIndexPosition(letter, currentLetterIndex);
            currentLetterIndex++;
        }
    }

    /*
     * Checks if there are still player turns remaining -- i.e., there are rows left.
     */
    private boolean noRowsRemaining() {
        return currentRowIndex == NUM_ROWS_PER_GAMEBOARD;
    }

    /**
     * Deletes the last inputted letter.
     */
    protected void backspaceKeyPushed() {
        if (currentLetterIndex > 0) {
            currentLetterIndex--;
            gameBoard[currentRowIndex][currentLetterIndex].setText(DEFAULT_LETTERBOX_CHARACTER);
        }
    }

    private void clearGameBoard() {
        for (Label[] row : gameBoard) {
            for (Label letter : row) {
                letter.setText(DEFAULT_LETTERBOX_CHARACTER);
                letter.setStyle(DEFAULT_LETTERBOX_COLOR);
            }
        }
    }

    private void initializeGameBoard() {
        gameBoard = new Label[][] {
                {letter1_1, letter1_2, letter1_3, letter1_4, letter1_5},
                {letter2_1, letter2_2, letter2_3, letter2_4, letter2_5},
                {letter3_1, letter3_2, letter3_3, letter3_4, letter3_5},
                {letter4_1, letter4_2, letter4_3, letter4_4, letter4_5},
                {letter5_1, letter5_2, letter5_3, letter5_4, letter5_5},
                {letter6_1, letter6_2, letter6_3, letter6_4, letter6_5}
        };
    }

    private void initializeGameBoardButtons() {
        startGame.setOnAction(actionEvent -> onPlayAgainButtonClick());
        quitGame.setOnAction(actionEvent  -> onQuitGameButtonClick());
    }
}
