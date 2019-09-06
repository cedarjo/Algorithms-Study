package xyz.cedarjo.datastructure.tree.binary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
        throw new UnsupportedOperationException();
    }

    @Override
    public void preOrder(List<E> orders) {
        if (dummyRoot.left == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(dummyRoot.left);
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            orders.add(top.e);
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }

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

    private enum Action {
        VISIT, PRINT
    }

    private class Msg {
        private Node node;
        private Action action;

        Msg(Node node, Action action) {
            this.node = node;
            this.action = action;
        }
    }

    public void preOrderWithMsg(List<E> orders) {
        if (dummyRoot.left == null) {
            return;
        }
        LinkedList<Msg> stack = new LinkedList<>();
        stack.push(new Msg(dummyRoot.left, Action.VISIT));
        while (!stack.isEmpty()) {
            Msg msg = stack.pop();
            if (Action.PRINT == msg.action) {
                orders.add(msg.node.e);
            } else {
                // 入栈顺序：右左中
                if (msg.node.right != null) {
                    stack.push(new Msg(msg.node.right, Action.VISIT));
                }
                if (msg.node.left != null) {
                    stack.push(new Msg(msg.node.left, Action.VISIT));
                }
                stack.push(new Msg(msg.node, Action.PRINT));
            }
        }
    }

    @Override
    public void inOrder(List<E> orders) {
        if (dummyRoot.left == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = dummyRoot.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            orders.add(pop.e);
            if (pop.right != null) {
                cur = pop.right;
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
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

    public void inOrderWithMsg(List<E> orders) {
        if (dummyRoot.left == null) {
            return;
        }
        LinkedList<Msg> stack = new LinkedList<>();
        stack.push(new Msg(dummyRoot.left, Action.VISIT));
        while (!stack.isEmpty()) {
            Msg msg = stack.pop();
            if (Action.PRINT == msg.action) {
                orders.add(msg.node.e);
            } else {
                // 入栈顺序：右中左
                if (msg.node.right != null) {
                    stack.push(new Msg(msg.node.right, Action.VISIT));
                }
                stack.push(new Msg(msg.node, Action.PRINT));
                if (msg.node.left != null) {
                    stack.push(new Msg(msg.node.left, Action.VISIT));
                }
            }
        }
    }

    @Override
    public void postOrder(List<E> orders) {
        Set<Node> visitRight = new HashSet<>();
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = dummyRoot.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            Node peek = stack.peek();
            if (visitRight.contains(peek)) {
                // 已经访问了右，该访问元素了
                orders.add(peek.e);
                stack.pop();
                visitRight.remove(peek);
            } else {
                // 还没访问右
                if (peek.right != null) {
                    cur = peek.right;
                    while (cur != null) {
                        stack.push(cur);
                        cur = cur.left;
                    }
                }
                visitRight.add(peek);
            }
        }
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

    public void postOrderWithMsg(List<E> orders) {
        if (dummyRoot.left == null) {
            return;
        }
        LinkedList<Msg> stack = new LinkedList<>();
        stack.push(new Msg(dummyRoot.left, Action.VISIT));
        while (!stack.isEmpty()) {
            Msg msg = stack.pop();
            if (Action.PRINT == msg.action) {
                orders.add(msg.node.e);
            } else {
                // 入展顺序：中右左
                stack.push(new Msg(msg.node, Action.PRINT));
                if (msg.node.right != null) {
                    stack.push(new Msg(msg.node.right, Action.VISIT));
                }
                if (msg.node.left != null) {
                    stack.push(new Msg(msg.node.left, Action.VISIT));
                }
            }
        }
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
        bTree.preOrder(orders);
        System.out.println("preOrder: " + orders);
        orders.clear();
        bTree.preOrderWithMsg(orders);
        System.out.println("preOrder: " + orders);
        orders.clear();

        // bTree.inOrderRecursive(orders);
        // System.out.println("inOrder: " + orders);
        // orders.clear();
        // bTree.inOrder(orders);
        // System.out.println("inOrder: " + orders);
        // orders.clear();
        // bTree.inOrderWithMsg(orders);
        // System.out.println("inOrder: " + orders);
        // orders.clear();

        // bTree.postOrderRecursive(orders);
        // System.out.println("postOrder: " + orders);
        // orders.clear();
        // bTree.postOrder(orders);
        // System.out.println("postOrder: " + orders);
        // orders.clear();
        // bTree.postOrderWithMsg(orders);
        // System.out.println("postOrder: " + orders);
        // orders.clear();

        bTree.levelOrder(orders);
        System.out.println("levelOrder: " + orders);
    }

}