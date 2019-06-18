package xiao.xss.study.demo.adt.test.list;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xiao.xss.study.demo.adt.list.ArrayList;
import xiao.xss.study.demo.adt.list.List;

/**
 * 线性表测试
 *
 * @author xiaoliang
 * @since 2019-06-18 10:29
 */
@RunWith(JUnit4.class)
public class ArrayListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private List<Integer> list;

    @Before
    public void before() {
        list = new ArrayList<>(6);
    }
    @After
    public void after() {
        list = null;
    }

    @Test
    public void test_add_1() {
        list.add(0);
        assert !list.isEmpty() : "测试add失败";
    }
    @Test
    public void test_add_2() {
        init(3);
        list.add(0);
        assert !list.isEmpty() : "测试add失败";
    }
    @Test
    public void test_add2_1() {
        init(3);
        list.add(1, 0);
        assert !list.isEmpty() : "测试add失败";
    }
    @Test
    public void test_add2_2() {
        list.add(0, 0);
        assert !list.isEmpty() : "测试add失败";
    }
    @Test
    public void test_add2_3() {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index out of bounds: 1");
        list.add(1, 0);
    }
    @Test
    public void test_remove_1() {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index out of bounds: 0");
        list.remove(0);
    }
    @Test
    public void test_remove_2() {
        init(3);
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index out of bounds: 4");
        list.remove(4);
    }
    @Test
    public void test_remove_3() {
        init(3);
        list.remove(2);
        assert list.size() == 2 : "测试remove失败";
    }
    @Test
    public void test_remove_4() {
        init(1);
        list.remove(0);
        assert list.size() == 0 : "测试remove失败";
    }
    @Test
    public void test_get_1() {
        init(1);
        assert list.get(0) == 1 : "测试get失败";
    }
    @Test
    public void test_get_2() {
        init(5);
        assert list.get(3) == 4 : "测试get失败";
    }
    @Test
    public void test_get_3() {
        init(5);
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index out of bounds: 5");
        list.get(5);
    }
    @Test
    public void test_replace_1() {
        init(5);
        list.replace(3, 0);
        assert list.get(3) == 0 : "测试replace失败";
    }
    @Test
    public void test_replace_2() {
        init(5);
        list.replace(4, 0);
        assert list.get(4) == 0 : "测试replace失败";
    }
    @Test
    public void test_replace_3() {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index out of bounds: 5");
        init(5);
        list.replace(5, 0);
    }
    @Test
    public void test_contains_1() {
        assert !list.contains(0) : "测试contains失败";
    }
    @Test
    public void test_contains_2() {
        init(2);
        assert !list.contains(0) : "测试contains失败";
    }
    @Test
    public void test_contains_3() {
        init(2);
        assert list.contains(1) : "测试contains失败";
    }
    @Test
    public void test_contains_4() {
        list.add(null);
        assert list.contains(null) : "测试contains失败";
    }
    @Test
    public void test_contains_5() {
        assert !list.contains(null) : "测试contains失败";
    }
    @Test
    public void test_isEmpty_1() {
        assert list.isEmpty() : "测试isEmpty失败";
    }
    @Test
    public void test_isEmpty_3() {
        init(1);
        assert !list.isEmpty() : "测试isEmpty失败";
    }
    @Test
    public void test_size_1() {
        assert list.size() == 0 : "测试size失败";
    }
    @Test
    public void test_size_2() {
        init(3);
        assert list.size() == 3 : "测试size失败";
    }
    @Test
    public void test_clear_1() {
        assert list.size() == 0 : "测试clear失败";
    }
    @Test
    public void test_clear_2() {
        init(3);
        list.clear();
        assert list.size() == 0 : "测试clear失败";
    }
    @Test
    public void test_all() {
        init(5);
        list.add(6);
        list.add(7);
        assert list.size() == 7;
        init(5);
        list.add(0);
        assert list.size() == 13;
        list.remove(12);
        list.remove(11);
        list.remove(10);
        list.remove(9);
        list.remove(8);
        list.remove(7);
        list.remove(6);
        list.remove(5);
        list.remove(4);
        list.remove(3);
        list.remove(2);
        list.remove(1);
        list.remove(0);
        assert list.isEmpty();
    }

    private void init(int size) {
        for(int i = 1; i <= size; i++)
            list.add(i);
    }
}
