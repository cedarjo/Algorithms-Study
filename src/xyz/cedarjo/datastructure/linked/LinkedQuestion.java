package xyz.cedarjo.datastructure.linked;

import java.util.Comparator;

class LinkedQuestion {

    /**
     * 链表的反转
     * 
     * @param <E>
     * @param header 原链表的头节点
     * @return 反转后链表的头节点
     */
    public <E> ListNode<E> reverse(ListNode<E> header) {
        // 反转后链表的头节点，初始情况下为空
        ListNode<E> newHeader = null;
        ListNode<E> cur = header;
        while (cur != null) {
            // 暂存待反转节点的下一个节点
            ListNode<E> temp = cur.getNext();
            // 将代反转节点的下一个节点设置为新链表的头节点
            cur.setNext(newHeader);
            // 最后将待反转节点赋值为新链表的头节点，完成反转
            newHeader = cur;
            // 下次要处理的节点
            cur = temp;
        }
        return newHeader;
    }

    /**
     * 链表是否有环
     * 
     * @param <E>
     * @param header
     * @return
     */
    public <E> boolean hasCircle(ListNode<E> header) {
        // 快慢指针：如果有环，快指针一定会追上慢指针
        return firstMeetNode(header) != null;
    }

    /**
     * 链表环检测时，返回快慢节点第一次相遇的节点
     * 
     * @param <E>
     * @param header
     * @return
     */
    private <E> ListNode<E> firstMeetNode(ListNode<E> header) {
        ListNode<E> fastNode, slowNode;
        fastNode = slowNode = header;
        while (fastNode != null && fastNode.getNext() != null) {
            slowNode = slowNode.getNext();
            fastNode = fastNode.getNext().getNext();
            if (slowNode == fastNode) {
                return slowNode;
            }
        }
        return null;
    }

    /**
     * 针对有环链表，获取环的起始节点
     * 
     * @param <E>
     * @param header 环链表的头节点
     * @return 入环的第一个节点
     */
    public <E> ListNode<E> circleStartNode(ListNode<E> header) {
        ListNode<E> firstMeetNode = firstMeetNode(header);
        if (firstMeetNode == null) {
            // 没有环
            return null;
        }
        // 假如带环链表的入环前有节点x个，环上有节点y个，那么该链表一共有节点(x+y)个
        // 快指针和慢指针在环上相遇时，快指针比慢指针多移动了ky个节点（即快指针比慢指针多在环上移动了k圈，k是正整数）
        // 由于快指针一次移动2，慢指针一次移动1，假如慢指针共移动m，则快指针共移动2m
        // 有2m-m=m=ky，如果都用ky表示，那么慢指针移动了ky，快指针移动了2ky
        // 如果只看环上的移动，慢指针移动了ky-x，快指针移动了2ky-x
        // 这相当于快慢指针以入环节点为起点绕环移动，相遇时慢指针移动了ky-x，快指针移动了2ky-x
        // 不难看出，第一次相遇的节点再移动x，就能回到入环节点
        // 这也是分析这么多的结论
        // 假如快慢指针在环上第一次相遇后，新的慢指针从链表头节点开始向后移动，原快指针休息，慢指针继续以原移动速度移动
        // 在新的慢指针移动x后，会与原慢指针在入环节点相遇
        ListNode<E> slowNode = header;
        while (slowNode != firstMeetNode) {
            slowNode = slowNode.getNext();
            firstMeetNode = firstMeetNode.getNext();
        }
        return slowNode;
    }

    /**
     * 针对有环链表，获取环的长度（环中节点个数）
     * 
     * @param <E>
     * @param header
     * @return
     */
    public <E> int circleLength(ListNode<E> header) {
        ListNode<E> firstMeetNode = firstMeetNode(header);
        if (firstMeetNode == null) {
            // 没有环
            return 0;
        }
        // 第一次相遇后的快指针和慢指针，按原速度继续移动
        // 第二次相遇时，快指针比慢指针恰好多移动了环的长度，即慢指针移动了环的长度
        ListNode<E> fastNode, slowNode;
        fastNode = slowNode = firstMeetNode;
        int length = 0;
        do {
            length++;
            fastNode = fastNode.getNext().getNext();
            slowNode = slowNode.getNext();
        } while (fastNode != slowNode);
        return length;
    }

