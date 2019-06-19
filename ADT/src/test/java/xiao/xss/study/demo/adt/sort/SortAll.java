package xiao.xss.study.demo.adt.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

/**
 * 排序测试
 *
 * @author xiaoliang
 * @since 2019-06-13 15:58
 */
@RunWith(JUnit4.class)
public class SortAll {
    @Rule
    public TestName method = new TestName();
    private final Integer[] except = new Integer[] {1, 2, 3, 4, 4, 6, 7, 9, 10, 13, 15, 17, 19, 21, 23};
    private Integer[] arr;
    private final int from = 0;
    private final int end = 14;

    @Before
    public void before() {
        arr = new Integer[]{9, 3, 6, 4, 7, 1, 2, 23, 19, 17, 4, 13, 15, 21, 10};
    }
    @After
    public void after() {
        assertEquals(except, arr, 0, 14);
    }

    @Test
    public void selectionSort() {
        SortArray.selectionSort(arr, from, end);
    }
    @Test
    public void insertionSort() {
        SortArray.insertionSort(arr, from, end);
    }
    @Test
    public void shellSort() {
        SortArray.shellSort(arr, from, end);
    }
    @Test
    public void bubbleSort() {
        SortArray.bubbleSort(arr, from, end);
    }
    @Test
    public void mergeSort() {
        SortArray.mergeSort(arr, from, end);
    }
    @Test
    public void quickSort() {
        SortArray.quickSort(arr, from, end);
    }

    private void assertEquals(Integer[] except, Integer[] result, int from, int end) {
        while(from++ <= end) {
            assert except[from - 1].equals(result[from - 1]) : String.format("排序失败：%s，第%d项和期望值不符\r\n期望值：%s\r\n实际值：%s",
                    method.getMethodName(), from, Arrays.toString(except), Arrays.toString(result));
        }
    }
}
