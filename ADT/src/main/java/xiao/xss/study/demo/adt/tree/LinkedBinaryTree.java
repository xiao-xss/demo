package xiao.xss.study.demo.adt.tree;

import xiao.xss.study.demo.adt.list.LinkedList;
import xiao.xss.study.demo.adt.list.List;
import xiao.xss.study.demo.adt.queue.LinkedQueue;
import xiao.xss.study.demo.adt.queue.Queue;
import xiao.xss.study.demo.adt.stack.LinkedStack;
import xiao.xss.study.demo.adt.stack.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author xiaoliang
 * @since 2019-06-19 11:00
 */
public class LinkedBinaryTree<T> implements BinaryTree<T> {
    private BinaryNode<T> rootNode;

    public LinkedBinaryTree() {
        rootNode = null;
    }
    public LinkedBinaryTree(T data) {
        rootNode = new BinaryNode<>(data);
    }
    public LinkedBinaryTree(T data, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right) {
        makeTree(data, left, right);
    }

    @Override
    public void setTree(T root) {
        rootNode = new BinaryNode<>(root);
    }

    @Override
    public void setTree(T root, BinaryTree<T> left, BinaryTree<T> right) {
        makeTree(root, (LinkedBinaryTree<T>) left, (LinkedBinaryTree<T>) right);
    }

    private void makeTree(T root, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right) {
        rootNode = new BinaryNode<>(root);
        if(left != null && !left.isEmpty()) {
            rootNode.setLeft(left.rootNode);
            left.clear();
        }
        if(right != null && !right.isEmpty()) {
            if(right != left) {
                rootNode.setRight(right.rootNode);
                right.clear();
            } else {
                rootNode.setRight(right.rootNode.copy());
            }
        }

        if(left != null && left != this) {
            left.clear();
        }
        if(right != null && right != this) {
            right.clear();
        }
    }

    @Override
    public T getRoot() {
        if(isEmpty()) {
            throw new EmptyTreeException("Tree is empty");
        }
        return rootNode.getData();
    }

    @Override
    public int getHeight() {
        if(isEmpty()) {
            return 0;
        } else {
            return rootNode.getHeight();
        }
    }

    @Override
    public int countNodes() {
        if(isEmpty()) {
            return 0;
        } else {
            return rootNode.countNodes();
        }
    }

    @Override
    public boolean isEmpty() {
        return rootNode == null;
    }

    @Override
    public void clear() {
        rootNode = null;
    }

    @Override
    public List<T> prevOrderList() {
        List<T> list = new LinkedList<>();
        prevOrderTraversal(rootNode, list);
        return list;
    }
    private void prevOrderTraversal(BinaryNode<T> node, List<T> list) {
        if(node != null) {
            list.add(node.getData());
            prevOrderTraversal(node.getLeft(), list);
            prevOrderTraversal(node.getRight(), list);
        }
    }

    @Override
    public List<T> midOrderList() {
        List<T> list = new LinkedList<>();
        midOrderTraversal(rootNode, list);
        return list;
    }
    private void midOrderTraversal(BinaryNode<T> node, List<T> list) {
        if(node != null) {
            midOrderTraversal(node.getLeft(), list);
            list.add(node.getData());
            midOrderTraversal(node.getRight(), list);
        }
    }

    @Override
    public List<T> backOrderList() {
        List<T> list = new LinkedList<>();
        backOrderTraversal(rootNode, list);
        return list;
    }
    private void backOrderTraversal(BinaryNode<T> node, List<T> list) {
        if(node != null) {
            backOrderTraversal(node.getLeft(), list);
            backOrderTraversal(node.getRight(), list);
            list.add(node.getData());
        }
    }

    @Override
    public List<T> levelOrderList() {
        List<T> list = new LinkedList<>();
        levelOrderTraversal(rootNode, list);
        return list;
    }
    private void levelOrderTraversal(BinaryNode<T> node, List<T> list) {
        if(node != null) {
            Queue<BinaryNode<T>> queue = new LinkedQueue<>();
            queue.add(node);
            BinaryNode<T> temp;
            while(!queue.isEmpty() && (temp = queue.remove()) != null) {
                list.add(temp.getData());
                if(temp.getLeft() != null) {
                    queue.add(temp.getLeft());
                }
                if(temp.getRight() != null) {
                    queue.add(temp.getRight());
                }
            }
        }
    }

    @Override
    public Iterator<T> prevOrderIterator() {
        return new PrevOrderIterator();
    }

    @Override
    public Iterator<T> midOrderIterator() {
        return new MidOrderIterator();
    }

    @Override
    public Iterator<T> backOrderIterator() {
        return new BackOrderIterator();
    }

    @Override
    public Iterator<T> levelOrderIterator() {
        return new LevelOrderIterator();
    }
    private class PrevOrderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> stack;
        private BinaryNode<T> current;

        PrevOrderIterator() {
            stack = new LinkedStack<>();
            current = rootNode;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        @Override
        public T next() {
            BinaryNode<T> next;
            if(current != null) {
                stack.push(current);
            }
            if(!stack.isEmpty()) {
                next = stack.pop();
                assert next != null;
                if(next.getRight() != null) {
                    stack.push(next.getRight());
                }
                current = next.getLeft();
            } else {
                throw new NoSuchElementException();
            }
            return next.getData();
        }
    }
    private class MidOrderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> stack;
        private BinaryNode<T> current;

        MidOrderIterator() {
            stack = new LinkedStack<>();
            current = rootNode;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        @Override
        public T next() {
            BinaryNode<T> next;
            while(current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            if(!stack.isEmpty()) {
                next = stack.pop();
                assert next != null;
                current = next.getRight();
            } else {
                throw new NoSuchElementException();
            }
            return next.getData();
        }
    }
    private class BackOrderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> stack;
        private BinaryNode<T> current;

        BackOrderIterator() {
            stack = new LinkedStack<>();
            current = rootNode;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        @Override
        public T next() {
            // TODO
            return null;
        }
    }
    private class LevelOrderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> stack;
        private BinaryNode<T> current;

        LevelOrderIterator() {
            stack = new LinkedStack<>();
            current = rootNode;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        @Override
        public T next() {
            // TODO
            return null;
        }
    }
}
