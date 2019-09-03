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
     * @return
     */
    List<E> preOrder();

    /**
     * 中序遍历
     * 
     * @return
     */
    List<E> inOrder();

    /**
     * 后序遍历
     */
    List<E> postOrder();

    /**
     * 层序遍历
     * 
     * @return
     */
    List<E> levelOrder();

}