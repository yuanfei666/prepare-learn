package top.forethought.sorts;

import top.forethought.sorts.interfaces.Sortable;

/**
 * @author  wangwei
 * @date     2019/1/20 13:35
 * @classDescription   希尔排序,直接插入排序的改良,通过增量法,将较大的一组,规模变成多组小规模分组,直到增量减为1
 *              每一组都是进行 直接插入排序
 *
 */
public class ShellSort implements Sortable{
    //增量
   private static int []steps=new int[]{1,3,5,7,11};
    @Override
    public void sort(int[] array) {
        for(int stepIndex=steps.length-1;stepIndex>=0;stepIndex--){
            int step=steps[stepIndex];
            //step 为多少,相当于原数组分成多少组,对每一组进行插入排序
          while (step>0){
              insertSort(step-1,steps[stepIndex],array);
              step--;
          }
        }
    }
    // start,start+step,start+2*step,,......插入排序
    //规定  [start,start+step,...start+n*step)   start+n*step=begin  [start,begin)区间元素是间隔step有序的
    void insertSort(int start,int step,int []array){
        //begin 指向无无序列表的第一个元素   begin-step 指向有序列表的最后一个元素
        for(int begin=start+step;begin<array.length;begin+=step){
            int toBePut=array[begin];
            //待插入元素在有序列表的值区间内,挪动位置,
            int leftIndex=begin-step;
            //元素右移
            while (leftIndex>=0 &&array[leftIndex]>toBePut){
                array[leftIndex+step]=array[leftIndex];
                leftIndex-=step;
            }
            array[leftIndex+step]=toBePut;

        }
    }
}
