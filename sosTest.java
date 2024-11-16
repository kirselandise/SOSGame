//Testing class for the SOS game utilizing JUnit 5

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class sosTest {

	//Declaration of Variables
    private Game board;
    private Player bluePlayer;
    private Player redPlayer;
    private GUI gui;
    private String capturedMessage;

    //Sets up Players. Also sets up GUI, needed for the chat GPT code, not exactly certain why
    @BeforeEach
    void setUp() {
        bluePlayer = new Human("Blue");
        redPlayer = new Human("Red");
        gui = new GUI() {
            @Override
            protected void showErrorMessage(String message) {
                capturedMessage = message;
            }
        };
    }
    
    //CHAT GPT GENERATED CODE START...
    @Test
    void testStartGameWithInvalidBoardSize() {
    	// Test with a valid board size (lower bound)
        GUI.getBoardSize().setText("3");
        gui.getStartGame().doClick();
        assertNull(capturedMessage); // Expect no error message
        
        // Test with an invalid board size (too small)
        GUI.getBoardSize().setText("2");
        gui.getStartGame().doClick();
        assertEquals("Invalid board size. Please enter a number between 3 and 20.", capturedMessage);

        // Reset captured message
        capturedMessage = null;

        // Test with an invalid board size (too large)
        GUI.getBoardSize().setText("21");
        gui.getStartGame().doClick();
        assertEquals("Invalid board size. Please enter a number between 3 and 20.", capturedMessage);
    }
    //...CHAT GPT GENERATED CODE END

    //Tests for correct board size and type
    @Test
    void correctSizeType() {
        board = new Game(8, GUI.Type.SIMPLE);
        assertEquals(8, board.getSize());
        assertEquals(GUI.Type.SIMPLE, board.getType());
        board = new Game(15, GUI.Type.GENERAL);
        assertEquals(15, board.getSize());
        assertEquals(GUI.Type.GENERAL, board.getType());
    }

    //Tests correct player, location, and letter
    @Test
    void correctPlayerMoves() {
        board = new Game(5, GUI.Type.SIMPLE);
        
        assertTrue(board.play(0, 0, 'S', bluePlayer));
        assertEquals('S', board.getCell(0, 0));
        assertTrue(board.play(1, 1, 'O', redPlayer));
        assertEquals('O', board.getCell(1, 1));
        assertTrue(board.play(2, 2, 'O', bluePlayer));
        assertEquals('O', board.getCell(2, 2));
        assertTrue(board.play(3, 3, 'S', redPlayer));
        assertEquals('S', board.getCell(3, 3));
    }

    //Tests valid and invalid locations
    @Test
    void validMoves() {
        board = new Game(5, GUI.Type.SIMPLE);

        assertTrue(board.play(0, 0, 'S', bluePlayer));
        assertFalse(board.play(0, 0, 'O', redPlayer));
        assertTrue(board.play(1, 1, 'O', redPlayer));
        assertFalse(board.play(5, 5, 'S', bluePlayer));
    }
    
    //Tests a full board of scores inside of a GENERAL game
    @Test
    void checkGeneral() {
    	//Actually equivalent to board size 3 cause size=size-1
        board = new Game(4, GUI.Type.GENERAL);

        assertTrue(board.play(0, 0, 'S', redPlayer));
        assertTrue(board.play(0, 1, 'O', redPlayer));
        assertTrue(board.play(0, 2, 'S', redPlayer));
        assertTrue(board.play(1, 0, 'S', redPlayer));
        assertTrue(board.play(1, 1, 'S', redPlayer));
        assertTrue(board.play(1, 2, 'S', redPlayer));
        assertTrue(board.play(2, 0, 'S', redPlayer));
        assertTrue(board.play(2, 1, 'S', redPlayer));
        assertTrue(board.play(2, 2, 'S', redPlayer));
        
        assertEquals(0, bluePlayer.getScore());
        assertEquals(1, redPlayer.getScore());
        assertTrue(redPlayer.getScore()>bluePlayer.getScore());
    }

    //Tests that a SIMPLE game ends early when SOS is made and checks scores
    @Test
    void checkSimple() {
        board = new Game(5, GUI.Type.SIMPLE);
        
        assertTrue(board.play(0, 0, 'S', bluePlayer));
        assertTrue(board.play(0, 1, 'O', bluePlayer));
        assertTrue(board.play(0, 2, 'S', bluePlayer));
        
        assertTrue(Simple.gameOver(redPlayer, bluePlayer));
        assertEquals(1, bluePlayer.getScore());
        assertEquals(0, redPlayer.getScore());
        assertTrue(bluePlayer.getScore()>redPlayer.getScore());
    }
}
