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

    public UI() {
        setTitle("Conga");
        setSize(1500, 1100);

        selectedRow = -1;
        selectedCol = -1;
        selectedDir = -1;

        game = new Game();

        initComponent();
        initEvent();
        setVisible(true);
    }

    private void initComponent() {
        setupSquare();
        setupDirection();

        move = new JButton("Move");
        move.setBounds(875, 300, 200, 40);
        move.setFont(new Font(move.getFont().getName(), move.getFont().getStyle(), 28));
        add(move);


        currentTurn = new JLabel("Player 1 turn");
        currentTurn.setFont(new Font(currentTurn.getFont().getName(), currentTurn.getFont().getStyle(), 28));
        currentTurn.setBounds(650, 420, 800, 200);
        add(currentTurn);
    }

    private void setupSquare() {
        square = new JButton[4][4];
        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square[row].length; col++) {
                String title = "0";
                square[row][col] = new JButton(title);
                square[row][col].setBounds(120 + col * 100, 100 + row * 100, 85, 85);
                square[row][col].setFont(new Font(square[row][col].getFont().getName(), square[row][col].getFont().getStyle(), 28));
                add(square[row][col]);
            }
        }

        square[0][0].setText("+10");
        square[3][3].setText("-10");
    }

    private void setupDirection() {
        dir = new JButton[8];

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

        for (int i = 0; i < dir.length; i++) {
            dir[i].setFont(new Font(dir[i].getFont().getName(), dir[i].getFont().getStyle(), 28));
            add(dir[i]);
        }
    }

    private void initEvent() {

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });

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

        for (int i = 0; i < dir.length; i++) {
            int finalI = i;
            dir[i].addActionListener(e -> {
                clearDirBGs();
                selectedDir = (byte) finalI;
                dir[finalI].setBackground(Color.RED);
                dir[finalI].setOpaque(true);
            });
        }

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

    private void clearSquareBGs() {
        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square[row].length; col++) {
                square[row][col].setBackground(null);
                //square[row][col].setOpaque(true);
            }
        }
    }

    private void clearDirBGs() {
        for (int i = 0; i < 8; i++) {
            dir[i].setBackground(null);
        }
    }

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
        // TODO: move this check to when we do the next turn instead of being in the UI
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

    public void restartGame() {
        game = new Game();
        updateUIForNewTurn();
    }
}