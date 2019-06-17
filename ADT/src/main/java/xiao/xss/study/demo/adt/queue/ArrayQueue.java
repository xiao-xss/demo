package xiao.xss.study.demo.adt.queue;

/**
 * 使用数组实现队列
 *
 * @author xiaoliang
 * @since 2019-06-17 11:13
 */
public class ArrayQueue<T> implements Queue<T> {
    private T[] items;
    private int head;
    private int tail;
    private int size;
    private final int initCapacity;
    private int capacity;

    public ArrayQueue(int capacity) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[capacity];
        this.items = temp;
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.capacity = capacity;
        this.initCapacity = capacity;
    }

    @Override
    public void add(T item) {
        if(size == 0) {
            items[0] = item;
            head = tail = 0;
        } else {
            ensureCapacity();
            tail = (tail + 1) % capacity;
            items[tail] = item;
        }
        size++;
    }

    @Override
    public T remove() {
        checkEmpty();
        T item = items[head];
        items[head] = null;
        head = (head + 1) % capacity;
        size--;
        ensureCapacity();
        return item;
    }

    @Override
    public T peek() {
        checkEmpty();
        return items[head];
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
        for(int i = 0; i < size; i++) {
            items[(head + i) % capacity] = null;
        }
        head = tail = size = 0;
    }

    private void checkEmpty() {
        if(isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
    }

    private void ensureCapacity() {
        int newCapacity;
        if(size == capacity) { // 扩容
            newCapacity = size * 2;
        } else if(size < capacity / 3 && capacity > initCapacity) { // 缩容
            newCapacity = capacity / 2;
            newCapacity = newCapacity < initCapacity ? initCapacity : newCapacity;
        } else {
            return;
        }
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[newCapacity];
        int idx = 0;
        for(int k = 0; k < size; k++) temp[idx++] = items[(k + head) % capacity];
        capacity = newCapacity;
        items = temp;
        head = 0;
        tail = size - 1;
    }
}
