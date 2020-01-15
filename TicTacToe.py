from enum import Enum


class TicTacToeEnum(Enum):
    IN_PROGRESS = 1
    X_WON = 2
    O_WON = 3
    DRAW = 4


class TicTacToe:

    __n_rows = 0
    __n_columns = 0
    __num_to_win = 0
    __grid = []
    __turn = None
    __game_state = None
    __n_marks = 0

    def __init__(self, initial_turn, n_rows=3, n_columns=3, num_to_win=3):
        if (n_rows < 1) or (n_columns < 1) or (num_to_win < 1):
            raise Exception('Rows, Columns, and Num to Win must be at least 1')

        self.__n_rows = n_rows
        self.__n_columns = n_columns
        self.__num_to_win = num_to_win
        self.reset(initial_turn)

    def reset(self, initial_turn):
        self.__turn = initial_turn
        # self.__grid = [[' ']*self.__n_rows]*self.__n_columns
        for i in range(self.__n_rows):
            self.__grid.append([" "] * self.__n_columns)
        self.__n_marks = 0
        self.__game_state = TicTacToeEnum.IN_PROGRESS

    def get_turn(self):
        return self.__turn

    def get_game_state(self):
        return self.__game_state

    def __char_to_enum(self, player):
        if player == 'X':
            self.__game_state = TicTacToeEnum.X_WON
        else:
            self.__game_state = TicTacToeEnum.O_WON
        return self.__game_state

    def take_turn(self, row, column):
        if self.__grid[row][column] != ' ':
            print("The spot that has been selected is already taken")
            return self.__game_state

        print(self.str())

        self.__grid[row][column] = self.__turn
        print(row)
        print(self.__turn)
        self.__n_marks += 1
        print(self.str())

        # find the winner
        self.__find_winner()
        if self.__turn == 'X':
            self.__turn = 'O'
        elif self.__turn == 'O':
            self.__turn = 'X'
        return self.__game_state

    def __find_winner(self):
        # win condition counter
        win_c = 0
        grid = self.__grid
        for i in range(self.__n_rows):
            for j in range(self.__n_columns):

                # checking horizontal win
                for k in range(self.__num_to_win):
                    if j + k < self.__n_columns:
                        if grid[i][j + k] == self.__turn:
                            win_c += 1
                            if win_c == self.__num_to_win:
                                self.__char_to_enum(self.__turn)
                                return self.__game_state
                        else:
                            win_c = 0
                win_c = 0

                # checking vertical win
                for k in range(self.__num_to_win):
                    if i + k < self.__n_rows:
                        if grid[i+k][k] == self.__turn:
                            win_c += 1
                            if win_c == self.__num_to_win:
                                self.__char_to_enum(self.__turn)
                                return self.__game_state
                        else:
                            win_c = 0
                win_c = 0

                # checking diagonal win
                # checking bottom right
                for k in range(self.__num_to_win):
                    if (i + k) < self.__n_rows and (j + k) < self.__n_columns:
                        if grid[i+k][j+k] == self.__turn:
                            win_c += 1
                            if win_c == self.__num_to_win:
                                self.__char_to_enum(self.__turn)
                                return self.__game_state
                        else:
                            win_c = 0
                win_c = 0

                # checking bottom left
                for k in range(self.__num_to_win):
                    if (i - k) >= 0 and (j + k) < self.__n_columns:
                        if grid[i-k][j+k] == self.__turn:
                            win_c += 1
                            if win_c == self.__num_to_win:
                                self.__char_to_enum(self.__turn)
                                return self.__game_state
                        else:
                            win_c = 0

        if self.__n_rows * self.__n_columns == self.__n_marks:
            self.__game_state = TicTacToeEnum.DRAW
        return self.__game_state

    def get_info(self):
        return "n_rows = {}, n_columns = {}, num_to_win = {}, turn = {}, grid = {}" .format(self.__n_rows,
                                                                                            self.__n_columns,
                                                                                            self.__num_to_win,
                                                                                            self.__turn,
                                                                                            self.__grid)

    def str(self):
        string = ""
        for i in range(self.__n_rows):
            for j in range(self.__n_columns):
                string += self.__grid[i][j] + " | "
            string += "\n"
        return string


if __name__ == "__main__":
    # print(repr(TicTacToeEnum.IN_PROGRESS))  # testing enum in python
    game = TicTacToe('X')
    while game.get_game_state() == TicTacToeEnum.IN_PROGRESS:
        print(game.str())
        print(game.get_turn(), ": Where do you want to mark?")
        row = int(input("Enter row\n"))
        column = int(input("Enter column\n"))
        game.take_turn(row, column)
    print(game.get_game_state())
