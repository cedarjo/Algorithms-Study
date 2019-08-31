package xyz.cedarjo.datastructure.stack;

public interface Stack<E> {

    /**
     * 入栈（推）
     * @param e
     */
    boolean push(E e);

    /**
     * 出栈（砰）
     * @return
     */
    E pop();

    /**
     * 查看栈顶（偷看）
     * @return
     */
    E peek();

    boolean isEmpty();

    int getSize();

}
