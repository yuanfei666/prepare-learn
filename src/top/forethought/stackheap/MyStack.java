package top.forethought.stackheap;

import java.util.Scanner;


/**
 * @author forethought
 * @date 2018/12/23
 * @description  实现zhan的基本操作
 **/

/**
 *   jdk中的Stack  由于继承vector，拥有通过对象的索引来删除元素
 *                                    具体是使用数组拷贝
 *                                    比如栈中是：1，2，3，4，5，6，7，8，9
 *                                     如果是想删除  5，则将6，7，8，9拷贝至 5的位置
 *                                     变成：1，2，3，4，6，7，8，9
 *                                     elementCount--
 *
 *                                     pop,push等操作使用synchonized关键字同步
 * @param <T>
 */


public class MyStack<T> {
    static int MAX_SIZE = 5;
    int top;//top 这里指向下一个入栈的位置   top-1 表示栈顶元素
    int size;
    Object datas[];

    public MyStack() {
        top = 0;
        size = MAX_SIZE;
        datas = new Object[MAX_SIZE];
    }

    public MyStack(int initSize) {
        if (initSize < 0) {
            throw new RuntimeException("stack size can not be A negative number");
        }
        top = 0;
        size = initSize;
        datas = new Object[MAX_SIZE];
    }

    public void push(Object item) {
        if (!isFull()) {
            datas[top++] = item;
            return;
        }
        throw new RuntimeException("statck is overflow!");
    }

    public Object pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }

        return datas[--top];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top >=size;
    }

    public int getTop() {
        return top;
    }

    public static void main(String[] args) {


        MyStack<Integer> stack = new MyStack<>();
        Scanner in = new Scanner(System.in);
        Integer input = null;
        System.out.println("请输入数据:\n");
        while ((input = in.nextInt()) != null) {
            try {
                stack.push(input);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            System.out.println("请输入数据:\n");
        }
        System.out.println("测试弹栈");
        while (!stack.isEmpty()){
            System.out.println("top:"+stack.getTop()+"data:"+stack.pop());
        }
    }


}
/**
 * 思考：如何在长度为n的数组的基础上实现两个栈，使得两个栈的元素数量和不超过n时，都不发生溢出？？
 * 解决：数组的 0  和length-1处分别作为两个栈的底部，push元素从两头向中间靠
 */
