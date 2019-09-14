package xyz.cedarjo.datastructure.tree.binary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 二分搜索树
 */
public class BinarySearchTree<E> extends LinkedBinaryTree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void insert(E e) {
        // dummyRoot.left = insert(dummyRoot.left, e);
        insertNonRecursive(e);
    }

    private Node insert(Node node, E e) {
        if (node == null) {
            return new Node(e, null, null);
        }
        if (this.comparator.compare(e, node.e) <= 0) {
            node.left = insert(node.left, e);
        } else {
            node.right = insert(node.right, e);
        }
        return node;
    }

    private void insertNonRecursive(E e) {
        Node parent = dummyRoot;
        Node son = dummyRoot.left;
        boolean direct = false; // false表示cur是parent的左子节点
        while (son != null) {
            if (this.comparator.compare(e, son.e) <= 0) {
                parent = son;
                son = son.left;
                direct = false;
            } else {
                parent = son;
                son = son.right;
                direct = true;
            }
        }
        if (direct) {
            parent.right = new Node(e, null, null);
        } else {
            parent.left = new Node(e, null, null);
        }
    }

    public boolean comtains(E e) {
        return false;
    }

    public E maximum() {
        return null;
    }

    public E minimum() {
        return null;
    }

    public void removeMin() {

    }

    public void removeMax() {

    }

    public void remove(E e) {

    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(Comparator.naturalOrder());
        bst.insert(5);
        bst.insert(6);
        bst.insert(2);
        bst.insert(0);
        bst.insert(3);
        bst.insert(4);
        bst.insert(1);
        List<Integer> orders = new ArrayList<>();
        bst.levelOrder(orders);
        System.out.println(orders);
    }

}