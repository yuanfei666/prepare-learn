package top.forethought.linklist;

public class SimpleNode {
    SimpleNode next;
   Object key;

    public SimpleNode(SimpleNode next, Object key) {
        this.next = next;
        this.key = key;
    }

    public SimpleNode getNext() {
        return next;
    }

    public void setNext(SimpleNode next) {
        this.next = next;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "SimpleNode{" +
                "next=" + next +
                ", key=" + key +
                '}';
    }
}
