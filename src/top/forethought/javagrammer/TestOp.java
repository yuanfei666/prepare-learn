package top.forethought.javagrammer;

import org.junit.Test;

public class TestOp {
    @Test
    public void testOr(){
       Object ob1=null;
       Object ob2=null;

        boolean result1=null==ob1^ null==ob2;
        System.out.println(result1);
        ob2=new Object();

        result1=null==ob1^ null==ob2;
        System.out.println(result1);
    }
}
