package top.forethought.linklist;
/**
* @author  forethought
* @date     2018/12/27
* @description  双向链表:
 *  只需要一个哨兵即可,标记头结点与尾节点
*
**/
public class DoubleLinkedList {
    private DoubleNode sentry;//哨兵

    public DoubleLinkedList() {
        sentry=new DoubleNode();
    }

    public DoubleLinkedList(DoubleNode sentry) {
        this.sentry = sentry;
    }

    /**
     *  1,双向链表判空 :没有头结点 和尾节点
     * @param doubleLinkedList
     * @return
     */
    public static boolean isEmpty(DoubleLinkedList doubleLinkedList){
        DoubleNode sentry=doubleLinkedList.getSentry();
        if((null==sentry.getPre()) && (null==sentry.getNext())){
            return true;
        }
        return false;
    }




    public DoubleNode getSentry() {
        return sentry;
    }

    public void setSentry(DoubleNode sentry) {
        this.sentry = sentry;
    }

    public static void main(String[] args) {

    }
}
