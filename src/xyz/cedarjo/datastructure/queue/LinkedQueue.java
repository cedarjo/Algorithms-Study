package xyz.cedarjo.datastructure.queue;

import xyz.cedarjo.datastructure.linked.Linked;

public class LinkedQueue<E> implements Queue<E> {

    private Linked<E> linked;

    private int capacity;

    public LinkedQueue(int capacity) {
        this.capacity = capacity;
        this.linked = new Linked<>();
    }

    public LinkedQueue() {
        this(8);
    }

    @Override
    public void enqueue(E e) {
        // 根据自定义Linked类中实现可知，head的增删时间复杂度都是O(1)，tail的增操作时间复杂度是O(1)，删操作是O(n)
        // 所以避免在tail做出队（队首），最终得出结论：head作队首，tail作队尾
        if (capacity == getSize()) {
            // 队列已满
            return;
        }
        linked.addLast(e);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            // 队列已空
            return null;
        }
        return linked.removeFirst();
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            // 队列已空
            return null;
        }
        return linked.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linked.isEmpty();
    }

    @Override
    public int getSize() {
        return linked.getSize();
    }

    @Override
    public String toString() {
        return linked.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedQueue<>();
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
