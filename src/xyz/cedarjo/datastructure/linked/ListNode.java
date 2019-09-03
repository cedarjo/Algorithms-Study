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

    public String show() {
        StringBuilder str = new StringBuilder();
        ListNode<E> header = this;
        while (header != null) {
            str.append(header.getE()).append(" -> ");
            header = header.next;
        }
        str.append("NULL");
        return str.toString();
    }

    public static <T> ListNode<T> initTestLinked(T... array) {
        ListNode<T> dummyHeader = new ListNode<>(null, null);
        ListNode<T> curNode = dummyHeader;
        for (T var : array) {
            curNode.setNext(new ListNode(var, null));
            curNode = curNode.getNext();
        }
        return dummyHeader.getNext();
    }
}