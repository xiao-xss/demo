package xiao.xss.study.demo.adt.stack;

import java.util.EmptyStackException;

/**
 * 使用链表实现栈
 *
 * @author xiaoliang
 * @since 2019-06-10 15:28
 */
public class LinkedStack<T> implements Stack<T> {
    private Node top;
    private int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(T item) {
        top = new Node(item, top);
        size++;
    }

    @Override
    public T pop() {
        T item = peek();
        top = top.next;
        size--;
        return item;
    }

    @Override
    public T peek() {
        checkEmpty();
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        top = null;
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

    private class Node {
        private T data;
        private Node next;

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
