//Abstract Player class, creates a player with a name and a score. Mostly unimplemented as winning logic is not required yet

abstract

class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }
    
    //Abstract method that is Overided by Computer and Human classes
    public abstract int[] makeMove(Game board, int row, int col, char selectedMove);
    
    //Helper method that communicates with GUI and Game classes)
    public boolean executeMove(Game board, int[] move, javax.swing.JLabel[][] labels) {
        if (board.play(move[0], move[1], move[2] == 0 ? 'S' : 'O', this)) {
            labels[move[0]][move[1]].setText(String.valueOf(move[2] == 0 ? 'S' : 'O'));
            return true;
        }
        return false;
    }
}
