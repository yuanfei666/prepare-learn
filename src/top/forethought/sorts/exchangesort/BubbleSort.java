package top.forethought.sorts.exchangesort;

import top.forethought.common.utils.ArrayUtil;
import top.forethought.sorts.interfaces.Sortable;

/**
 * @author wangwei
 * @date 2019/1/24 18:49
 * @classDescription 冒泡排序:
 * 思想:每趟比较,将较大的向后交换,有序子序列从后往前扩张
 * 稳定性:稳定
 */
public class BubbleSort implements Sortable {
    @Override
    public void sort(int[] array) {
        //这只是一个小小的优化
        //优化1:冒泡排序结束的条件:最后一趟没有发生交换
        //优化2:第i趟发生的最后一次交换位置未被记录,导致第i+1 趟会重复 扫描未上一次没有发生交换的位置
        // 比如 初始是  1,-1,3,5,2,7,8,9
        //第一趟扫描之后:-1,1,3,2,5 ,7,8,9 最后一次2交换位置是(下标)   3
        //第二趟扫描之后: -1,1,2,3,5,7,8,9  最后一次2交换位置是2(下标)  扫描范围也应该是 [0,3] 而不是[0,到length]


        boolean exchanged = true;
        int m = array.length-1;
        while (m > 1 && exchanged) {
            exchanged = false;
            int lastSwapIndex = 1;//记录最后一次交换位置,初始化为1
            for (int j = 0; j <= m-1; j++) {
                if (array[j] > array[j + 1]) {
                    ArrayUtil.swap(array, j, j + 1);
                    exchanged = true;
                    lastSwapIndex = j;
                }
            }
            m = lastSwapIndex;
            if (!exchanged) {
                return;
            }

        }

    }
}

