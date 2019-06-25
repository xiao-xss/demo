package xiao.xss.study.demo.adt.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author xiaoliang
 * @since 2019-06-25 9:33
 */
public class RedixSort {
    @Rule
    public TestName method = new TestName();
    private final Integer[] exceptAll = new Integer[] {1, 3, 7, 10, 11, 13, 17, 20, 21, 23, 38, 56, 99, 102, 153};
    private final Integer[] exceptSub = new Integer[] {56, 3, 1, 7, 11, 13, 17, 20, 23, 38, 99, 102, 153, 21, 10};
    private Integer[] arr;
    private int digits;
    private final int from = 0;
    private final int end = 14;

    @Before
    public void before() {
        arr = new Integer[]{56, 3, 102, 38, 7, 11, 20, 23, 1, 17, 99, 13, 153, 21, 10};
        digits = 3;
    }
    @After
    public void after() {

    }

    @Test
    public void test_sortAll() {
        SortArray.redixSort(arr, 0, 14, digits);
        assertEquals(exceptAll, arr, 0, 14);
    }
    @Test
    public void test_sortSub() {
        SortArray.redixSort(arr, 2, 12, digits);
        assertEquals(exceptSub, arr, 0, 14);
    }


    private void assertEquals(Integer[] except, Integer[] result, int from, int end) {
        while(from++ <= end) {
            assert except[from - 1].equals(result[from - 1]) : String.format("排序失败：%s，第%d项和期望值不符\r\n期望值：%s\r\n实际值：%s",
                    method.getMethodName(), from, Arrays.toString(except), Arrays.toString(result));
        }
    }
}
