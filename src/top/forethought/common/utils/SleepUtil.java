package top.forethought.common.utils;

public class SleepUtil {
    public static void second(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
