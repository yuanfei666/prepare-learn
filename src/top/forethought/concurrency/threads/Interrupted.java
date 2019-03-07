package top.forethought.concurrency.threads;

import top.forethought.common.utils.SleepUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author  wangwei
 * @date     2019/3/2 17:11
 * @classDescription 测试中断
 *
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
     // sleepThread 不停的尝试休眠
      Thread sleepThread=new Thread(new SleepRunner(),"SleepThread");
      sleepThread.setDaemon(true);
      // busyThread 不停的运行
      Thread busyThread=new Thread(new BusyRunner(),"BusyThread");
      busyThread.setDaemon(true);
      sleepThread.start();
      busyThread.start();
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupted is "+sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is "+busyThread.isInterrupted());
    }
    static class SleepRunner implements  Runnable{

        @Override
        public void run() {
            while (true){
                SleepUtil.second(1);
            }
        }
    }
    static class BusyRunner implements Runnable{

        @Override
        public void run() {
            while (true){

            }
        }
    }
}
