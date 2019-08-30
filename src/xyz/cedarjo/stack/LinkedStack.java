package xyz.cedarjo.stack;

import xyz.cedarjo.linked.Linked;

public class LinkedStack<E> implements Stack<E> {

    private Linked<E> linked;

    public LinkedStack() {
        this.linked = new Linked<>();
    }

    @Override
    public boolean push(E e) {
        linked.addLast(e);
        return true;
    }

    @Override
    public E pop() {
        return linked.removeLast();
    }

    @Override
    public E peek() {
        return linked.getLast();
    }

    @Override
    public boolean isEmpty() {
        return linked.isEmpty();
    }

    @Override
    public int getSize() {
        return linked.getSize();
    }
}
