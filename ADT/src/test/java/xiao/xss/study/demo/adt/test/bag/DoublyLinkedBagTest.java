package xiao.xss.study.demo.adt.test.bag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xiao.xss.study.demo.adt.bag.Bag;
import xiao.xss.study.demo.adt.bag.DoublyLinkedBag;

/**
 * DoublyLinkedBag
 *
 * @author xiaoliang
 * @since 2019-06-06 17:20
 */
public class DoublyLinkedBagTest {
    private Bag<Integer> bag;

    @Before
    public void before() {
        bag = new DoublyLinkedBag<>();
    }
    @After
    public void after() {
        bag = null;
    }

    @Test
    public void test_add_1() {
        boolean result = bag.add(1);
        assert result : "测试add失败";
    }
    @Test
    public void test_add_2() {
        initBag(2);
        boolean result = bag.add(5);
        assert result : "测试add失败";
        assert bag.getSize() == 3 : "测试add失败";
    }
    @Test
    public void test_remove_1() {
        Integer result = bag.remove();
        assert result == null : "测试remove失败";
    }
    @Test
    public void test_remove_2() {
        initBag(1);
        Integer result = bag.remove();
        assert result == 1 : "测试remove失败";
        result = bag.remove();
        assert result == null : "测试remove失败";
    }
    @Test
    public void test_remove_3() {
        initBag(2);
        initBag(3);
        Integer result = bag.remove();
        assert result == 3 : "测试remove失败";
    }
    @Test
    public void test_remove2_1() {
        boolean result = bag.remove(1);
        assert !result : "测试remove2失败";
    }
    @Test
    public void test_remove2_2() {
        initBag(1);
        boolean result = bag.remove(2);
        assert !result : "测试remove2失败";
    }
    @Test
    public void test_remove2_3() {
        initBag(1);
        boolean result = bag.remove(1);
        assert result : "测试remove2失败";
        assert bag.getSize() == 0 : "测试remove2失败";
    }
    @Test
    public void test_remove2_4() {
        initBag(2);
        boolean result = bag.remove(1);
        assert result : "测试remove2失败";
        assert bag.getSize() == 1 : "测试remove2失败";
    }
    @Test
    public void test_remove2_5() {
        initBag(2);
        boolean result = bag.remove(2);
        assert result : "测试remove2失败";
        assert bag.getSize() == 1 : "测试remove2失败";
    }
    @Test
    public void test_remove2_6() {
        initBag(5);
        boolean result = bag.remove(2);
        assert result : "测试remove2失败";
        assert bag.getSize() == 4 : "测试remove2失败";
    }
    @Test
    public void test_clear_1() {
        initBag(5);
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
        initBag(1);
        boolean result = bag.isEmpty();
        assert !result : "测试isEmpty失败";
    }
    @Test
    public void test_contains_1() {
        boolean result = bag.contains(1);
        assert !result : "测试contains失败";
    }
    @Test
    public void test_contains_2() {
        initBag(2);
        boolean result = bag.contains(4);
        assert !result : "测试contains失败";
    }
    @Test
    public void test_contains_3() {
        initBag(2);
        boolean result = bag.contains(1);
        assert result : "测试contains失败";
    }
    @Test
    public void test_getFrequencyOf_1() {
        int result = bag.getFrequencyOf(1);
        assert result == 0 : "测试getFrequencyOf失败";
    }
    @Test
    public void test_getFrequencyOf_2() {
        initBag(2);
        int result = bag.getFrequencyOf(4);
        assert result == 0 : "测试getFrequencyOf失败";
    }
    @Test
    public void test_getFrequencyOf_3() {
        initBag(6);
        initBag(2);
        initBag(4);
        int result = bag.getFrequencyOf(4);
        assert result == 2 : "测试getFrequencyOf失败";
    }
    @Test
    public void test_getSize_1() {
        int result = bag.getSize();
        assert result == 0 : "测试getSize失败";
    }
    @Test
    public void test_getSize_2() {
        initBag(2);
        int result = bag.getSize();
        assert result == 2 : "测试getSize失败";
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
