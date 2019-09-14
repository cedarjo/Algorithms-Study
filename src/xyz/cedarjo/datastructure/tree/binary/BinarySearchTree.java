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

    /**
     * 递归的方式为指定子树新增元素
     * 
     * @param node 指定子树的根节点
     * @param e
     * @return 新增完元素后子树的根节点
     */
    private Node insert(Node node, E e) {
        if (node == null) {
            this.size++;
            return new Node(e, null, null);
        }
        if (this.comparator.compare(e, node.e) <= 0) {
            node.left = insert(node.left, e);
        } else {
            node.right = insert(node.right, e);
        }
        return node;
    }

    /**
     * 非递归的方式新增元素
     * 
     * @param e
     */
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
        this.size++;
    }

    public boolean contains(E e) {
        // return contains(dummyRoot.left, e) != null;
        return containsNonRecursive(e) != null;
    }

    /**
     * 递归的方式判断指定子树中是否存在元素
     * 
     * @param node 指定子树的根节点
     * @param e
     * @return
     */
    private Node contains(Node node, E e) {
        if (node == null) {
            return null;
        }
        int compare = this.comparator.compare(e, node.e);
        if (compare == 0) {
            return node;
        }
        if (compare < 0) {
            return contains(node.left, e);
        }
        return contains(node.right, e);
    }

    /**
     * 非递归的方式判断是否存在元素
     * 
     * @param e
     * @return
     */
    private Node containsNonRecursive(E e) {
        Node cur = dummyRoot.left;
        while (cur != null) {
            int compare = this.comparator.compare(e, cur.e);
            if (compare == 0) {
                return cur;
            }
            if (compare < 0) {
                cur = cur.left;
            } else {// compare > 0
                cur = cur.right;
            }
        }
        return null;
    }

    /**
     * 二分搜索树的最大元素
     * 
     * @return
     */
    public E maximum() {
        Node maximumNode = maximumNode(dummyRoot.left);
        return maximumNode == null ? null : maximumNode.e;
    }

    /**
     * 指定子树的最大节点
     * 
     * @param node
     * @return
     */
    private Node maximumNode(Node node) {
        if (node == null) {
            return null;
        }
        // 一路向右
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 二分搜索树的最小元素
     * 
     * @return
     */
    public E minimum() {
        Node minimumNode = minimumNode(dummyRoot.left);
        return minimumNode == null ? null : minimumNode.e;
    }

    /**
     * 指定子树的最小节点
     * 
     * @param node
     * @return
     */
    private Node minimumNode(Node node) {
        if (node == null) {
            return null;
        }
        // 一路向左
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 移除最小
     */
    public void removeMin() {
        dummyRoot.left = removeMin(dummyRoot.left);
    }

    /**
     * 移除指定子树的最小
     * 
     * @param node 指定子树的根结点
     * @return 移除最小后子树的根节点
     */
    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }
        Node dummyHead = new Node(null, node, null);
        Node parent = dummyHead;
        Node leftSon = parent.left;
        while (leftSon.left != null) {
            parent = leftSon;
            leftSon = leftSon.left;
        }
        // 将最小节点的右子节点赋值为最小节点的父节点的左子节点
        parent.left = leftSon.right;
        leftSon.right = null; // gc
        this.size--;
        return dummyHead.left;
    }

    /**
     * 移除最大
     */
    public void removeMax() {
        dummyRoot.left = removeMax(dummyRoot.left);
    }

    /**
     * 移除指定子树的最大
     * 
     * @param node 指定子树的根结点
     * @return 移除最大后子树的根节点
     */
    private Node removeMax(Node node) {
        if (node == null) {
            return null;
        }
        Node dummyHead = new Node(null, node, null);
        Node parent = dummyHead;
        Node rightSon = parent.left;
        while (rightSon.right != null) {
            parent = rightSon;
            rightSon = rightSon.right;
        }
        // 将最大节点的左子节点赋值为最大节点的父节点的右子节点
        parent.right = rightSon.left;
        rightSon.left = null; // gc
        this.size--;
        return dummyHead.left;
    }

    /**
     * 移除指定元素
     * 
     * @param e
     */
    public void remove(E e) {
        Node parent = dummyRoot;
        Node son = dummyRoot.left;
        int compare = 0;
        boolean direct = false; // false表示son是parent的左子节点
        while (son != null && (compare = this.comparator.compare(e, son.e)) != 0) {
            // 找的过程
            if (compare < 0) {
                direct = false;
                parent = son;
                son = son.left;
            } else {
                direct = true;
                parent = son;
                son = son.right;
            }
        }
        if (son == null) {
            // 没找到
            return;
        }
        // 找到了
        if (son.right == null) {
            if (direct) {
                parent.right = son.left;
            } else {
                parent.left = son.left;
            }
            son.left = null; // gc
            this.size--;
            // 这里也可以从左子树中移除最大节点，并将最大节点替换目标节点
        } else {
            // 从右子树中移除最小节点，并将最小节点替换目标节点
            Node replace = minimumNode(son.right);
            son.right = removeMin(son.right);
            if (direct) {
                parent.right = replace;
            } else {
                parent.left = replace;
            }
            replace.left = son.left;
            replace.right = son.right;
            son.left = null; // gc
            son.right = null; // gc
        }
    }

    /**
     * 前驱元素
     * 
     * @param e
     * @return
     */
    public E predecessor(E e) {
        Node predecessorNode = predecessor(containsNonRecursive(e));
        return predecessorNode == null ? null : predecessorNode.e;
    }

    /**
     * 指定节点的前驱节点
     * 
     * @param node
     * @return
     */
    private Node predecessor(Node node) {
        if (node == null) {
            return null;
        }
        return maximumNode(node.left);
    }

    /**
     * 后继元素
     * 
     * @param e
     * @return
     */
    public E successor(E e) {
        Node successorNode = successor(containsNonRecursive(e));
        return successorNode == null ? null : successorNode.e;
    }

    /**
     * 指定节点的后继节点
     * 
     * @param node
     * @return
     */
    private Node successor(Node node) {
        if (node == null) {
            return null;
        }
        return minimumNode(node.right);
    }

    /**
     * 比指定元素小的最大元素
     * 
     * @param e
     * @return
     */
    public E floor(E e) {
        return null;
    }

    /**
     * 比指定元素小的最大节点
     * 
     * @param e
     * @return
     */
    private Node floorNode(E e) {
        return null;
    }

    /**
     * 比指定元素大的最小元素
     * 
     * @param e
     * @return
     */
    public E ceil(E e) {
        return null;
    }

    /**
     * 比指定元素大的最小节点
     * 
     * @param e
     * @return
     */
    private Node ceilNode(E e) {
        return null;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(Comparator.naturalOrder());
        bst.insert(5);
        System.out.println(bst.getSize());
        bst.insert(6);
        bst.insert(2);
        System.out.println(bst.getSize());
        bst.insert(0);
        bst.insert(3);
        System.out.println(bst.getSize());
        bst.insert(4);
        bst.insert(1);
        System.out.println("maximum : " + bst.maximum());
        System.out.println("minimum : " + bst.minimum());

        System.out.println("the predecessor of 2 is " + bst.predecessor(2));
        System.out.println("the successor of 2 is " + bst.successor(2));
        // bst.removeMax();
        // System.out.println(bst.getSize());
        // bst.removeMin();
        // System.out.println(bst.getSize());
        // bst.remove(5);
        // bst.remove(3);

        // List<Integer> orders = new ArrayList<>();
        // bst.levelOrder(orders);
        // System.out.println(orders);
        // System.out.println(bst.contains(4));
        // System.out.println(bst.contains(7));
    }

}