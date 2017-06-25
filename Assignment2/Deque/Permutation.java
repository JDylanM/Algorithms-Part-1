import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        String n = args[0];
        int randomTimes = Integer.parseInt(n);
        String[] words = StdIn.readAllStrings();
        RandomizedQueue<String> que = new RandomizedQueue<>();
        for( String inputWord: words ) {
            que.enqueue(inputWord);
        }

        for( int i=0; i<randomTimes; i++) {
            StdOut.println(que.sample());
        }
    }
}
