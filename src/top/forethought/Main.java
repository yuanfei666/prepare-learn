package top.forethought;


import jdk.nashorn.internal.ir.WhileNode;

import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
	      System.out.println(Math.pow(2,10));
	      long end1=System.currentTimeMillis();
	      System.out.println(1<<10);
	      long end2=System.currentTimeMillis();
	    System.out.println("time1:"+(end1-start));
        System.out.println("time2:"+(end2-end1));

    }
}
