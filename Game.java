//Board class, determines logic for the GUI.
//Some of it is leftover from my previous attempt, I'm uncertain if all of it is necessary

import java.awt.Color;

class Game {
	//Declaration of variables
    private char[][] grid;
    private int size;
    private GUI.Type type;

    //Creates grid with '-' as empty placeholders
    public Game(int size, GUI.Type type) {
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
            score(row, col, letter, player);
            return true;
        }
        return false;
    }
    
  //Checks for winning play
    void score(int row, int col, char letter, Player player) {
    	int size = GUI.boardSize();
    	size = size-1;
    	//Checks all 8 boxes for O's with a following S surrounding the played S
        if (letter == 'S') {
        	//Outer if statements are for checking out of bounds
        	//Inner statements check for win conditions
        	//Innermost statements set color to that of the scoring player
        	//Checks top left
        	if (row-2>=0 && col-2>=0) {
        		if (grid[row-1][col-1] == 'O' && grid[row-2][col-2] == 'S') {
            		player.addScore();
            		if(player.getName()=="Blue") {
            			GUI.labels[row][col].setForeground(Color.BLUE);
            			GUI.labels[row-1][col-1].setForeground(Color.BLUE);
            			GUI.labels[row-2][col-2].setForeground(Color.BLUE);
            		}
            		else {
            			GUI.labels[row][col].setForeground(Color.RED);
            			GUI.labels[row-1][col-1].setForeground(Color.RED);
            			GUI.labels[row-2][col-2].setForeground(Color.RED);
            		}
            	}
        	}
        	if (col-2 >= 0) {
                //Checks above
                if (grid[row][col-1] == 'O' && grid[row][col-2] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row][col].setForeground(Color.BLUE);
                        GUI.labels[row][col-1].setForeground(Color.BLUE);
                        GUI.labels[row][col-2].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row][col].setForeground(Color.RED);
                        GUI.labels[row][col-1].setForeground(Color.RED);
                        GUI.labels[row][col-2].setForeground(Color.RED);
                    }
                }
            }
            if (row+2 <= size && col-2 >= 0) {
                //Checks top right
                if (grid[row+1][col-1] == 'O' && grid[row+2][col-2] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row+1][col-1].setForeground(Color.BLUE);
                        GUI.labels[row+2][col-2].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row+1][col-1].setForeground(Color.RED);
                        GUI.labels[row+2][col-2].setForeground(Color.RED);
                    }
                }
            }
            if (row-2 >= 0) {
                //Checks left
                if (grid[row-1][col] == 'O' && grid[row-2][col] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row-1][col].setForeground(Color.BLUE);
                        GUI.labels[row-2][col].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row-1][col].setForeground(Color.RED);
                        GUI.labels[row-2][col].setForeground(Color.RED);
                    }
                }
            }
            if (row+2 <= size) {
                //Checks right
                if (grid[row+1][col] == 'O' && grid[row+2][col] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row+1][col].setForeground(Color.BLUE);
                        GUI.labels[row+2][col].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row+1][col].setForeground(Color.RED);
                        GUI.labels[row+2][col].setForeground(Color.RED);
                    }
                }
            }
            if (row-2 >= 0 && col+2 <= size) {
                //Checks bottom left
                if (grid[row-1][col+1] == 'O' && grid[row-2][col+2] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row-1][col+1].setForeground(Color.BLUE);
                        GUI.labels[row-2][col+2].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row-1][col+1].setForeground(Color.RED);
                        GUI.labels[row-2][col+2].setForeground(Color.RED);
                    }
                }
            }
            if (col+2 <= size) {
                //Checks below
                if (grid[row][col+1] == 'O' && grid[row][col+2] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row][col+1].setForeground(Color.BLUE);
                        GUI.labels[row][col+2].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row][col+1].setForeground(Color.RED);
                        GUI.labels[row][col+2].setForeground(Color.RED);
                    }
                }
            }
            if (row+2 <= size && col+2 <= size) {
                //Checks bottom right
                if (grid[row+1][col+1] == 'O' && grid[row+2][col+2] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row+1][col+1].setForeground(Color.BLUE);
                        GUI.labels[row+2][col+2].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row+1][col+1].setForeground(Color.RED);
                        GUI.labels[row+2][col+2].setForeground(Color.RED);
                    }
                }
            }
        } 
        else { 
        	//Checks for only 4 pairs of S's surrounding the played O
            //Checks top to bottom diagonal
            if (row-1 >= 0 && col-1 >= 0 && row+1 <= size && col+1 <= size) {
                if (grid[row-1][col-1] == 'S' && grid[row+1][col+1] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row-1][col-1].setForeground(Color.BLUE);
                        GUI.labels[row+1][col+1].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row-1][col-1].setForeground(Color.RED);
                        GUI.labels[row+1][col+1].setForeground(Color.RED);
                    }
                }
                //Checks bottom to top diagonal
                if (grid[row+1][col-1] == 'S' && grid[row-1][col+1] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row+1][col-1].setForeground(Color.BLUE);
                        GUI.labels[row-1][col+1].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row+1][col-1].setForeground(Color.RED);
                        GUI.labels[row-1][col+1].setForeground(Color.RED);
                    }
                }
            }
            if (col-1 >= 0 && col+1 <= size) {
                //Checks vertical
                if (grid[row][col-1] == 'S' && grid[row][col+1] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row][col-1].setForeground(Color.BLUE);
                        GUI.labels[row][col+1].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row][col-1].setForeground(Color.RED);
                        GUI.labels[row][col+1].setForeground(Color.RED);
                    }
                }
            }
            if (row-1 >= 0 && row+1 <= size) {
                //Checks horizontal
                if (grid[row-1][col] == 'S' && grid[row+1][col] == 'S') {
                    player.addScore();
                    if (player.getName() == "Blue") {
                        GUI.labels[row-1][col].setForeground(Color.BLUE);
                        GUI.labels[row+1][col].setForeground(Color.BLUE);
                    } else {
                        GUI.labels[row-1][col].setForeground(Color.RED);
                        GUI.labels[row+1][col].setForeground(Color.RED);
                    }
                }
            }
        }
    }
    	
    //Checks for filled grid spot
    boolean validMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && grid[row][col] == '-';
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
