import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.lang.Math;
import java.util.Stack;

public class Board {
    private int[][] board;
    int n; // n by n board;
    private int manhattan = 0;

    public Board(int[][] blocks) {
        board = blocks;
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
                    verticalDist = Math.abs(((value - 1) / n ) - i);
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
            for (int j = 0; j < n-1; j++) {
                int currentPos = board[i][j];
                if (currentPos != tileNumber) {
                    return false;
                }
                tileNumber++;
            }
        }
        return true;
    }

    public Board twin() {
        // exchanges last block with first block
        int tmpFirst = board[0][0];
        int tmpLast = board[n-1][n-1];
        board[0][0] = tmpLast;
        board[n-1][n-1] = tmpFirst;
        Board twin = new Board(board);
        // reverse
        board[n-1][n-1] = tmpLast;
        board[0][0] = tmpFirst;
        return twin;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;
        return that.toString() == toString();
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>;
        int zeroXPos = 0;
        int zeroYpos = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; i++) {
                if (board[i][j] == 0) {
                    zeroXPos = i;
                    zeroYpos = j;
                }
            }
        }

        if (zeroXPos - 1 > 0) {
            //abovetile exists
        }

        if (zeroXPos + 1 < n) {
            //belowtie exists
        }

        if (zeroYpos - 1 > 0) {
            //lefttile exists
        }

        if (zeroYpos + 1 < n) {
            // righttile exists
        }

        int aboveSite = currentSite - gridSize;
        int leftOfSite = currentSite - 1;
        int rightOfSite = currentSite + 1;
        int belowSite = currentSite + gridSize;

        if (aboveSite > 0) {
            if (grid[aboveSite]) {
                uf.union(currentSite, aboveSite);
            }
        }

        if (rightOfSite % gridSize != 0 && rightOfSite < totalSites) {
            if (grid[rightOfSite]) {
                uf.union(currentSite, rightOfSite);
            }
        }

        if (leftOfSite % gridSize != gridSize-1 && leftOfSite > 0) {
            if (grid[leftOfSite]) {
                uf.union(currentSite, leftOfSite);
            }
        }

        if (belowSite < totalSites) {
            if (grid[belowSite]) {
                uf.union(currentSite, belowSite);
            }
        }
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


    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        System.out.println(initial.hamming());
        System.out.println(initial.manhattan());
        System.out.println(initial.isGoal());
        StdOut.println(initial);
    }
}
