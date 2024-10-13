//Testing class for the SOS game utilizing JUnit 5

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class sosTest {

	//Declaration of Variables
    private Board board;
    private Player bluePlayer;
    private Player redPlayer;
    private GUI gui;
    private String capturedMessage;

    //Sets up Players. Also sets up GUI, needed for the chat GPT code, not exactly certain why
    @BeforeEach
    void setUp() {
        bluePlayer = new Player("Blue");
        redPlayer = new Player("Red");
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
        gui.getBoardSize().setText("3");
        gui.getStartGame().doClick();
        assertNull(capturedMessage); // Expect no error message
        
        // Test with an invalid board size (too small)
        gui.getBoardSize().setText("2");
        gui.getStartGame().doClick();
        assertEquals("Invalid board size. Please enter a number between 3 and 20.", capturedMessage);

        // Reset captured message
        capturedMessage = null;

        // Test with an invalid board size (too large)
        gui.getBoardSize().setText("21");
        gui.getStartGame().doClick();
        assertEquals("Invalid board size. Please enter a number between 3 and 20.", capturedMessage);
    }
    //...CHAT GPT GENERATED CODE END

    //Tests for correct board size and type
    @Test
    void correctSizeType() {
        board = new Board(8, GUI.Type.SIMPLE);
        assertEquals(8, board.getSize());
        assertEquals(GUI.Type.SIMPLE, board.getType());
        board = new Board(15, GUI.Type.GENERAL);
        assertEquals(15, board.getSize());
        assertEquals(GUI.Type.GENERAL, board.getType());
    }

    //Tests correct player, location, and letter
    @Test
    void correctPlayerMoves() {
        board = new Board(5, GUI.Type.SIMPLE);
        
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
        board = new Board(5, GUI.Type.SIMPLE);

        assertTrue(board.play(0, 0, 'S', bluePlayer));
        assertFalse(board.play(0, 0, 'O', redPlayer));
        assertTrue(board.play(1, 1, 'O', redPlayer));
        assertFalse(board.play(5, 5, 'S', bluePlayer));
    }

    //Tests for full board after making board full
    @Test
    void checkFull() {
        board = new Board(3, GUI.Type.SIMPLE);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.play(i, j, 'S', (i + j) % 2 == 0 ? bluePlayer : redPlayer);
            }
        }
        assertTrue(board.full());
    }
}
