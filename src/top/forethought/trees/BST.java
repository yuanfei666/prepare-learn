package top.forethought.trees;


/**
 * @author wangwei
 * @date 2019/2/15 16:18
 * @classDescription 二叉排序树
 * 定义:满足左<根<右关系的二叉树
 */
public class BST {
    /**
     * 1.插入节点
     */
    public static BTreeNode insertNode(BTreeNode tree, Integer newKey) {
        if (null == tree) {
            return new BTreeNode(newKey);
        }
        //如果节点已经存在,则返回,不再插入
        if (null != searchNode(tree, newKey)) {
            System.out.println("---- found ---" + newKey);
            return tree;
        }
        BTreeNode currNode = tree;
        BTreeNode preNode = null;
        BTreeNode newNode = new BTreeNode(newKey);
        while (currNode != null) {
            preNode = currNode;
            //newKey 较小,往左子树查找
            if (newKey < (int) currNode.getData()) {
                currNode = currNode.getLChild();
                //newKey较大,往右子树查找
            } else {
                currNode = currNode.getRChild();
            }
        }
        //处理节点插入:新节点应该是插入到preNode 的孩子位置,退出循环时是 currNode=null
        if (newKey < (int) preNode.getData()) {
            preNode.setLChild(newNode);
            return tree;
        }
        preNode.setRChild(newNode);
        return tree;

    }

    /**
     * 2. 搜索节点
     *
     * @param tree
     * @param targetKey
     * @return 搜索节点
     */
    public static BTreeNode searchNode(BTreeNode tree, Integer targetKey) {

        if (tree == null || tree.getData() == null) {
            return null;
        }
        if (targetKey > (int) tree.getData()) {
            return searchNode(tree.getRChild(), targetKey);
        }
        if (targetKey < (int) tree.getData()) {
            return searchNode(tree.getLChild(), targetKey);
        }
        if (targetKey == (int) tree.getData()) {
            return tree;
        }
        return null;
    }

    /**
     * 3.删除节点
     *
     * @param tree
     * @param targetKey
     * @return
     */
//    public static BTreeNode deleteNode(BTreeNode tree, Integer targetKey) {
//        BTreeNode targetNode = searchNode(tree, targetKey);
//        if (null == targetNode) {
//            throw new RuntimeException(" 被删除节点不存在");
//        }
//        /**
//         *  分情况讨论:1,被删除节点是叶子节点(直接删除)
//         *             2,被删除节点是根节点,并且有左右孩子  找到后继,复制后继数据到被删除位置,然后删除后继所在节点
//         *             2.1   只有左孩子,父节点左(右)指向被删除节点的左孩子
//         *             2.2  只有右孩子,父节点左(右)指向被删除节点的右孩子
//         *
//         */

    //删除叶子节点,需要找到他的父节点

