//Testing class for the Player class and its subclasses, Human and Computer, utilizing JUnit 5

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Game board;
    private Player humanPlayer;
    private Player computerPlayer;
    private javax.swing.JLabel[][] labels;

    
    //Sets up a board and players for the tests
    @BeforeEach
    void setUp() {
        board = new Game(5, GUI.Type.SIMPLE);
        humanPlayer = new Human("TestHuman");
        computerPlayer = new Computer("TestComputer");
        labels = new javax.swing.JLabel[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                labels[i][j] = new javax.swing.JLabel();
            }
        }
    }

    //Tests the makeMove method for Humans
    @Test
    void testHumanMakeMove() {
        int[] moveS = humanPlayer.makeMove(board, 2, 3, 'S');
        assertNotNull(moveS);
        assertEquals(2, moveS[0]);
        assertEquals(3, moveS[1]);
        assertEquals(0, moveS[2]);
        int[] moveO = humanPlayer.makeMove(board, 1, 4, 'O');
        assertNotNull(moveO);
        assertEquals(1, moveO[0]);
        assertEquals(4, moveO[1]);
        assertEquals(1, moveO[2]);
    }

    //Tests the Computer's makeMove method
    //Tests multiple times to ensure the random plays are working
    @Test
    void testComputerMakeMove() {
        for (int i = 0; i < 10; i++) {
            int[] move = computerPlayer.makeMove(board, 0, 0, 'S');
            assertNotNull(move);
            assertTrue(move[0] >= 0 && move[0] < board.getSize());
            assertTrue(move[1] >= 0 && move[1] < board.getSize());
            assertTrue(move[2] == 0 || move[2] == 1);
        }
    }

    //Tests scoring works for both Human and Computer
    @Test
    void testPlayerScoring() {
        assertEquals(0, humanPlayer.getScore());
        assertEquals(0, computerPlayer.getScore());
        humanPlayer.addScore();
        assertEquals(1, humanPlayer.getScore());
        computerPlayer.addScore();
        computerPlayer.addScore();
        assertEquals(2, computerPlayer.getScore());
    }
}