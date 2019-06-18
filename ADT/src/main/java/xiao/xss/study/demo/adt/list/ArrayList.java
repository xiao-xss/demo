package xiao.xss.study.demo.adt.list;

/**
 * 使用数组实现线性表
 *
 * @author xiaoliang
 * @since 2019-06-18 9:44
 */
public class ArrayList<T> implements List<T> {
    private final int initCapacity;
    private int capacity;
    private T[] items;
    private int size;

    public ArrayList(int capacity) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[capacity];
        this.items = temp;
        this.initCapacity = capacity;
        this.capacity = capacity;
        this.size = 0;
    }

    @Override
    public void add(T item) {
        ensureCapacity();
        items[size++] = item;
    }

    @Override
    public void add(int index, T item) {
        checkAddIndex(index);
        ensureCapacity();
        for(int k = size - 1; k >= index; k--)
            items[k + 1] = items[k];
        items[index] = item;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T old = items[index];
        for(int k = index + 1; k < size; k++)
            items[k - 1] = items[k];
        items[--size] = null;
        ensureCapacity();
        return old;
    }

    @Override
    public T replace(int index, T item) {
        checkIndex(index);
        T old = items[index];
        items[index] = item;
        return old;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public boolean contains(T item) {
        if(item == null) {
            for(int i = 0; i < size; i++)
                if(items[i] == null)
                    return true;
        } else {
            for(int i = 0; i < size; i++)
                if(items[i].equals(item))
                    return true;
        }
        return false;
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
        for(int k = 0; k < size - 1; k++)
            items[k] = null;
        size = 0;
    }

    private void checkAddIndex(int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    private void checkIndex(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
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
        for(int k = 0; k < size; k++) temp[k] = items[k];
        capacity = newCapacity;
        items = temp;
    }
}
