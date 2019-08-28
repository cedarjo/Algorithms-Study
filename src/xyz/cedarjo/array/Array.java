package xyz.cedarjo.array;

public class Array<E> {

    private E[] array;

    private int size;

    public Array() {
        array = (E[]) new Object[10];
        size = 0;
    }

    public Array(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("can't init array with zero capacity");
        }
        array = (E[]) new Object[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return array.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("out of size");
        }
        array[index] = e;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("out of size");
        }
        // 满时加倍
        if (size == array.length) {
            resize(size << 1);
        }
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = e;
        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("out of size");
        }
        E ret = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = null;

        // 到1/4时再缩减
        if (size == (array.length >> 2) && (array.length >> 1) > 0) {
            resize(array.length >> 1);
        }

        return ret;
    }

    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("out of size");
        }
        return array[index];
    }


    public void addFirst(E e) {
        add(0, e);
    }

    public E removeFirst() {
        return remove(0);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("out of size");
        }
        if (j < 0 || j >= size) {
            throw new IllegalArgumentException("out of size");
        }
        if (i == j) {
            return;
        }
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void resize(int capacity) {
        E[] newArray = (E[]) new Object[capacity];
        // 拷贝
        int newLength = Math.min(capacity, array.length);
        for (int i = 0; i < newLength; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
    
}
