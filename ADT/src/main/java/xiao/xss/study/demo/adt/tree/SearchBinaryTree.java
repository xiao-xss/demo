package xiao.xss.study.demo.adt.tree;

import xiao.xss.study.demo.adt.list.List;

import java.util.Iterator;

/**
 * 二叉搜索树
 *
 * @author xiaoliang
 * @since 2019-06-24 9:29
 */
public class SearchBinaryTree<T extends Comparable<? super T>> extends LinkedBinaryTree<T> implements SearchTree<T> {

    public SearchBinaryTree() {
        super();
    }

    public SearchBinaryTree(T entry) {
        assert entry != null : "Cannot add null entry";
        setRootNode(new BinaryNode<>(entry));
    }

    @Override
    public T add(T entry) {
        assert entry != null : "Cannot add null entry";
        T result = entry;
        if(isEmpty()) {
            setRootNode(new BinaryNode<>(entry));
        } else {
            result = addEntry(getRootNode(), entry);
        }
        return result;
    }
    private T addEntry(BinaryNode<T> node, T entry) {
        assert node != null;
        assert entry != null;
        T result = entry;
        while(true) {
            int temp = entry.compareTo(node.getData());
            if(temp == 0) {
                result = node.getData();
                node.setData(entry);
                break;
            } else if(temp > 0) {
                if(node.hasRight()) {
                    node = node.getRight();
                } else {
                    node.setRight(new BinaryNode<>(entry));
                    break;
                }
            } else {
                if(node.hasLeft()) {
                    node = node.getLeft();
                } else {
                    node.setLeft(new BinaryNode<>(entry));
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public T get(T entry) {
        assert entry != null;
        T result = null;
        if(!isEmpty()) {
            result = getEntry(getRootNode(), entry);
        }
        return result;
    }

    @Override
    public boolean contains(T entry) {
        assert entry != null;
        boolean result = false;
        if(!isEmpty()) {
            result = getEntry(getRootNode(), entry) != null;
        }
        return result;
    }
    private T getEntry(BinaryNode<T> node, T entry) {
        assert node != null;
        T result = null;
        while(true) {
            int temp = entry.compareTo(node.getData());
            if(temp == 0) {
                result = node.getData();
                break;
            } else if(temp > 0) {
                if(node.hasRight()) {
                    node = node.getRight();
                } else {
                    break;
                }
            } else {
                if(node.hasLeft()) {
                    node = node.getLeft();
                } else {
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public T remove(T entry) {
        assert entry != null;
        T result = null;
        NodePair pair = findNode(entry);
        NodePair rm;
        if(pair.current != null) {
            result = pair.current.getData();
            if(pair.current.hasLeft() && pair.current.hasRight()) {
                rm = getNodeToRemove(pair.current);
                pair.current.setData(rm.current.getData());
            } else {
                rm = pair;
            }
            removeEntry(rm.parent, rm.current);
        }
        return result;
    }
    private NodePair findNode(T entry) {
        BinaryNode<T> parent = null;
        BinaryNode<T> current = getRootNode();
        int temp;
        while(current != null) {
            temp = entry.compareTo(current.getData());
            if(temp == 0) {
                break;
            } else if(temp > 0) {
                parent = current;
                current = current.getRight();
            } else {
                parent = current;
                current = current.getLeft();
            }
        }
        return new NodePair(parent, current);
    }
    private NodePair getNodeToRemove(BinaryNode<T> node) {
        BinaryNode<T> parent = node;
        BinaryNode<T> current = node.getLeft();
        while(current.hasRight()) {
            parent = current;
            current = current.getRight();
        }
        return new NodePair(parent, current);
    }
    private void removeEntry(BinaryNode<T> parent, BinaryNode<T> current) {
        BinaryNode<T> child;
        if(current.hasLeft()) {
            child = current.getLeft();
        } else {
            child = current.getRight();
        }
        if(current == getRootNode()) {
            setRootNode(child);
        } else if(parent.getLeft() == current) {
            parent.setLeft(child);
        } else if(parent.getRight() == current) {
            parent.setRight(child);
        }
    }

    /* setTree破坏搜索树的结构，需要禁用？ */
    @Override
    public void setTree(T root) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTree(T root, BinaryTree<T> left, BinaryTree<T> right) {
        throw new UnsupportedOperationException();
    }

    /* 以下方法是否需要禁用？ */
    @Override
    public List<T> prevOrderList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> backOrderList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> levelOrderList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> prevOrderIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> backOrderIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> levelOrderIterator() {
        throw new UnsupportedOperationException();
    }

    class NodePair {
        BinaryNode<T> parent;
        BinaryNode<T> current;

        NodePair(BinaryNode<T> parent, BinaryNode<T> current) {
            this.parent = parent;
            this.current = current;
        }
    }
}
