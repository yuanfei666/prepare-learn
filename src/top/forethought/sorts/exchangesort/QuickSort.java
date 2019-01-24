package top.forethought.sorts.exchangesort;

import top.forethought.sorts.interfaces.Sortable;

/**
 * @author  wangwei
 * @date     2019/1/24 18:58
 * @classDescription
 *    快速排序 思想: 不断地选取枢纽("中间值"),大,小的元素分居两侧,
 *      比如: 1,4,3,5,6,2
 *       假设选取  3 为中间值
 *       第一趟之后:1,2,3,5,6,4
 *       稳定性:不稳定
 *
 *
 *       需要注意是:优化需要考虑 "中间值"的选取方式
 *           常用的选取方式:1,取首元素(不一定能取到靠中的位置,比如 1,2,3,4,5,6)
 *                          2,随机数法 (比较耗时)
 *                          3,三者取中(耗时短)
 *
 *       待排序数组的特点也会影响排序效率:如果数组接近有序,快排会退化为冒泡排序
 *
 */
public class QuickSort implements Sortable {
    @Override
    public void sort(int[] array) {

    }
  //计算分片位置

}
