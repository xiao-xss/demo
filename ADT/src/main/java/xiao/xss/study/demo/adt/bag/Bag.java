package xiao.xss.study.demo.adt.bag;

/**
 * 抽象数据结构：包
 *
 * @author xiaoliang
 * @since 2019-06-06 10:23
 */
public interface Bag<T> {
    /**
     * 将给定的对象添加到包中
     * @param entry 给定对象
     * @return True：成功，False：失败
     */
    public boolean add(T entry);

    /**
     * 从包中删除最后一个对象
     * @return 删除的对象
     */
    public T remove();

    /**
     * 从包中删除第一次出现出现的给定的对象
     * @param entry 给定对象
     * @return True：成功，False：失败
     */
    public boolean remove(T entry);

    /**
     * 清空包中的对象
     */
    public void clear();

    /**
     * 查看包是否为空
     * @return True：包为空，False：包不为空
     */
    public boolean isEmpty();

    /**
     * 查询包中是否包含给定对象
     * @param entry 给定对象
     * @return True：包含，False：不包含
     */
    public boolean contains(T entry);

    /**
     * 查询给定对象在包中出现的次数
     * @param entry 给定对象
     * @return 出现次数
     */
    public int getFrequencyOf(T entry);

    /**
     * 查看包中已有对象数
     * @return 已有对象数
     */
    public int getSize();

    /**
     * 将包中对象转换为数组
     * @return 转换后对象
     */
    public T[] toArray();
}
