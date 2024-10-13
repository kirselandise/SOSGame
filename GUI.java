// Code by Trinity McCann | CS449 - Final Project | Sprint 2 Submission
// Instructions for how to run:
// Hit run, Select Game Mode, Select Board Size, Click Start Game. Blue goes first, pick your letter and place it down. Once board is filled, it reports a tie.


// Class that creates the GUI for interfacing with the user
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
	//Declaration of variables
    private JLabel[][] labels;
    private JLabel status;
    private Player blue, red;
    private Board board;
    private boolean blueTurn = true;

    private JRadioButton blueS, blueO, redS, redO; // Red and Blue's move buttons
    private ButtonGroup blueGroup, redGroup; //Defines red and blue group which holds their buttons on a certain side of the screen

    private JRadioButton simpleMode, generalMode;
    private ButtonGroup modeGroup; //Defines group that holds the game modes
    private JTextField boardSize; //Determines board size by user input
    
    private JButton startGame; //Starts game with appropriate modifiers (board size and game type)
    private JButton exitGame; //Exits application, after prompting user, are you sure
    private JButton startOver; //Sends you back to the start game screen, 
    
    // Enumeration holding the game modes
    public enum Type {
        SIMPLE,
        GENERAL;
    }

    public GUI() {
        setUp();
    }

    
    //Method that 
    private void setUp() {
        //Ensure reset between games
        getContentPane().removeAll();
        revalidate();
        repaint();

        //Creates the window for the application
        setTitle("SOS Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Creates a flexible GridBag layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); //Padding for the grid bag
        add(mainPanel);

        //Creates buttons for mode selection
        simpleMode = new JRadioButton("Simple game", true);
        generalMode = new JRadioButton("General game");
        modeGroup = new ButtonGroup();
        modeGroup.add(simpleMode);
        modeGroup.add(generalMode);

        //Allows player to select board size
        JLabel boardSizeLabel = new JLabel("Board size: ");
        setBoardSize(new JTextField("8", 3)); //Defaults the board size to 8

        // Initialize buttons
        setStartGame(new JButton("Start Game"));
        getStartGame().addActionListener(e -> startGame());
        exitGame = new JButton("Exit Game");
        exitGame.addActionListener(e -> exitGame());

        //Grid Bag setup (I am new to using this and do not believe this to be the most efficient way, but it does work)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(new JLabel("SOS Game"), gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        mainPanel.add(simpleMode, gbc);
        gbc.gridx = 1;
        mainPanel.add(generalMode, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(boardSizeLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(getBoardSize(), gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(getStartGame(), gbc);
        gbc.gridx = 1;
        mainPanel.add(exitGame, gbc);

        setVisible(true); //Necessary for showing GUI
    }

    //Method for the start game screen
    private void startGame() {
        int size;
    	//Ensures board size is from 3 to 20
        try {
            size = Integer.parseInt(getBoardSize().getText());
            if (size < 3 || size > 20) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
        	showErrorMessage("Invalid board size. Please enter a number between 3 and 20.");
            return;
        }

        //Old initialization of the Player class. Will be used for scoring in later sprints
        blue = new Player("Blue");
        red = new Player("Red");

        //Selected type
        Type gameType = simpleMode.isSelected() ? Type.SIMPLE : Type.GENERAL;
        board = new Board(size, gameType);

        //Selected size
        newBoard(size);
    }

    //Method to exit application, similar to 'X', but it will ask for confirmation
    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the game?", "Exit Game", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    //Creates the board screen
    private void newBoard(int size) {
        getContentPane().removeAll(); //Reset board

        setSize(600, 600); //Larger screen
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        //Makes game grid using size variable
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(size, size));
        labels = new JLabel[size][size];

        //Creates the grid visually, using black lines for outlines and larger size letters for 's' and 'o'
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                labels[row][col] = new JLabel(" ", SwingConstants.CENTER);
                labels[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labels[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                boardPanel.add(labels[row][col]);
            }
        }

        //Blue or Left Panel
        JPanel bluePanel = new JPanel(new GridLayout(3, 1));
        bluePanel.add(new JLabel("Blue player")); //Would like to add this to be Player name instead of hardcoded as red or blue. For eventually flexibility of Player names
        blueS = new JRadioButton("S", true);
        blueO = new JRadioButton("O");
        blueGroup = new ButtonGroup();
        blueGroup.add(blueS);
        blueGroup.add(blueO);
        bluePanel.add(blueS);
        bluePanel.add(blueO);

        //Red or Right Panel
        JPanel redPanel = new JPanel(new GridLayout(3, 1));
        redPanel.add(new JLabel("Red player")); //Same for blue player
        redS = new JRadioButton("S", true);
        redO = new JRadioButton("O");
        redGroup = new ButtonGroup();
        redGroup.add(redS);
        redGroup.add(redO);
        redPanel.add(redS);
        redPanel.add(redO);

        //Reports current turn
        JPanel bottomPanel = new JPanel(new BorderLayout());
        status = new JLabel("Current turn: Blue");
        bottomPanel.add(status, BorderLayout.WEST);
        
        //Allows you to restart a game so completion is not necessary
        startOver = new JButton("Start Over");
        startOver.addActionListener(e -> setUp());
        bottomPanel.add(startOver, BorderLayout.EAST);

        //Adds the created panels to their appropriate locations
        mainPanel.add(bluePanel, BorderLayout.WEST);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(redPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();

        moveWait(size); //Activates listeners
    }

    //Creates listeners for the grid
    private void moveWait(int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final int r = row;
                final int c = col;
                labels[row][col].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        moveMade(r, c);
                    }
                });
            }
        }
    }

    //Determines what happens if the grid is clicked
    private void moveMade(int row, int col) {
        Player currentPlayer = blueTurn ? blue : red; //Places move as that player
        char move = blueTurn ? (blueS.isSelected() ? 'S' : 'O') : (redS.isSelected() ? 'S' : 'O'); //Places selected 's' or 'o'

        if (board.play(row, col, move, currentPlayer)) {
            labels[row][col].setText(String.valueOf(move));

            if (board.full()) {
                declareWinner();
            } else {
                blueTurn = !blueTurn;
                status.setText("Current turn: " + (blueTurn ? "Blue" : "Red")); //Updates status
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move, try again."); //Does not allow repeat moves (moves in a taken spot)
        }
    }

    //Not implemented yet. Relies on determining logic from Player and Board classes in a later Sprint
    private void declareWinner() {
        if (blue.getScore() > red.getScore()) {
            JOptionPane.showMessageDialog(this, "Blue wins with score: " + blue.getScore());
        } else if (red.getScore() > blue.getScore()) {
            JOptionPane.showMessageDialog(this, "Red wins with score: " + red.getScore());
        } else {
            JOptionPane.showMessageDialog(this, "It's a tie!");
        }

        int restart = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Restart", JOptionPane.YES_NO_OPTION);
        if (restart == JOptionPane.YES_OPTION) {
            setUp();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new); //From my understanding, the invokeLater method helps Swing work properly instead of just calling the method directly
    }


    //Getters and Setters created by Eclipse for the size test
	public JTextField getBoardSize() {
		return boardSize;
	}


	public void setBoardSize(JTextField boardSize) {
		this.boardSize = boardSize;
	}


	public JButton getStartGame() {
		return startGame;
	}


	public void setStartGame(JButton startGame) {
		this.startGame = startGame;
	}
	
	//
	protected void showErrorMessage(String message) {
	    JOptionPane.showMessageDialog(this, message);
	}
}
