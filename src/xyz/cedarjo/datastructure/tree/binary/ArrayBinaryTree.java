package xyz.cedarjo.datastructure.tree.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基于数组的二叉树主要应用于堆Heap<br/>
 * 这里只讨论基于定长数组的完全二叉树
 */
public class ArrayBinaryTree<E> implements BinaryTree<E> {

    // 基于动态数组存储，从0开始存储
    protected ArrayList<E> data;

    public ArrayBinaryTree() {
        this.data = new ArrayList<>();
    }

    public ArrayBinaryTree(ArrayList<E> data) {
        this.data = new ArrayList<>(data);
    }

    @Override
    public int getSize() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    protected int leftSon(int parent) {
        return (parent << 1) + 1;
    }

    protected int rightSon(int parent) {
        return leftSon(parent) + 1;
    }

    protected int parent(int son) {
        return (son - 1) >> 1;
    }

    @Override
    public int getDepth() {
        int tailIndex = this.data.size() - 1;
        int depth = 0;
        while (tailIndex >= 0) {
            depth++;
            tailIndex = parent(tailIndex);
        }
        return depth;
    }

    @Override
    public void preOrder(List<E> orders) {
        preOrder(0, orders);
    }

    private void preOrder(int index, List<E> orders) {
        if (index >= getSize()) {
            return;
        }
        orders.add(this.data.get(index));
        preOrder(leftSon(index), orders);
        preOrder(rightSon(index), orders);
    }

    @Override
    public void inOrder(List<E> orders) {
        inOrder(0, orders);
    }

    private void inOrder(int index, List<E> orders) {
        if (index >= getSize()) {
            return;
        }
        inOrder(leftSon(index), orders);
        orders.add(this.data.get(index));
        inOrder(rightSon(index), orders);
    }

    @Override
    public void postOrder(List<E> orders) {
        postOrder(0, orders);
    }

    private void postOrder(int index, List<E> orders) {
        if (index >= getSize()) {
            return;
        }
        postOrder(leftSon(index), orders);
        postOrder(rightSon(index), orders);
        orders.add(this.data.get(index));
    }

    @Override
    public void levelOrder(List<E> orders) {
        orders.addAll(data);
    }

    public static void main(String[] args) {
        ArrayBinaryTree<Integer> bTree = new ArrayBinaryTree<>(
                new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        List<Integer> orders = new ArrayList<>();
        bTree.preOrder(orders);
        System.out.println("preOrder: " + orders);
        orders.clear();
        bTree.inOrder(orders);
        System.out.println("inOrder: " + orders);
        orders.clear();
        bTree.postOrder(orders);
        System.out.println("postOrder: " + orders);
        orders.clear();
        bTree.levelOrder(orders);
        System.out.println("levelOrder: " + orders);
        System.out.println("depth: " + bTree.getDepth());
    }

}