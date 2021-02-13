package DataStructure.Queues;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {
    private Node front;
    private Node rear;
    private int N;

    public LinkedQueue() {
        this.front = this.rear = null;
        this.N = 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class Node {
        Item data;
        Node next;

        public Node(Item data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    public void enqueue(Item data) {
        Node newNode = new Node(data);
        if (isEmpty()) front = rear = newNode;
        else {
            rear.next = newNode;
            rear = newNode;
        }
        N++;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is Empty!");
        return front.data;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is Empty!");
        Item item = front.data;
        front = front.next;
        N--;
        if (isEmpty()) rear = null;
        return item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = front;

        @Override
        public boolean hasNext() { return current != null; }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.data;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

