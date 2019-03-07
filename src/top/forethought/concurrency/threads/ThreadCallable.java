package top.forethought.concurrency.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * @author  wangwei
 * @date     2019/3/2 9:03
 * @classDescription  使用实现Callable接口的方式实现线程,可以通过FutureTask  来获取线程执行结果
 *
 */
public class ThreadCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
       System.out.println(Thread.currentThread().getName());
        int i=0;
        while (++i<Integer.MAX_VALUE){

        }
        System.out.println("return 123");
       return 123;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadCallable threadCallable=new ThreadCallable();
        FutureTask<Integer> task=new FutureTask<>(threadCallable);
        Thread thread=new Thread(task);
        thread.start();
        System.out.println("result:"+task.get());
        System.out.println(Thread.currentThread().getName());
        // 怎么判断task.get 方法会不会被阻塞呢?
        // 如果使用: main 一定会在返回结果后输出
        // 如果使用: main 可能在返回结果之前输出
     }
    // futureTask 的get方法获取返回值,会阻塞一直到拿到返回值,才会继续
    // public V get() throws InterruptedException, ExecutionException {
    //        int s = state;
    //        if (s <= COMPLETING)
    //            s = awaitDone(false, 0L);
    //        return report(s);//返回包装结果,如果是normal 表示线程正常完成任务,可以返回结果
                              // 否则抛出异常
    //    }
    //awaitDone 方法
    //  /**
    //     * Awaits completion or aborts on interrupt or timeout.
    //     *
    //     * @param timed true if use timed waits
    //     * @param nanos time to wait, if timed
    //     * @return state upon completion
    //     */
    //    private int awaitDone(boolean timed, long nanos)
    //        throws InterruptedException {
    //        final long deadline = timed ? System.nanoTime() + nanos : 0L;
    //        WaitNode q = null;
    //        boolean queued = false;
    //  死循环
    //        for (;;) {
    //  // 如果线程中断,就移除等待节点(当前中断的线程)
    //    if (Thread.interrupted()) {
    //                removeWaiter(q);
    //                throw new InterruptedException();
    //            }
    //
    //            int s = state;
         // 状态为 COMPLETING之后的状态,就可以返回了
    //            if (s > COMPLETING) {
    //                if (q != null)
    //                    q.thread = null;
    //                return s;
    //            }
    // 状态为COMPLETING (表示线程还在正常执行
    //            else if (s == COMPLETING) // cannot time out yet
    //                Thread.yield();
    //            else if (q == null)
    //                q = new WaitNode();
    //            else if (!queued)
    //                queued = UNSAFE.compareAndSwapObject(this, waitersOffset,
    //                                                     q.next = waiters, q);
    //            else if (timed) {
    //                nanos = deadline - System.nanoTime();
    //                if (nanos <= 0L) {
    //                    removeWaiter(q);
    //                    return state;
    //                }
    //                LockSupport.parkNanos(this, nanos);
    //            }
    //            else
    //                LockSupport.park(this);
    //        }
    //    }
}
