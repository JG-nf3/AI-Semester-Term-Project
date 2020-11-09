public class Board {
    /**
     * If a square has a Positive number of stones, that means Player 1 has that number in that square
     * if a square has 0 then neither player has a stone there
     * If a square has a Negative number of stones, that means Player 2 has that number in that square
     **/
    private byte[][] stonePosition;
    private boolean player1Turn;

    Board() {
        // Initial board setup, a 4x4 2d array to store all the squares
        stonePosition = new byte[4][4];
        // Player 1 starts with 10 in the top left
        stonePosition[0][0] = 10;
        // Player 2 starts with 10 in the bottom right
        stonePosition[3][3] = -10;

        // and it's player 1's turn
        player1Turn = true;
    }

    Board(Board toMake) {
        stonePosition = toMake.getStonePosition();
        player1Turn = toMake.getPlayer1Turn();
    }

    Board(byte[][] stonePosition, boolean player1Turn) {
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
     * @return
     * 0 -> invalid move
     * 1 -> valid move and can move 1 square
     * 2 -> valid move and can move 2 squares
     * 3 -> valid move and can move 3 squares
     **/
    public byte validMove(byte row, byte col, byte dir) {
        byte spaceToMove = 0;

        // for player1 turn
        if (player1Turn) {
            // check if there is no stones in the square
            if (stonePosition[row][col] > 0) {
                // section to see how many squares before run out
                byte squaresBeforeRunOut;
                if (stonePosition[row][col] > 3) {
                    squaresBeforeRunOut = 3;
                } else if (stonePosition[row][col] > 1) {
                    squaresBeforeRunOut = 2;
                } else {
                    squaresBeforeRunOut = 1;
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
                        if (stonePosition[row][col] < 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] < 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] < 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] < 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] < 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] < 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] < 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] < 0) {
                            return spaceToMove;
                        } else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
            }
        } else {
            // Player 2's turn
            if (stonePosition[row][col] < 0) {
                // see how many squares before we run out of stones
                byte squaresBeforeRunOut;
                if (stonePosition[row][col] < -3) {
                    squaresBeforeRunOut = 3;
                } else if (stonePosition[row][col] < -1) {
                    squaresBeforeRunOut = 2;
                } else {
                    squaresBeforeRunOut = 1;
                }

                // check for moves
                while (squaresBeforeRunOut > 0) {
                    if (dir == 0) {
                        // check if moving out of bounds
                        if (row == 0) {
                            return spaceToMove;
                        }
                        // check if new square has opponents stones
                        row--;
                        if (stonePosition[row][col] > 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] > 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] > 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] > 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] > 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] > 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] > 0) {
                            return spaceToMove;
                        } else {
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
                        if (stonePosition[row][col] > 0) {
                            return spaceToMove;
                        } else {
                            spaceToMove++;
                            squaresBeforeRunOut--;
                        }
                    }
                }
            }
        }

        return spaceToMove;
    }

    /**
     *
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
        byte movingStones = tempStonePosition[row][col];
        tempStonePosition[row][col] = 0;

        // updates the board with the new stone arrangements
        // if player1 turn
        if (player1Turn) {
            // run through each direction
            if (dir == 0) {
                if (toMove == 1) {
                    row--;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row--;
                    tempStonePosition[row][col] += 1;
                    row--;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if (toMove == 3) {
                    row--;
                    tempStonePosition[row][col] += 1;
                    row--;
                    tempStonePosition[row][col] += 2;
                    row--;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            } else if (dir == 1) {
                if (toMove == 1) {
                    row++;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row++;
                    tempStonePosition[row][col] += 1;
                    row++;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if (toMove == 3) {
                    row++;
                    tempStonePosition[row][col] += 1;
                    row++;
                    tempStonePosition[row][col] += 2;
                    row++;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            } else if (dir == 2) {
                if (toMove == 1) {
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    col--;
                    tempStonePosition[row][col] += 1;
                    col--;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if (toMove == 3) {
                    col--;
                    tempStonePosition[row][col] += 1;
                    col--;
                    tempStonePosition[row][col] += 2;
                    col--;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            } else if (dir == 3) {
                if (toMove == 1) {
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    col++;
                    tempStonePosition[row][col] += 1;
                    col++;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if (toMove == 3) {
                    col++;
                    tempStonePosition[row][col] += 1;
                    col++;
                    tempStonePosition[row][col] += 2;
                    col++;
                    tempStonePosition[row][col] += movingStones - 3;
                }
            } else if (dir == 4) {
                if (toMove == 1) {
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row--;
                    col--;
                    tempStonePosition[row][col] += 1;
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if (toMove == 3) {
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
            } else if (dir == 5) {
                if (toMove == 1) {
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row--;
                    col++;
                    tempStonePosition[row][col] += 1;
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if (toMove == 3) {
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
            } else if (dir == 6) {
                if (toMove == 1) {
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row++;
                    col--;
                    tempStonePosition[row][col] += 1;
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if (toMove == 3) {
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
            } else if (dir == 7) {
                if (toMove == 1) {
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row++;
                    col++;
                    tempStonePosition[row][col] += 1;
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones - 1;
                }
                if (toMove == 3) {
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
        } else {
            // TODO: there has to be a better way to do this
            // if player2 turn
            // run through each direction
            if (dir == 0) {
                if (toMove == 1) {
                    row--;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row--;
                    tempStonePosition[row][col] += -1;
                    row--;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if (toMove == 3) {
                    row--;
                    tempStonePosition[row][col] += -1;
                    row--;
                    tempStonePosition[row][col] += -2;
                    row--;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            } else if (dir == 1) {
                if (toMove == 1) {
                    row++;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row++;
                    tempStonePosition[row][col] += -1;
                    row++;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if (toMove == 3) {
                    row--;
                    tempStonePosition[row][col] += -1;
                    row--;
                    tempStonePosition[row][col] += -2;
                    row--;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            } else if (dir == 2) {
                if (toMove == 1) {
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    col--;
                    tempStonePosition[row][col] += -1;
                    col--;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if (toMove == 3) {
                    col--;
                    tempStonePosition[row][col] += -1;
                    col--;
                    tempStonePosition[row][col] += -2;
                    col--;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            } else if (dir == 3) {
                if (toMove == 1) {
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    col++;
                    tempStonePosition[row][col] += -1;
                    col++;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if (toMove == 3) {
                    col++;
                    tempStonePosition[row][col] += -1;
                    col++;
                    tempStonePosition[row][col] += -2;
                    col++;
                    tempStonePosition[row][col] += movingStones + 3;
                }
            } else if (dir == 4) {
                if (toMove == 1) {
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row--;
                    col--;
                    tempStonePosition[row][col] += -1;
                    row--;
                    col--;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if (toMove == 3) {
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
            } else if (dir == 5) {
                if (toMove == 1) {
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row--;
                    col++;
                    tempStonePosition[row][col] += -1;
                    row--;
                    col++;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if (toMove == 3) {
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
            } else if (dir == 6) {
                if (toMove == 1) {
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row++;
                    col--;
                    tempStonePosition[row][col] += -1;
                    row++;
                    col--;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if (toMove == 3) {
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
            } else if (dir == 7) {
                if (toMove == 1) {
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones;
                }
                if (toMove == 2) {
                    row++;
                    col++;
                    tempStonePosition[row][col] += -1;
                    row++;
                    col++;
                    tempStonePosition[row][col] += movingStones + 1;
                }
                if (toMove == 3) {
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
        return new Board(tempStonePosition, !player1Turn);
    }
}
