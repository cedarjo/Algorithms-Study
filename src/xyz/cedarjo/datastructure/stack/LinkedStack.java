package xyz.cedarjo.datastructure.stack;

import xyz.cedarjo.datastructure.linked.Linked;

public class LinkedStack<E> implements Stack<E> {

    private Linked<E> linked;

    private int capacity;

    public LinkedStack() {
        this(8);
    }

    public LinkedStack(int capacity) {
        this.capacity = capacity;
        this.linked = new Linked<>();
    }

    @Override
    public boolean push(E e) {
        if (linked.getSize() == capacity) {
            // 栈满
            return false;
        }
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
