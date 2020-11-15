public class Board {
    /**
     * If a square has a Positive number of stones, that means Player 1 has that number in that square
     * if a square has 0 then neither player has a stone there
     * If a square has a Negative number of stones, that means Player 2 has that number in that square
     **/
    private final byte[][] stonePosition;

    /**
     * true if it is player 1's turn, false otherwise
     */
    private boolean player1Turn;

    /**
     * Default constructor, sets up an initial conga board
     */
    public Board() {
        // Initial board setup, a 4x4 2d array to store all the squares
        stonePosition = new byte[4][4];
        // Player 1 starts with 10 in the top left
        stonePosition[0][0] = 10;
        // Player 2 starts with 10 in the bottom right
        stonePosition[3][3] = -10;

        // and it's player 1's turn
        player1Turn = true;
    }

    /**
     * Copies a board
     *
     * @param toMake - board to be copied
     */
    public Board(Board toMake) {
        // Copy the stone position and whose turn it is from the other board
        stonePosition = toMake.getStonePosition();
        player1Turn = toMake.getPlayer1Turn();
    }

    /**
     * Creates a new board with a given stonePosition, and player1Turn
     *
     * @param stonePosition - the stone position for the new board
     * @param player1Turn   - whose turn it is, true for player 1 and false for player 2
     */
    public Board(byte[][] stonePosition, boolean player1Turn) {
        this.stonePosition = stonePosition;
        this.player1Turn = player1Turn;
    }

    public boolean getPlayer1Turn() {
        return player1Turn;
    }

    public byte[][] getStonePosition() {
        return stonePosition;
    }

    /**
     * @param dir - the direction we want to move in
     *            0 -> up vertically
     *            1 -> down vertically
     *            2 -> left horizontally
     *            3 -> right horizontally
     *            4 -> up left diagonally
     *            5 -> up right diagonally
     *            6 -> down left diagonally
     *            7 -> down right diagonally
     * @return 0 -> invalid move
     * 1 -> valid move and can move 1 square
     * 2 -> valid move and can move 2 squares
     * 3 -> valid move and can move 3 squares
     **/
    public byte validMove(byte row, byte col, byte dir) {
        // Variables to store how many spaces we need to move
        byte spaceToMove = 0;
        byte squaresBeforeRunOut;

        if (player1Turn) {
            // If we don't have any stones in the given square return 0
            if (stonePosition[row][col] <= 0) {
                return spaceToMove;
            }
            // Otherwise, determine how many squares we can put stones in
            // and update squaresBeforeRunOut
            if (stonePosition[row][col] > 3) {
                squaresBeforeRunOut = 3;
            } else if (stonePosition[row][col] > 1) {
                squaresBeforeRunOut = 2;
            } else {
                squaresBeforeRunOut = 1;
            }
        } else {
            // If we don't have any stones in the given square return 0
            if (stonePosition[row][col] >= 0) {
                return spaceToMove;
            }
            // Otherwise, determine how many squares we can put stones in
            // and update squaresBeforeRunOut
            if (stonePosition[row][col] < -3) {
                squaresBeforeRunOut = 3;
            } else if (stonePosition[row][col] < -1) {
                squaresBeforeRunOut = 2;
            } else {
                squaresBeforeRunOut = 1;
            }
        }

        while (squaresBeforeRunOut > 0) {
            // check for moves
            if (dir == 0) {
                // check if moving out of bounds
                if (row == 0) {
                    return spaceToMove;
                }
                // check if new square has opponents stones
                row--;
                if (doesOpponentPopulate(row, col)) {
                    return spaceToMove;
                } else {
                    // If it doesn't, increase the number of spaces we can move
                    // and reduce the number of squares we can move
                    spaceToMove++;
                    squaresBeforeRunOut--;
                }
            } else if (dir == 1) {
                // check if moving out of bounds
                if (row == 3) {
                    return spaceToMove;
                }
                // check if new square has opponents stones
                row++;
                if (doesOpponentPopulate(row, col)) {
                    return spaceToMove;
                } else {
                    // If it doesn't, increase the number of spaces we can move
                    // and reduce the number of squares we can move
                    spaceToMove++;
                    squaresBeforeRunOut--;
                }
            } else if (dir == 2) {
                // check if moving out of bounds
                if (col == 0) {
                    return spaceToMove;
                }
                // check if new square has opponents stones
                col--;
                if (doesOpponentPopulate(row, col)) {
                    return spaceToMove;
                } else {
                    // If it doesn't, increase the number of spaces we can move
                    // and reduce the number of squares we can move
                    spaceToMove++;
                    squaresBeforeRunOut--;
                }
            } else if (dir == 3) {
                // check if moving out of bounds
                if (col == 3) {
                    return spaceToMove;
                }
                // check if new square has opponents stones
                col++;
                if (doesOpponentPopulate(row, col)) {
                    return spaceToMove;
                } else {
                    // If it doesn't, increase the number of spaces we can move
                    // and reduce the number of squares we can move
                    spaceToMove++;
                    squaresBeforeRunOut--;
                }
            } else if (dir == 4) {
                // check if moving out of bounds
                if (row == 0 || col == 0) {
                    return spaceToMove;
                }
                // check if new square has opponents stones
                row--;
                col--;
                if (doesOpponentPopulate(row, col)) {
                    return spaceToMove;
                } else {
                    // If it doesn't, increase the number of spaces we can move
                    // and reduce the number of squares we can move
                    spaceToMove++;
                    squaresBeforeRunOut--;
                }
            } else if (dir == 5) {
                // check if moving out of bounds
                if (row == 0 || col == 3) {
                    return spaceToMove;
                }
                // check if new square has opponents stones
                row--;
                col++;
                if (doesOpponentPopulate(row, col)) {
                    return spaceToMove;
                } else {
                    // If it doesn't, increase the number of spaces we can move
                    // and reduce the number of squares we can move
                    spaceToMove++;
                    squaresBeforeRunOut--;
                }
            } else if (dir == 6) {
                // check if moving out of bounds
                if (row == 3 || col == 0) {
                    return spaceToMove;
                }
                // check if new square has opponents stones
                row++;
                col--;
                if (doesOpponentPopulate(row, col)) {
                    return spaceToMove;
                } else {
                    // If it doesn't, increase the number of spaces we can move
                    // and reduce the number of squares we can move
                    spaceToMove++;
                    squaresBeforeRunOut--;
                }
            } else if (dir == 7) {
                // check if moving out of bounds
                if (row == 3 || col == 3) {
                    return spaceToMove;
                }
                // check if new square has opponents stones
                row++;
                col++;
                if (doesOpponentPopulate(row, col)) {
                    return spaceToMove;
                } else {
                    // If it doesn't, increase the number of spaces we can move
                    // and reduce the number of squares we can move
                    spaceToMove++;
                    squaresBeforeRunOut--;
                }
            }
        }

        return spaceToMove;
    }

    /**
     * Creates a new board, with the passed in move made on the current board
     *
     * @param row - the row of the stones we want to move
     * @param col - the column of the stones we want to move
     * @param dir - the direction we want to move in
     *            0 -> up vertically
     *            1 -> down vertically
     *            2 -> left horizontally
     *            3 -> right horizontally
     *            4 -> up left diagonally
     *            5 -> up right diagonally
     *            6 -> down left diagonally
     *            7 -> down right diagonally
     * @return the new board
     */
    public Board newBoard(byte row, byte col, byte dir) {
        // create new 2D array with new address
        byte[][] tempStonePosition = new byte[4][4];
        for (int rowI = 0; rowI < tempStonePosition.length; rowI++) {
            for (int colI = 0; colI < tempStonePosition[rowI].length; colI++) {
                tempStonePosition[rowI][colI] = stonePosition[rowI][colI];
            }
        }

        // setup to move stones
        byte toMove = validMove(row, col, dir);
        // The number of stones we're working with
        byte movingStones = tempStonePosition[row][col];
        // set the current position to 0 now
        tempStonePosition[row][col] = 0;

        // Create a multiplier based on whose turn it is
        // so we move around the correct numbers of stones
        int multiplier = player1Turn ? 1 : -1;

        // updates the board with the new stone arrangements
        // run through each direction
        if (dir == 0) {
            if (toMove == 1) {
                row--;
                tempStonePosition[row][col] += movingStones;
            } else if (toMove == 2) {
                row--;
                tempStonePosition[row][col] += multiplier;
                row--;
                tempStonePosition[row][col] += movingStones - (multiplier);
            } else if (toMove == 3) {
                row--;
                tempStonePosition[row][col] += multiplier;
                row--;
                tempStonePosition[row][col] += 2 * multiplier;
                row--;
                tempStonePosition[row][col] += movingStones - (3 * multiplier);
            }
        } else if (dir == 1) {
            if (toMove == 1) {
                row++;
                tempStonePosition[row][col] += movingStones;
            } else if (toMove == 2) {
                row++;
                tempStonePosition[row][col] += multiplier;
                row++;
                tempStonePosition[row][col] += movingStones - (multiplier);
            } else if (toMove == 3) {
                row++;
                tempStonePosition[row][col] += multiplier;
                row++;
                tempStonePosition[row][col] += 2 * multiplier;
                row++;
                tempStonePosition[row][col] += movingStones - (3 * multiplier);
            }
        } else if (dir == 2) {
            if (toMove == 1) {
                col--;
                tempStonePosition[row][col] += movingStones;
            } else if (toMove == 2) {
                col--;
                tempStonePosition[row][col] += multiplier;
                col--;
                tempStonePosition[row][col] += movingStones - (multiplier);
            } else if (toMove == 3) {
                col--;
                tempStonePosition[row][col] += multiplier;
                col--;
                tempStonePosition[row][col] += 2 * multiplier;
                col--;
                tempStonePosition[row][col] += movingStones - (3 * multiplier);
            }
        } else if (dir == 3) {
            if (toMove == 1) {
                col++;
                tempStonePosition[row][col] += movingStones;
            } else if (toMove == 2) {
                col++;
                tempStonePosition[row][col] += multiplier;
                col++;
                tempStonePosition[row][col] += movingStones - (multiplier);
            } else if (toMove == 3) {
                col++;
                tempStonePosition[row][col] += multiplier;
                col++;
                tempStonePosition[row][col] += 2 * multiplier;
                col++;
                tempStonePosition[row][col] += movingStones - (3 * multiplier);
            }
        } else if (dir == 4) {
            if (toMove == 1) {
                row--;
                col--;
                tempStonePosition[row][col] += movingStones;
            } else if (toMove == 2) {
                row--;
                col--;
                tempStonePosition[row][col] += multiplier;
                row--;
                col--;
                tempStonePosition[row][col] += movingStones - (multiplier);
            } else if (toMove == 3) {
                row--;
                col--;
                tempStonePosition[row][col] += multiplier;
                row--;
                col--;
                tempStonePosition[row][col] += 2 * multiplier;
                row--;
                col--;
                tempStonePosition[row][col] += movingStones - (3 * multiplier);
            }
        } else if (dir == 5) {
            if (toMove == 1) {
                row--;
                col++;
                tempStonePosition[row][col] += movingStones;
            } else if (toMove == 2) {
                row--;
                col++;
                tempStonePosition[row][col] += multiplier;
                row--;
                col++;
                tempStonePosition[row][col] += movingStones - (multiplier);
            } else if (toMove == 3) {
                row--;
                col++;
                tempStonePosition[row][col] += multiplier;
                row--;
                col++;
                tempStonePosition[row][col] += 2 * multiplier;
                row--;
                col++;
                tempStonePosition[row][col] += movingStones - (3 * multiplier);
            }
        } else if (dir == 6) {
            if (toMove == 1) {
                row++;
                col--;
                tempStonePosition[row][col] += movingStones;
            } else if (toMove == 2) {
                row++;
                col--;
                tempStonePosition[row][col] += multiplier;
                row++;
                col--;
                tempStonePosition[row][col] += movingStones - (multiplier);
            } else if (toMove == 3) {
                row++;
                col--;
                tempStonePosition[row][col] += multiplier;
                row++;
                col--;
                tempStonePosition[row][col] += 2 * multiplier;
                row++;
                col--;
                tempStonePosition[row][col] += movingStones - (3 * multiplier);
            }
        } else if (dir == 7) {
            if (toMove == 1) {
                row++;
                col++;
                tempStonePosition[row][col] += movingStones;
            } else if (toMove == 2) {
                row++;
                col++;
                tempStonePosition[row][col] += multiplier;
                row++;
                col++;
                tempStonePosition[row][col] += movingStones - (multiplier);
            } else if (toMove == 3) {
                row++;
                col++;
                tempStonePosition[row][col] += multiplier;
                row++;
                col++;
                tempStonePosition[row][col] += 2 * multiplier;
                row++;
                col++;
                tempStonePosition[row][col] += movingStones - (3 * multiplier);
            }
        }
        // returns a new board with opposite turn
        return new Board(tempStonePosition, !player1Turn);
    }

    /**
     * @param row - the row to check
     * @param col - the col to check
     * @return true if the opponent has stones in the specified square
     * false otherwise
     */
    private boolean doesOpponentPopulate(byte row, byte col) {
        if (player1Turn) {
            return stonePosition[row][col] < 0;
        } else {
            return stonePosition[row][col] > 0;
        }
    }

    /**
     * Calculates the heuristic function h for the board
     *
     * @return the heuristic value for the board
     */
    public int getScore() {
        // total score
        int total = 0;
        // keep track of whose turn it is
        boolean hold = player1Turn;

        // set player1turn to true so we can use validMove
        // the random agent is player 1
        player1Turn = true;
        for (byte row = 0; row < 4; row++) {
            for (byte col = 0; col < 4; col++) {
                for (byte dir = 0; dir < 8; dir++) {
                    // Loop through all the locations on the board and check if they are a valid move
                    // for player 1
                    byte tempByte = validMove(row, col, dir);

                    if (tempByte > 0) {
                        // If that is a valid move, then reduce the total by 5 + the number of squares we can move
                        // because player 1 in our game is the min agent
                        total -= 5 + tempByte;
                    }
                }
            }
        }

        // case for winning move
        // if total is still 0 at this point then that means the random agent
        // has no valid moves, so we want to return the maximum integer value
        // so that our minimax will ALWAYS take this path
        if (total == 0) {
            return Integer.MAX_VALUE;
        }

        // Now set player1Turn to false to check our minimax agent's moves
        player1Turn = false;
        for (byte row = 0; row < 4; row++) {
            for (byte col = 0; col < 4; col++) {
                for (byte dir = 0; dir < 8; dir++) {
                    // Loop through all the moves for the minimax agent finding valid ones
                    byte tempByte = validMove(row, col, dir);
                    // If its valid move increase the total by 5 + the number of squares we can move
                    if (tempByte > 0) {
                        total += 5 + tempByte;
                    }
                }
            }
        }

        // Restore player1Turn to its value from the beginning of the method call
        player1Turn = hold;
        // and return our heuristic
        if (total >= 3000) {
            System.out.println("what");
        }
        return total;
    }
}