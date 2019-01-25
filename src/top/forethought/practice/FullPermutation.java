package top.forethought.practice;

import top.forethought.common.utils.ArrayUtil;

/**
 * @author  wangwei
 * @date     2019/1/25 20:43
 * @classDescription  打印n个数的全排列:每个元素都可以放到位置k, k 初始化为0,k之后的元素都可以交换到位置k,
 *                                                每个元素都可以放到k+1,
 *                                                知道k位于最后一个位置,表示已经处理完,可以输出
 *    比如输入 3
 *    输出:1,2,3
 *         1,3,2
 *         2,1,3
 *         2,3,1
 *         3,2,1
 *         3,1,2
 */
public class FullPermutation {
    public static void print(int array[],int k){
        if(k==array.length-1){
            for(int i=0;i<array.length;i++){
                System.out.print(array[i]+" ");
            }
            System.out.println();
            return;
        }
        //
        boolean[] record=new boolean[1024];//记录交换的数据
        for(int i=k;i<array.length;i++){
           if(record[array[i]]){
               continue;//有交换记录,跳过此次交换
           }
            ArrayUtil.swap(array,k,i);
            print(array,k+1);
            ArrayUtil.swap(array,k,i);
            record[array[i]]=true;
        }

    }

    public static void main(String[] args) {
        int [] array=new int[]{1,2,2};

        print(array,0);
    }
    /**
     * 思考:如果有重复元素,那么怎么输出所有的全排列呢?
    *           需要记录与当前k交换的元素值,如果同一种元素交换到k位置过,那么就不再交换
     */
}
