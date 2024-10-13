//Board class, determines logic for the GUI.
//Some of it is leftover from my previous attempt, I'm uncertain if all of it is necessary

class Board {
	//Declaration of variables
    private char[][] grid;
    private int size;
    private GUI.Type type;

    //Creates grid with '-' as empty placeholders
    public Board(int size, GUI.Type type) {
        this.size = size;
        this.type = type;
        grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-';
            }
        }
    }

    //Receives a play from GUI, calculating score and filling the board
    public boolean play(int row, int col, char letter, Player player) {
        if (validMove(row, col)) {
            grid[row][col] = letter;
            if (win(row, col, player)) {
                player.addScore();
            }
            return true;
        }
        return false;
    }

    //Checks for filled grid spot
    private boolean validMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && grid[row][col] == '-';
    }

    //Unimplemented as it is not required yet
    private boolean win(int row, int col, Player player) {
        //Write code to check for win conditions in future sprints
        return false;
    }

    //Determines if the board is full, run directly from GUI
    public boolean full() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    //Getters for testing purposes
    public int getSize() {
        return size;
    }

    public GUI.Type getType() {
        return type;
    }

    public char getCell(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return grid[row][col];
        }
        throw new IndexOutOfBoundsException("Invalid move");
    }
}
