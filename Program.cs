using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TTTConsoleGameApp

{
    enum TicTacToeEnum
    {
        IN_PROGRESS, X_WON, O_WON, DRAW
    }

    class TicTacToe
    {
        private int nRows;
        private int nColumns;
        private int num_to_win;
        private char[,] grid;
        private char turn;
        TicTacToeEnum gameState;
        private int nMarks;

        public TicTacToe(char initialTurn)
        {
            this.nRows = 3;
            this.nColumns = 3;
            this.num_to_win = 3;
            Reset(initialTurn);
        }
        public TicTacToe(int nRows, int nColumns, int num_to_win, char initialTurn)
        {
            if(nRows < 0 || nColumns < 0 || num_to_win < 0)
            {
                throw new Exception("Must have positive values for Rows, Columns, and Number of Wins");
            }
            this.nRows = nRows;
            this.nColumns = nColumns;
            this.num_to_win = num_to_win;
            Reset(initialTurn);
        }

        private void Reset(char initialTurn)
        {
            this.turn = initialTurn;
            grid = new char[nRows, nColumns];
            for(int i=0; i<nRows; i++)
            {
                for(int j = 0; j<nColumns; j++)
                {
                    grid[i, j] = ' ';
                }
            }
            nMarks = 0;
            gameState = TicTacToeEnum.IN_PROGRESS;

        }

        private char GetTurn()
        {
            return turn;
        }

        private TicTacToeEnum GetGameState()
        {
            return gameState;
        }

        private TicTacToeEnum CharToEnum(char player)
        {
            if(player == 'X')
            {
                gameState = TicTacToeEnum.X_WON;
            }
            else
            {
                gameState = TicTacToeEnum.O_WON;
            }
            return gameState;
        }

        public TicTacToeEnum TakeTurn(int row, int column)
        {
            if(grid[row, column] !=' ')
            {
                Console.WriteLine("The spot that has been selected is already taken");
                return gameState;
            }

            grid[row, column] = turn;
            nMarks++;
            findWinner();
            if(turn == 'X')
            {
                turn = 'O';
            }
            else if(turn == 'O')
            {
                turn = 'X';
            }

            return gameState;
        }

        private TicTacToeEnum findWinner()
        {
            int winC = 0;
            for(int i = 0; i < nRows; i++)
            {
                for(int j = 0; j < nColumns; j++)
                {
                    //checking horizontal win
                    for(int k = 0; k < num_to_win; k++)
                    {
                        if (j + k < nColumns) {
                            if (grid[i, j + k] == turn)
                            {
                                winC++;
                                if(winC == num_to_win)
                                {
                                    CharToEnum(turn);
                                    return gameState;
                                }
                            }
                            else
                            {
                                winC = 0;
                            }
                        }
                    }
                    winC = 0;

                    //check vertical win
                    for (int k = 0; k < num_to_win; k++)
                    {
                        if (i + k < nRows)
                        {
                            if (grid[i+k, j] == turn)
                            {
                                winC++;
                                if (winC == num_to_win)
                                {
                                    CharToEnum(turn);
                                    return gameState;
                                }
                            }
                            else
                            {
                                winC = 0;
                            }
                        }
                    }
                    winC = 0;

                    //check for bottom right diagonal win
                    for (int k = 0; k < num_to_win; k++)
                    {
                        if (i + k < nRows && j + k < nColumns)
                        {
                            if (grid[i + k, j + k] == turn)
                            {
                                winC++;
                                if (winC == num_to_win)
                                {
                                    CharToEnum(turn);
                                    return gameState;
                                }
                            }
                            else
                            {
                                winC = 0;
                            }
                        }
                    }
                    winC = 0;

                    //check for bottom left diagonal win
                    for (int k = 0; k < num_to_win; k++)
                    {
                        if (i - k >= 0 && j + k < nColumns)
                        {
                            if (grid[i - k, j + k] == turn)
                            {
                                winC++;
                                if (winC == num_to_win)
                                {
                                    CharToEnum(turn);
                                    return gameState;
                                }
                            }
                            else
                            {
                                winC = 0;
                            }
                        }
                    }
                    winC = 0;
                }

                
             
            }

            if (nRows * nColumns == nMarks)
            {
                gameState = TicTacToeEnum.DRAW;
            }

            return gameState;
        }

        public override string ToString()
        {
            string sGrid = "";
            for(int i = 0; i < nRows; i++)
            {
                for(int j = 0; j < nColumns; j++)
                {
                    sGrid += Char.ToString(grid[i, j]) + " | ";
                }
                sGrid += "\n";
            }
            return sGrid;
        }

        static void Main(string[] args)
        {
            TicTacToe game = new TicTacToe('X');
            do
            {
                Console.WriteLine(game.ToString());
                Console.WriteLine(game.GetTurn() + ": Where do you want to mark? Enter your Row");
                int row = Convert.ToInt32(Console.ReadLine());
                Console.WriteLine("Enter your Column");
                int column = Convert.ToInt32(Console.ReadLine());
                game.TakeTurn(row, column);
            }
            while (game.GetGameState() == TicTacToeEnum.IN_PROGRESS);
            Console.WriteLine(game.GetGameState());
        }
    }
}
