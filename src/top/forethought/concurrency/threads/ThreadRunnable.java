package top.forethought.concurrency.threads;

public class ThreadRunnable implements  Runnable {
    @Override
    public void run() {
        for(int i=0;i<10;i++){
           // System.out.println(Thread.currentThread().getId()+":"+i);
//            try {
//                Thread.sleep(1);// sleep 方法不会释放锁,但是会释放cpu的占用
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
       Runnable thread1=new ThreadRunnable();
       Runnable thread2=new ThreadRunnable();
      Thread t1=  new Thread(thread1);
       Thread t2= new Thread(thread2);
       t1.start();
       t2.start();
//     t1.join();
//     t2.join();

    }
}
