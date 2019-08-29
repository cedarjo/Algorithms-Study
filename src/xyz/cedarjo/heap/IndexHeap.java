package xyz.cedarjo.heap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 索引堆
 */
public class IndexHeap<E> {

    // 存储实体对象
    private ArrayList<E> data;

    // 对data中的索引建堆
    private ArrayList<Integer> indexes;

    // 表示data中索引在indexes中的索引位置，例如data中index=2的对象在indexes中的位置为5，那么rev.get(2)=5
    // 满足 indexes.get(x)=y; rev.get(y)=x
    private ArrayList<Integer> rev;

    private Comparator<E> comparator;

    public IndexHeap(Comparator<E> comparator) {
        this.data = new ArrayList<>();
        this.indexes = new ArrayList<>();
        this.rev = new ArrayList<>();
        this.comparator = comparator;
    }

    public int getSize() {
        return this.indexes.size();
    }

    public boolean isEmpty() {
        return this.indexes.isEmpty();
    }

    private int leftSon(int parent) {
        return (parent << 1) + 1;
    }

    private int rightSon(int parent) {
        return leftSon(parent) + 1;
    }

    private int parent(int son) {
        return (son - 1) >> 1;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = parent(index);
            Integer sonIndex = this.indexes.get(index);
            Integer parentIndex = this.indexes.get(parent);
            E sonElement = this.data.get(sonIndex);
            E parentElement = this.data.get(parentIndex);
            if (comparator.compare(parentElement, sonElement) >= 0) {
                break;
            }
            // swap
            this.indexes.set(index, parentIndex);
            this.indexes.set(parent, sonIndex);

            // 原本 indexes.get(index)=sonIndex; indexes.get(parent)=parentIndex
            // 交换后 indexes.get(index)=parentIndex; indexes.get(parent)=sonIndex
            this.rev.set(parentIndex, index);
            this.rev.set(sonIndex, parent);

            index = parent;
        }
    }

    private void siftDown(int index) {
        while (leftSon(index) < getSize()) {
            int son = leftSon(index);

            if (rightSon(index) < getSize()
                    && this.comparator.compare(
                    this.data.get(this.indexes.get(rightSon(index))),
                    this.data.get(this.indexes.get(son))) > 0) {
                son = rightSon(index);
            }

            Integer parentIndex = this.indexes.get(index);
            Integer sonIndex = this.indexes.get(son);
            E parentElement = this.data.get(parentIndex);
            E sonElement = this.data.get(sonIndex);
            if (this.comparator.compare(parentElement, sonElement) >= 0) {
                break;
            }

            this.indexes.set(index, sonIndex);
            this.indexes.set(son, parentIndex);

            // 原本 indexes.get(index)=parentIndex; indexes.get(son)=sonIndex
            // 交换后 indexes.get(index)=sonIndex; indexes.get(son)=parentIndex
            this.rev.set(sonIndex, index);
            this.rev.set(parentIndex, son);

            index = son;

        }
    }

    public void add(E e) {
        this.data.add(e);
        this.indexes.add(this.data.size() - 1);
        this.rev.add(this.data.size() - 1);
        siftUp(getSize() - 1);
    }

    public E getTop() {
        return this.data.get(getTopIndex());
    }

    public int getTopIndex() {
        return this.indexes.get(0);
    }

    public E removeTop() {
        return this.data.get(removeTopIndex());
    }

    public int removeTopIndex() {
        int topIndex = getTopIndex();
        Integer tailIndex = this.indexes.remove(getSize() - 1);
        this.indexes.set(0, tailIndex);
        // 将topIndex处的rev值设置为null表示不存在
        this.rev.set(topIndex, null);
        siftDown(0);
        return topIndex;
    }

    public boolean contains(int index) {
        // index 范围[0, this.data.size())
        if (!(index >= 0 && index < this.data.size())) {
            throw new IllegalArgumentException("越界");
        }
        return this.rev.get(index) != null;
    }

    public void change(int index, E e) {
        Integer heapIndex = this.rev.get(index);
        if (heapIndex == null) {
            throw new IllegalArgumentException("该处值已被删除，不可修改");
        }
        this.data.set(index, e);
        siftUp(heapIndex);
        siftDown(heapIndex);
    }

    public static void main(String[] args) {
        IndexHeap<Integer> heap = new IndexHeap<Integer>(Comparator.naturalOrder());
        heap.add(30);
        heap.add(10);
        heap.add(20);
        heap.add(40);
        heap.add(60);
        heap.add(50);
        heap.add(70);
        System.out.println(heap.data);
        System.out.println(heap.indexes);
        heap.change(3, 80);
        System.out.println(heap.data);
        System.out.println(heap.indexes);
    }

}
