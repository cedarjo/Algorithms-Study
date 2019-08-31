package xyz.cedarjo.datastructure.queue;

public class ArrayCircularQueue<E> implements Queue<E> {

    private E[] data;

    private int headIndex;

    private int tailIndex;

    private int capacity;

    public ArrayCircularQueue(int capacity) {
        this.capacity = capacity + 1; // 预留一个空位置
        this.data = (E[]) new Object[this.capacity];
        this.headIndex = this.tailIndex = 0;
    }

    public ArrayCircularQueue() {
        this(8);
    }

    @Override
    public void enqueue(E e) {
        // 队列已满的条件（数组长度为５）
        // index 0 1 2 3 4 headIndex tailIndex
        //       o o o o x     0         4
        //       x o o o o     1         0
        //       o x o o o     2         1
        //       o o x o o     3         2
        //       o o o x o     4         3
        //                     i         (i+4)%5
        if (tailIndex == (headIndex + this.capacity - 1) % this.capacity) {
            // 队列已满
            return;
        }
        data[tailIndex] = e;
        tailIndex = (tailIndex + 1) % this.capacity;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            // 队列已空
            return null;
        }
        return data[headIndex++];
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            // 队列已空
            return null;
        }
        return data[headIndex];
    }

    @Override
    public boolean isEmpty() {
        return headIndex == tailIndex;
    }

    @Override
    public int getSize() {
        int size = tailIndex - headIndex;
        return size >= 0 ? size : size + this.capacity;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("HEAD -> ");
        int i = headIndex;
        while (i != tailIndex) {
            str.append(data[i]).append(" -> ");
            i = (i + 1) % this.capacity;
        }
        str.append("TAIL").append("\n");
        str.append("\t").append("headIndex = ").append(headIndex)
                .append(", tailIndex = ").append(tailIndex)
                .append(", size = ").append(getSize());
        return str.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayCircularQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
    }
}
