package xiao.xss.study.demo.adt.queue;

/**
 * 使用链式数据结构实现双端队列
 *
 * @author xiaoliang
 * @since 2019-06-17 14:52
 */
public class LinkedDeque<T> implements Deque<T> {
    private Node head;
    private Node tail;
    private int size;

    @Override
    public void addHead(T item) {
        if(isEmpty()) {
            head = tail = new Node(item, null, null);
        } else {
            head = new Node(item, null, head);
        }
        size++;
    }

    @Override
    public void addTail(T item) {
        if(isEmpty()) {
            head = tail = new Node(item, null, null);
        } else {
            tail = new Node(item, tail, null);
        }
        size++;
    }

    @Override
    public T removeHead() {
        checkEmpty();
        T item = head.data;
        if(size() == 1) {
            head = tail = null;
        } else {
            head = head.next;
        }
        size--;
        return item;
    }

    @Override
    public T removeTail() {
        checkEmpty();
        T item = tail.data;
        if(size() == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
        }
        size--;
        return item;
    }

    @Override
    public T peekHead() {
        checkEmpty();
        return head.data;
    }

    @Override
    public T peekTail() {
        checkEmpty();
        return tail.data;
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
        private Node prev;
        private Node next;

        private Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
            if(this.prev != null) this.prev.next = this;
            if(this.next != null) this.next.prev = this;
        }
    }
}
