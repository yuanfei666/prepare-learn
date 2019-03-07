package top.forethought.concurrency.threads;

public class ThreadByThread extends Thread {
    @Override
    public void run() {
        super.run();
        for(int i=0;i<5;i++){
            System.out.println("["+Thread.currentThread().getName() +"]"+i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
       Thread t1= new ThreadByThread();
       Thread t2=new ThreadByThread();
        Thread t3=new ThreadByThread();
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t1.start();
        t1.join();// join 方法的使用,使得t1结束再继续t2.start 的调用
        t2.start();
        t2.join();
        t3.start();
        t3.join();


        System.out.println("main "+Thread.currentThread().getName());

        // 不使用join 的结果 ,发现 线程的执行顺序不固定
//        main main
//[t2]0
//[t2]1
//[t2]2
//[t2]3
//[t2]4
//[t3]0
//[t1]0
//[t3]1
//[t1]1
//[t3]2
//[t1]2
//[t3]3
//[t1]3
//[t3]4
//[t1]4
        //使用join
//[t1]0
//[t1]1
//[t1]2
//[t1]3
//[t1]4
//[t2]0
//[t2]1
//[t2]2
//[t2]3
//[t2]4
//[t3]0
//[t3]1
//[t3]2
//[t3]3
//[t3]4
//main main
    }
}
