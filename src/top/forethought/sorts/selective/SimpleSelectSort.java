package top.forethought.sorts.selective;

import top.forethought.common.utils.ArrayUtil;
import top.forethought.sorts.interfaces.Sortable;

/**
 * @author  wangwei
 * @date     2019/1/24 10:47
 * @classDescription  简单选择排序
 *   排序思想:从无序子序列中"选择"关键字最小(升序排列)或最大的记录,并将其加入到有序子序列中,以此扩充有序子序列长度
 *    排序稳定性:不稳定,由于使用了交换
 */
public class SimpleSelectSort implements Sortable{
    @Override
    public void sort(int[] array) {
     for(int lastSortedIndex=0;lastSortedIndex<array.length;lastSortedIndex++){
         int minIndex=lastSortedIndex;
         for(int lookIndex=lastSortedIndex;lookIndex<array.length;lookIndex++){
             if(array[lookIndex]<array[minIndex]){
                 minIndex=lookIndex;
             }
         }
         if(minIndex!=lastSortedIndex){
             ArrayUtil.swap(array,lastSortedIndex,minIndex);
         }
     }
    }
}
