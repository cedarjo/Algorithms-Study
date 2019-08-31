package xyz.cedarjo.linked;

public class Linked<E> {

    /**
     * 节点类
     */
    class Node {
        private E e;
        private Node next;

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        Node() {
            this.e = null;
            this.next = null;
        }
    }

    private Node dummyHead; // 虚拟头节点

    private Node tail;

    private int size;

    public Linked() {
        this.dummyHead = new Node();
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(int index, E e) {
        // index 范围[0, size]
        if (!(index >= 0 && index <= this.size)) {
            throw new IllegalArgumentException("越界");
        }
        // 找到待添加位置index的上一个节点
        Node preNode = dummyHead;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        // 原结构 preNode -> indexNode -> node
        // 添加后的结构 preNode -> newNode -> indexNode -> node
        Node newNode = new Node(e, preNode.next);
        preNode.next = newNode;
        // 维护tail
        if (this.size == index) {
            this.tail = newNode;
        }
        // 维护size
        this.size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(this.size, e);
    }

    public E remove(int index) {
        // index 范围[0, size)
        if (!(index >= 0 && index < size)) {
            throw new IllegalArgumentException("越界");
        }
        // 找到待删除位置index的上一个节点
        Node preNode = dummyHead;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        // 原结构 preNode -> removeNode -> node
        // 删除后的结构 preNode -> node
        Node removeNode = preNode.next;
        preNode.next = removeNode.next;
        removeNode.next = null; // gc
        // 维护tail
        if (index == this.size - 1) {
            tail = preNode;
        }
        // 维护size
        this.size--;
        return removeNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(this.size - 1);
    }

    public E set(int index, E e) {
        // index 范围[0, size)
        if (!(index >= 0 && index < size)) {
            throw new IllegalArgumentException("越界");
        }
        // 找到待修改位置index的节点
        Node node = dummyHead;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        E oldElement = node.e;
        node.e = e;
        return oldElement;
    }

    public boolean contains(E e) {
        Node node = dummyHead.next;
        while (node != null) {
            if (node.e.equals(e)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public E get(int index) {
        // index 范围[0, size)
        if (!(index >= 0 && index < size)) {
            throw new IllegalArgumentException("越界");
        }
        Node preNode = dummyHead;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        return preNode.next.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return this.tail == null ? null : this.tail.e;
    }

    public void swap(int indexA, int indexB) {
        // index 范围[0, size)
        if (!(indexA >= 0 && indexA < size
                && indexB >= 0 && indexB < size)) {
            throw new IllegalArgumentException("越界");
        }
        if (indexA == indexB) {
            return;
        }
        // 交换前 preA -> aNode -> nextA -> ... -> preB -> bNode -> nextB
        // 交换后 preA -> bNode -> nextA -> ... -> preB -> aNode -> nextB
        Node preA = dummyHead;
        Node preB = dummyHead;
        Node preNode = dummyHead;
        for (int i = 0; i < Math.max(indexA, indexB); i++) {
            preNode = preNode.next;
            if (i == indexA - 1) {
                preA = preNode;
            } else if (i == indexB - 1) {
                preB = preNode;
            }
        }

        Node aNode = preA.next;
        Node bNode = preB.next;

        Node nextA = aNode.next;
        Node nextB = bNode.next;

        preA.next = bNode;
        bNode.next = nextA;
        preB.next = aNode;
        aNode.next = nextB;

        // 维护tail
        if (indexA == this.size - 1) {
            tail = bNode;
        } else if (indexB == this.size - 1) {
            tail = aNode;
        }
    }

    public void reverse() {
        // 维护tail
        tail = dummyHead.next;
        // 起始状态 A -> B -> C -> D
        // 中间状态 A <- B    C -> D
        // 下一步后 A <- B <- C    D
        Node curNode = dummyHead.next;
        dummyHead.next = null;
        while (curNode != null) {
            Node nextNode = curNode.next;
            curNode.next = dummyHead.next;
            dummyHead.next = curNode;
            curNode = nextNode;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node node = dummyHead.next;
        while (node != null) {
            str.append(node.e).append(" -> ");
            node = node.next;
        }
        str.append("NULL");
        return str.toString();
    }

    public static void main(String[] args) {
        Linked<Integer> linked = new Linked<>();
        for (int i = 0; i < 10; i++) {
            linked.addLast(i);
        }
        System.out.println(linked);
        linked.swap(4, 8);
        System.out.println(linked);
        linked.reverse();
        System.out.println(linked);
    }

}
