import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<Item> implements Iterable<Item>{
    private Node head;
    private Node tail;
    private int N;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.N = 0;
    }



    private class Node {
        Item data;
        Node next;

        public Node(Item data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }
    public int size() { return N; }

    public void append(Item data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            N++;
            return;
        }
        tail.next = newNode;
        tail = newNode;
        N++;
    }

    public void add(int index, Item data) {
        if (index < 0 || index > N) {
            System.out.println(index + '>' + N);
            throw new RuntimeException("Index out of bound!");
        }
        Node newNode = new Node(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (isEmpty()) tail = newNode;
            N++;
            return;
        }

        Node temp = head;
        for (int i = 0; i < index-1; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        if (temp == tail) tail = newNode;
        N++;
    }

    public Item get(int index) {
        if (index < 0 || index >= N) {
            throw new RuntimeException("Index out of bound!");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public void remove(int index) {
        if (index < 0 || index >= N) {
            System.out.println(index + " > " + N);
            System.out.println("Error: Out of bound!");
            return;
        }

        if (index == 0) {
            head = head.next;
            if (isEmpty()) tail = null;
            N--;
            return;
        }
        Node temp = head;
        for (int i = 0; i < index-1; i++) {
            temp = temp.next;
        }

        Node delete = temp.next;
        temp.next = delete.next;
        if (temp.next == null) tail = temp;
        N--;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
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
    }
}
