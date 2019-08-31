package xyz.cedarjo.datastructure.stack;

/**
 * 顺序栈
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

    private E[] data;

    private int size;

    private int capacity;

    public ArrayStack() {
        this(8);
    }

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.data = (E[]) new Object[capacity];
    }

    @Override
    public boolean push(E e) {
        if (this.size == this.capacity) {
            // 栈已满
            return false;
        }
        this.data[this.size] = e;
        this.size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            // 栈空
            return null;
        }
        E rst = this.data[this.size - 1];
        this.size--;
        return rst;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            // 栈空
            return null;
        }
        return this.data[this.size - 1];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
