package top.forethought.jdkknowledge;

public class TestString {
    public static void main(String[] args) {
        String s1="abc";//常量池
        String s2="abc";
        String s3=new String ("abc");
        String s4="ab";
        String s5="c";
        String s6=s4+s5;//相当于执行了new
        System.out.println(s1==s2);//true
        System.out.println(s1==s3);//false
        System.out.println(s1.equals(s2));//true
        System.out.println(s1.equals(s3));//true
        System.out.println(s1==s6);//false!!!!!
        System.out.println(s3==s6);//false

    }
}
