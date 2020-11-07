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

        for (byte i = 0; i < dir.length; i++) {
            dir[i].setFont(new Font(dir[i].getFont().getName(), dir[i].getFont().getStyle(), 28));
            add(dir[i]);
        }
    }

    private void initEvent(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });

        for (byte row = 0; row < square.length; row++) {
            for (byte col = 0; col < square[row].length; col++) {
                byte finalRow = row;
                byte finalCol = col;
                square[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setButtonSquareBlank();
                        selectedRow = finalRow;
                        selectedCol = finalCol;
                        square[finalRow][finalCol].setBackground(Color.RED);
                        square[finalRow][finalCol].setOpaque(true);
                    }
                });
            }
        }

        for (byte i = 0; i < dir.length; i++) {
            byte finalI = i;
            dir[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setButtonDirBlank();
                    selectedDir = finalI;
                    dir[finalI].setBackground(Color.RED);
                    dir[finalI].setOpaque(true);
                }
            });
        }
        
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






