package top.forethought.sorts.selective;

import top.forethought.common.utils.RandomUtil;
import top.forethought.sorts.interfaces.Sortable;



/**
 * @author wangwei
 * @date 2019/1/24 15:09
 * @classDescription 归并排序
 * 思想:将整体无序的原数组,分成若干规模小到可以直接排序的分组,每个分组完成排序后,两两合并(两个有序数组的合并)
 * 最终达到整体有序的状态
 */
public class MergeSort implements Sortable {
    @Override
    public void sort(int[] array) {
  int []tempArray=new int[array.length];
//      mergeSort(array,0,array.length-1);
        mergeSort2(array,tempArray,0,array.length-1);
    }

    void mergeSort(int[] array, int leftPos, int rightPos) {

        if(leftPos<rightPos){
            int center=(leftPos+rightPos)/2;
            mergeSort(array,leftPos,center);
            mergeSort(array,center+1,rightPos);
            merge(array,leftPos,center+1,rightPos);

        }



    }
    //执行合并操作

    /**
     *
     * @param array
     * @param leftPos  第一个有序区域的起始索引
     * @param rightPos  第二个有序区域的起始索引
     * @param rightEnd  第二个有序区域的结束索引
     */
    void merge(int []array,int leftPos,int rightPos,int rightEnd){
        final int leftEnd= rightPos-1;//第一个有序区域的最后的一个下标
        final int eleCount=rightEnd-leftPos+1;
        final  int start=leftPos;
//        int tempPos=leftPos;
        int []tempArrays=new int[eleCount];
        int tempPos=0;//tempArrays索引
       while (leftPos<=leftEnd && rightPos<=rightEnd){
           if(array[leftPos]<=array[rightPos]){
               tempArrays[tempPos++]=array[leftPos++];
           }else{
               tempArrays[tempPos++]=array[rightPos++];
           }
       }
       //处理两个数组合并之后仍然有一个数组剩余数据,直接放入tempArray
        while (leftPos<=leftEnd){
            tempArrays[tempPos++]=array[leftPos++];
        }
        while (rightPos<=rightEnd){
            tempArrays[tempPos++]=array[rightPos++];
        }
        //拷贝tempArray 到原数组中
        //注意只是:区间 leftPos至rightEnd区间 原数组的此区间元素已经归并放在tempArrays中
//        System.arraycopy(tempArray,0,array,0,array.length);
        RandomUtil.printArray(tempArrays);
        for(int i=0;i<eleCount;i++){
            array[start+i]=tempArrays[i];
           // System.out.println(tempArray[rightEnd]);
        }

    }
    void mergeSort2(int []array,int []tempArray,int leftPos,int rightPos){
        if(leftPos<rightPos){
            int center=(leftPos+rightPos)/2;
            mergeSort2(array,tempArray,leftPos,center);
            mergeSort2(array,tempArray,center+1,rightPos);
            merge2(array,tempArray,leftPos,center+1,rightPos);

        }
    }
    void merge2(int []array,int []tempArrays,int leftPos,int rightPos,int rightEnd){
        final int leftEnd= rightPos-1;//第一个有序区域的最后的一个下标
        final int eleCount=rightEnd-leftPos+1;
        int tempPos=leftPos;
        while (leftPos<=leftEnd && rightPos<=rightEnd){
            if(array[leftPos]<=array[rightPos]){
                tempArrays[tempPos++]=array[leftPos++];
            }else{
                tempArrays[tempPos++]=array[rightPos++];
            }
        }
        //处理两个数组合并之后仍然有一个数组剩余数据,直接放入tempArray
        while (leftPos<=leftEnd){
            tempArrays[tempPos++]=array[leftPos++];
        }
        while (rightPos<=rightEnd){
            tempArrays[tempPos++]=array[rightPos++];
        }
        //拷贝tempArray 到原数组中
        //注意只是:区间 leftPos至rightEnd区间 原数组的此区间元素已经归并放在tempArrays中
        RandomUtil.printArray(tempArrays);
        for(int i=0;i<eleCount;i++,rightEnd--){
            array[rightEnd]=tempArrays[rightEnd];
        }

    }
}
