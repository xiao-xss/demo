package xiao.xss.study.demo.adt.bag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * LinkedBagTest
 *
 * @author xiaoliang
 * @since 2019-06-06 15:26
 */
public class LinkedBagTest {
    private Bag<Integer> bag;

    @Before
    public void before() {
        bag = new LinkedBag<>();
    }
    @After
    public void after() {
        bag = null;
    }

    @Test
    public void test_add_1() {
        boolean result = bag.add(5);
        assert result : "测试add失败";
        assert bag.getSize() == 1 : "测试add失败";
    }

    @Test
    public void test_remove_1() {
        initBag(3);
        int result = bag.remove();
        assert result == 3 : "测试remove失败";
    }
    @Test
    public void test_remove_2() {
        Integer result = bag.remove();
        assert result == null : "测试remove失败";
    }
    @Test
    public void test_remove_3() {
        initBag(3);
        bag.remove();
        assert !bag.contains(3) : "测试remove失败";
    }
    @Test
    public void test_remove2_1() {
        initBag(4);
        boolean result = bag.remove(2);
        assert result : "测试remove失败";
        assert bag.getSize() == 3 : "测试remove失败";
    }
    @Test
    public void test_remove2_2() {
        initBag(4);
        boolean result = bag.remove(5);
        assert !result : "测试remove失败";
    }
    @Test
    public void test_contains_1() {
        boolean result = bag.contains(5);
        assert !result : "测试contains失败";
    }
    @Test
    public void test_contains_2() {
        initBag(5);
        boolean result = bag.contains(5);
        assert result : "测试contains失败";
    }
    @Test
    public void test_contains_3() {
        initBag(4);
        boolean result = bag.contains(5);
        assert !result : "测试contains失败";
    }
    @Test
    public void test_clear_1() {
        initBag(4);
        bag.clear();
        assert !bag.contains(2) : "测试clear失败";
        assert bag.getSize() == 0 : "测试clear失败";
    }
    @Test
    public void test_clear_2() {
        bag.clear();
        assert !bag.contains(2) : "测试clear失败";
        assert bag.getSize() == 0 : "测试clear失败";
    }
    @Test
    public void test_isEmpty_1() {
        boolean result = bag.isEmpty();
        assert result : "测试isEmpty失败";
    }
    @Test
    public void test_isEmpty_2() {
        initBag(1);
        boolean result = bag.isEmpty();
        assert !result : "测试isEmpty失败";
    }
    @Test
    public void test_getSize_1() {
        initBag(1);
        int result = bag.getSize();
        assert result == 1 : "测试getSize失败";
    }
    @Test
    public void test_getSize_2() {
        int result = bag.getSize();
        assert result == 0 : "测试getSize失败";
    }
    @Test
    public void test_getSize_3() {
        initBag(4);
        int result = bag.getSize();
        assert result == 4 : "测试getSize失败";
    }
    @Test
    public void test_getFrequencyOf_1() {
        int result = bag.getFrequencyOf(2);
        assert result == 0 : "测试getFrequencyOf失败";
    }
    @Test
    public void test_getFrequencyOf_2() {
        initBag(2);
        int result = bag.getFrequencyOf(2);
        assert result == 1 : "测试getFrequencyOf失败";
    }
    @Test
    public void test_getFrequencyOf_32() {
        initBag(2);
        initBag(6);
        initBag(3);
        int result = bag.getFrequencyOf(2);
        assert result == 3 : "测试getFrequencyOf失败";
    }
    @Test
    public void test_toArray_1() {
        initBag(2);
        Object[] result = bag.toArray();
        assert result.length == 2 && (Integer) result[0] == 2 && (Integer) result[1] == 1 : "测试toArray失败";
    }
    @Test
    public void test_toArray_2() {
        Object[] result = bag.toArray();
        assert result.length == 0 : "测试toArray失败";
    }

    private void initBag(int count) {
        for(int i = 1; i <= count; i++) {
            bag.add(i);
        }
    }
}
