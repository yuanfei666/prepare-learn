package top.forethought.javagrammer;

public class Parent {
    public int publicPro;
    private int privatePro;
    protected int protectedPro;
    int defaultPro;
    static {
        System.out.println(" static block 1 parent");
    }
    static {
        System.out.println("static block 2 parent");
    }
    {
        System.out.println("block parent");
    }
    public void publicMeth(){
        System.out.println("public method parent");
    }
    private void privateMeth(){
        System.out.println("private method parent");
    }
    protected void protectedMeth(){
        System.out.println("protected method parent");
    }
    public Parent(){
        System.out.println("constructor parent");
    }


}
