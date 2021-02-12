package DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class stack<Item> implements Iterable<Item>{
    private Node head;
    private int N;

    public stack() {
        this.head = null;
        this.N = 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class Node {
        Item data;
        Node next;

        public Node(Item data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() { return head == null; }

    public int size() { return N; }

    public void push(Item data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        N++;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is Empty!");
        return head.data;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is Empty!");
        Item item = head.data;
        head = head.next;
        N--;
        return item;
    }

    public String toString() {
        // StringBuilder provides mutable String;
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    private class StackIterator implements Iterator<Item>{
        private Node current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
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
