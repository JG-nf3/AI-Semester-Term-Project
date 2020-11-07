import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI  extends JFrame {
    private JFrame frame;
    private JButton square00;
    private JLabel whoseTurn;
    private JButton[][] square;
    private JButton[] dir;
    private JButton move;
    private byte selectedRow;
    private byte selectedCol;
    private byte selectedDir;
    private Game game;

    public UI(){
        setTitle("Conga");
        setSize(1500,1100);

        selectedRow = -1;
        selectedCol = -1;
        selectedDir = -1;

        game = new Game();

        initComponent();
        initEvent();
        setVisible(true);
    }

    private void initComponent(){
        setupSquare();
        setupDirection();

        move = new JButton("Move");
        move.setBounds(875, 300, 200, 40);
        move.setFont(new Font(move.getFont().getName(), move.getFont().getStyle(), 28));
        add(move);


        whoseTurn = new JLabel("Player 1 turn");
        whoseTurn.setFont(new Font(whoseTurn.getFont().getName(), whoseTurn.getFont().getStyle(), 28));
        whoseTurn.setBounds(650, 420, 800, 200);
        add(whoseTurn);
    }

    private void setupSquare() {
        square = new JButton[4][4];
        for(int row = 0; row < square.length; row++){
            for(int col = 0; col < square[row].length; col++){
                String title = "0";
                square[row][col] = new JButton(title);
                square[row][col].setBounds(120 + col*100, 100 + row*100, 85, 85);
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
        dir[0].setFont(new Font(dir[0].getFont().getName(), dir[0].getFont().getStyle(), 28));
        add(dir[0]);

        dir[1] = new JButton("Down");
        dir[1].setBounds(875, 360, 200, 40);
        dir[1].setFont(new Font(dir[1].getFont().getName(), dir[1].getFont().getStyle(), 28));
        add(dir[1]);

        dir[2] = new JButton("Left");
        dir[2].setBounds(650, 300, 200, 40);
        dir[2].setFont(new Font(dir[2].getFont().getName(), dir[2].getFont().getStyle(), 28));
        add(dir[2]);

        dir[3] = new JButton("Right");
        dir[3].setBounds(1100, 300, 200, 40);
        dir[3].setFont(new Font(dir[3].getFont().getName(), dir[3].getFont().getStyle(), 28));
        add(dir[3]);

        dir[4] = new JButton("Up-Left");
        dir[4].setBounds(650, 240, 200, 40);
        dir[4].setFont(new Font(dir[4].getFont().getName(), dir[4].getFont().getStyle(), 28));
        add(dir[4]);

        dir[5] = new JButton("Up-Right");
        dir[5].setBounds(1100, 240, 200, 40);
        dir[5].setFont(new Font(dir[5].getFont().getName(), dir[5].getFont().getStyle(), 28));
        add(dir[5]);

        dir[6] = new JButton("Down-Left");
        dir[6].setBounds(650, 360, 200, 40);
        dir[6].setFont(new Font(dir[6].getFont().getName(), dir[6].getFont().getStyle(), 28));
        add(dir[6]);

        dir[7] = new JButton("Down-Right");
        dir[7].setBounds(1100, 360, 200, 40);
        dir[7].setFont(new Font(dir[7].getFont().getName(), dir[7].getFont().getStyle(), 28));
        add(dir[7]);
    }

    private void initEvent(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });

        square[0][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 0;
                selectedCol = 0;
                square[0][0].setBackground(Color.RED);
                square[0][0].setOpaque(true);
            }
         });
        square[0][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 0;
                selectedCol = 1;
                square[0][1].setBackground(Color.RED);
                square[0][1].setOpaque(true);
            }
        });
        square[0][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 0;
                selectedCol = 2;
                square[0][2].setBackground(Color.RED);
                square[0][2].setOpaque(true);
            }
        });
        square[0][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 0;
                selectedCol = 3;
                square[0][3].setBackground(Color.RED);
                square[0][3].setOpaque(true);
            }
        });
        square[1][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 1;
                selectedCol = 0;
                square[1][0].setBackground(Color.RED);
                square[1][0].setOpaque(true);
            }
        });
        square[1][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 1;
                selectedCol = 1;
                square[1][1].setBackground(Color.RED);
                square[1][1].setOpaque(true);
            }
        });
        square[1][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 1;
                selectedCol = 2;
                square[1][2].setBackground(Color.RED);
                square[1][2].setOpaque(true);
            }
        });
        square[1][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 1;
                selectedCol = 3;
                square[1][3].setBackground(Color.RED);
                square[1][3].setOpaque(true);
            }
        });
        square[2][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 2;
                selectedCol = 0;
                square[2][0].setBackground(Color.RED);
                square[2][0].setOpaque(true);
            }
        });
        square[2][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 2;
                selectedCol = 1;
                square[2][1].setBackground(Color.RED);
                square[2][1].setOpaque(true);
            }
        });
        square[2][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 2;
                selectedCol = 2;
                square[2][2].setBackground(Color.RED);
                square[2][2].setOpaque(true);
            }
        });
        square[2][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 2;
                selectedCol = 3;
                square[2][3].setBackground(Color.RED);
                square[2][3].setOpaque(true);
            }
        });
        square[3][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 3;
                selectedCol = 0;
                square[3][0].setBackground(Color.RED);
                square[3][0].setOpaque(true);
            }
        });
        square[3][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 3;
                selectedCol = 1;
                square[3][1].setBackground(Color.RED);
                square[3][1].setOpaque(true);
            }
        });
        square[3][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 3;
                selectedCol = 2;
                square[3][2].setBackground(Color.RED);
                square[3][2].setOpaque(true);
            }
        });
        square[3][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonSquareBlank();
                selectedRow = 3;
                selectedCol = 3;
                square[3][3].setBackground(Color.RED);
                square[3][3].setOpaque(true);
            }
        });

        dir[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonDirBlank();
                selectedDir = 0;
                dir[0].setBackground(Color.RED);
                dir[0].setOpaque(true);
            }
        });
        dir[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonDirBlank();
                selectedDir = 1;
                dir[1].setBackground(Color.RED);
                dir[1].setOpaque(true);
            }
        });
        dir[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonDirBlank();
                selectedDir = 2;
                dir[2].setBackground(Color.RED);
                dir[2].setOpaque(true);
            }
        });
        dir[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonDirBlank();
                selectedDir = 3;
                dir[3].setBackground(Color.RED);
                dir[3].setOpaque(true);
            }
        });
        dir[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonDirBlank();
                selectedDir = 4;
                dir[4].setBackground(Color.RED);
                dir[4].setOpaque(true);
            }
        });
        dir[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonDirBlank();
                selectedDir = 5;
                dir[5].setBackground(Color.RED);
                dir[5].setOpaque(true);
            }
        });
        dir[6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonDirBlank();
                selectedDir = 6;
                dir[6].setBackground(Color.RED);
                dir[6].setOpaque(true);
            }
        });
        dir[7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setButtonDirBlank();
                selectedDir = 7;
                dir[7].setBackground(Color.RED);
                dir[7].setOpaque(true);
            }
        });

        move.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // checks if a square and move is selected
                if(selectedDir != -1 && selectedRow != -1){
                    // check if leagle move
                    if(game.isLegal(selectedRow, selectedCol, selectedDir)){
                        // next turn in game
                        game.nextTurn(selectedRow, selectedCol, selectedDir);
                        update();

                        // set buttons blank and clear selected info
                        setButtonSquareBlank();
                        setButtonDirBlank();
                        selectedRow = -1;
                        selectedCol = -1;
                        selectedDir = -1;

                        //checks if game is done
                        if(game.isOver()){
                            if(game.getBoard().getplayer1Turn()){
                                whoseTurn.setText("Player 2 WINS");
                            }
                            else{
                                whoseTurn.setText("Player 1 WINS");
                            }
                        }
                        // if game not done then update whose turn
                        else{
                            if(whoseTurn.getText().equals("Player 1 turn")){
                                whoseTurn.setText("Player 2 turn");
                            }
                            else{
                                whoseTurn.setText("Player 1 turn");
                            }
                        }
                    }
                }
            }
        });
    }

    private void setButtonSquareBlank(){
        for(int row = 0; row < square.length; row++){
            for(int col = 0; col < square[row].length; col++){
                square[row][col].setBackground(null);
                //square[row][col].setOpaque(true);
            }
        }
    }

    private void setButtonDirBlank(){
        for(int i = 0; i < 8; i++){
            dir[i].setBackground(null);
        }
    }

    private void update(){
        byte[][] temp = game.getBoard().getstonePosition();

        for(int row = 0; row < square.length; row++){
            for(int col = 0; col < square[row].length; col++){
                String title;
                if(temp[row][col] > 0){
                    title = "+" + temp[row][col];
                }
                else if(temp[row][col] == 0){
                    title = "0";
                }
                else{
                    title = "" + temp[row][col];
                }
                square[row][col].setText(title);
            }
        }
    }
}






