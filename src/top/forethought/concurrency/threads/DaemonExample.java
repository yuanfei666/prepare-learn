package top.forethought.concurrency.threads;
/**
 * @author  wangwei
 * @date     2019/3/2 13:05
 * @classDescription
 * 守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。

当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。

main() 属于非守护线程。

使用 setDaemon() 方法将一个线程设置为守护线程
 *
 */
public class DaemonExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new ThreadRunnable());
        thread.setDaemon(true);
        thread.setName("我是守护线程");
        thread.start();
        for(int i=0;i<10;i++){
            new ThreadByThread().start();
        }
        Thread.sleep(60*1000);
        System.out.println("main 结束");
    }
}
