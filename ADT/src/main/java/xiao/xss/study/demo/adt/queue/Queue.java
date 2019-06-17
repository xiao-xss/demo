package xiao.xss.study.demo.adt.queue;

/**
 * 队列
 *
 * @author xiaoliang
 * @since 2019-06-14 17:44
 */
public interface Queue<T> {
    /**
     * 将新项添加到队列末尾
     * @param item 新项
     */
    public void add(T item);

    /**
     * 删除并返回队列的前端项
     * @return 队列前端项
     */
    public T remove();

    /**
     * 获取队列的前端
     * @return 队列前端项
     */
    public T peek();

    /**
     * 检查队列是否为空
     * @return True：空，False：非空
     */
    public boolean isEmpty();

    /**
     * 获取队列的大小
     * @return 队列大小
     */
    public int size();

    /**
     * 清空队列
     */
    public void clear();
}
