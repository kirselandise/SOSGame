//Human class that extends the Player class

class Human extends Player {
    public Human(String name) {
        super(name);
    }
    
    @Override
    public int[] makeMove(Game board, int row, int col, char selectedMove) {
        return new int[]{row, col, selectedMove == 'S' ? 0 : 1};
    }
}
