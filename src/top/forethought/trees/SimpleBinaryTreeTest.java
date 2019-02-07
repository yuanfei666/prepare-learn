package top.forethought.trees;


import org.junit.Test;


import java.util.ArrayDeque;
import java.util.Queue;


/**
 * @author  wangwei
 * @date     2019/2/7 14:36
 * @classDescription  普通二叉树
 *
 */
public class SimpleBinaryTreeTest {

    /**
     * @author  wangwei
     * @date     2019/2/7 14:42
     * @methodDescription   生成二叉树
     *          A
     *         /\
     *        B  C
     *       /  /\
     *      D  E F
     */
    public BTreeNode buildBinTree(){
       BTreeNode node=new BTreeNode('A');
        BTreeNode nodeB=new BTreeNode('B');
        BTreeNode nodeC=new BTreeNode('C');
        BTreeNode nodeD=new BTreeNode('D');
        BTreeNode nodeE=new BTreeNode('E');
        BTreeNode nodeF=new BTreeNode('F');
        nodeB.setLChild(nodeD);
        node.setLChild(nodeB);
        node.setRChild(nodeC);
        nodeC.setLChild(nodeE);
        nodeC.setRChild(nodeF);
        return node;


    }

    /**
     * @author  wangwei
     * @date     2019/2/7 14:37
     * @methodDescription  测试层序遍历:
     *    每层将节点入队,出队时,孩子入队
     *
     */
    @Test

    public void testVisitByHierarchy(){
        BTreeNode tree=buildBinTree();

        Queue<BTreeNode> queue=new ArrayDeque();
        queue.add(tree);
        BTreeNode.visitBinTreeByHierarchy(queue);


    }
    @Test
    /**
     * @author  wangwei
     * @date     2019/2/7 15:29
     * @classDescription  测试先序遍历
     *
     */
    public void testPreVisit(){
        BTreeNode tree=buildBinTree();
        Queue queue=new ArrayDeque();
        BTreeNode.preorderVisit(tree,queue);


    }
    @Test
    /**
     * @author  wangwei
     * @date     2019/2/7 15:34
     * @methodDescription 测试中序遍历
     *
     */
   public void testInorderVisit(){
       BTreeNode tree=buildBinTree();
        Queue queue=new ArrayDeque();
       BTreeNode.inorderVisit(tree,queue);

   }
   /**
    * @author  wangwei
    * @date     2019/2/7 15:52
    * @methodDescription  测试后序遍历
    *
    */
   @Test
   public void testPostorderVisit(){
       BTreeNode tree=buildBinTree();
       Queue queue=new ArrayDeque();
       BTreeNode.postorderVisit(tree,queue);
       System.out.println("队列:"+queue.toString());

   }
   /**
    * @author  wangwei
    * @date     2019/2/7 16:50
    * @methodDescription  测试二叉树还原
    *   后序+中序 还原二叉树
    */
   @Test
    public void restoreBTree(){
       BTreeNode originTree=buildBinTree();
       Queue postQueue=new ArrayDeque();
       Queue inQueue=new ArrayDeque();
      BTreeNode.postorderVisit(originTree,postQueue);
      BTreeNode.inorderVisit(originTree,inQueue);
      System.out.println("后序结果:"+postQueue.toString());
      System.out.println("中序结果:"+inQueue.toString());
     Object []postorderList=postQueue.parallelStream().toArray();
       Object []inorderList=inQueue.parallelStream().toArray();

       BTreeNode restoreTree=BTreeNode.restoreBTreePostInorder(postorderList,postorderList.length-1,inorderList,0,inorderList.length-1);
         System.out.println(restoreTree);
         System.out.println("-------- 分割线--------- 还原后的遍历输出");
       Queue postQueue2=new ArrayDeque();
       Queue inQueue2=new ArrayDeque();
       BTreeNode.postorderVisit(restoreTree,postQueue2);
       BTreeNode.inorderVisit(restoreTree,inQueue2);
       System.out.println("后序结果:"+postQueue2.toString());
       System.out.println("中序结果:"+inQueue2.toString());
   }
    /**
     * @author  wangwei
     * @date     2019/2/7 16:50
     * @methodDescription  测试二叉树还原
     *   先序+中序 还原二叉树
     */
    @Test
    public void restoreBTree2(){
        BTreeNode originTree=buildBinTree();
        Queue preQueue=new ArrayDeque();
        Queue inQueue=new ArrayDeque();
        BTreeNode.preorderVisit(originTree,preQueue);
        BTreeNode.inorderVisit(originTree,inQueue);
        System.out.println("先序结果:"+preQueue.toString());
        System.out.println("中序结果:"+inQueue.toString());
        Object []preorderList=preQueue.parallelStream().toArray();
        Object []inorderList=inQueue.parallelStream().toArray();

        BTreeNode restoreTree=BTreeNode.restoreBTreePreInorder(preorderList,0,inorderList,0,inorderList.length-1);
        System.out.println(restoreTree);
        System.out.println("-------- 分割线--------- 还原后的遍历输出");
        Queue preQueue2=new ArrayDeque();
        Queue inQueue2=new ArrayDeque();
        BTreeNode.preorderVisit(restoreTree,preQueue2);
        BTreeNode.inorderVisit(restoreTree,inQueue2);
        System.out.println("先序结果:"+preQueue2.toString());
        System.out.println("中序结果:"+inQueue2.toString());
    }
}
