package com.example.wordleclonerebuild;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

/**
 * Controls the main game screen.
 *
 * @author Mahannah
 * @version 5 January 2023
 */
public class WordleController {

    private static final int LETTERS_PER_ROW    = 5;
    private static final int ROWS_PER_GAMEBOARD = 6;

    private final Updatable gameWord;
    private final Updatable playerWord;
    private final GameWinCondition GameWinCondition;

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
        this.gameWord           = new GameWord();
        this.playerWord         = new PlayerWord();
        this.GameWinCondition = new GameWinCondition();
    }

    /**
     * Initializes the gameboard and buttons.
     */
    public final void initializeGameScreenControls() {
        initializeGameBoardButtons();
        initializeGameBoard();

        GameWord gameWordObject = (GameWord) gameWord;
        gameWordObject.setGameWord();
        System.out.println(gameWordObject.getGameWord());
    }

    /**
     * Resets the gameboard. Erases all letters and resets the letter and row indices.
     */
    public void onPlayAgainButtonClick() {
        clearGameBoard();

        GameWord gameWordObject = (GameWord) gameWord;
        gameWordObject.setGameWord();
        System.out.println(gameWordObject.getGameWord());

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
     * Javadoc needed
     */
    protected void enterKeyPushed() {

        PlayerWord playerWordObject = (PlayerWord) playerWord;
        GameWord gameWordObject     = (GameWord) gameWord;

        if (!playerWordObject.validWord()) {
            for (int index = 0; index <LETTERS_PER_ROW; index++) {
                Animations.playWiggleAnimation(gameBoard[currentRowIndex][index]);
            }

        } else if (playerWordObject.validWord()) {
            String[] playerLetters = playerWordObject.getPlayerWordLetters();
            String[] gameLetters   = gameWordObject.getGameWordLetters();

            for (int index = 0; index <LETTERS_PER_ROW; index++) {
                checkAndAnimateLetters(gameWordObject, playerLetters, gameLetters, index);
            }
            currentRowIndex++;
            currentLetterIndex = 0;
        }
        if (noRowsRemaining()) {
            PopUpWindow.display("You lose!", "The word was " + gameWordObject.getGameWord());
        }
        if (GameWinCondition.satisfied()) {
            PopUpWindow.display("You win!", "The word was indeed " + gameWordObject.getGameWord());
        }
    }

    /**
     * Adds a letter to the gameboard.
     *
     * @param letter the letter to be added to the gameboard.
     */
    protected void letterKeyPushed(final String letter) {
        if (currentLetterIndex < LETTERS_PER_ROW) {
            gameBoard[currentRowIndex][currentLetterIndex].setText(letter);
            playerWord.updateLetterAtIndexPosition(letter, currentLetterIndex);
            currentLetterIndex++;
        }
    }

    /*
     * Checks if there are still player turns remaining -- i.e., there are rows left.
     */
    private boolean noRowsRemaining() {
        return currentRowIndex == ROWS_PER_GAMEBOARD;
    }

    /*
     * Compares letters in player word to letters in game word and animates the letters appropriately.
     */
    private void checkAndAnimateLetters(GameWord gameWordObject, String[] playerLetters, String[] gameLetters, int i) {
        if (GameWinCondition.lettersAreEqual(playerLetters[i], gameLetters[i])) {
            Animations.playFlipAnimation(gameBoard[currentRowIndex][i], Animations.Colors.GREEN);
            GameWinCondition.updateWinCondition(i, true);

        } else if (GameWinCondition.letterIsInWord(playerLetters[i], gameWordObject.getGameWord())) {
            Animations.playFlipAnimation(gameBoard[currentRowIndex][i], Animations.Colors.YELLOW);

        } else {
            Animations.playFlipAnimation(gameBoard[currentRowIndex][i], Animations.Colors.GREY);
        }
    }

    /**
     * Deletes the last inputted letter.
     */
    protected void backspaceKeyPushed() {
        if (currentLetterIndex > 0) {
            currentLetterIndex--;
            gameBoard[currentRowIndex][currentLetterIndex].setText("");
        }
    }

    private void clearGameBoard() {
        for (Label[] row : gameBoard) {
            for (Label letter : row) {
                letter.setText("");
                letter.setStyle("-fx-background-color: #DCDCDC");
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
