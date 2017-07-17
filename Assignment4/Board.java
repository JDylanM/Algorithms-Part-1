import java.util.Stack;
import java.util.Arrays;

// check twin
// check equals

public class Board {
    private final int[][] board;
    private final int n; // n by n board;
    private int manhattan = 0;

    public Board(int[][] blocks) {
        board = copy2D(blocks);
        n = board.length;
        computeManhattan();
    }

    private void computeManhattan() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = board[i][j];
                int verticalDist = 0;
                int horizontalDist = 0;
                if (value != 0) {
                    horizontalDist = Math.abs(j - (value - 1) % n);
                    verticalDist = Math.abs(((value - 1) / n) - i);
                }
                manhattan += verticalDist + horizontalDist;
            }
        }
    }

    public int dimension()
    {
        return n;
    }

    public int hamming() {
        int boardNumber = 1; // number from 1 -> n
        int blocksInWrongPos = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int currentPos = board[i][j];
                if (currentPos != boardNumber && currentPos != 0) {
                    blocksInWrongPos++;
                }
                boardNumber++;
            }
        }
        return blocksInWrongPos;
    }
    public int manhattan() {
        return manhattan;
    }

    public boolean isGoal() {
        int tileNumber = 1; // number from 1 -> n
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int currentPos = board[i][j];
                if (n-1 == j && n-1 == i) break;
                if (currentPos != tileNumber) {
                    return false;
                }
                tileNumber++;
            }
        }
        return true;
    }

    public Board twin() {
        // exchanges last block with first blockequa
        Board twin = new Board(board);

        if (twin.board[0][0] == 0) {
            exch(twin.board, 1, 0, 1, 1);
        }
        else if (twin.board[0][1] == 0) {
            exch(twin.board, 1, 0, 1, 1);
        }
        else {
            exch(twin.board, 0, 0, 0, 1);
        }

        return twin;
    }

    private void exch(int[][] matrix, int i, int j, int p, int q) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[p][q];
        matrix[p][q] = tmp;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;
        if (that.dimension() != dimension()) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != (that.board)[i][j]) return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();
        int zeroX = 0;
        int zeroY = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }

        if (zeroX - 1 >= 0) {
            int aboveX = zeroX - 1;
            Board neighbor = verticalNeighbor(aboveX, zeroX, zeroY);
            neighbors.push(neighbor);
        }

        if (zeroX + 1 < n) {
            int belowX = zeroX + 1;
            Board neighbor = verticalNeighbor(belowX, zeroX, zeroY);
            neighbors.push(neighbor);
        }

        if (zeroY - 1 >= 0) {
            int leftY = zeroY - 1;
            Board neighbor = horizontalNeighbor(leftY, zeroX, zeroY);
            neighbors.push(neighbor);
        }

        if (zeroY + 1 < n) {
            int rightY = zeroY + 1;
            Board neighbor = horizontalNeighbor(rightY, zeroX, zeroY);
            neighbors.push(neighbor);
        }
        return neighbors;
    }

    private Board horizontalNeighbor(int horizontalY, int zeroX, int zeroY) {
        // move
        board[zeroX][zeroY] = board[zeroX][horizontalY];
        board[zeroX][horizontalY] = 0;
        Board neighbor = new Board(board);
        // move back
        board[zeroX][horizontalY] = board[zeroX][zeroY];
        board[zeroX][zeroY] = 0;
        return neighbor;
    }

    private int[][] copy2D(int[][] old) {
        int[][] copy = new int[old.length][old.length];
        for (int i = 0; i < old.length; i++) {
            for (int j = 0; j < old[i].length; j++) {
                copy[i][j] = old[i][j];
            }
        }
        return copy;
    }

    private Board verticalNeighbor(int verticalX, int zeroX, int zeroY) {
        // move
        board[zeroX][zeroY] = board[verticalX][zeroY];
        board[verticalX][zeroY] = 0;
        int[][] copy = Arrays.copyOf(board, board.length);
        Board neighbor = new Board(copy);
        // move back
        board[verticalX][zeroY] = board[zeroX][zeroY];
        board[zeroX][zeroY] = 0;
        return neighbor;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
