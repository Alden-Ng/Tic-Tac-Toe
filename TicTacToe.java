/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_1.pkg1;

import java.util.Scanner;

/**
 *
 * @author alden
 */
public final class TicTacToe {
    
    //main method to test the game
        public static void main(String args[]) {
        TicTacToe game = new TicTacToe('X');
        Scanner scanner = new Scanner(System.in);

        do { 
            System.out.println(game.toString());
            System.out.println(game.getTurn() + 
                ": Where do you want to mark? Enter row column");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
                    
            scanner.nextLine();
            game.takeTurn(row, column);
            
        } while (game.getGameState() == TicTacToeEnum.IN_PROGRESS);
        System.out.println( game.getGameState());
       
    }

    //creating all the variables
    private int nRows;
    private int nColumns;
    private int numToWin;
    private char[][] grid;
    private char turn;
    TicTacToeEnum gameState;
    private int nMarks;

    //creates a standard tictactoe game
    public TicTacToe(char initialTurn) {
        this.nRows = 3;
        this.nColumns = 3;
        this.numToWin = 3;
        reset(initialTurn);
    }

    /**
     *
     * @param nRows
     * @param nColumns
     * @param numToWins
     * @param initialTurn
     */
    //creates a custom tictactoe game
    public TicTacToe(int nRows, int nColumns, int numToWins, char initialTurn) {
        if (nRows < 0 || nColumns < 0 || numToWins < 0) {
            throw new IllegalArgumentException("Must have positive values for Rows, Columns, and Number of Wins.");
        }
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.numToWin = numToWins;
        this.turn = initialTurn;
        reset(initialTurn);
    }

    //resets the board to empty
    public void reset(char initialTurn) {
        this.turn = initialTurn;
        this.grid = new char[this.nRows][this.nColumns];
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nColumns; j++) {
                this.grid[i][j] = ' ';
            }
        }
        this.nMarks = 0;
        this.gameState = TicTacToeEnum.IN_PROGRESS;
    }

    //gets whose turn it is
    public char getTurn() {
        return turn;
    }

    // gets the game state
    public TicTacToeEnum getGameState() {
        return gameState;
    }

    // checks who won the game
    private TicTacToeEnum charToEnum(char player) {
        if (player == 'X') {
            gameState = TicTacToeEnum.X_WON;
        } else {
            gameState = TicTacToeEnum.O_WON;
        }
        return gameState;
    }
    
    // allows the user to take their turn
    public TicTacToeEnum takeTurn(int row, int column) {
        if (grid[row][column] != ' ') {
            System.out.println("The spot that has been selected is already taken");
            return gameState;
        }
        grid[row][column] = turn;
        nMarks++;
        findWinner();
        if (this.turn == 'X') {
            this.turn = 'O';
        } else if (this.turn == 'O') {
            this.turn = 'X';
        }

        return gameState;
    }
    //determines whether or not someone won
    private TicTacToeEnum findWinner() {
        int winC = 0;
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                
                //checking horizontal win
                for (int k = 0; k < numToWin; k++) {
                    if (j + k < nColumns) {
                        if (grid[i][j + k] == turn) {
                            winC++;
                            if (winC == numToWin) {
                                charToEnum(turn);
                                return gameState;
                            }
                        } else {
                            winC = 0;
                        }
                    }
                }
                winC = 0;
                
                //checking vertical win 
                for (int k = 0; k < numToWin; k++) {

                    if (i + k < nRows) {
                        if (grid[i + k][j] == turn) {
                            winC++;
                            if (winC == numToWin) {
                                charToEnum(turn);
                                return gameState;
                            }
                        } else {
                            winC = 0;
                        }
                    }
                }
                winC = 0;

                //diagonal bottom right win check
                for (int k = 0; k < numToWin; k++) {
                    if (i + k < nRows && j + k < nColumns) {
                        if (grid[i + k][j + k] == turn) {
                            winC++;
                            if (winC == numToWin) {
                                charToEnum(turn);
                                return gameState;
                            }
                        } else {
                            winC = 0;
                        }
                    }
                }
                winC = 0;

                //diagonal bottom left win check
                for (int k = 0; k < numToWin; k++) {
                    if (i - k >= 0 && j + k < nColumns) {
                        if (grid[i - k][j + k] == turn) {
                            winC++;
                            if (winC == numToWin) {
                                charToEnum(turn);
                                return gameState;
                            }
                        } else {
                            winC = 0;
                        }
                    }
                }
                winC = 0;
            }
        }

        if (nRows * nColumns == nMarks) {
            gameState = TicTacToeEnum.DRAW;
        }
        return gameState;

    }

    // turning the board into a string value
    @Override
    public String toString() {
        String sGrid = "";
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                sGrid += Character.toString(grid[i][j]) + " | ";
            }
            sGrid += "\n";
        }
        return sGrid;
    }
}
