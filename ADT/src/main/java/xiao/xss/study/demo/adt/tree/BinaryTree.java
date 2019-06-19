package xiao.xss.study.demo.adt.tree;

/**
 * 二叉树
 *
 * @author xiaoliang
 * @since 2019-06-19 9:54
 */
public interface BinaryTree<T> extends Tree<T>, TreeIterator<T> {
    /**
     * 创建单节点二叉树树
     * @param root 根节点
     */
    public void setTree(T root);

    /**
     * 根据子树创建二叉树
     * @param root 根节点
     * @param left 左子树
     * @param right 右子树
     */
    public void setTree(T root, BinaryTree<T> left, BinaryTree<T> right);
}
