package top.forethought.concurrency.threads;

import top.forethought.common.utils.SleepUtil;

/**
 * @author  wangwei
 * @date     2019/3/2 16:05
 * @classDescription  测试线程的状态转换
 *
 */
public class ThreadState {
    public static void main(String[] args) {
      new Thread(new TimeWaiting(),"TimeWaitingThread").start();
      new Thread(new Waiting(),"WaitingThread").start();
      // 使用两个Blocked 线程,一个获取锁成功,另一个阻塞
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();
    }


    // 该线程不断进行睡眠
  public   static class TimeWaiting implements Runnable{
       @Override
       public void run() {
        while (true){
            SleepUtil.second(1);
        }
       }
   }

   //该线程在Waiting.class 实例上等待
   public static class Waiting implements Runnable{

       @Override
       public void run() {
           while(true){
               synchronized (Waiting.class){
                   try {
                       Waiting.class.wait();
                       System.out.println("waiting run");
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }

           }
       }
   }
   //该线程在Blocked.class 实例上加锁后,不会释放锁
   public  static class Blocked implements Runnable{

       @Override
       public void run() {
           synchronized (Blocked.class){
               while (true){
                   SleepUtil.second(100);
               }
           }
       }
   }
}
