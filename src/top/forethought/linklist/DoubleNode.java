package top.forethought.linklist;
/**
* @author  forethought
* @date     2018/12/27
* @description  双向链表节点
*
**/

public class DoubleNode {
    private DoubleNode pre;
    private DoubleNode next;
    private Object data;

    public DoubleNode(DoubleNode pre, Object data) {
        this.pre = pre;
        this.data = data;
    }

    public DoubleNode() {
    }

    public DoubleNode getPre() {
        return pre;
    }

    public void setPre(DoubleNode pre) {
        this.pre = pre;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
