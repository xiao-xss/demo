package xiao.xss.study.demo.adt.tree;

/**
 * 树的基本操作
 *
 * @author xiaoliang
 * @since 2019-06-19 9:43
 */
public interface Tree<T> {
    /**
     * 获取树根
     * @return 树根
     */
    public T getRoot();

    /**
     * 获取树的高度
     * @return 树高
     */
    public int getHeight();

    /**
     * 获取树的结点数
     * @return 树的结点数
     */
    public int countNodes();

    /**
     * 判断树是否为空
     * @return True：空、False：非空
     */
    public boolean isEmpty();

    /**
     * 清空树
     */
    public void clear();
}
