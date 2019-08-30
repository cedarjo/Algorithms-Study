package xyz.cedarjo.stack;

import java.util.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public boolean push(E e) {
        linkedList.push(e);
        return true;
    }

    @Override
    public E pop() {
        return linkedList.pop();
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
