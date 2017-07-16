import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.lang.Math;

public class Board {
    private int[][] board;
    private int manhattan = 0;

    public Board(int[][] blocks) {
        board = blocks;
        computeManhattan();
    }

    private void computeManhattan() {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = board[i][j];
                int verticalDist = 0;
                int horizontalDist = 0;
                if(value != 0) {
                    horizontalDist = Math.abs(j - (value - 1) % n);
                    verticalDist = Math.abs(((value - 1) / n ) - i);
                }
                manhattan += verticalDist + horizontalDist;
            }
        }
    }
    public int dimension()
 {
        return 0;
    }
    public int hamming() {
        int n = board.length;
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
        return true;
    }
    /*public Board twin() {

    }*/
    public boolean equals(Object y) {
        return true;
    }

    /*public Iterable<Board> neighbors() {

    }*/
    public String toString() {
        return "hej";
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
    }
}
