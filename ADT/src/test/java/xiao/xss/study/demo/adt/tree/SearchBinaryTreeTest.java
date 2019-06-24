package xiao.xss.study.demo.adt.tree;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xiao.xss.study.demo.adt.list.List;

/**
 * 二叉搜索树测试
 *
 * @author xiaoliang
 * @since 2019-06-24 11:04
 */
@RunWith(JUnit4.class)
public class SearchBinaryTreeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private SearchTree<Person> tree;
    @Before
    public void before() {
        tree = new SearchBinaryTree<>();
    }
    @After
    public void after() {
        tree = null;
    }

    @Test
    public void test_new_1() {
        assert tree.isEmpty() : "测试new失败";
        assert tree.countNodes() == 0 : "测试new失败";
        assert tree.getHeight() == 0 : "测试new失败";

        thrown.expect(EmptyTreeException.class);
        thrown.expectMessage("Tree is empty");
        tree.getRoot();
    }
    @Test
    public void test_new_2() {
        Person person = Person.create("A", 10, "aaa");
        tree = new SearchBinaryTree<>(person);
        assert !tree.isEmpty() : "测试new失败";
        assert tree.countNodes() == 1 : "测试new失败";
        assert tree.getHeight() == 1 : "测试new失败";
        assert tree.getRoot().equals(person) : "测试new失败";
    }
    @Test
    public void test_add_1() {
        tree.add(Person.create("F", 10, "fff"));
        tree.add(Person.create("M", 11, "mmm"));
        tree.add(Person.create("A", 11, "aaa"));
        tree.add(Person.create("A", 12, "aaa"));
        System.out.println(toString(tree.midOrderList()));
    }
    @Test
    public void test_add_2() {
        tree.add(Person.create("F", 10, "fff"));
        tree.add(Person.create("M", 11, "mmm"));
        tree.add(Person.create("A", 11, "aaa"));
        tree.add(Person.create("A", 12, "aaa"));
        System.out.println(toString(tree.midOrderList()));

        // replace A,11,aaa -> A,11,replace
        Person p = tree.add(Person.create("A", 11, "replace"));
        System.out.println(toString(tree.midOrderList()));
        assert p.toString().equals("[A    , 11,aaa       ]");
    }

    @Test
    public void test_get_1() {
        tree.add(Person.create("F", 10, "fff"));
        tree.add(Person.create("M", 11, "mmm"));
        tree.add(Person.create("A", 11, "aaa"));
        tree.add(Person.create("A", 12, "aaa"));
        System.out.println(toString(tree.midOrderList()));
        Person p = tree.get(Person.create("A", 12, null));
        assert p.toString().equals("[A    , 12,aaa       ]");
    }
    @Test
    public void test_get_2() {
        tree.add(Person.create("F", 10, "fff"));
        tree.add(Person.create("M", 11, "mmm"));
        tree.add(Person.create("A", 11, "aaa"));
        tree.add(Person.create("A", 12, "aaa"));
        System.out.println(toString(tree.midOrderList()));
        Person p = tree.get(Person.create("C", 12, null));
        assert p == null;
    }
    @Test
    public void test_contains_1() {
        tree.add(Person.create("F", 10, "fff"));
        tree.add(Person.create("M", 11, "mmm"));
        tree.add(Person.create("A", 11, "aaa"));
        tree.add(Person.create("A", 12, "aaa"));
        System.out.println(toString(tree.midOrderList()));
        boolean result = tree.contains(Person.create("A", 12, null));
        assert result;
    }
    @Test
    public void test_contains_2() {
        tree.add(Person.create("F", 10, "fff"));
        tree.add(Person.create("M", 11, "mmm"));
        tree.add(Person.create("A", 11, "aaa"));
        tree.add(Person.create("A", 12, "aaa"));
        System.out.println(toString(tree.midOrderList()));
        boolean result = tree.contains(Person.create("A", 13, null));
        assert !result;
    }
    @Test
    public void test_remove_1() {
        tree.add(Person.create("F", 0, ""));
        tree.add(Person.create("M", 0, ""));
        tree.add(Person.create("K", 0, ""));
        tree.add(Person.create("B", 0, ""));
        tree.add(Person.create("N", 0, ""));
        tree.add(Person.create("A", 0, ""));
        tree.add(Person.create("C", 0, ""));
        tree.add(Person.create("J", 0, ""));
        tree.add(Person.create("L", 0, ""));
        System.out.println(toString(tree.midOrderList()));

        tree.remove(Person.create("F", 0, null));
        Person p = tree.getRoot();
        assert "[C    ,  0,          ]".equals(p.toString());
    }
    @Test
    public void test_remove_2() {
        tree.add(Person.create("F", 0, ""));
        tree.add(Person.create("M", 0, ""));
        tree.add(Person.create("K", 0, ""));
        tree.add(Person.create("B", 0, ""));
        tree.add(Person.create("N", 0, ""));
        tree.add(Person.create("A", 0, ""));
        tree.add(Person.create("C", 0, ""));
        tree.add(Person.create("J", 0, ""));
        tree.add(Person.create("L", 0, ""));
        System.out.println(toString(tree.midOrderList()));

        Person rm = tree.remove(Person.create("M", 0, null));
        System.out.println(toString(tree.midOrderList()));
        assert rm != null;
        Person p = tree.getRoot();
        assert "[F    ,  0,          ]".equals(p.toString());
    }

    private String toString(List list) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            sb.append("\r\n");
        }
        return sb.toString();
    }
    static class Person implements Comparable<Person> {
        String name;
        int age;
        String addr;
        Person(String name, int age, String addr) {
            this.name = name;
            this.age = age;
            this.addr = addr;
        }

        static Person create(String name, int age, String addr) {
            return new Person(name, age, addr);
        }

        @Override
        public int compareTo(Person o) {
            if(o == null) {
                return 1;
            }
            if(name == null && o.name == null) {
                return age - o.age;
            } else if(name == null) {
                return -1;
            } else if(o.name == null) {
                return 1;
            } else {
                int temp = name.compareTo(o.name);
                if(temp == 0) {
                    return age - o.age;
                } else {
                    return temp;
                }
            }
        }
        @Override
        public String toString() {
            return String.format("[%-5s,%3d,%-10s]", name, age, addr);
        }
    }
}
