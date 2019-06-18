package xiao.xss.study.demo.adt.list;

/**
 * 线性表
 *
 * @author xiaoliang
 * @since 2019-06-18 9:32
 */
public interface List<T> {
    /**
     * 将新项添加到线性表末尾
     * @param item 新项
     */
    public void add(T item);

    /**
     * 将新项添加到线性表指定的位置
     * @param index 指定索引
     * @param item 新项
     */
    public void add(int index, T item);

    /**
     * 从线性表删除指定位置的项
     * @param index 指定索引
     */
    public T remove(int index);

    /**
     * 用新项替换线性表中指定位置的项
     * @param index 指定索引
     * @param item 新项
     * @return 旧项
     */
    public T replace(int index, T item);

    /**
     * 获取线性表指定位置的项
     * @param index 指定索引
     * @return 项
     */
    public T get(int index);

    /**
     * 查看线性表是否包含给定的项
     * @param item 给定项
     * @return True：包含、False：不包含
     */
    public boolean contains(T item);

    /**
     * 返回线性表是否为空
     * @return True：空、False：非空
     */
    public boolean isEmpty();

    /**
     * 返回线性表大小
     * @return 线性表大小
     */
    public int size();

    /**
     * 清空线性表
     */
    public void clear();
}