    /**
     * @param tree
     * @param targetKey
     * @return 删除节点, 分为2种情况
     * 1, 待删除节点是根节点(继续细分是否有左右孩子的请情况)
     * 2, 不是跟根节点
     * 2.1, 待删除节点只有一个孩子,将父节点的对应孩子指针指向被删除节点的孩子
     * 2.2,待删除节点有俩孩子(需要找到被删除节点的后继,将后继的值复制到被删除位置,然后删除后继节点
     */
    public static BTreeNode deleteNode(BTreeNode tree, Integer targetKey) {

        if (null == tree) {
            throw new RuntimeException("不能在空树中删除节点");
        }
        BTreeNode targetNode = searchNode(tree, targetKey);
        if (null == targetNode) {
            throw new RuntimeException("删除节点不存在");
        }
        BTreeNode parent = null;
//        BTreeNode child = tree;

        //寻找被删除节点以及其对应的父节点
//        while (child != null && (int) child.getData() != targetKey) {
//            parent = child;
//            if ((int) child.getData() < targetKey) {
//                child = child.getRChild();
//            } else if ((int) child.getData() > targetKey) {
//                child = child.getLChild();
//            } else {
//
//                break;//找到了对应的孩子节点
//            }
//        }
        parent=findParentNode(tree,targetKey);
        //如果父节点为null,这个是需要考虑的,表示待删除节点是根节点,那么删除之后后继将作为新的根节点
        //目前为止找到了待删除节点的父节点
        //需要注意的是 是左孩子还是右孩子
        //1.被删除节点是根节点
        if (null == parent) {

            //1.1 根节点没有右孩子,删除根之后,返回左子树
            if (null == targetNode.getRChild()) {
                return targetNode.getLChild();
            }
            // 1.2 根节点没有左孩子,返回右子树
            if (null == targetNode.getLChild()) {
                return targetNode.getRChild();
            }
            //1.3 左右孩子都有,需要查找根节点的后继,将后继的值复制到根节点,同时删除后继节点
            BTreeNode rChild = targetNode.getRChild();
//            BTreeNode mostLChild = rChild;
//            BTreeNode mostLChildParent = null;
//            while (mostLChild.getLChild() != null) {
//                mostLChildParent = mostLChild;
//                mostLChild = mostLChild.getLChild();
//            }
            BTreeNode mostLChild =findSuccessorNode(targetNode);
            BTreeNode mostLChildParent = findParentNode(mostLChild,(int)mostLChild.getData());
            targetNode.setData(mostLChild.getData());
            if (mostLChildParent != null) {
                mostLChildParent.setLChild(null);
            } else {
                //处理根节点的右孩子没有左子树
                targetNode.setRChild(rChild.getRChild());
            }
            return targetNode;
        }


        BTreeNode lChild = parent.getLChild();
        BTreeNode rChild = parent.getRChild();
        //2,处理被删除节点只有一个孩子的情况
        //左孩子节点是被删除节点
        if (lChild != null && lChild.getData().equals(targetKey)) {
            BTreeNode lLChild = lChild.getLChild();
            BTreeNode lRChild = lChild.getRChild();
            //被删除节点无孩子
            //2.1 被删除节点无孩子
            boolean noChild = (null == lRChild && null == lLChild);
            if (noChild) {
                parent.setLChild(null);
                return tree;
            }
            //2.2被删除节点只有一个孩子
            boolean onlyOneChild = null == lRChild ^ null == lLChild;
            if (onlyOneChild) {
                parent.setLChild(null == lRChild ? lLChild : lRChild);
                return tree;
            }
            // 2.3 被删除节点有两个孩子
            //被删除节点有两个孩子  根据排序二叉树的特性  中序遍历是升序排列,那么删除节点后仍然需要满足升序排列
            //也就是需要找被删除节点的后继作为被删除节点的前驱的后继   ,使用这样找到的节点去替换被删除节点的值,然后删除找到的那个节点
            // 被删除节点的后继应该是 该节点的右孩子的最左孩子
//            BTreeNode mostLChild = lRChild;
//            BTreeNode mostLChildParent = null;//最左孩子的父节点(被删除节点的后继的父节点)
//            while (mostLChild.getLChild() != null) {
//                mostLChildParent = mostLChild;
//                mostLChild = mostLChild.getLChild();
//            }
            BTreeNode mostLChild = findSuccessorNode(lChild);// 查找被删除节点的后继
            BTreeNode mostLChildParent = findParentNode(tree,(int)mostLChild.getData());//最左孩子的父节点(被删除节点的后继的父节点)

            //将 后继(最左孩子mostLChild)的值拷贝到 被删除节点位置
            //同时删除最左孩子
            lChild.setData(mostLChild.getData());
            if (mostLChildParent != null) {
                mostLChildParent.setLChild(null);
            } else {
                //这是处理 被删除节点右孩子没有最左子节点的情况,后继就是被删除节点的右孩子
                lChild.setRChild(lRChild.getRChild());
            }
            return tree;
        }
        //3,待删除节点是右孩子,需要找到被删除节点的右孩子的最左孩子
        if (rChild != null && rChild.getData().equals(targetKey)) {

            BTreeNode rLChild = rChild.getLChild();
            BTreeNode rRChild = rChild.getRChild();
            //3.1 被删除节点无孩子
            boolean noChild = (null == rLChild && null == rRChild);
            if (noChild) {
                parent.setRChild(null);
                return tree;
            }
            //3.2 被删除节点 只有一个孩子
            boolean onlyOneChild = null == rLChild ^ null == rRChild;
            if (onlyOneChild) {
                parent.setRChild(null == rLChild ? rRChild : rLChild);
                return tree;
            }
            // 处理被删除节点带两个孩子节点的情况
            // 3.3  被删除节点有两个孩子
            //被删除节点有两个孩子  根据排序二叉树的特性  中序遍历是升序排列,那么删除节点后仍然需要满足升序排列
            //也就是需要找被删除节点的后继作为被删除节点的前驱的后继   ,使用这样找到的节点去替换被删除节点的值,然后删除找到的那个节点
            // 被删除节点的后继应该是 该节点的右孩子的最左孩子
//            BTreeNode mostLChild = rRChild;
//            BTreeNode mostLChildParent = null;//后继节点的父节点
//            while (mostLChild.getLChild() != null) {
////                mostLChildParent = mostLChild;
////                mostLChild = mostLChild.getLChild();
////            }
            BTreeNode mostLChild = findSuccessorNode(rChild);
            BTreeNode mostLChildParent = findParentNode(tree,(int)mostLChild.getData());//后继节点的父节点
            //将 后继(最左孩子mostLChild)的值拷贝到 被删除节点位置
            //同时删除最左孩子
            rChild.setData(mostLChild.getData());
            if (mostLChildParent != null) {
                mostLChildParent.setLChild(null);
            } else {
                //这是处理 被删除节点的右孩子没有左孩子的情况,那么被删除节点的后继就是被删除节点的右孩子
                //由于将后继的值拷贝到被删除节点位置了,那么就需要将被删节点的右孩子指向
                // 原本被删节点的右孩子的右孩子
                rChild.setRChild(rRChild.getRChild());
            }
            return tree;
        }
        return tree;
    }

    /**
     * 查找key节点的父节点
     *
     * @param root
     * @param key
     * @return
     */
    public static BTreeNode findParentNode(BTreeNode root, Integer key) {
        if (null == root) {
            return null;
        }
        if(null==searchNode(root,key)){
            throw new RuntimeException("节点不存在");
        }
        BTreeNode parent = null;
        BTreeNode currNode = root;
        while (currNode != null && !currNode.getData().equals(key)) {
            parent = currNode;
            currNode = key > (int) currNode.getData() ? currNode.getRChild() : currNode.getLChild();
        }
       return parent;
    }

    /**
     *  查找后继节点(中序遍历中的后继节点:即右孩子的最左孩子)
     * @param startPoint
     * @return
     */
    public static BTreeNode findSuccessorNode(BTreeNode startPoint){
        if(null==startPoint){
            return null;
        }
        BTreeNode successorNode=startPoint.getRChild();
        while (successorNode!=null && successorNode.getLChild()!=null ){
            successorNode=successorNode.getLChild();
        }
        return successorNode;
    }
}




