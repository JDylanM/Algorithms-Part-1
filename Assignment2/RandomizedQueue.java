import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;

    public RandomizedQueue() {

    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {

    }
    public Item dequeue() {

    }
    public Item sample() {

    }
    public Iterator<Item> iterator() {
        return new RandomIterator;
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n-1;
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
        System.out.println("tja");
    }
}
