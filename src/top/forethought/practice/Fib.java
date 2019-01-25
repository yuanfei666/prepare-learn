package top.forethought.practice;

/**
 * @author wangwei
 * @date 2019/1/25 19:47
 * @classDescription 斐波拉契数问题:1,1,2,3,5,,,,,,
 * 1,递归实现方式
 * 2,迭代实现方式
 */
public class Fib {
    //递归版本
    public static int solutionRec(int n) {
        if (n <= 2) {
            return 1;
        }


        return solutionRec(n - 1) + solutionRec(n - 2);//尾递归
    }
   //迭代版本
    public static int solutionIter(int n){
        int result=0;
        int pre=0;
        int beforePre=0;

        for(int i=1;i<=n;i++){
            if(i<=2){
                pre=1;
                beforePre=1;
                result+=1;
            }else {
                result=pre+beforePre;
                beforePre=pre;
                pre=result;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(solutionRec(6));
        System.out.println(solutionIter(6));
    }
}
