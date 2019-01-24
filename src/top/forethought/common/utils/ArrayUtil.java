package top.forethought.common.utils;

public class ArrayUtil {
    public static  void swap(int []array,int index1,int index2){
   if(index1<0||index1>array.length-1||index2<0||index2>array.length-1){
       throw new RuntimeException(" index1 or index2 is out of index range");
   }
   int temp=array[index1];
   array[index1]=array[index2];
   array[index2]=temp;

    }
}
