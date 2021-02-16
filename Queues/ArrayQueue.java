import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int N;
    private int front;
    private int rear;

    public ArrayQueue() {
        q = (Item[]) new Object[8];
        this.front = this.rear = this.N = 0;
    }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    //public int arraySize() { return q.length; }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = q[(front+i)%q.length];
        }
        front = 0;
        rear = N;
        q = copy;
    }

    public void enqueue(Item data) {
        if (N == q.length) { resize(2 * q.length); }
        q[rear] = data;
        rear = (rear+1) % q.length;
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is Empty!");
        Item item = q[front];
        q[front] = null;  // releasing the memory
        front = (front+1)%q.length;
        N--;
        if (N > 0 && N == q.length/4) resize(q.length/2);
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is Empty!");
        return q[front];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item>{
        private int i = 0;

        @Override
        public boolean hasNext() { return i < N; }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i+front)%q.length];
            i++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}