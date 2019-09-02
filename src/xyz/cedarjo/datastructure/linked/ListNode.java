package xyz.cedarjo.datastructure.linked;

public class ListNode<E> {
    private E e;
    private ListNode<E> next;

    public ListNode(E e, ListNode<E> next) {
        this.e = e;
        this.next = next;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public ListNode<E> getNext() {
        return next;
    }

    public void setNext(ListNode<E> next) {
        this.next = next;
    }
}