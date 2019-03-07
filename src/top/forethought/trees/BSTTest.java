package top.forethought.trees;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import top.forethought.common.utils.ArrayUtil;
import top.forethought.common.utils.RandomUtil;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author  wangwei
 * @date     2019/2/15 17:08
 * @classDescription  排序二叉树测试
 *
 */

public class BSTTest {
   @Test
    public void testInsert(){
     int []nums=  {10,2,5,7,8,9,10,3,1,14};

     BTreeNode tree=null;
     for(int num:nums){
         System.out.println(num);
       tree= BST.insertNode(tree,num);
     }
     Queue<Integer> queue=new ArrayDeque<>();
     BTreeNode.inorderVisit(tree,queue);
      System.out.println(queue.toString());
       Queue<Integer> queue2=new ArrayDeque<>();
       System.out.println("删除10 之后-----------");
       tree=BST.deleteNode(tree,10);
       BTreeNode.inorderVisit(tree,queue2);
       System.out.println(queue2.toString());
    }

}
