import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {
    private MinPQ<Node> priorityQueue;
    private MinPQ<Node> twinPriorityQueue;
    private int moves = 0;
    private boolean isSolvable = false;
    private Node goalNode;

    private Comparator<Node> manhattanPriority = new Comparator<Node>() {
        @Override
        public int compare(Node a, Node b) {
            return a.board.manhattan() + numMoves(a) - b.board.manhattan() - numMoves(b);
        }
    };

    public Solver(Board initial) {
        priorityQueue = new MinPQ<Node>(manhattanPriority);
        twinPriorityQueue = new MinPQ<Node>(manhattanPriority);

        Node initialNode = new Node(initial, null, 0);
        Board twin = initial.twin();
        /* StdOut.println("---------");
        StdOut.println(twin);
        StdOut.println(initial);
        StdOut.println("---------"); */
        Node initialTwinNode = new Node(twin, null, 0);

        priorityQueue.insert(initialNode);
        twinPriorityQueue.insert(initialTwinNode);

        Node dequeuedNode = initialNode;
        Node dequeuedTwinNode = initialTwinNode;
        while (!dequeuedNode.board.isGoal() && !dequeuedTwinNode.board.isGoal()) {
            dequeuedNode = priorityQueue.delMin();
            dequeuedTwinNode = twinPriorityQueue.delMin();

            Iterable<Board> neighbors = dequeuedNode.board.neighbors();
            insertValidNeighbors(dequeuedNode, neighbors, false);

            Iterable<Board> twinNeighbors = dequeuedTwinNode.board.neighbors();
            insertValidNeighbors(dequeuedTwinNode, twinNeighbors, true);
        }

        if (dequeuedTwinNode.board.isGoal()) {
            return;
        }

        goalNode = dequeuedNode;
        isSolvable = true;
    }

    private void insertValidNeighbors(Node dequeuedNode, Iterable<Board> neighbors, boolean twin) {
        for (Board neighbor: neighbors) {
            if (dequeuedNode.prevNode == null || !neighbor.equals(dequeuedNode.prevNode.board)) {
                Node approvedNeighbor = new Node(neighbor, dequeuedNode, moves);
                if (twin) twinPriorityQueue.insert(approvedNeighbor);
                else priorityQueue.insert(approvedNeighbor);
            }
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }
    public int moves() {
        if (!isSolvable) return -1;
        Node current = goalNode;
        int moves = 0;

        while (current.prevNode != null) {
            moves++;
            current = current.prevNode;
        }
        return moves;
    }

    private static int numMoves(Node node) {
        int moves = 0;
        Node current = node;

        while (current.prevNode != null) {
            moves++;
            current = current.prevNode;
        }
        return moves;
    }

    public Iterable<Board> solution() {
        ArrayList<Board> solution = new ArrayList<>();
        Node currentNode = goalNode;
        while (currentNode.prevNode != null) {
            solution.add(currentNode.board);
            currentNode = currentNode.prevNode;
        }
        solution.add(currentNode.board);
        Collections.reverse(solution);
        return solution;
    }

    private class Node {
        private Board board;
        private Node prevNode;
        private int moves;
        public Node(Board b, Node pN, int m) {
            board = b;
            prevNode = pN;
            moves = m;
        }
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

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
