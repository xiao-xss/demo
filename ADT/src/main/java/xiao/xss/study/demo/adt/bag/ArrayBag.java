package xiao.xss.study.demo.adt.bag;

import java.util.Arrays;

/**
 * 使用数组实现包
 *
 * @author xiaoliang
 * @since 2019-06-06 10:39
 */
public class ArrayBag<T> implements Bag<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private final T[] bag;
    private int size = 0;

    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayBag(int capacity) {
        // safe cast
        @SuppressWarnings(value = "unchecked")
        T[] tmp = (T[]) new Object[capacity];
        bag = tmp;
    }

    @Override
    public boolean add(T entry) {
        if(isFull()) {
            return false;
        } else {
            bag[size++] = entry;
            return true;
        }
    }

    @Override
    public T remove() {
        return remove0(size - 1);
    }

    @Override
    public boolean remove(T entry) {
        return remove0(getIndex(entry)) != null;
    }

    private T remove0(int index) {
        if(index == -1 || isEmpty()) return null;
        T entry = bag[index];
        bag[index] = bag[size - 1];
        bag[size - 1] = null;
        size--;
        return entry;
    }

    @Override
    public void clear() {
        while(remove() != null);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T entry) {
        return getIndex(entry) > -1;
    }

    private int getIndex(T entry) {
        int index = -1;
        if(!isEmpty()) {
            for(int i = 0; i < size; i++) {
                if(entry.equals(bag[i])) {
                    return i;
                }
            }
        }
        return index;
    }

    @Override
    public int getFrequencyOf(T entry) {
        int count = 0;
        if(!isEmpty()) {
            for(int i = 0; i < size; i++) {
                if(entry.equals(bag[i])) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(bag, size);
    }

    private boolean isFull() {
        return size == bag.length;
    }
}
