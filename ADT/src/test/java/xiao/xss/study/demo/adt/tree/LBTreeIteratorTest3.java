package xiao.xss.study.demo.adt.tree;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xiao.xss.study.demo.adt.list.List;

import java.util.Iterator;

/**
 *
 * @author xiaoliang
 * @since 2019-06-21 10:30
 */
@RunWith(JUnit4.class)
public class LBTreeIteratorTest3 {
    private static BinaryTree<String> tree;
    private static String prev;
    private static String mid;
    private static String back;
    private static String level;

    @BeforeClass
    public static void beforeClass() {
        LinkedBinaryTree<String> D = new LinkedBinaryTree<>("D");
        LinkedBinaryTree<String> C = new LinkedBinaryTree<>("C", null, D);
        LinkedBinaryTree<String> B = new LinkedBinaryTree<>("B", C, null);

        LinkedBinaryTree<String> H = new LinkedBinaryTree<>("H");
        LinkedBinaryTree<String> F = new LinkedBinaryTree<>("F", null, H);

        LinkedBinaryTree<String> K = new LinkedBinaryTree<>("K");
        LinkedBinaryTree<String> I = new LinkedBinaryTree<>("I", null, K);
        LinkedBinaryTree<String> J = new LinkedBinaryTree<>("J");
        LinkedBinaryTree<String> G = new LinkedBinaryTree<>("G", I, J);

        LinkedBinaryTree<String> E = new LinkedBinaryTree<>("E", F, G);

        LinkedBinaryTree<String> A = new LinkedBinaryTree<>("A", B, E);

        prev = "ABCDEFHGIKJ";
        mid = "CDBAFHEIKGJ";
        back = "DCBHFKIJGEA";
        level = "ABECFGDHIJK";

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

    private String toString(List list) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
