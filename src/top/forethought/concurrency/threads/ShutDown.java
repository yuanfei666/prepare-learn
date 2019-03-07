package top.forethought.concurrency.threads;

import java.util.concurrent.TimeUnit;

/**
 * @author  wangwei
 * @date     2019/3/2 19:23
 * @classDescription  演示安全的终止线程
 *   1. 调用interrupted()
 *   2,设置boolean变量控制是否中断
 *
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        //1,使用interrupt 方法结束线程
        Runner one=new Runner();
        Thread countThread=new Thread(one,"CountThread1");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        // 2,使用自定义的cancle结束线程
        Runner two=new Runner();
        countThread=new Thread(two,"CountThread2");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }

    private static class Runner implements Runnable{
      private long count;
      private volatile  boolean on=true;
        @Override
        public void run() {
        while (on && !Thread.currentThread().isInterrupted()){
         count++;
         }
            System.out.println("Count:"+count);
        }

        public void cancel() {
           on=false;
        }
    }
}
