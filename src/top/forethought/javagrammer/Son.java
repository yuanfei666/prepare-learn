package top.forethought.javagrammer;

public class Son extends Parent  implements MyInterface{
    static {
        System.out.println(" static block 1 son");
    }
    static {
        System.out.println("static block 2 son");
    }
    {
        System.out.println("block son");
    }

    public Son(){

//        super();//调用super() 必须在子类构造方法函数第一行
       System.out.println("constructor son");


    }

    public static void main(String[] args) {
        Son son=new Son();
        System.out.println("--------分割线---------");
        son=new Son();

    }

    @Override
    public void m1() {

    }
}
