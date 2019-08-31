package xyz.cedarjo.datastructure.stack;

import java.util.ArrayList;

/**
 * 顺序栈
 * @param <E>
 */
public class ArrayListStack<E> implements Stack<E> {

    private ArrayList<E> data;

    private int capacity;

    public ArrayListStack() {
        this(8);
    }

    public ArrayListStack(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>(capacity);
    }

    @Override
    public boolean push(E e) {
        if (data.size() == capacity) {
            // 栈满
            return false;
        }
        data.add(e);
        return true;
    }

    @Override
    public E pop() {
        if (data.size() == 0) {
            // 栈空
            return null;
        }
        return data.remove(data.size() - 1);
    }

    @Override
    public E peek() {
        if (data.size() == 0) {
            // 栈空
            return null;
        }
        return data.get(data.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int getSize() {
        return data.size();
    }
}
