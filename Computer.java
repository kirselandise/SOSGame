//Computer class that is a subclass of Player class

class Computer extends Player {
	//This is my best attempt to try and prevent repetition when determining a win con as we discussed after class. I feel like it only makes things more convoluted/less readable
	//but I tried breaking it up into smaller methods per your requests in class
	
	//Constant DIRECTIONS to determine which way to look for win con
    private static final int[][] DIRECTIONS = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1},
        {1, 1},
        {-1, -1},
        {1, -1},
        {-1, 1}
    };

    public Computer(String name) {
        super(name);
    }

    @Override
    public int[] makeMove(Game board, int row, int col, char selectedMove) {
    	//Determines if there is a scoring play to make
        int[] scoringMove = canScore(board);
        if (scoringMove != null) {
            return scoringMove;
        }
        //If not, designates a random move
        do {
            row = (int) (Math.random() * board.getSize());
            col = (int) (Math.random() * board.getSize());
        } while (!board.validMove(row, col));
        int moveType = Math.random() < 0.5 ? 0 : 1;
        return new int[]{row, col, moveType};
    }

    //Checks entire board with helper functions to determine scoring play
    private int[] canScore(Game board) {
        int size = board.getSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!board.validMove(row, col)) continue;
                if (findS(board, row, col)) {
                    return new int[]{row, col, 0};
                }
                if (findO(board, row, col)) {
                    return new int[]{row, col, 1};
                }
            }
        }
        return null;
    }

    //Checks for "SO" or "OS" pattern
    private boolean findS(Game board, int row, int col) {
        for (int[] dir : DIRECTIONS) {
            if (validS(board, row, col, dir[0], dir[1])) {
                return true;
            }
        }
        return false;
    }

    //Checks for "S-S" pattern
    private boolean findO(Game board, int row, int col) {
        for (int[] dir : DIRECTIONS) {
            if (validO(board, row, col, dir[0], dir[1])) {
                return true;
            }
        }
        return false;
    }

    //Makes sure that the S placement is valid
    //Next is +1, next next is +2
    private boolean validS(Game board, int row, int col, int dRow, int dCol) {
        int nextRow = row + dRow;
        int nextNextRow = row + 2 * dRow;
        int nextCol = col + dCol;
        int nextNextCol = col + 2 * dCol;

        return inBounds(board, nextRow, nextCol) && 
               inBounds(board, nextNextRow, nextNextCol) &&
               board.getCell(nextRow, nextCol) == 'O' &&
               board.getCell(nextNextRow, nextNextCol) == 'S';
    }

    //Makes sure that the 0 placement is valid
    //Next is +1, previous is -1
    private boolean validO(Game board, int row, int col, int dRow, int dCol) {
        int prevRow = row - dRow;
        int nextRow = row + dRow;
        int prevCol = col - dCol;
        int nextCol = col + dCol;

        return inBounds(board, prevRow, prevCol) && 
               inBounds(board, nextRow, nextCol) &&
               board.getCell(prevRow, prevCol) == 'S' &&
               board.getCell(nextRow, nextCol) == 'S';
    }

    //Helper method for the valid'' methods
    private boolean inBounds(Game board, int row, int col) {
        int size = board.getSize();
        return row >= 0 && row < size && col >= 0 && col < size;
    }
}