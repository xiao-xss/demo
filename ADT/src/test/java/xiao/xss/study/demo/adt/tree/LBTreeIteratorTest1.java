package xiao.xss.study.demo.adt.tree;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xiao.xss.study.demo.adt.list.List;

import java.util.Iterator;

/**
 * 二叉树遍历
 *
 * @author xiaoliang
 * @since 2019-06-19 14:21
 */
@RunWith(JUnit4.class)
public class LBTreeIteratorTest1 {
    private static BinaryTree<String> tree;
    private static String prev;
    private static String mid;
    private static String back;
    private static String level;

    @BeforeClass
    public static void beforeClass() {
        LinkedBinaryTree<String> D = new LinkedBinaryTree<>("D");
        LinkedBinaryTree<String> E = new LinkedBinaryTree<>("E");
        LinkedBinaryTree<String> F = new LinkedBinaryTree<>("F");
        LinkedBinaryTree<String> C = new LinkedBinaryTree<>("C", D, E);
        LinkedBinaryTree<String> B = new LinkedBinaryTree<>("B", C, F);

        LinkedBinaryTree<String> J = new LinkedBinaryTree<>("J");
        LinkedBinaryTree<String> K = new LinkedBinaryTree<>("K");
        LinkedBinaryTree<String> H = new LinkedBinaryTree<>("H");
        LinkedBinaryTree<String> I = new LinkedBinaryTree<>("I", J, K);
        LinkedBinaryTree<String> G = new LinkedBinaryTree<>("G", H, I);

        LinkedBinaryTree<String> A = new LinkedBinaryTree<>("A", B, G);

        prev = "ABCDEFGHIJK";
        mid = "DCEBFAHGJIK";
        back = "DECFBHJKIGA";
        level = "ABGCFHIDEJK";

        tree = A;
    }
    @AfterClass
    public static void afterClass() {
        tree.clear();
    }

    @Test
    public void test_prevOrderList() {
        String expect = prev;
        List<String> list = tree.prevOrderList();
        String result = toString(list);
        assert expect.equals(result) : String.format("前序遍历失败：\n期望：%s\n实际：%s", expect, result);
    }
    @Test
    public void test_midOrderList() {
        String expect = mid;
        List<String> list = tree.midOrderList();
        String result = toString(list);
        assert expect.equals(result) : String.format("中序遍历失败：\n期望：%s\n实际：%s", expect, result);
    }
    @Test
    public void test_backOrderList() {
        String expect = back;
        List<String> list = tree.backOrderList();
        String result = toString(list);
        assert expect.equals(result) : String.format("后序遍历失败：\n期望：%s\n实际：%s", expect, result);
    }
    @Test
    public void test_levelOrderList() {
        String expect = level;
        List<String> list = tree.levelOrderList();
        String result = toString(list);
        assert expect.equals(result) : String.format("层序遍历失败：\n期望：%s\n实际：%s", expect, result);
    }
    @Test
    public void test_prevOrderIterator() {
        String expect = prev;
        Iterator<String> ite = tree.prevOrderIterator();
        StringBuilder sb = new StringBuilder();
        while(ite.hasNext()) {
            sb.append(ite.next());
        }
        assert expect.equals(sb.toString()) : String.format("前序遍历失败：\n期望：%s\n实际：%s", expect, sb.toString());
    }
    @Test
    public void test_midOrderIterator() {
        String expect = mid;
        Iterator<String> ite = tree.midOrderIterator();
        StringBuilder sb = new StringBuilder();
        while(ite.hasNext()) {
            sb.append(ite.next());
        }
        assert expect.equals(sb.toString()) : String.format("中序遍历失败：\n期望：%s\n实际：%s", expect, sb.toString());
    }
    @Test
    public void test_backOrderIterator() {
        String expect = back;
        Iterator<String> ite = tree.backOrderIterator();
        StringBuilder sb = new StringBuilder();
        while(ite.hasNext()) {
            sb.append(ite.next());
        }
        assert expect.equals(sb.toString()) : String.format("后序遍历失败：\n期望：%s\n实际：%s", expect, sb.toString());
    }
    @Test
    public void test_levelOrderIterator() {
        String expect = level;
        Iterator<String> ite = tree.levelOrderIterator();
        StringBuilder sb = new StringBuilder();
        while(ite.hasNext()) {
            sb.append(ite.next());
        }
        assert expect.equals(sb.toString()) : String.format("后序遍历失败：\n期望：%s\n实际：%s", expect, sb.toString());
    }

    @Test
    public void test_getRoot() {
        assert "A".equals(tree.getRoot()): "测试getRoot失败";
    }
    @Test
    public void test_getHeight() {
        assert tree.getHeight() == 4: "测试getHeight失败";
    }
    @Test
    public void test_countNodes() {
        assert tree.countNodes() == 11: "测试countNodes失败";
    }
    @Test
    public void test_isEmpty() {
        assert !tree.isEmpty(): "测试isEmpty失败";
    }
    @Test
    public void test_clear() {
        LinkedBinaryTree<String> D = new LinkedBinaryTree<>("D");
        LinkedBinaryTree<String> E = new LinkedBinaryTree<>("E");
        LinkedBinaryTree<String> C = new LinkedBinaryTree<>("C", D, E);
        assert !C.isEmpty(): "测试clear失败";
        C.clear();
        assert C.isEmpty(): "测试clear失败";
        assert C.getHeight() == 0: "测试clear失败";
    }

    private String toString(List list) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
