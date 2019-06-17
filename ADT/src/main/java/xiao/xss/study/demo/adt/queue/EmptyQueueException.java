package xiao.xss.study.demo.adt.queue;

/**
 * 空队列异常
 *
 * @author xiaoliang
 * @since 2019-06-17 10:55
 */
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String msg) {
        super(msg);
    }
}
