package xyz.cedarjo.queue;

public class ArrayQueue<E> implements Queue<E> {

    private E[] data;

    private int capacity;

    private int headIndex;

    private int tailIndex;

    public ArrayQueue(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.headIndex = this.tailIndex = 0;
    }

    public ArrayQueue() {
        this(8);
    }

    @Override
    public void enqueue(E e) {
        if (tailIndex == capacity) {
            // 队列已满
            return;
        }
        data[tailIndex++] = e;
        // 搬移
        if (tailIndex == capacity && headIndex > 0) {
            // 将 [headIndex, tailIndex) 向前搬移headIndex位
            for (int i = headIndex; i < tailIndex; i++) {
                data[i - headIndex] = data[i];
            }
            tailIndex -= headIndex;
            headIndex = 0;
        }
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        return data[headIndex++];
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
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
        return tailIndex - headIndex;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("HEAD -> ");
        for (int i = headIndex; i < tailIndex; i++) {
            str.append(data[i]).append(" -> ");
        }
        str.append("TAIL").append("\n");
        str.append("\t").append("headIndex = ").append(headIndex)
                .append(", tailIndex = ").append(tailIndex)
                .append(", size = ").append(getSize());
        return str.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<>();
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
