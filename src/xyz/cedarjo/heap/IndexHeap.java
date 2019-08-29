package xyz.cedarjo.heap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 索引堆
 */
public class IndexHeap<E> {

    private ArrayList<E> data;

    private ArrayList<Integer> indexes;

    private Comparator<E> comparator;

    public IndexHeap(Comparator<E> comparator) {
        this.data = new ArrayList<>();
        this.indexes = new ArrayList<>();
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
            E sonElement = this.data.get(this.indexes.get(index));
            E parentElement = this.data.get(this.indexes.get(parent));
            if (comparator.compare(parentElement, sonElement) >= 0) {
                break;
            }
            // swap
            this.indexes.set(index, parentIndex);
            this.indexes.set(parent, sonIndex);

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

            index = son;

        }
    }

    public void add(E e) {
        this.data.add(e);
        this.indexes.add(this.data.size() - 1);
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
        siftDown(0);
        return topIndex;
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
        heap.removeTop();
        System.out.println(heap.data);
        System.out.println(heap.indexes);
        heap.removeTop();
        System.out.println(heap.data);
        System.out.println(heap.indexes);
        heap.removeTop();
        System.out.println(heap.data);
        System.out.println(heap.indexes);
        heap.removeTop();
        System.out.println(heap.data);
        System.out.println(heap.indexes);
    }

}
