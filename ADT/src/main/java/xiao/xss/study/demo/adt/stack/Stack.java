package xiao.xss.study.demo.adt.stack;

/**
 * 栈
 *
 * @author xiaoliang
 * @since 2019-06-10 14:00
 */
public interface Stack<T> {
    /**
     * 添加新项到栈顶
     * @param item 项
     */
    public void push(T item);

    /**
     * 删除并返回栈顶项
     * @return 栈顶项
     */
    public T pop();

    /**
     * 返回栈顶项
     * @return 栈顶项
     */
    public T peek();

    /**
     * 判断栈是否为空
     * @return True：栈空，False：栈非空
     */
    public boolean isEmpty();

    /**
     * 清空栈
     */
    public void clear();

    /**
     * 栈大小
     * @return 栈大小
     */
    public int size();
}
