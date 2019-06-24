package xiao.xss.study.demo.adt.tree;

import xiao.xss.study.demo.adt.list.List;

import java.util.Iterator;

/**
 * 查找树
 *
 * @author xiaoliang
 * @since 2019-06-21 16:47
 */
public interface SearchTree<T extends Comparable<? super T>> extends Tree<T> {
    /**
     * 向查找树中添加一项并返回对应项，如果项已存在（compareTo判断）则新项替换旧项
     * @param entry 新项
     * @return 旧项
     */
    public T add(T entry);

    /**
     * 根据条件项获取查找树中的项
     * @param entry 条件项
     * @return 查找树中的项,如果未找到返回null
     */
    public T get(T entry);

    /**
     * 根据条件项删除查找树中的项
     * @param entry 条件项
     * @return 查找树中的项,如果未找到返回null
     */
    public T remove(T entry);

    /**
     * 根据条件项查看查找树中是否存在该项
     * @param entry 条件项
     * @return True：存在，False：不存在
     */
    public boolean contains(T entry);

    /**
     * 中序遍历树
     * @return 结果
     */
    public List<T> midOrderList();

    /**
     * 中序遍历器
     * @return 遍历器
     */
    public Iterator<T> midOrderIterator();
}
