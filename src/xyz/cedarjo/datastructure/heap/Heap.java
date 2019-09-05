package xyz.cedarjo.datastructure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import xyz.cedarjo.datastructure.tree.binary.ArrayBinaryTree;

public class Heap<E> extends ArrayBinaryTree<E> {

    private Comparator<E> comparator;

    public Heap(Comparator<E> comparator) {
        this.data = new ArrayList<>();
        this.comparator = comparator;
    }

    public Heap(E[] array, Comparator<E> comparator) {
        this.comparator = comparator;
        heapify(array);
        this.data = new ArrayList<>(Arrays.asList(array));
    }

    /**
     * 堆化 时间复杂度O(n)
     * 
     * @param array
     */
    public void heapify(E[] array) {
        for (int i = parent(array.length - 1); i >= 0; i--) {
            int index = i;
            while (leftSon(index) < array.length) {
                int son = leftSon(index);
                if (rightSon(index) < array.length && this.comparator.compare(array[rightSon(index)], array[son]) > 0) {
                    son = rightSon(index);
                }

                E parentElement = array[index];
                E sonElement = array[son];
                if (this.comparator.compare(parentElement, sonElement) >= 0) {
                    break;
                }
                array[index] = sonElement;
                array[son] = parentElement;

                index = son;
            }
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = parent(index);
            E sonElement = this.data.get(index);
            E parentElement = this.data.get(parent);
            if (comparator.compare(parentElement, sonElement) >= 0) {
                break;
            }
            // swap
            this.data.set(parent, sonElement);
            this.data.set(index, parentElement);

            index = parent;
        }
    }

    private void siftDown(int index) {
        while (leftSon(index) < getSize()) {
            int son = leftSon(index);

            // 如果右子节点存在，且右子节点更大，用右子节点做判断
            if (rightSon(index) < getSize()
                    && this.comparator.compare(this.data.get(rightSon(index)), this.data.get(son)) > 0) {
                son = rightSon(index);
            }

            E parentElement = this.data.get(index);
            E sonElement = this.data.get(son);
            if (this.comparator.compare(parentElement, sonElement) >= 0) {
                break;
            }
            // swap
            this.data.set(index, sonElement);
            this.data.set(son, parentElement);

            index = son;
        }
    }

    public void add(E e) {
        this.data.add(e);
        siftUp(getSize() - 1);
    }

    public E getTop() {
        return this.data.get(0);
    }

    public E removeTop() {
        E top = getTop();
        E tailElement = this.data.remove(getSize() - 1);
        if (isEmpty()) {
            return top;
        }
        this.data.set(0, tailElement);
        siftDown(0);
        return top;
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<Integer>(Comparator.naturalOrder());
        heap.add(30);
        heap.add(10);
        heap.add(20);
        heap.add(40);
        heap.add(60);
        heap.add(50);
        heap.add(70);
        System.out.println(heap.data);
        heap.removeTop();
        System.out.println(heap.data);
        heap.removeTop();
        System.out.println(heap.data);
        heap.removeTop();
        System.out.println(heap.data);
        heap.removeTop();
        System.out.println(heap.data);

        Integer[] array = { 1, 6, 2, 8, 3, 5, 9, 0, 7, 4 };
        heap.heapify(array);
        System.out.println(Arrays.toString(array));
    }
}
