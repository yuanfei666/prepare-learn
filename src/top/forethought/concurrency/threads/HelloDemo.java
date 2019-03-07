package top.forethought.concurrency.threads;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class HelloDemo {
//    [6]Monitor Ctrl-Break
//[5]Attach Listener
//[4]Signal Dispatcher // 分发处理发送给JVM信号的线程
//[3]Finalizer     // 调用对象finalize方法的线程
//[2]Reference Handler // 清除Reference的线程
//[1]main      // main 线程,用户程序入口
    public static void main(String[] args) {
        ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
        ThreadInfo[] infos=threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo info:infos){
            System.out.println("["+info.getThreadId()+"]"+info.getThreadName());
        }
    }
}
