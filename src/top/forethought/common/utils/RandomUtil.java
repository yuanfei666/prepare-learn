package top.forethought.common.utils;
/**
 * @author  wangwei
 * @date     2019/1/20 13:58
 * @classDescription  随机数据产生工具类
 *
 */
public class RandomUtil {
    /**
     *
     * @param from
     * @param to
     * @param count
     * @return  产生 大小范围是 [from,to]的count个元素的int数组
     */
  public static   int [] randomInts(int from, int to, int count){
        if(from>to){
            int temp=from;
            from=to;
            to=temp;
        }
        if(count<=0){
            throw new RuntimeException("count can not be negative");
        }
        int []result=new int[count];
        while (--count>=0){
            result[count]= (int) (from+Math.random()*(to-from+1));
        }
        return result;
    }
    public static void printArray(int[] array){
      StringBuilder builder=new StringBuilder();
      for(int i :array){
          builder.append(i).append(" ");
      }
      System.out.println(builder.toString()+"\n");
    }
}
