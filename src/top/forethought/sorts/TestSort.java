package top.forethought.sorts;

import org.junit.Test;

import java.util.Arrays;

public class TestSort {
    private static final int MIN_NUMBER=1;
    private static final int MAX_NUMBER=1000;
    private static final int NUMBER_COUNT=20;
    private static final int []finalArray=RandomUtil.randomInts(MIN_NUMBER,MAX_NUMBER,NUMBER_COUNT);
   @Test
   // 规模 1000  耗时:7
   //           相同数据:3,5,5
   // 10000规模,相同数据:26,27.28
    public void testSimpleInsertSort(){
//        int[] data=RandomUtil.randomInts(MIN_NUMBER,MAX_NUMBER,NUMBER_COUNT);

      int []data= Arrays.copyOf(finalArray,finalArray.length);
        RandomUtil.printArray(data);
       long start=System.currentTimeMillis();
       new  SimpleInsertSort().sort(data);
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
    }
}
