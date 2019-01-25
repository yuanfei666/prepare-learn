package top.forethought.sorts.exchangesort;

import top.forethought.common.utils.ArrayUtil;
import top.forethought.sorts.interfaces.Sortable;

/**
 * @author wangwei
 * @date 2019/1/24 18:58
 * @classDescription 快速排序 思想: 不断地选取枢纽("中间值"),大,小的元素分居两侧,
 * 比如: 1,4,3,5,6,2
 * 假设选取  3 为中间值
 * 第一趟之后:1,2,3,5,6,4
 * 稳定性:不稳定
 * <p>
 * <p>
 * 需要注意是:优化需要考虑 "中间值"的选取方式
 * 常用的选取方式:1,取首元素(不一定能取到靠中的位置,比如 1,2,3,4,5,6)  有序与逆序情况这是糟糕的
 * 2,随机数法 (比较耗时)
 * 3,三者取中(耗时短)
 * <p>
 * 待排序数组的特点也会影响排序效率:如果数组接近有序,快排会退化为冒泡排序
 */
public class QuickSort implements Sortable {
    @Override
    public void sort(int[] array) {
        qkSort(array, 0, array.length - 1);
//        quickSort(array,0,array.length-1);
    }

    void qkSort(int[] array, int low, int high) {

        if (low >= high) {
            return;
        }

        //获取枢纽值，并将其放在当前待处理序列末尾

        //这里的keyPos 是选取的三者取中的方法,当然也可以采用随机数,(耗时)),取首元素(对于完全有序或逆序,会很差的性能)
        dealPivot(array, low, high);
        int keyPos = high - 1;
        System.out.println(keyPos);
        int keyVal = array[keyPos];
        int left = low +1;
        int right = high-1;
        while (left < right) {
            //从左往右找到第一个比kVal大的元素 x1
            while ((left < right) && array[left] <= keyVal) {
                left++;
            }
            //从右往左找到第一个比kVal 小的元素 x2
            //从右往左,大的往右交换
            while ((left < right) && array[right] >= keyVal) {
                right--;
            }
            //交换 x1,x2
            if (left < right) {
                ArrayUtil.swap(array, left, right);

            }


        }
        //防止出现left与right碰头处元素大于kVal,如果不发生交换,此次排序相当于无用
        if (array[right] > keyVal) {
            ArrayUtil.swap(array, right, keyPos);
        }
        qkSort(array, low, right - 1);
        qkSort(array, right + 1, high);


    }

    // 三者取中  将 两端 与中间位置元素比较,将中间大小的数据放在数组倒数第二个位置,最小放在开头,最大放在末尾
    private void dealPivot(int[] array, int left, int right) {

//比如:1 4 2 4 7 3  8    首:1,中:4,尾:8  排序为  1,4,8  调整后数据变为  1,4,2,7,3,4,8
        int mid = (left + right) / 2;
        if (array[mid] > array[right]) {
            ArrayUtil.swap(array, mid, right);
        }
        if (array[left] > array[right]) {
            ArrayUtil.swap(array, left, right);
        }
        if (array[left] > array[mid]) {
            ArrayUtil.swap(array, left, mid);
        }
        ArrayUtil.swap(array, mid, right - 1);
    }

    public void quickSort(int[] array, int from, int to) {
        if (from < 0 || to >= array.length) {
            return;
        }
        if (from >= to) {
            return;
        }
        int kVal = array[from];
        int kPos = from;
//        int kPos=(from+to)/2;
//        int kVal=array[kPos];
        int low = from;
        int high = to;
        while (low < high) {
            //从右往左找比kVal小的元素
            while (low < high && array[high] >= kVal) {
                high--;
            }
            //从右往左出现比kVal小的元素了，交换
            if (high > low) {
                array[kPos] = array[high];
                // array[high]=kVal;
                kPos = high;
                low++;
            }
            //从左往右，找比kVal大的元素
            while (low < high && array[low] <= kVal) {
                low++;
            }
            //找到比kVal大的元素，交换
            if (low < high) {
                array[kPos] = array[low];
                // array[low]=kVal;
                kPos = low;
                high--;
            }
        }
        array[kPos] = kVal;

        //到此时，kPos左边全是比kVal小的元素
        //           右边全是比kVal大的元素

        quickSort(array, from, kPos - 1);

        quickSort(array, kPos + 1, to);


    }
}
