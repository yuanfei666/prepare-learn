package top.forethought.practice;

import top.forethought.common.utils.ArrayUtil;
import top.forethought.common.utils.RandomUtil;

import java.util.Queue;
import java.util.Stack;

/**
 * @author wangwei
 * @date 2019/3/7 14:03
 * @classDescription 输入是一个无无序数组
 * 输出:每个元素的之后一个第一个比自己大的元素
 * 要求o(n) 的时间复杂度
 */
public class FindFirstBiggerAfterSelf {

    public int [] solution(int[] input) {
        if(null==input||0==input.length){
            return  null ;
        }
        Stack<Integer> stack = new Stack<>();
        int bottom = 0; // 初始化栈底指针
        int [] result=new int[input.length];

        for (int i = 0; i < input.length; i++) {
            if (stack.isEmpty()) {

                bottom = i;
                stack.push(i);
                continue;
            }
            // 栈底是栈中最大元素
            // 假设栈中是  3  1
            // 输入是  2     3>2  入栈  ,但是2大于栈顶的1  ,也就是说 2 是1 后面第一个比他大的数
            //   这是后应该将1弹出,2压入
            // 栈:
            //         2 比3小,入栈
            // 栈:3 2
            // 输入  4          3<4  弹栈(清空栈)  4入栈
            // 栈:  4
            if (input[bottom] >= input[i]) {
                // 需要判断栈顶是否也是比新元素大
                // 栈顶较小,说明新元素是栈顶元素的后面的第一个比他大的元素
                // 将栈顶元素对应的原数组相应位置设置为 input[i] 的值
                if (input[stack.peek()] < input[i]) {
                    result[stack.pop()] = input[i];
                }
                // 这里保证了 栈中元素是: 栈底->栈顶 是由大到小的顺序
                if (stack.isEmpty()) {
                    bottom = i;// 更新栈底指针
                }
                stack.push(i);
                continue;
            }

            // 输入元素大于栈底,那么全部弹栈
            while (!stack.isEmpty()) {
                result[stack.pop()] = input[i];
            }
            bottom = i;
            stack.push(i);

        }
        // 处理没找到之后的比他大的位置,此时索引应该还在栈中
        while (!stack.isEmpty()){
            result[stack.pop()]=Integer.MIN_VALUE;
        }
        return result;


    }

    public static void main(String[] args) {
        int[] input = RandomUtil.randomInts(1, 20, 10);
        RandomUtil.printArray(input);
        System.out.println("------------------------");
       int [] result=new FindFirstBiggerAfterSelf().solution(input);
        RandomUtil.printArray(result);
    }
}
