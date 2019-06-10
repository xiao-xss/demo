package xiao.xss.study.demo.adt.test.stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import xiao.xss.study.demo.adt.stack.LinkedStack;
import xiao.xss.study.demo.adt.stack.Stack;

import java.util.EmptyStackException;

/**
 * 测试使用链表实现栈
 *
 * @author xiaoliang
 * @since 2019-06-10 16:11
 */
public class LinkedStackTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Stack<Integer> stack;

    @Before
    public void before() {
        stack = new LinkedStack<>();
    }
    @After
    public void after(){
        stack = null;
    }

    @Test
    public void test_push_1() {
        stack.push(2);
        stack.push(1);
        assert stack.size() == 2 : "测试push失败";
    }
    @Test
    public void test_peek_1() throws EmptyStackException {
        thrown.expect(EmptyStackException.class);
        stack.peek();
    }
    @Test
    public void test_peek_2() {
        initStack(3);
        Integer result = stack.peek();
        assert result == 3 : "测试peek失败";
        assert stack.size() == 3 : "测试peek失败";
    }
    @Test
    public void test_peek_3() {
        initStack(3);
        initStack(2);
        Integer result = stack.peek();
        assert result == 2 : "测试peek失败";
        assert stack.size() == 5 : "测试peek失败";
    }
    @Test
    public void test_pop_1() throws EmptyStackException {
        thrown.expect(EmptyStackException.class);
        stack.pop();
    }
    @Test
    public void test_pop_2() {
        initStack(3);
        Integer result = stack.pop();
        assert result == 3 : "测试pop失败";
        assert stack.size() == 2 : "测试pop失败";
    }
    @Test
    public void test_pop_3() {
        initStack(3);
        initStack(2);
        Integer result = stack.pop();
        assert result == 2 : "测试pop失败";
        assert stack.size() == 4 : "测试pop失败";
    }
    @Test
    public void test_isEmpty_1() {
        assert stack.isEmpty() : "测试isEmpty失败";
    }
    @Test
    public void test_isEmpty_2() {
        initStack(1);
        assert !stack.isEmpty() : "测试isEmpty失败";
    }
    @Test
    public void test_isEmpty_3() {
        initStack(1);
        stack.pop();
        assert stack.isEmpty() : "测试isEmpty失败";
    }
    @Test
    public void test_clear_1() {
        assert stack.isEmpty();
        stack.clear();
        assert stack.isEmpty() : "测试clear失败";
        assert stack.size() == 0 : "测试clear失败";
    }
    @Test
    public void test_clear_2() {
        initStack(2);
        assert !stack.isEmpty();
        stack.clear();
        assert stack.isEmpty() : "测试clear失败";
        assert stack.size() == 0 : "测试clear失败";
    }
    @Test
    public void test_size_1() {
        assert stack.size() == 0 : "测试size失败";
    }
    @Test
    public void test_size_2() {
        initStack(3);
        assert stack.size() == 3 : "测试size失败";
    }

    private void initStack(int count) {
        for(int i = 1; i <= count; i++) {
            stack.push(i);
        }
    }
}
