import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n; // size of stack
    private Node first; // top of stack
    private Node last; // last of stack

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }


    public Deque() {
        first = null;
        last = null;
        n = 0;
        assert check();
    }

    public boolean isEmpty()
    {
        return first == null;
    }
    public int size()
    {
        return n;
    }

    public void addFirst(Item item)
    {
        if (item == null) throw new IllegalArgumentException("Cant add null");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (isEmpty()) last = first;
        else oldfirst.prev = first;
        n++;
        assert check();
    }
    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException("Cant add null");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
        assert check();
    }

    public Item removeFirst() // should return Item
    {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;
        n--;
        if (isEmpty()) last = first;
        else first.prev = null;
        assert check();
        return item;                   // return the saved item
    }
    public Item removeLast() // should return Item
    {

        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = last.item;
        last = last.prev; // second last is now new last
        if (last != null) {
            last.next = null;
        }
        assert check();
        return item;                   // return the saved item
    }

    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

   // check internal invariants
    private boolean check() {

        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) return false;
        }
        else if (n == 1) {
            if (first == null)      return false;
            if (first.next != null) return false;
        }
        else {
            if (first == null)      return false;
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;

        return true;
    }
}
