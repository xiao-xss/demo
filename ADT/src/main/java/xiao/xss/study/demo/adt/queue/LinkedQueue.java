package xiao.xss.study.demo.adt.queue;

/**
 * 使用链式数据结构实现队列
 *
 * @author xiaoliang
 * @since 2019-06-17 10:49
 */
public class LinkedQueue<T> implements Queue<T> {
    private Node head;
    private Node tail;
    private int size;

    public LinkedQueue() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        Node node = new Node(item, null);
        if(isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public T remove() {
        checkEmpty();
        T item = head.data;
        head = head.next;
        size--;
        return item;
    }

    @Override
    public T peek() {
        checkEmpty();
        return head.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    private void checkEmpty() {
        if(isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
    }

    private class Node {
        private T data;
        private Node next;
        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
