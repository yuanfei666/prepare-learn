package top.forethought.linklist;


/**
 * @author forethought
 * @date 2018/12/24
 * @description 测试单向链表的查，删,
 * 需要注意的是：单向链表删除 是o(n)，由于需要找到pre节点，需要遍历
 *               双向链表删除 是o (1)，由于每个节点都保留着对pre节点的引用
 *
 *
 **/
public class SimpleLinkList {
    //保持对头节点的引用
    private SimpleNode head;
    private SimpleNode linkList;

    public SimpleLinkList(SimpleNode head) {
        this.head = head;
        linkList = head;
    }


    public static void main(String[] args) {
        SimpleNode head = new SimpleNode(null, "1");
        SimpleNode temp = head;
        temp.next = new SimpleNode(null, "2");
        temp = temp.next;
        temp.next = new SimpleNode(null, "3");
        temp = temp.next;
        temp.next = new SimpleNode(null, "4");
        SimpleLinkList simpleLinkList = new SimpleLinkList(head);
        System.out.println("初始值：" + simpleLinkList.toString());
        SimpleLinkList.insert(simpleLinkList, new SimpleNode(null, "insert 5"));
        System.out.println("after insert：" + simpleLinkList.toString());
        SimpleLinkList.delete(simpleLinkList, new SimpleNode(null, "insert 5"));
        System.out.println("after delete：" + simpleLinkList.toString());
        System.out.println("search key=insert 5:"+SimpleLinkList.search(simpleLinkList,"insert 5"));
        System.out.println("search key = 3:"+SimpleLinkList.search(simpleLinkList,"3"));
    }

    public static SimpleNode search(SimpleLinkList list, Object key) {
        if (null == list) {
            return null;
        }
        SimpleNode result = list.head;
        while (result != null) {
            if (result.key.equals(key)) {
                return result;
            }
            result = result.next;
        }
        return null;
    }

    /**
     * 单向链表的删除需要找到待删除节点的 “pre” 节点，由于是单项链表，不能直接的找到pre节点
     *
     * @param list
     * @param target
     * @return
     */
    public static boolean delete(SimpleLinkList list, SimpleNode target) {
        if (null == list || list.head == null) {
            throw new RuntimeException("list must not be empty!!");
        }
        if (null == target) {
            return false;
        }

        SimpleNode head = list.head;
        if (head.getKey().equals(target.getKey())) {
            list.head = head.next;
            list.linkList=list.head;
            return true;
        }
        SimpleNode temp = head;
        while (temp.next != null) {
            if (temp.next.getKey().equals(target.getKey())) {
                temp.next = target.next;
                list.linkList=list.head;
                return true;
            }
        }
        System.out.println("待删除节点不存在：" + target.toString());
        return false;

    }
/*
* 头插法:新节点充当头结点
*
* */
    public static void insert(SimpleLinkList list, SimpleNode newNode) {
       if(null==list){
           throw new RuntimeException("list must not be null");

       }
        if (null == newNode) {
            throw new RuntimeException("newNode must not be null");
        }


        newNode.next = list.head;
        list.head = newNode;
        list.linkList = list.head;
    }

    @Override
    public String toString() {
        return "\nSimpleLinkList{" +
                "head=" + head +
                ", \nlinkList=" + linkList +
                '}';
    }
}
/**
 * 1，单链表实现一个栈：满足 PUSH和POP运行是时间仍为 o(1)
 *           解决:考虑到栈的  先进后出的特点  通过头节点head的引用，push的节点总是位于push后的head节点位置
 *                  PUSH 与POP操作只是对head节点位置的变化
 * 2， 单链表实现队列：满足入队与出队的运行时间仍为 o(1)
 *          解决：队列是先进先出  添加一个对尾节点的引用，或者是添加一个哨兵，同时指向头节点与尾节点，方便即使找到头节点
 *               尾节点
 *  3，动态数据结合操作UNION以两个不想叫的集合S1和S2来作为输入，并返回集合S=S1US2,如何选取一个适合的表类结构，在O（1)的
 *     时间内，实现UNION操作？
 *          解决：最简单的当然是链表的合并，由于s1与s2不相交，那么可以直接使用链表的合并 ，链表需要哨兵或者是两个指针去
 *          指向头节点
 *           L1.head,L1.tail,L2.head,L2.tail
 *           合并后的记为L3
 *
 *           L1.tail.next=L2.head
 *           L3.head=L1.head
 *           L3.tail=L2.tail
 * 4,给出一个 (O)theta (n)的非递归过程，实现对一个含n个元素的单链表的逆转，要求除存储链表本身所需要的空间外，该过程只能使用
 *   固定大小的空间
 *          解决： 逆序，自然想到栈
 *          使用大小为n的 栈，遍历链表节点，压入栈中，弹栈时构建链表
 *           固定大小的空间时指：L.head  这个对头节点的引用
 *
 *
 *
 *
 * **/