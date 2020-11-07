public class board {
    /**
     * player1 squares are positive number to represent number of stones
     * if 0 then the square is blank
     * player2 squares are negative number to represent number of stones
     **/
    private byte[][] stonePosition;
    private boolean player1Turn;

    board(){
        stonePosition = new byte[4][4];
        stonePosition[0][0] = 10;
        stonePosition[3][3] = -10;

        player1Turn = true;
    }

    board(board toMake){
        stonePosition = toMake.getstonePosition();
        player1Turn = toMake.getplayer1Turn();
    }

    board(byte[][] stonePosition, boolean player1Turn){
        this.stonePosition = stonePosition;
        this.player1Turn = player1Turn;
    }

    public boolean getplayer1Turn(){
        return player1Turn;
    }

    public byte[][] getstonePosition(){
        return stonePosition;
    }

    /**
     * if return 0 -> not valid move
     * if return 1 -> valid move and can move 1 square
     * if return 2 -> valid move and can move 2 square
     * if return 3 -> valid move and can move 3 square
     *
     * dir = 0 -> up vertically
     * dir = 1 -> down vertically
     * dir = 2 -> left horizontally
     * dir = 3 -> right horizontally
     * dir = 4 -> up left diagonally
     * dir = 5 -> up right diagonally
     * dir = 6 -> down left diagonally
     * dir = 7 -> down right diagonally
     **/
    public byte validMove(byte row, byte col, byte dir){
        byte spaceToMove = 0;

        // for player1 turn
        if(player1Turn) {
            // check if there is no stones in the square
            if(stonePosition[row][col] > 0){
                // section to see how many squares before run out
                byte squaresBeforeRunOut;
                if(stonePosition[row][col] > 3) {
                    squaresBeforeRunOut = 3;
                }
                else if(stonePosition[row][col] > 1) {
                    squaresBeforeRunOut = 2;
                }
                else {
                    squaresBeforeRunOut = 1;
                }


                // section check for moves
                if(dir == 0){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 0){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row--;
                        if(stonePosition[row][col] < 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 1){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 3){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row++;
                        if(stonePosition[row][col] < 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 2){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(col == 0){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        col--;
                        if(stonePosition[row][col] < 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 3){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(col == 3){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        col++;
                        if(stonePosition[row][col] < 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 4){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 0 || col == 0){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row--;
                        col--;
                        if(stonePosition[row][col] < 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 5){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 0 || col == 3){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row--;
                        col++;
                        if(stonePosition[row][col] < 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 6){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 3 || col == 0){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row++;
                        col--;
                        if(stonePosition[row][col] < 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 7){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 3 || col == 3){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row++;
                        col++;
                        if(stonePosition[row][col] < 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
            }
        }
        // for player2 turn
        else {
            if(stonePosition[row][col] < 0){
                // section to see how many squares before run out
                byte squaresBeforeRunOut;
                if(stonePosition[row][col] < -3) {
                    squaresBeforeRunOut = 3;
                }
                else if(stonePosition[row][col] < -1) {
                    squaresBeforeRunOut = 2;
                }
                else {
                    squaresBeforeRunOut = 1;
                }


                // section check for moves
                if(dir == 0){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 0){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row--;
                        if(stonePosition[row][col] > 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 1){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 3){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row++;
                        if(stonePosition[row][col] > 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 2){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(col == 0){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        col--;
                        if(stonePosition[row][col] > 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 3){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(col == 3){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        col++;
                        if(stonePosition[row][col] > 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 4){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 0 || col == 0){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row--;
                        col--;
                        if(stonePosition[row][col] > 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 5){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 0 || col == 3){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row--;
                        col++;
                        if(stonePosition[row][col] > 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 6){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 3 || col == 0){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row++;
                        col--;
                        if(stonePosition[row][col] > 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
                else if(dir == 7){
                    while(squaresBeforeRunOut > 0){
                        // check if moving out of balance
                        if(row == 3 || col == 3){
                            return spaceToMove;
                        }
                        // check square has opponents stones
                        row++;
                        col++;
                        if(stonePosition[row][col] > 0){
                            return spaceToMove;
                        }
                        else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
            }
        }

        return spaceToMove;
    }

    public board newBoard(byte row, byte col, byte dir){
        // create new 2D array with new address
        byte[][] tempStonePosition = new byte[4][4];
        for(byte rowI = 0; rowI < tempStonePosition.length; rowI++){
            for(byte colI = 0; colI < tempStonePosition[rowI].length; colI++){
                tempStonePosition[rowI][colI] = stonePosition[rowI][colI];
            }
        }

        // setup to move stones
        byte toMove = validMove(row, col, dir);
        byte movingStones = tempStonePosition[row][col];
        tempStonePosition[row][col] = 0;

        // updates the board
        // if player1 turn
        if(player1Turn) {
            // run through each direction
            if(dir == 0){
                if(toMove == 1){
                    row--;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row--;
                    tempStonePosition[row][col] += 1;
                    row--;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if(toMove == 3){
                    row--;
                    tempStonePosition[row][col] += 1;
                    row--;
                    tempStonePosition[row][col] += 2;
                    row--;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            }
            else if(dir == 1){
                if(toMove == 1){
                    row++;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row++;
                    tempStonePosition[row][col] += 1;
                    row++;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if(toMove == 3){
                    row++;
                    tempStonePosition[row][col] += 1;
                    row++;
                    tempStonePosition[row][col] += 2;
                    row++;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            }
            else if(dir == 2){
                if(toMove == 1){
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    col--;
                    tempStonePosition[row][col] += 1;
                    col--;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if(toMove == 3){
                    col--;
                    tempStonePosition[row][col] += 1;
                    col--;
                    tempStonePosition[row][col] += 2;
                    col--;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            }
            else if(dir == 3){
                if(toMove == 1){
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    col++;
                    tempStonePosition[row][col] += 1;
                    col++;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if(toMove == 3){
                    col++;
                    tempStonePosition[row][col] += 1;
                    col++;
                    tempStonePosition[row][col] += 2;
                    col++;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            }
            else if(dir == 4){
                if(toMove == 1){
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row--;
                    col--;
                    tempStonePosition[row][col] += 1;
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if(toMove == 3){
                    row--;
                    col--;
                    tempStonePosition[row][col] += 1;
                    row--;
                    col--;
                    tempStonePosition[row][col] += 2;
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            }
            else if(dir == 5){
                if(toMove == 1){
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row--;
                    col++;
                    tempStonePosition[row][col] += 1;
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if(toMove == 3){
                    row--;
                    col++;
                    tempStonePosition[row][col] += 1;
                    row--;
                    col++;
                    tempStonePosition[row][col] += 2;
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            }
            else if(dir == 6){
                if(toMove == 1){
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row++;
                    col--;
                    tempStonePosition[row][col] += 1;
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if(toMove == 3){
                    row++;
                    col--;
                    tempStonePosition[row][col] += 1;
                    row++;
                    col--;
                    tempStonePosition[row][col] += 2;
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            }
            else if(dir == 7){
                if(toMove == 1){
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row++;
                    col++;
                    tempStonePosition[row][col] += 1;
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if(toMove == 3){
                    row++;
                    col++;
                    tempStonePosition[row][col] += 1;
                    row++;
                    col++;
                    tempStonePosition[row][col] += 2;
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            }
        }
        // if player2 turn
        else {
            // run through each direction
            if(dir == 0){
                if(toMove == 1){
                    row--;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row--;
                    tempStonePosition[row][col] += -1;
                    row--;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if(toMove == 3){
                    row--;
                    tempStonePosition[row][col] += -1;
                    row--;
                    tempStonePosition[row][col] += -2;
                    row--;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            }
            else if(dir == 1){
                if(toMove == 1){
                    row++;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row++;
                    tempStonePosition[row][col] += -1;
                    row++;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if(toMove == 3){
                    row--;
                    tempStonePosition[row][col] += -1;
                    row--;
                    tempStonePosition[row][col] += -2;
                    row--;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            }
            else if(dir == 2){
                if(toMove == 1){
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    col--;
                    tempStonePosition[row][col] += -1;
                    col--;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if(toMove == 3){
                    col--;
                    tempStonePosition[row][col] += -1;
                    col--;
                    tempStonePosition[row][col] += -2;
                    col--;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            }
            else if(dir == 3){
                if(toMove == 1){
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    col++;
                    tempStonePosition[row][col] += -1;
                    col++;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if(toMove == 3){
                    col++;
                    tempStonePosition[row][col] += -1;
                    col++;
                    tempStonePosition[row][col] += -2;
                    col++;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            }
            else if(dir == 4){
                if(toMove == 1){
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row--;
                    col--;
                    tempStonePosition[row][col] += -1;
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if(toMove == 3){
                    row--;
                    col--;
                    tempStonePosition[row][col] += -1;
                    row--;
                    col--;
                    tempStonePosition[row][col] += -2;
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            }
            else if(dir == 5){
                if(toMove == 1){
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row--;
                    col++;
                    tempStonePosition[row][col] += -1;
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if(toMove == 3){
                    row--;
                    col++;
                    tempStonePosition[row][col] += -1;
                    row--;
                    col++;
                    tempStonePosition[row][col] += -2;
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            }
            else if(dir == 6){
                if(toMove == 1){
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row++;
                    col--;
                    tempStonePosition[row][col] += -1;
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if(toMove == 3){
                    row++;
                    col--;
                    tempStonePosition[row][col] += -1;
                    row++;
                    col--;
                    tempStonePosition[row][col] += -2;
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            }
            else if(dir == 7){
                if(toMove == 1){
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if(toMove == 2){
                    row++;
                    col++;
                    tempStonePosition[row][col] += -1;
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if(toMove == 3){
                    row++;
                    col++;
                    tempStonePosition[row][col] += -1;
                    row++;
                    col++;
                    tempStonePosition[row][col] += -2;
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            }
        }

        // returns a new board with opposite turn
        return new board(tempStonePosition, !player1Turn);
    }
}