    /**
     * 合并两个有序的链表
     * 
     * @param <E>
     * @param a   有序链表A
     * @param b   有序链表B
     * @return 合并后链表头节点
     */
    public <E> ListNode<E> mergeSortedLinked(ListNode<E> a, ListNode<E> b, Comparator<E> comparator) {
        ListNode<E> aNode = a;
        ListNode<E> bNode = b;
        ListNode<E> retNode;
        // 头节点
        if (comparator.compare(aNode.getE(), bNode.getE()) <= 0) {
            retNode = aNode;
            aNode = aNode.getNext();
        } else {
            retNode = bNode;
            bNode = bNode.getNext();
        }
        ListNode<E> curNode = retNode;
        while (aNode != null && bNode != null) {
            if (comparator.compare(aNode.getE(), bNode.getE()) <= 0) {
                curNode.setNext(aNode);
                aNode = aNode.getNext();
            } else {
                curNode.setNext(bNode);
                bNode = bNode.getNext();
            }
            curNode = curNode.getNext();
        }
        if (aNode != null) {
            curNode.setNext(aNode);
        }
        if (bNode != null) {
            curNode.setNext(bNode);
        }
        return retNode;
    }

    /**
     * 删除单链表中倒数第x个节点
     * 
     * @param <E>
     * @param header 链表的头节点
     * @param x
     * @return 返回头节点
     */
    public <E> ListNode<E> removeByLastNum(ListNode<E> header, int x) {
        // 最直观的做法是，遍历链表，获得节点总数n
        // 接着计算倒数第x个节点的前一个节点是正数第(n-x)个
        // 最后从头节点开始，遍历至该节点，完成操作
        // 时间复杂度O(n)
        // 这里有个时间复杂度不变，但可以遍历一次取到正数第(n-x)个节点的操作
        // 快慢指针均从头开始，不过快指针先移动(x+1)
        // 接着快慢指针一起移动，当快指针移动到NULL时，慢指针落到了(n-x)个节点

        assert x > 0;

        // 使用虚拟头节点
        ListNode<E> dummyHeader = new ListNode<E>(null, header);
        ListNode<E> fastNode, slowNode;
        fastNode = slowNode = dummyHeader;
        for (int i = 0; i <= x; i++) {
            if (fastNode == null) {
                throw new IllegalArgumentException("越界");
            }
            fastNode = fastNode.getNext();
        }

        while (fastNode != null) {
            slowNode = slowNode.getNext();
            fastNode = fastNode.getNext();
        }

        slowNode.setNext(slowNode.getNext().getNext());

        // 头节点有可能也会被删掉
        return dummyHeader.getNext();
    }

    /**
     * 获取链表的中间节点<br/>
     * 比如一共5个节点，返回第3个；一共4个节点，返回第2个
     * 
     * @param <E>
     * @param header
     * @return
     */
    public <E> ListNode<E> getMidNode(ListNode<E> header) {
        // 快慢指针
        ListNode<E> fastNode, slowNode;
        slowNode = header;
        fastNode = header.getNext();
        while (fastNode != null && fastNode.getNext() != null) {
            fastNode = fastNode.getNext().getNext();
            slowNode = slowNode.getNext();
        }
        return slowNode;
    }

    public static void main(String[] args) {
        ListNode<String> header = ListNode.initTestLinked("A", "B", "C", "D", "E");
        System.out.println(header.show());
        LinkedQuestion handler = new LinkedQuestion();
        System.out.println(handler.getMidNode(header).getE());
        header = handler.removeByLastNum(header, 3);
        System.out.println(header.show());
        System.out.println(handler.getMidNode(header).getE());
    }

}