package top.forethought.stackheap;

import java.util.Queue;
import java.util.Scanner;

/**
 * @author forethought
 * @date 2018/12/23
 * @description 队列实现
 * 特点：先进先出
 * head=tail+1时，表示队列已满
 * tail始终指向下一个插入元素的位置
 **/
public class MyQueue {
    int MAX_SIZE = 3;
    int head;
    int tail;
    int size;
    Object datas[];

    public MyQueue(int size) {
        this.size = size;
        datas = new Object[size];
        head = tail = 0;

    }

    public MyQueue() {
        this.size = MAX_SIZE;
        datas = new Object[size];
        head = tail = 0;

    }

    /**
     * 入队操作需要注意是否队满，发生上溢
     *
     * @param o
     */
    public void insert(Object o) {
       if(isFull()){
           throw new RuntimeException("queue is fulled,no more space to insert!");
       }
       datas[tail++]=o;
       tail=(tail==size)?0:tail;
    }

    /**
     * 出对操作需要注意是否发生下溢
     * @return
     */
    public Object remove() {
     if(isEmpty()){
         throw new RuntimeException("no more element to remove!");
     }
     Object obj= datas[head++];
     if(head==size){
         head=0;
     }
     return obj;
    }
    boolean isFull(){
        return (tail+1)%size==head;
    }
    boolean isEmpty(){
        return tail==head;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public static void main(String[] args) {


       MyQueue queue=new MyQueue();
        Scanner in = new Scanner(System.in);
        Integer input = null;
        System.out.println("请输入数据:\n");
        while ((input = in.nextInt()) != null) {
            try {
                queue.insert(input);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            System.out.println("请输入数据:\n");
        }
        System.out.println("测试弹栈");
        while (!queue.isEmpty()){
            System.out.println("data:"+queue.remove()+" head:"+queue.getHead()+" tail:"+queue.getTail());
        }
    }

}
