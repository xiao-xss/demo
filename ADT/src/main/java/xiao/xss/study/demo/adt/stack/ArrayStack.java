package xiao.xss.study.demo.adt.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 使用数组实现栈
 *
 * @author xiaoliang
 * @since 2019-06-10 16:38
 */
public class ArrayStack<T> implements Stack<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private T[] items;
    private int size = 0;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayStack(int capacity) {
        int initCapacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[initCapacity];
        items = temp;
    }
    @Override
    public void push(T item) {
        ensureCapacity();
        items[size++] = item;
    }

    @Override
    public T pop() {
        T item = peek();
        size--;
        return item;
    }

    @Override
    public T peek() {
        checkEmpty();
        return items[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkEmpty() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
    }
    private void ensureCapacity() {
        // TODO 容量伸缩
    }
}
