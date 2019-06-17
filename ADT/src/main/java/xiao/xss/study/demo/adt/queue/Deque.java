package xiao.xss.study.demo.adt.queue;

/**
 * 双端队列
 *
 * @author xiaoliang
 * @since 2019-06-17 10:27
 */
public interface Deque<T> {
    /**
     * 将新项添加到队列前端
     * @param item 新项
     */
    public void addHead(T item);
    /**
     * 将新项添加到队列尾端
     * @param item 新项
     */
    public void addTail(T item);

    /**
     * 删除并返回队列的前端项
     * @return 队列前端项
     */
    public T removeHead();
    /**
     * 删除并返回队列的尾端项
     * @return 队列尾端项
     */
    public T removeTail();

    /**
     * 获取队列的前端
     * @return 队列前端项
     */
    public T peekHead();
    /**
     * 获取队列的尾端
     * @return 队列尾端项
     */
    public T peekTail();

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
