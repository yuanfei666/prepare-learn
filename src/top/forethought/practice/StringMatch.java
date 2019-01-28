package top.forethought.practice;

import top.forethought.common.utils.RandomUtil;

/**
 * @author wangwei
 * @date 2019/1/26 19:47
 * @classDescription 字符串匹配问题:
 * 问题描述:给定一个较长的字符串,找出目的字串第一次出现的位置
 * <p>
 * 比如:abcdcdef
 * 目的字串 cd
 * 那么需要返回 2
 * 没有匹配,则返回-1
 * 解决方式:
 * 1,最直接的是将 目的字串的首字母与原串挨个比较,
 * 1.1如果找到,换用目的字串的下一个字符与原字符串的下一个字符比较,如果相同,继续1.1
 * 否则进入 1.2
 * 1.2 换用目的首字母与原字符串回退到第一次匹配的字符的下一个字符开始挨个匹配比较
 * 此方法: 效率比较低,因为一旦匹配中断,将会回退到原字符串的第一个匹配字符的下一个位置开始(也就是此次匹配,只是将原字符串
 * 的匹配范围减少了1个字符而已)
 * 方式2:kms 匹配,先将目的字串预处理,获取某些特别的信息,在匹配中断时,回退较少的字符,提高效率
 */
public class StringMatch {

    public static void main(String[] args) {
        char[] origin = "abcdcdefcd".toCharArray();
        char[] target = "efcd".toCharArray();
        System.out.println(matchMethod1(origin, target));
//       RandomUtil.printArray(preHandleKms("abababc".toCharArray()));
        System.out.println(matchMethodKMP(origin, target));

    }


    public static int matchMethod1(char[] origin, char[] target) {
        if (target.length > origin.length) {
            return -1;
        }
        int i = 0;
        int j = 0;

        for (; j < origin.length && i < target.length; ) {
            if (target[i] == origin[j]) {
                //1,如果是target的最后一个字符匹配,表示完全匹配
                if (i == target.length - 1) {
                    return j - target.length + 1;
                }
                i++;
                j++;
            } else {
                j++;
                //2,之前匹配上一部分字符,突然匹配中断,那么就得从target 头部开始,重新匹配
                if (i != 0) {
                    j =j-i+1;//3,从原字符串origin的第一次匹配字符的下一个位置开始
                    i = 0;
                }
            }
        }




        return -1;

    }

    /**
     * 使用kmp 算法,提高匹配效率,需要对目的串target 进行预处理,获取一些比较重要的数据,减少回退次数
     *
     * @param origin
     * @param target
     * @return
     */
    public static int matchMethodKMP(char[] origin, char[] target) {
        int[] backSteps = preHandleKmp(target);
        int i = 0;
        int j = 0;
        while (j < target.length) {
            if (origin[i] == target[j]) {
                i++;
                j++;
            } else {
                //之前有匹配过,target 需要回退,目的串需要回退
                if (j != 0) {
                    j = backSteps[j - 1];
                } else {
                    i++;
                }
            }
            //目标串最后一个字符已经匹配,需要返回第一个字符匹配位置
            if (j == target.length) {
                return i - j;
            }
        }

        return -1;
    }

    private static int[] preHandleKmp(char[] target) {
        int[] backSteps = new int[target.length];
        //比如字符串target:  abababc
        //当前匹配到target下标为 k           (最大长度)
        //k=0    前缀: a       后缀:空    公共长度:  0
        //k=1    前缀:a        后缀:b      公共长度:0
        //k=2     前缀:a ,ab,  后缀:ba,a    公共长度:1(a)
        //k=3    前缀:a, ab,aba 后缀: bab  ,ab ,b  公共长度:2(ab)
        //k=4    前缀:a, ab,aba,abab后缀: baba  ,aba ,ba ,a 公共长度:3(aba)
        //k=5    前缀:a, ab,aba,abab,ababa后缀: babab  ,abab ,bab ,ab,b  公共长度:4(abab)
        //k=6    前缀:a, ab,aba,abab,ababa,ababab后缀: bababc  ,ababc ,babc ,abc,bc,c 公共长度:0
//        int k = 0;
//        int q = 2;
//        for (; q < target.length; q++) {
//        do{
//           k=backSteps[k];
//           if(target[k+1]==target[q]){
//               k+=1;
//           }
//           backSteps[q]=k;
//
//        }while (k>0 && target[k+1]!=target[q]);
//        }
//        return backSteps;
        int m = target.length;
        int[] lps = new int[m];
        int k = 0;//前缀
        int q = 1;//后缀
        while (q < m) {
            if (target[k] == target[q]) {
                k++;
                lps[q] = k;
                q++;
            } else {
                //k!=0,表示之前有公共前缀,那么就使用之前的前缀长度
                if (k != 0) {
                    k = lps[k - 1];
                }
                //k=0,表示一次公共前缀都没出现过,那么公共前缀就是为0
                else {
                    lps[q] = 0;
                    q++;
                }
            }
        }
        return lps;
    }
}
