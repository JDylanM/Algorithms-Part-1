import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;

public class Solver {
    public Solver(Board initial) {

    }
    public boolean isSolvable() {
        return true;
    }
    public int moves() {
        return 0;
    }
    //public Iterable<Board> solution() {
//
//    }
    public static void main(String[] args) {

    }

    private class Node {
        private Board board;
        private Board prevBoard;
        private int moves;
        public Node(Board b, Board pB, int m) {
            board = b;
            prevBoard = pB;
            moves = m;
        }
    }

    private Comparator<Node> nodeComparator = new Comparator<Node>() {
         @Override
         private int compare(Node a, Node b) {
             return a.board.manhattan() + numMoves(a) - b.board.manhattan() - numMoves(b);
         }
     };

}
