package xyz.cedarjo.stack;

import java.util.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

    // 实际使用中，直接用java.util.LinkedList当做栈来使用
    private LinkedList<E> linkedList;

    private int capacity;

    public LinkedListStack() {
        this(8);
    }

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
        this.linkedList = new LinkedList<>();
    }

    @Override
    public boolean push(E e) {
        if (linkedList.size() == capacity) {
            // 栈满
            return false;
        }
        linkedList.push(e);
        return true;
    }

    @Override
    public E pop() {
        return linkedList.pollFirst();
    }

    @Override
    public E peek() {
        return linkedList.peek();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.size();
    }
}
