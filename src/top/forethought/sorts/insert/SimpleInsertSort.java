package top.forethought.sorts.insert;

import top.forethought.sorts.interfaces.Sortable;

/**
 * @author  wangwei
 * @date     2019/1/20 13:26
 * @classDescription  实现简单插入排序
 * 排序思想:模拟摸牌,手中牌始终是有序的,新元素插入手中,需要判断是否需要挪动
 *        空间:o(1)
 *        时间:o(n^2)
 *        稳定性:稳定
 */
public class SimpleInsertSort implements Sortable{
    @Override
    public void sort(int[] array) {
     int currIndex=1;///标记有序和无序元素的分界点 [0,currIndex) 表示有序区间
        //随着有序区间不断的扩增,直到完全覆盖整个数组,表示完成排序
        //currIndex处元素即为待排序元素
        for(;currIndex<array.length;currIndex++){
          int toBePut=array[currIndex];
          //如果是值在有序数组范围内,需要考虑挪动,放入
          if(toBePut<array[currIndex-1]){
              //leftIndex表示有序序列最大下标
              int leftIndex = currIndex-1;
              while ((leftIndex >= 0) && (array[leftIndex] > toBePut)) {
                  array[leftIndex + 1] = array[leftIndex];
                  leftIndex--;
              }
              array[leftIndex+1]=toBePut;
          }
        }
    }
    /**
     * 思考简单插入排序的改进:
     * 1,查找新元素的适合位置 时,需要o(n)次比较,可以使用二分查找,改进为lg(n)
     * 2,减少排序的规模:使用希尔排序,划分组
     */
}
