package xyz.cedarjo.datastructure.tree.binary;

import java.util.List;

public class LinkedBinaryTree<E> implements BinaryTree<E> {

    class Node {

        private E e;
        private Node left;
        private Node right;

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }

    }

    private Node root;

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public List<E> preOrder() {
        return null;
    }

    @Override
    public List<E> inOrder() {
        return null;
    }

    @Override
    public List<E> postOrder() {
        return null;
    }

    @Override
    public List<E> levelOrder() {
        return null;
    }

}