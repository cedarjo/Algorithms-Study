package xyz.cedarjo.datastructure.tree.binary;

import java.util.List;

/**
 * 二叉树
 */
public interface BinaryTree<E> {

    /**
     * 节点个数
     * 
     * @return
     */
    int getSize();

    /**
     * 是否为空
     * 
     * @return
     */
    boolean isEmpty();

    /**
     * 深度
     * 
     * @return
     */
    int getDepth();

    /**
     * 前序遍历
     * 
     * @param orders
     */
    void preOrder(List<E> orders);

    /**
     * 中序遍历
     * 
     * @param orders
     */
    void inOrder(List<E> orders);

    /**
     * 后序遍历
     * 
     * @param orders
     */
    void postOrder(List<E> orders);

    /**
     * 层序遍历
     * 
     * @param orders
     */
    void levelOrder(List<E> orders);

}