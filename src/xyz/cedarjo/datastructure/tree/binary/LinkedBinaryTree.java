package xyz.cedarjo.datastructure.tree.binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedBinaryTree<E> implements BinaryTree<E> {

    protected class Node {

        private E e;
        private Node left;
        private Node right;

        public Node() {
            this(null, null, null);
        }

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }

    }

    protected Node dummyRoot; // 虚拟根节点，真实root是其左子节点

    protected int size;

    public LinkedBinaryTree() {
        this.dummyRoot = new Node();
        this.size = 0;
    }

    public void initTestData(E[] array) {
        assert array.length == 9;

        Node node9 = new Node(array[8], null, null);
        Node node8 = new Node(array[7], null, null);
        Node node7 = new Node(array[6], null, null);
        // Node node6 = new Node(array[5], null, null);
        Node node6 = null;
        // Node node5 = new Node(array[4], null, null);
        Node node5 = null;
        Node node4 = new Node(array[3], node8, node9);
        Node node3 = new Node(array[2], node6, node7);
        Node node2 = new Node(array[1], node4, node5);
        Node node1 = new Node(array[0], node2, node3);
        this.dummyRoot.left = node1;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public void preOrder(List<E> orders) {

    }

    public void preOrderRecursive(List<E> orders) {
        preOrderRecursive(this.dummyRoot.left, orders);
    }

    private void preOrderRecursive(Node node, List<E> orders) {
        if (node == null) {
            return;
        }
        orders.add(node.e);
        preOrderRecursive(node.left, orders);
        preOrderRecursive(node.right, orders);
    }

    @Override
    public void inOrder(List<E> orders) {
        // TODO Auto-generated method stub

    }

    public void inOrderRecursive(List<E> orders) {
        inOrderRecursive(this.dummyRoot.left, orders);
    }

    private void inOrderRecursive(Node node, List<E> orders) {
        if (node == null) {
            return;
        }
        inOrderRecursive(node.left, orders);
        orders.add(node.e);
        inOrderRecursive(node.right, orders);
    }

    @Override
    public void postOrder(List<E> orders) {
        // TODO Auto-generated method stub

    }

    public void postOrderRecursive(List<E> orders) {
        postOrderRecursive(this.dummyRoot.left, orders);
    }

    private void postOrderRecursive(Node node, List<E> orders) {
        if (node == null) {
            return;
        }
        postOrderRecursive(node.left, orders);
        postOrderRecursive(node.right, orders);
        orders.add(node.e);
    }

    @Override
    public void levelOrder(List<E> orders) {
        if (dummyRoot.left == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(dummyRoot.left);
        while (!queue.isEmpty()) {
            Node dequeue = queue.poll();
            orders.add(dequeue.e);
            if (dequeue.left != null) {
                queue.offer(dequeue.left);
            }
            if (dequeue.right != null) {
                queue.offer(dequeue.right);
            }
        }
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bTree = new LinkedBinaryTree<>();
        bTree.initTestData(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        List<Integer> orders = new ArrayList<>();
        bTree.preOrderRecursive(orders);
        System.out.println("preOrder: " + orders);
        orders.clear();
        bTree.inOrderRecursive(orders);
        System.out.println("inOrder: " + orders);
        orders.clear();
        bTree.postOrderRecursive(orders);
        System.out.println("postOrder: " + orders);
        orders.clear();
        bTree.levelOrder(orders);
        System.out.println("levelOrder: " + orders);
        System.out.println("depth: " + bTree.getDepth());
    }

}