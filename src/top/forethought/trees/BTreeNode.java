package top.forethought.trees;

import lombok.Data;

import java.util.Queue;

/**
 * @author  wangwei
 * @date     2019/2/7 14:23
 * @classDescription  二叉树节点
 *
 */
@Data
public class BTreeNode <T> {
    private T data;
    private BTreeNode lChild;
    private BTreeNode rChild;

    public BTreeNode(T data) {
        this.data = data;
        lChild=null;
        rChild=null;
    }

    /**
     *  二叉树的层序遍历:利用队列先进先出的特点实现
     * @param queue
     */
    public static void visitBinTreeByHierarchy(Queue<BTreeNode> queue){
        if(queue.isEmpty()){
            return;
        }
        BTreeNode head=  queue.remove();
//        System.out.println(" 节点 data:"+head.getData());
        if(head.getLChild()!=null){
            queue.add(head.getLChild());
        }
        if(head.getRChild()!=null){
            queue.add(head.getRChild());
        }
        visitBinTreeByHierarchy(queue);
    }

    /**
     *  问题:1,二叉树的先序遍历
     *       2,二叉树的中序遍历
     *       3,二叉树的后续遍历
     *
     *
     */
    /**
     * @author  wangwei
     * @date     2019/2/7 15:21
     * @methodDescription  先序遍历
     *  使用队列记录遍历结果
     */
    public static void preorderVisit(BTreeNode tree, Queue queue){
        if(null==tree){
            return ;
        }
//        System.out.println(tree.getData());
        queue.add(tree.getData());
        preorderVisit(tree.getLChild(),queue);
        preorderVisit(tree.getRChild(),queue);
    }
    /**
     * @author  wangwei
     * @date     2019/2/7 15:31
     * @classDescription  中序遍历    左,根,右
     *
     */
    public static void  inorderVisit(BTreeNode tree,Queue queue){
        if(null== tree){
            return;
        }
        inorderVisit(tree.getLChild(),queue);
//        System.out.println(tree.getData());
        queue.add(tree.getData());
        inorderVisit(tree.getRChild(),queue);

    }
   /**
    * @author  wangwei
    * @date     2019/2/7 15:48
    * @methodDescription  后序遍历: 左,右,根
    *
    */
   public static void postorderVisit(BTreeNode tree,Queue queue){
       if(null==tree){
           return;
       }
       postorderVisit(tree.getLChild(),queue);
       postorderVisit(tree.getRChild(),queue);
//       System.out.println(tree.getData());
       queue.add(tree.getData());
   }

    /**
     *   问题:已知二叉树的先序,中序  或 中序 ,后序  输出,还原二叉树
     *
     */
   // 思想:先根据先序(或者后序)找到根节点,
    // 然后定位根节点在中序中的位置,将中序拆分成两棵子树,递归处理
    //递归的过程中:需要得到两棵子树各自的根节点在先序(或者后序)中的位置
    // 例如:后序遍历结果是: D B E F C A
    //      中序遍历结果是: D B A E C F
    /**
     *  思路:先根据后序遍历得到 根节点   A
     *   再检查中序遍历  A  的左右两侧,可以得出 左子树是  D B  右子树是 E C F
     *   后序遍历序列中删除 节点A  ,得到 C, 说明子树 ECF 中  C 为根节点,E,F 分别为左右孩子
     *                              后序序列中弹出 E,F,得到B,说明 子树DB 中 B 为根节点,
     *                              再检查中序遍历,得出D 为B的左孩子
     *
     */
   // start end 表示当前处理的中序遍历区间 start-end 为一棵树
    // rootIndex  表示该趟 start-end 这棵树的根节点在后序遍历中的位置
     public static BTreeNode restoreBTreePostInorder(Object []postorderList, int rootIndex, Object []inorderList, int start, int end) {
         if (null == postorderList || null == inorderList) {
             return null;
         }
         if (postorderList.length != inorderList.length) {
             throw new RuntimeException(" 后序遍历与中序遍历节点个数应该相等");
         }
         if(start>end){
             return null;
         }

         //定位 当前start-end 这棵树的根节点在中序遍历中的位置,然后对左右子树进行相似的递归处理
         int targetIndex=0;
         while (inorderList[targetIndex]!=postorderList[rootIndex]){
             targetIndex++;
         }
         //左右子树根节点在后序遍历中的位置
         //左子树的根节点位置 =rootIndex-右子树节点个数-1 (这里的1表示根节点占一个位置,这是在后序遍历中的计算,结果是左子树在 后序遍历中的位置)
         //右子树节点个数   = end-targetIndex  (这是在中序遍历中的计算,)

         //右子树根节点位置=rootIndex-1
         int rChildRootIndex=rootIndex-1;
         //左子树节点位置=右子树根节点-右子树节点个数
         int lChildRootIndex=rChildRootIndex-(end-targetIndex);



         BTreeNode lChild= restoreBTreePostInorder(postorderList,lChildRootIndex,inorderList,start,targetIndex-1);
         BTreeNode rChild= restoreBTreePostInorder(postorderList,rChildRootIndex,inorderList,targetIndex+1,end);
         BTreeNode rootNode=new BTreeNode(postorderList[rootIndex]);
         rootNode.setLChild(lChild);
         rootNode.setRChild(rChild);
         return rootNode;

     }
     /**
      * @author  wangwei
      * @date     2019/2/7 17:26
      * @methodDescription  已知 先序,中序 还原二叉树
      *  通过先序获取 根节点
      *   先序: A B D C E F
      *   中序: D B A E C F
      */
    public static BTreeNode restoreBTreePreInorder(Object []preorderList, int rootIndex, Object []inorderList, int start, int end) {
        if (null == preorderList || null == inorderList) {
            return null;
        }
        if (preorderList.length != inorderList.length) {
            throw new RuntimeException(" 先序遍历与中序遍历节点个数应该相等");
        }
        if(start>end){
            return null;
        }

        //定位 当前start-end 这棵树的根节点在中序遍历中的位置,然后对左右子树进行相似的递归处理
        int targetIndex=0;
        while (inorderList[targetIndex]!=preorderList[rootIndex]){
            targetIndex++;
        }
        //左右子树根节点在中序遍历中的位置
        //左子树的根节点位置 =rootIndex+1


        int lChildRootIndex=rootIndex+1;
        //右子树根节点位置=左子树根节点位置+左子树节点数
        // 左子树节点数=targetIndex-start
        int rChildRootIndex=lChildRootIndex+(targetIndex-start);


        BTreeNode lChild= restoreBTreePreInorder(preorderList,lChildRootIndex,inorderList,start,targetIndex-1);
        BTreeNode rChild= restoreBTreePreInorder(preorderList,rChildRootIndex,inorderList,targetIndex+1,end);
        BTreeNode rootNode=new BTreeNode(preorderList[rootIndex]);
        rootNode.setLChild(lChild);
        rootNode.setRChild(rChild);
        return rootNode;

    }


}
