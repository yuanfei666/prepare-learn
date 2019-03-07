package top.forethought.concurrency.threads;
/**
 * @author  wangwei
 * @date     2019/3/2 19:45
 * @classDescription  测试线程间通信之synchronize
 *
 * synchronize: 可以修饰方法或者以同步块,确保多个线程在同一时刻,只能有一个线程处于方法或者同步块中,保证线程对变量
 *            的可见性和排他性
 *
 * volatile:可以修饰变量,就是告知程序任何对变量的访问都需要从共享内存获取,而对他的改变必须同部刷新
 *             回共享内存,保证所有线程对变量访问的可见性(过多使用,会降低程序执行效率)
 *
 */
public class Synchronized {
    public static void main(String[] args) {
        // 对Synchronized Class 对象加锁
        synchronized (Synchronized.class){

        }
        // 静态同步方法,对Synchronized Class 对象加锁
        m();
    }
    public static synchronized  void m(){

    }
}
