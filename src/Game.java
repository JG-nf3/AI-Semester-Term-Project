import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board current;

    Game() {
        current = new Board();
    }

    public boolean isOver() {
        for (byte row = 0; row < 4; row++) {
            for (byte col = 0; col < 4; col++) {
                for (byte dir = 0; dir < 8; dir++) {
                    if (isLegal(row, col, dir)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void nextTurn(byte row, byte col, byte dir) {
        current = new Board(current.newBoard(row, col, dir));
    }

    public Board getBoard() {
        return current;
    }

    public boolean isLegal(byte row, byte col, byte dir) {
        return current.validMove(row, col, dir) != 0;
    }

    public List getLegalMoves() {
        List<byte[]> moves= new ArrayList<byte[]>();

        for (byte row = 0; row < 4; row++) {
            for (byte col = 0; col < 4; col++) {
                for (byte dir = 0; dir < 8; dir++) {
                    if (isLegal(row, col, dir)) {
                        byte[] move = new byte[3];
                        move[0] = row;
                        move[1] = col;
                        move[2] = dir;
                        moves.add(move);
                    }
                }
            }
        }

        return moves;
    }

}
