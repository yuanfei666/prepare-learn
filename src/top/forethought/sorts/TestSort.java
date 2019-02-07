package top.forethought.sorts;

import org.junit.Test;
import top.forethought.common.utils.RandomUtil;
import top.forethought.sorts.exchangesort.BubbleSort;
import top.forethought.sorts.exchangesort.QuickSort;
import top.forethought.sorts.insert.ShellSort;
import top.forethought.sorts.insert.SimpleInsertSort;
import top.forethought.sorts.interfaces.Sortable;
import top.forethought.sorts.selective.MergeSort;
import top.forethought.sorts.selective.SimpleSelectSort;

import java.util.Arrays;

public class TestSort {
    private static final int MIN_NUMBER=1;
    private static final int MAX_NUMBER=100;
    private static final int NUMBER_COUNT=100;
    private static final int []finalArray= RandomUtil.randomInts(MIN_NUMBER,MAX_NUMBER,NUMBER_COUNT);
   @Test
   // 规模 1000  耗时:7
   //           相同数据:3,5,5
   // 10000规模,相同数据:26,27.28
    public void testSimpleInsertSort(){
//        int[] data=RandomUtil.randomInts(MIN_NUMBER,MAX_NUMBER,NUMBER_COUNT);

      int []data= Arrays.copyOf(finalArray,finalArray.length);
        RandomUtil.printArray(data);
       long start=System.currentTimeMillis();
       new SimpleInsertSort().sort(data);
       long spent=System.currentTimeMillis()-start;
       RandomUtil.printArray(data);
       System.out.println("耗时 :"+spent);
    }
    @Test
    //规模:1000 耗时:3
    //            相同数据:2,2,3
    // 10000规模,相同数据:11,11,9
    public void testShellSort(){
//        int[] data=RandomUtil.randomInts(MIN_NUMBER,MAX_NUMBER,NUMBER_COUNT);
        int []data= Arrays.copyOf(finalArray,finalArray.length);
        RandomUtil.printArray(data);
        long start=System.currentTimeMillis();
        new ShellSort().sort(data);
        long spent=System.currentTimeMillis()-start;

        RandomUtil.printArray(data);
        System.out.println("耗时:"+spent);
    }

    public static void main(String[] args) {
      TestSort testSort=new TestSort();
      testSort.testSimpleInsertSort();
      testSort.testShellSort();
      testSort.testJdkSort();
      testSort.testQkSort();
    }
    @Test
    public void testJdkSort(){
        int []data= Arrays.copyOf(finalArray,finalArray.length);
        RandomUtil.printArray(data);
        long start=System.currentTimeMillis();
        Arrays.sort(data);
        long spent=System.currentTimeMillis()-start;
        RandomUtil.printArray(data);
        System.out.println("jdk sort 耗时 :"+spent);
    }
    @Test
    public void testSimpleSelectSort(){
        int []data= Arrays.copyOf(finalArray,finalArray.length);
        RandomUtil.printArray(data);
        long start=System.currentTimeMillis();
        new SimpleSelectSort().sort(data);
        long spent=System.currentTimeMillis()-start;
        RandomUtil.printArray(data);
        System.out.println("SimpleSelectSort sort 耗时 :"+spent);
    }
    @Test
    public void testSimpleSelectSort2(){
        int []data= Arrays.copyOf(finalArray,finalArray.length);
        RandomUtil.printArray(data);
    //    long start=System.currentTimeMillis();
       Sortable simpleSelectSort= top.forethought.designpattern.TimeLogProxy.getProxyInstance(SimpleSelectSort.class);
//        new SimpleSelectSort().sort(data);
        simpleSelectSort.sort(data);
       // long spent=System.currentTimeMillis()-start;
        RandomUtil.printArray(data);
      //  System.out.println("SimpleSelectSort sort 耗时 :"+spent);
    }
    @Test
    public void testMergeSort(){
        int []data= Arrays.copyOf(finalArray,finalArray.length);
        RandomUtil.printArray(data);
        Sortable mergeSort= top.forethought.designpattern.TimeLogProxy.getProxyInstance(MergeSort.class);
        mergeSort.sort(data);
        RandomUtil.printArray(data);
    }
    @Test
    public void testBubbleSort(){
        int []data= Arrays.copyOf(finalArray,finalArray.length);
        RandomUtil.printArray(data);
        Sortable bubbleSort= top.forethought.designpattern.TimeLogProxy.getProxyInstance(BubbleSort.class);
        bubbleSort.sort(data);
        RandomUtil.printArray(data);
    }
    @Test
    public void testQkSort(){
        int []data= Arrays.copyOf(finalArray,finalArray.length);
//        int []data=new int[]{12,85,25,16,34,23,49,95,17,61};
        RandomUtil.printArray(data);
        Sortable qkSort= top.forethought.designpattern.TimeLogProxy.getProxyInstance(QuickSort.class);
        qkSort.sort(data);
        RandomUtil.printArray(data);
    }
}
