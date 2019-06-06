package xiao.xss.study.demo.adt.bag;

/**
 * 使用双端链表实现包
 *
 * @author xiaoliang
 * @since 2019-06-06 15:21
 */
public class DoublyLinkedBag<T> implements Bag<T> {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedBag() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean add(T entry) {
        if(size == 0) {
            head = tail = new Node(entry, null, null);
        } else {
            tail = new Node(entry, tail, null); // 新元素添加到尾部
            // head = new Node(entry, null, head); // 新元素添加到头部
        }
        size++;
        return true;
    }

    @Override
    public T remove() {
        return remove0(tail);
    }

    @Override
    public boolean remove(T entry) {
        return remove0(getRef(entry)) != null;
    }

    private T remove0(Node node) {
        if(isEmpty() || node == null) return null;
        T result = node.data;
        if(node.prev != null) node.prev.next = node.next;
        if(node.next != null) node.next.prev = node.prev;
        size--;
        return result;
    }

    @Override
    public void clear() {
        head = tail = null;
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
        T[] copy = (T[]) new Object[size];
        Node node = head;
        int i = 0;
        while(node != null) {
            copy[i++] = node.data;
            node = node.next;
        }
        return copy;
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
