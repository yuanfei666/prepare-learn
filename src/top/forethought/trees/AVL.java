package top.forethought.trees;


public class AVL  {

/**
 *   平衡二叉树的插入操作
 */
public static AVLNode insertNode(AVLNode avlTree, Integer newKey) {
    if (null ==  avlTree) {
        avlTree = new AVLNode(newKey);
        return  avlTree;
    }

    if (newKey < (int)  avlTree.getData()) {
        if (null ==  avlTree.getLChild()) {

            avlTree.setLChild(new AVLNode(newKey));
            return  avlTree;
        }
        return insertNode( avlTree.getLChild(), newKey);
    }
    if (newKey > (int)  avlTree.getData()) {
        if (null ==  avlTree.getRChild()) {
            avlTree.setRChild(new AVLNode(newKey));
            return  avlTree;
        }
        return insertNode( avlTree.getRChild(), newKey);
    }
    //开始处理子树节点的调整
    return null;
}



    /**
     * 2. 搜索节点
     *
     * @param tree
     * @param targetKey
     * @return 搜索节点
     */
    public static AVLNode searchNode( AVLNode tree, Integer targetKey) {

        if (tree == null || tree.getData() == null) {
            return null;
        }
        if (targetKey > (int) tree.getData()) {
            return searchNode(tree.getRChild(), targetKey);
        }
        if (targetKey < (int) tree.getData()) {

            return searchNode( tree.getLChild(), targetKey);
        }
       return tree;
    }
}
