package xiao.xss.study.demo.adt.test.bag;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xiao.xss.study.demo.adt.bag.ArrayBag;
import xiao.xss.study.demo.adt.bag.Bag;

/**
 * ArrayBagTest
 *
 * @author xiaoliang
 * @since 2019-06-06 10:50
 */
@RunWith(JUnit4.class)
public class ArrayBagTest {
    private static final int capacity = 5;
    private Bag<Integer> bag;

    @Before
    public void before() {
        bag = new ArrayBag<>(capacity);
    }

    @After
    public void after() {
        bag = null;
    }

    @Test
    public void test_init_1() {
        bag = new ArrayBag<>();
        assert true : "初始化失败";
    }

    @Test
    public void test_add_1() {
        boolean result = bag.add(1);
        assert result : "添加测试失败";
    }
    @Test
    public void test_add_2() {
        initBag(1);
        boolean result = bag.add(2);
        assert result : "添加测试失败";
    }
    @Test
    public void test_add_3() {
        initBag(2);
        boolean result = bag.add(3);
        assert result : "添加测试失败";
    }
    @Test
    public void test_add_4() {
        initBag(3);
        boolean result = bag.add(4);
        assert result : "添加测试失败";
    }
    @Test
    public void test_add_5() {
        initBag(4);
        boolean result = bag.add(5);
        assert result : "添加测试失败";
    }
    @Test
    public void test_add_6() {
        initBag(5);
        boolean result = bag.add(6);
        assert !result : "添加测试失败";
    }
    @Test
    public void test_remove_1() {
        initBag(5);
        Integer result = bag.remove();
        assert result == 5 : "删除测试失败";
    }
    @Test
    public void test_remove_2() {
        initBag(4);
        Integer result = bag.remove();
        assert result == 4 : "删除测试失败";
    }
    @Test
    public void test_remove_3() {
        initBag(3);
        Integer result = bag.remove();
        assert result == 3 : "删除测试失败";
    }
    @Test
    public void test_remove_4() {
        initBag(2);
        Integer result = bag.remove();
        assert result == 2 : "删除测试失败";
    }
    @Test
    public void test_remove_5() {
        initBag(1);
        Integer result = bag.remove();
        assert result == 1 : "删除测试失败";
    }
    @Test
    public void test_remove_6() {
        Integer result = bag.remove();
        assert result == null : "删除测试失败";
    }
    @Test
    public void test_remove2_1() {
        initBag(3);
        boolean result = bag.remove(2);
        assert result : "删除测试失败";
    }
    @Test
    public void test_remove2_2() {
        initBag(3);
        boolean result = bag.remove(5);
        assert !result : "删除测试失败";
    }

    @Test
    public void test_clear_1() {
        initBag(3);
        bag.clear();
        assert bag.getSize() == 0 : "测试clear失败";
    }
    @Test
    public void test_clear_2() {
        bag.clear();
        assert bag.getSize() == 0 : "测试clear失败";
    }
    @Test
    public void test_isEmpty_1() {
        boolean result = bag.isEmpty();
        assert result : "测试isEmpty失败";
    }
    @Test
    public void test_isEmpty_2() {
        initBag(3);
        boolean result = bag.isEmpty();
        assert !result : "测试isEmpty失败";
    }
    @Test
    public void test_contains_1() {
        initBag(3);
        boolean result = bag.contains(2);
        assert result : "测试contains失败";
    }
    @Test
    public void test_contains_2() {
        initBag(3);
        boolean result = bag.contains(5);
        assert !result : "测试contains失败";
    }
    @Test
    public void test_contains_3() {
        boolean result = bag.contains(1);
        assert !result : "测试contains失败";
    }
    @Test
    public void test_getFrequencyOf_1() {
        initBag(1);
        initBag(2);
        initBag(2);
        int result = bag.getFrequencyOf(1);
        assert result == 3 : "测试getFrequencyOf失败";
    }
    @Test
    public void test_getFrequencyOf_2() {
        initBag(2);
        initBag(2);
        initBag(1);
        int result = bag.getFrequencyOf(6);
        assert result == 0 : "测试getFrequencyOf失败";
    }
    @Test
    public void test_getFrequencyOf_3() {
        int result = bag.getFrequencyOf(2);
        assert result == 0 : "测试getFrequencyOf失败";
    }
    @Test
    public void test_getSize_1() {
        initBag(3);
        int result = bag.getSize();
        assert result == 3 : "测试getSize失败";
    }
    @Test
    public void test_getSize_2() {
        int result = bag.getSize();
        assert result == 0 : "测试getSize失败";
    }

    @Test
    public void test_toArray_1() {
        Object[] result = bag.toArray();
        assert result.length == 0 : "测试toArray失败";
    }

    @Test
    public void test_toArray_2() {
        initBag(2);
        Object[] result = bag.toArray();
        assert result.length == 2 && (Integer) result[0] == 1 && (Integer) result[1] == 2 : "测试toArray失败";
    }

    private void initBag(int count) {
        for(int i = 1; i <= count; i++) {
            bag.add(i);
        }
    }
}
