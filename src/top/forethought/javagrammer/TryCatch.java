package top.forethought.javagrammer;

import com.sun.jmx.snmp.SnmpStatusException;

public class TryCatch {
    public static void main(String[] args) {
//        System.out.println("main:"+test001(1));
//        System.out.println("--------------------------------");
//        System.out.println("main:"+test001(2));

        System.out.println("main:" + test002());
    }


    public static String test001(int a) {
        try {
          if(a==1){
              throw new Exception();
          }
            return "try block";

        } catch (Exception e) {

            System.out.println(" catch block");
            return "catch return";
        } finally {
            System.out.println(" finally block");
            return " finally return";
        }
    }
    public static String test002() {
        try {

            return "try block return";

        } catch (Exception e) {

            System.out.println(" catch block");
        } finally {
            System.out.println(" finally block");
            return " finally return";
        }

//        return "result";
    }
}
