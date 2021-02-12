package DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Iterable<Item>{
    private Item[] stack;
    private int top;

    public ArrayStack() {
        this.stack = (Item[]) new Object[8];
        this.top = 0;
    }

    private void resize(int capacity) {
        assert capacity >= top;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < top; i++) {
            copy[i] = stack[i];
        }
        stack = copy;
    }

    public boolean isEmpty() { return top == 0; }

    public int size() { return top; }

    public void push(Item data) {
        if (top == stack.length) resize(2 * stack.length);
        stack[top++] = data;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is Empty!");
        return stack[top-1];
    }

    public Item pop() {
        Item item = stack[--top];
        stack[top] = null;
        if (top > 0 && top == stack.length/4) resize(stack.length / 2);
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

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        private int i = top-1;
        @Override
        public boolean hasNext() {
            return  i >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return stack[i--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

