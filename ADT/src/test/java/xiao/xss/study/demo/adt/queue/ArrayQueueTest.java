package xiao.xss.study.demo.adt.queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 队列测试
 *
 * @author xiaoliang
 * @since 2019-06-17 11:30
 */
@RunWith(JUnit4.class)
public class ArrayQueueTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Queue<Integer> queue;

    @Before
    public void before() {
        queue = new ArrayQueue<>(6);
    }
    @After
    public void after() {
        queue = null;
    }

    @Test
    public void test_add_1() {
        queue.add(5);
        assert queue.peek() == 5 : "测试add失败";
        assert queue.size() == 1 : "测试add失败";
    }
    @Test
    public void test_add_2() {
        init(3);
        queue.add(5);
        assert queue.peek() == 1 : "测试add失败";
        assert queue.size() == 4 : "测试add失败";
    }
    @Test
    public void test_peek_1() throws EmptyQueueException {
        thrown.expect(EmptyQueueException.class);
        thrown.expectMessage("Queue is empty");
        queue.peek();
    }
    @Test
    public void test_peek_2() {
        init(3);
        queue.add(5);
        assert queue.peek() == 1 : "测试peek失败";
        assert queue.size() == 4 : "测试peek失败";
    }
    @Test
    public void test_remove_1() throws EmptyQueueException {
        thrown.expect(EmptyQueueException.class);
        thrown.expectMessage("Queue is empty");
        queue.remove();
    }
    @Test
    public void test_remove_2() {
        init(3);
        queue.add(5);
        assert queue.remove() == 1 : "测试remove失败";
        assert queue.size() == 3 : "测试remove失败";
    }
    @Test
    public void test_isEmpty_1() {
        assert queue.isEmpty() : "测试isEmpty失败";
    }
    @Test
    public void test_isEmpty_2() {
        init(2);
        queue.remove();
        assert !queue.isEmpty() : "测试isEmpty失败";
    }
    @Test
    public void test_size_1() {
        assert queue.size() == 0 : "测试size失败";
    }
    @Test
    public void test_size_2() {
        init(2);
        assert queue.size() == 2 : "测试size失败";
    }
    @Test
    public void test_size_3() {
        init(2);
        queue.remove();
        queue.remove();
        assert queue.size() == 0 : "测试size失败";
    }
    @Test
    public void test_clear_1() {
        queue.clear();
        assert queue.isEmpty() : "测试clear失败";
    }
    @Test
    public void test_clear_2() {
        init(3);
        queue.clear();
        assert queue.isEmpty() : "测试clear失败";
    }
    @Test
    public void test_all_1() {
        init(6);
        queue.remove();
        queue.remove();
        queue.remove();
        queue.add(3);
        queue.add(1);
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(7);
        queue.add(8);
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        assert true;
    }

    private void init(int size) {
        for(int i = 1; i <= size; i++) {
            queue.add(i);
        }
    }
}
