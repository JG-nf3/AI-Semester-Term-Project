import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UI extends JFrame {
    private JLabel currentTurn;
    private JButton[][] square;
    private JButton[] dir;
    private JButton move;
    private byte selectedRow;
    private byte selectedCol;
    private byte selectedDir;

    public Game getGame() {
        return game;
    }

    private Game game;

    /**
     * Default constructor
     */
    public UI() {
        // Title the window and set its size
        setTitle("Conga");
        setSize(1500, 1100);

        // No row, col, or dir selected initially
        selectedRow = -1;
        selectedCol = -1;
        selectedDir = -1;

        // Create a new game instance
        game = new Game();

        // Initialize components & events then make our window visible
        initComponents();
        initEvent();
        setVisible(true);
    }

    /**
     * Initializes all components and adds them to the JFrame
     */
    private void initComponents() {
        // Setup the buttons for the squares and direction selectors
        setupSquare();
        setupDirection();

        // Move button setup
        move = new JButton("Move");
        move.setBounds(875, 300, 200, 40);
        move.setFont(new Font(move.getFont().getName(), move.getFont().getStyle(), 28));
        add(move);

        // Label for whose turn it is
        currentTurn = new JLabel("Player 1 turn");
        currentTurn.setFont(new Font(currentTurn.getFont().getName(), currentTurn.getFont().getStyle(), 28));
        currentTurn.setBounds(650, 420, 800, 200);
        add(currentTurn);
    }

    /**
     * Initializes all the square buttons
     */
    private void setupSquare() {
        // Initializes a 4x4 JButton array to store the squares
        square = new JButton[4][4];
        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square[row].length; col++) {
                // Loops through creating 16 squares in the array with their appropriate positioning
                String title = "0";
                square[row][col] = new JButton(title);
                square[row][col].setBounds(120 + col * 100, 100 + row * 100, 85, 85);
                square[row][col].setFont(new Font(square[row][col].getFont().getName(), square[row][col].getFont().getStyle(), 28));
                add(square[row][col]);
            }
        }

        // Give the top left and bottom right squares appropriate labels
        square[0][0].setText("+10");
        square[3][3].setText("-10");
    }

    /**
     * Initialize all the direction selectors
     */
    private void setupDirection() {
        // Dir is an 8 length JButton array
        dir = new JButton[8];

        // Create the various buttons with the appropriate labels
        dir[0] = new JButton("Up");
        dir[0].setBounds(875, 240, 200, 40);

        dir[1] = new JButton("Down");
        dir[1].setBounds(875, 360, 200, 40);

        dir[2] = new JButton("Left");
        dir[2].setBounds(650, 300, 200, 40);

        dir[3] = new JButton("Right");
        dir[3].setBounds(1100, 300, 200, 40);

        dir[4] = new JButton("Up-Left");
        dir[4].setBounds(650, 240, 200, 40);

        dir[5] = new JButton("Up-Right");
        dir[5].setBounds(1100, 240, 200, 40);

        dir[6] = new JButton("Down-Left");
        dir[6].setBounds(650, 360, 200, 40);

        dir[7] = new JButton("Down-Right");
        dir[7].setBounds(1100, 360, 200, 40);

        // Loop through and set all their fonts correctly and add them to the JFrame
        for (JButton jButton : dir) {
            jButton.setFont(new Font(jButton.getFont().getName(), jButton.getFont().getStyle(), 28));
            add(jButton);
        }
    }

    /**
     * Initializes event handlers
     */
    private void initEvent() {

        // If the window closes, we need to exit
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });

        // Loop through all the square buttons and add action listeners for when they are clicked
        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square[row].length; col++) {
                int finalRow = row;
                int finalCol = col;
                square[row][col].addActionListener(e -> {
                    clearSquareBGs();
                    selectedRow = (byte) finalRow;
                    selectedCol = (byte) finalCol;
                    square[finalRow][finalCol].setBackground(Color.RED);
                    square[finalRow][finalCol].setOpaque(true);
                });
            }
        }

        // Loop through all the direction buttons and add action listeners for when they are clicked
        for (int i = 0; i < dir.length; i++) {
            int finalI = i;
            dir[i].addActionListener(e -> {
                clearDirBGs();
                selectedDir = (byte) finalI;
                dir[finalI].setBackground(Color.RED);
                dir[finalI].setOpaque(true);
            });
        }

        // Add an action listener to the move button for when we click that
        move.addActionListener(e -> {
            // checks if a square and move is selected
            if (selectedDir != -1 && selectedRow != -1) {
                // check if legal move
                if (game.isLegal(selectedRow, selectedCol, selectedDir)) {
                    // next turn in game
                    game.nextTurn(selectedRow, selectedCol, selectedDir);
                    // update our UI to match the new turn
                    updateUIForNewTurn();
                }
            }
        });
    }

    /**
     * Clears the background of all the squares
     */
    private void clearSquareBGs() {
        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square[row].length; col++) {
                square[row][col].setBackground(null);
            }
        }
    }

    /**
     * Clears the background of all the direction buttons
     */
    private void clearDirBGs() {
        for (int i = 0; i < 8; i++) {
            dir[i].setBackground(null);
        }
    }

    /**
     * Updates the labels of all of the square buttons
     * to reflect the positions of the stones on the board
     */
    private void update() {
        byte[][] temp = game.getBoard().getStonePosition();

        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square[row].length; col++) {
                String label;
                if (temp[row][col] > 0) {
                    label = "+" + temp[row][col];
                } else if (temp[row][col] == 0) {
                    label = "0";
                } else {
                    label = "" + temp[row][col];
                }
                square[row][col].setText(label);
            }
        }
    }

    /**
     * Does various tasks to update the UI for the new turn
     */
    public void updateUIForNewTurn() {
        // updates the square labels
        update();

        // clears the backgrounds and selected info
        clearSquareBGs();
        clearDirBGs();
        selectedRow = -1;
        selectedCol = -1;
        selectedDir = -1;

        // checks if game is done
        if (game.isOver()) {
            if (!game.getBoard().getPlayer1Turn()) {
                // if it isn't player 1's turn, but the game is over, then player 1 must have won
                currentTurn.setText("Player 1 WINS");
            } else {
                currentTurn.setText("Player 2 WINS");
            }
        }

        // if game isn't over then update whose turn it is
        else {
            if (game.getBoard().getPlayer1Turn()) {
                currentTurn.setText("Player 1 turn");
            } else {
                currentTurn.setText("Player 2 turn");
            }
        }
    }

    /**
     * Creates a new game in the same UI
     */
    public void restartGame() {
        game = new Game();
        updateUIForNewTurn();
    }
}