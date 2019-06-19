package xiao.xss.study.demo.adt.tree;

/**
 * 二叉节点类
 *
 * @author xiaoliang
 * @since 2019-06-19 10:53
 */
class BinaryNode<T> {
    private T data;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    BinaryNode(T data) {
        this(data, null, null);
    }
    BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    BinaryNode<T> getLeft() {
        return left;
    }

    void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    BinaryNode<T> getRight() {
        return right;
    }

    void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    boolean hasLeft() {
        return left != null;
    }

    boolean hasRight() {
        return right != null;
    }

    boolean isLeaf() {
        return left == null && right == null;
    }

    int countNodes() {
        int l = 0, r = 0;
        if(left != null) l = left.countNodes();
        if(right != null) r = right.countNodes();
        return 1 + r + l;
    }

    int getHeight() {
        return getHeight(this);
    }
    private int getHeight(BinaryNode<T> node) {
        int height = 0;
        if(node != null) {
            height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
        return height;
    }

    BinaryNode<T> copy() {
        BinaryNode<T> node = new BinaryNode<>(data);
        if(left != null) node.setLeft(left.copy());
        if(right != null) node.setRight(right.copy());
        return node;
    }
}
