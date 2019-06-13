package xiao.xss.study.demo.adt.test.recursive;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import xiao.xss.study.demo.adt.recursive.Recursive;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xiaoliang
 * @since 2019-06-13 9:02
 */
@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecursiveTest {
    private long start;
    private int n;
    private long result;
    @Rule
    public TestName method = new TestName();

    @Before
    public void before() {
        result = 0;
        n = init();
        Recursive.counter = 0L;
        start = System.nanoTime();
    }
    @After
    public void after() {
        long end = System.nanoTime();
        System.out.println(String.format("%s:%d, result:%s -> Time:%d -> counter:%d",
                method.getMethodName(), n, result, end - start, Recursive.counter));
    }

    @Test
    public void countDown() {
        Recursive.countDown(n);
    }
    @Test
    public void countUp() {
        Recursive.countUp(n);
    }
    @Test
    public void sumOf() {
        result = Recursive.sumOf(n);
    }
    @Test
    public void multiplyOf() {
        result = Recursive.multiplyOf(n);
    }
    @Test
    public void powOf1() {
        result = Recursive.powOf1(1, n);
    }
    @Test
    public void powOf() {
        result = Recursive.powOf(1, n);
    }
    @Test
    public void hano1() {
        char start = 'A', temp = 'B', end = 'C';
        Recursive.hano1(n, start, temp, end);
    }
    @Test
    public void hano() {
        char start = 'A', temp = 'B', end = 'C';
        Recursive.hano(n, start, temp, end);
    }
    @Test
    public void fibo1() {
        result = Recursive.fibo1(n);
    }
    @Test
    public void fibo() {
        Map<Integer, Long> cache = new HashMap<>();
        result = Recursive.fibo(n, cache);
    }

    private int init() {
        switch(method.getMethodName()) {
            case "countDown":
            case "countUp":
                n = 10_000; break;
            case "sumOf":
                n = 10_000; break;
            case "multiplyOf":
                n = 10_000; break;
            case "powOf1":
            case "powOf":
                n = 10000; break;
            case "hano1":
            case "hano":
                n = 20; break;
            case "fibo1":
            case "fibo":
                n = 40; break;
            default: break;
        }
        return n;
    }
}
