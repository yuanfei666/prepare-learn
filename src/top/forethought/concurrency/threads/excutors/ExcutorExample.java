package top.forethought.concurrency.threads.excutors;

import top.forethought.concurrency.threads.ThreadRunnable;
import top.forethought.concurrency.threads.ThreadState;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
//
//主要有三种 Executor：
//
//    CachedThreadPool：一个任务创建一个线程；
//    FixedThreadPool：所有任务只能使用固定大小的线程；
//    SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool
public class ExcutorExample {

    public static void main(String[] args) {
//        ExecutorService executorService= Executors.newCachedThreadPool();
//        ExecutorService executorService= Executors.newSingleThreadExecutor();
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        for(int i=0;i<50;i++){
            executorService.execute(new ThreadRunnable());
        }
        System.out.println("before shut down ");
//        注意调用shutdown 方法,并不会立刻将线程中断
        executorService.shutdown();
        System.out.println("after shut down ");

       System.out.println("唤醒 waiting thread");
    }
}
