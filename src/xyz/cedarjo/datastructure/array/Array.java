package xyz.cedarjo.datastructure.array;

public class Array<E> {

    private E[] data;

    private int capacity;

    private int size;

    public Array() {
        this.capacity = 8;
        this.size = 0;
        this.data = (E[]) new Object[this.capacity];
    }

    public Array(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.data = (E[]) new Object[this.capacity];
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(int index, E e) {
        // index 需要满足 [0, size]
        if (!(index >= 0 && index <= size)) {
            throw new IllegalArgumentException("越界");
        }
        // 将[index, size)位置后移，腾出index
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        // size加一
        size++;
        // 赋值index
        data[index] = e;
        // 使用量满后翻倍
        if (size == capacity) {
            resize(capacity << 1);
        }
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E remove(int index) {
        // index 需要满足 [0, size)
        if (!(index >= 0 && index < size)) {
            throw new IllegalArgumentException("越界");
        }
        // 取出index作为返回值
        E rst = data[index];
        // 将[index+1, size)位置前移，覆盖index
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        // 将size-1位置置为null，便于gc
        data[size - 1] = null;
        // size减一
        size--;
        // 使用量为1/4时减半
        if (size == (capacity >> 2) && (capacity >> 1) > 0) {
            resize(capacity >> 1);
        }
        return rst;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    private void resize(int capacity) {
        E[] replaceData = (E[]) new Object[capacity];
        // resize操作可能是大变小或小变大，例如原容量100改为50，就只拷贝前50条；原容量50改为100，也是只拷贝前50条
        int copy = Math.min(this.capacity, capacity);
        for (int i = 0; i < copy; i++) {
            replaceData[i] = this.data[i];
        }
        this.data = replaceData;
        this.capacity = capacity;
    }

    public E set(int index, E e) {
        // index 需要满足[0, size)
        if (!(index >= 0 && index < size)) {
            throw new IllegalArgumentException("越界");
        }
        // 获取被替换的元素
        E oldElement = data[index];
        data[index] = e;
        return oldElement;
    }

    public void swap(int indexA, int indexB) {
        // index 需要满足[0, size)
        if (!(indexA >= 0 && indexA < size
                && indexB >= 0 && indexB < size)) {
            throw new IllegalArgumentException("越界");
        }
        E foo = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = foo;
    }

    public int indexOf(E e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

}
