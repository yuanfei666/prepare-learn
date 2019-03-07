package top.forethought.concurrency.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author  wangwei
 * @date     2019/3/1 12:06
 * @classDescription  测试线程的优先级
 *
 */
public class Priority {
    private static volatile boolean notStart=true;
    private static volatile boolean notEnd=true;

    static class Job implements  Runnable{
        private int priority;
        private long jobCount;

        public Job(int priority, long jobCount) {
            this.priority = priority;
            this.jobCount = jobCount;
        }

        @Override
        public void run() {
            while (notStart){
                System.out.println("进入 notStart while loop:"+Thread.currentThread().getName());
                Thread.yield();// 此方法暗示调度器,自己愿意放弃处理器使用
                // 将线程由运行状态转到可运行状态,这不保证一定有效果
            }
            while (notEnd){
                System.out.println("进入 notEnd while loop:"+Thread.currentThread().getName());

                Thread.yield();
               // 这里用来测试 第一个执行到这里的线程是谁,
                // 如果yield 生效,应该是最后一个线程会输出
               System.out.println(Thread.currentThread().getName());
                jobCount++;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        List<Job>jobs=new ArrayList<>();
        for(int i=0;i<6;i++){
            int priority=i<3?Thread.MIN_PRIORITY:Thread.MAX_PRIORITY;
            Job job=new Job(priority,0);
            jobs.add(job);
            Thread thread=new Thread(job,"Thread:"+i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart=false;
        TimeUnit.MICROSECONDS.sleep(100);// 让job执行1秒钟
        notEnd=false;
        for(Job job:jobs){
            System.out.println(" Job Priority:"+job.priority+" count:"+job.jobCount);
        }
    }
}
