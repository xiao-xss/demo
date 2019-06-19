package xiao.xss.study.demo.adt.tree;

import java.util.Iterator;

/**
 * 树遍历器
 *
 * @author xiaoliang
 * @since 2019-06-19 9:46
 */
public interface TreeIterator<T> {
    /**
     * 获取树的前序遍历器
     * @return 前序遍历器
     */
    public Iterator<T> prevOrderIterator();
    /**
     * 获取树的中序遍历器
     * @return 中序遍历器
     */
    public Iterator<T> midOrderIterator();
    /**
     * 获取树的后序遍历器
     * @return 后序遍历器
     */
    public Iterator<T> backOrderIterator();
    /**
     * 获取树的层序遍历器
     * @return 层序遍历器
     */
    public Iterator<T> levelOrderIterator();
}
