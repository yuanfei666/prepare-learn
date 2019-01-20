package top.forethought.sorts;

import top.forethought.sorts.interfaces.Sortable;

/**
 * @author  wangwei
 * @date     2019/1/20 13:26
 * @classDescription  实现简单插入排序
 * 排序思想:模拟摸牌,手中牌始终是有序的,新元素插入手中,需要判断是否需要挪动
 *        空间:o(1)
 *        时间:o(n^2)
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
     * @author  wangwei
     * @date     2019/1/20 13:52
     * @methodDescription  将元素toBePut 放置在array的  [0,splitIndex)之间恰当的位置
     *                   注意:[0,splitIndex)区间是有序的
     *
     */
    void putItIntoSorted(int []array,int splitIndex,int toBePut) {
        int leftIndex = splitIndex-1;
        while ((leftIndex >= 0) && (array[leftIndex] > toBePut)) {
            array[leftIndex + 1] = array[leftIndex];
            leftIndex--;
        }
         array[leftIndex+1]=toBePut;
    }
}
