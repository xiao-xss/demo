package xiao.xss.study.demo.adt.list;

/**
 * 使用链表实现线性表
 *
 * @author xiaoliang
 * @since 2019-06-18 10:58
 */
public class LinkedList<T> implements List<T> {
    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        if(head == null) {
            head = new Node(item, null);
        } else {
            Node parent = nodeOf(size - 1);
            parent.next = new Node(item, null);
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        checkAddIndex(index);
        if(index == 0) {
            head = new Node(item, head);
        } else {
            Node parent = nodeOf(index - 1);
            parent.next = new Node(item, parent.next);
        }
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T item;
        if(index == 0) {
            item = head.data;
            head = head.next;
        } else {
            Node parent = nodeOf(index - 1);
            item = parent.next.data;
            parent.next = parent.next.next;
        }
        size--;
        return item;
    }

    @Override
    public T replace(int index, T item) {
        checkIndex(index);
        Node node = nodeOf(index);
        T old = node.data;
        node.data = item;
        return old;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        Node node = nodeOf(index);
        return node.data;
    }

    @Override
    public boolean contains(T item) {
        Node node = head;
        int k = 0;
        if(item == null) {
            while(k++ < size) {
                if(node.data == null)
                    return true;
                node = node.next;
            }
        } else {
            while(k++ < size) {
                if(node.data.equals(item))
                    return true;
                node = node.next;
            }
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
        head = null;
        size = 0;
    }

    private Node nodeOf(int index) {
        Node node = head;
        int k = 0;
        while(k++ < index) {
            node = node.next;
        }
        return node;
    }
    private void checkAddIndex(int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    private void checkIndex(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    private class Node {
        T data;
        Node next;
        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
