package xiao.xss.study.demo.adt.queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 双端队列测试
 *
 * @author xiaoliang
 * @since 2019-06-17 15:36
 */
@RunWith(JUnit4.class)
public class ArrayDequeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Deque<Integer> deque;
    @Before
    public void before() {
        deque = new ArrayDeque<>(6);
    }
    @After
    public void after() {
        deque = null;
    }

    @Test
    public void test_addHead_1() {
        deque.addHead(2);
        assert deque.peekHead() == 2 : "测试addHead失败";
        assert deque.peekTail() == 2 : "测试addHead失败";
    }
    @Test
    public void test_addHead_2() {
        deque.addHead(2);
        deque.addHead(3);
        assert deque.peekHead() == 3 : "测试addHead失败";
        assert deque.peekTail() == 2 : "测试addHead失败";
    }
    @Test
    public void test_addTail_1() {
        deque.addTail(2);
        assert deque.peekHead() == 2 : "测试addTail失败";
        assert deque.peekTail() == 2 : "测试addTail失败";
    }
    @Test
    public void test_addTail_2() {
        deque.addTail(2);
        deque.addTail(3);
        assert deque.peekHead() == 2 : "测试addTail失败";
        assert deque.peekTail() == 3 : "测试addTail失败";
    }
    @Test
    public void test_peekHead_1() throws EmptyQueueException {
        thrown.expect(EmptyQueueException.class);
        thrown.expectMessage("Queue is empty");
        deque.peekHead();
    }
    @Test
    public void test_peekHead_2() {
        init(2);
        assert deque.peekHead() == 1 : "测试peekHead失败";
    }
    @Test
    public void test_peekTail_1() throws EmptyQueueException {
        thrown.expect(EmptyQueueException.class);
        thrown.expectMessage("Queue is empty");
        deque.peekTail();
    }
    @Test
    public void test_peekTail_2() {
        init(2);
        assert deque.peekTail() == 2 : "测试peekTail失败";
    }
    @Test
    public void test_removeHead_1() throws EmptyQueueException {
        thrown.expect(EmptyQueueException.class);
        thrown.expectMessage("Queue is empty");
        deque.removeHead();
    }
    @Test
    public void test_removeHead_2() {
        init(2);
        assert deque.removeHead() == 1 : "测试removeHead失败";
    }
    @Test
    public void test_removeTail_1() throws EmptyQueueException {
        thrown.expect(EmptyQueueException.class);
        thrown.expectMessage("Queue is empty");
        deque.removeTail();
    }
    @Test
    public void test_removeTail_2() {
        init(2);
        assert deque.removeTail() == 2 : "测试removeTail失败";
    }
    @Test
    public void test_isEmpty_1() {
        assert deque.isEmpty() : "测试isEmpty失败";
    }
    @Test
    public void test_isEmpty_2() {
        init(2);
        deque.removeHead();
        assert !deque.isEmpty() : "测试isEmpty失败";
    }
    @Test
    public void test_size_1() {
        assert deque.size() == 0 : "测试size失败";
    }
    @Test
    public void test_size_2() {
        init(2);
        assert deque.size() == 2 : "测试size失败";
    }
    @Test
    public void test_size_3() {
        init(2);
        deque.removeHead();
        deque.removeTail();
        assert deque.size() == 0 : "测试size失败";
    }
    @Test
    public void test_clear_1() {
        deque.clear();
        assert deque.isEmpty() : "测试clear失败";
    }
    @Test
    public void test_clear_2() {
        init(3);
        deque.clear();
        assert deque.isEmpty() : "测试clear失败";
    }
    @Test
    public void test_all_1() {
        deque.addTail(1);
        deque.addTail(2);
        deque.addTail(3);
        deque.addTail(4);
        deque.addTail(5);
        deque.addHead(0);
        deque.removeTail();
        deque.removeTail();
        deque.removeTail();
        deque.addHead(1);
        deque.addHead(2);
        deque.removeHead();
        deque.removeHead();
        deque.removeHead();
        deque.removeHead();
        deque.removeHead();

        init(6);
        deque.addHead(9);
        deque.addHead(8);
        deque.addHead(7);
        deque.removeTail();
        deque.removeTail();
        deque.removeTail();
        deque.removeTail();
        deque.removeTail();
        deque.removeTail();
        deque.removeTail();
        assert true;
    }

    private void init(int size) {
        for(int i = 1; i <= size; i++) {
            deque.addTail(i);
        }
    }
}
