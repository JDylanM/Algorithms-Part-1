import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestDeque {
    @Test
    public void testAddRemoveFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(5);
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        assertEquals(1, (int) deque.removeFirst());
        assertEquals(2, (int) deque.removeFirst());
        assertEquals(3, (int) deque.removeFirst());
        assertEquals(4, (int) deque.removeFirst());
        assertEquals(5, (int) deque.removeFirst());
    }

    @Test
    public void testAddRemoveLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(5);
        deque.addLast(4);
        deque.addLast(3);
        deque.addLast(2);
        deque.addLast(1);
        assertEquals(1, (int) deque.removeLast());
        assertEquals(2, (int) deque.removeLast());
        assertEquals(3, (int) deque.removeLast());
        assertEquals(4, (int) deque.removeLast());
        assertEquals(5, (int) deque.removeLast());
    }

    @Test
    public void testAddRemoveCombined() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(5);
        deque.addLast(4);
        deque.addFirst(1);
        assertEquals(1, (int) deque.removeFirst());
        assertEquals(5, (int) deque.removeFirst());
        assertEquals(4, (int) deque.removeLast());
    }

    @Test
    public void testEmpty() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(5);
        deque.addLast(4);
        deque.addFirst(1);
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.addLast(5);
        assertEquals(5, (int) deque.removeFirst());
    }
}
