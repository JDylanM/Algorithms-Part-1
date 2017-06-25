import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;

    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;

       // alternative implementation
       // a = java.util.Arrays.copyOf(a, capacity);
    }


    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if ( item == null ) throw new IllegalArgumentException("Cant add null");
        if (n == a.length) resize(2*a.length);    // double size of array if necessary
        a[n++] = item;                            // add item
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int random = StdRandom.uniform(0,n);
        Item item = a[random];
        if (random != n-1) {
            a[random] = a[n-1];
        }
        a[n-1] = null;
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }

    // need fix
    public Item sample() {
        int random = StdRandom.uniform(0,n);
        return a[random];
    }
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int i;

        public RandomIterator() {
            i = n-1;
            StdRandom.shuffle(a);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();
        test.enqueue(5);
        test.enqueue(1);
        test.enqueue(3);
        test.enqueue(4);
        for(Integer b: test) {
            StdOut.println(b);
        }
    }
}
