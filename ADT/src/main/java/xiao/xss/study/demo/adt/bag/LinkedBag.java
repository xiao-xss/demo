package xiao.xss.study.demo.adt.bag;

/**
 * 使用链表实现包
 *
 * @author xiaoliang
 * @since 2019-06-06 15:20
 */
public class LinkedBag<T> implements Bag<T> {
    private Node head;
    private int size;

    public LinkedBag() {
        head = null;
        size = 0;
    }

    @Override
    public boolean add(T entry) {
        head = new Node(entry, head);
        size++;
        return true;
    }

    @Override
    public T remove() {
        return remove0(head);
    }

    @Override
    public boolean remove(T entry) {
        return remove0(getRef(entry)) != null;
    }

    private T remove0(Node node) {
        if(isEmpty() || node == null) return null;
        T result = node.data;
        node.data = head.data;
        head = head.next;
        size--;
        return result;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T entry) {
        return getRef(entry) != null;
    }

    @Override
    public int getFrequencyOf(T entry) {
        if(isEmpty()) {
            return 0;
        } else {
            int count = 0;
            Node node = head;
            while(node != null) {
                if(entry.equals(node.data)) {
                    count++;
                }
                node = node.next;
            }
            return count;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings(value = "unchecked")
        T[] result = (T[]) new Object[size];
        Node node = head;
        int index = 0;
        while(node != null) {
            result[index++] = node.data;
            node = node.next;
        }
        return result;
    }

    private Node getRef(T entry) {
        Node result = null;
        if(!isEmpty()) {
            Node node = head;
            while(node != null) {
                if(entry.equals(node.data)) {
                    result = node;
                    break;
                }
                node = node.next;
            }
        }
        return result;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
