package xiao.xss.study.demo.adt.sort;

/**
 * 数组排序
 *
 * @author xiaoliang
 * @since 2019-06-13 11:30
 */
public class SortArray {
    /**
     * 基数排序
     * @param arr 数组
     * @param from 开始位置，0~len-1
     * @param end 结束位置，~len-1
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<? super T>> void redixSort(T[] arr, int from, int end) {

    }
    /**
     * 快速排序
     * @param arr 数组
     * @param from 开始位置，0~len-1
     * @param end 结束位置，~len-1
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] arr, int from, int end) {

    }
    /**
     * 归并排序
     * @param arr 数组
     * @param from 开始位置，0~len-1
     * @param end 结束位置，~len-1
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr, int from, int end) {

    }
    /**
     * 冒泡排序
     * @param arr 数组
     * @param from 开始位置，0~len-1
     * @param end 结束位置，~len-1
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr, int from, int end) {
        if(!needSort(arr, from, end)) return;
        boolean swap = true;
        for(int i = from; i < end && swap; i++) {
            swap = false;
            for(int j = from; j < end + from - i; j++) {
                if(arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j+1);
                    swap = true;
                }
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
    /**
     * 希尔排序
     * @param arr 数组
     * @param from 开始位置，0~len-1
     * @param end 结束位置，~len-1
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] arr, int from, int end) {
        if(!needSort(arr, from, end)) return;
        int step = (from + end + 1) / 2;
        while(step > 0) {
            for(int x = from; x < from + step; x++) {
                for(int y = x; y < end && y + step <= end; y += step) {
                    T e = arr[y + step];
                    int z = y;
                    while(z >= from && arr[z].compareTo(e) > 0) {
                        arr[z + step] = arr[z];
                        z -= step;
                    }
                    arr[z + step] = e;
//                    System.out.println(Arrays.toString(arr));
                }
            }
            step = step / 2;
        }
    }
    /**
     * 插入排序
     * @param arr 数组
     * @param from 开始位置，0~len-1
     * @param end 结束位置，~len-1
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr, int from, int end) {
        if(!needSort(arr, from, end)) return;
        for(int idx = from; idx < end; idx++) {
            T e = arr[idx + 1];
            int x = idx;
            while(x >= from && arr[x].compareTo(e) > 0) {
                arr[x + 1] = arr[x];
                x--;
            }
            arr[x + 1] = e;
//            System.out.println(Arrays.toString(arr));
        }
    }
    /**
     * 选择排序
     * @param arr 数组
     * @param from 开始位置，0~len-1
     * @param end 结束位置，~len-1
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr, int from, int end) {
        if(!needSort(arr, from, end)) return;
        for(int idx = from; idx < end; idx++) {
            int minIdx = idx;
            for(int x = idx + 1; x <= end; x++) {
                if(arr[x].compareTo(arr[minIdx]) < 0){
                    minIdx = x;
                }
            }
            swap(arr, idx, minIdx);
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static boolean needSort(Object[] arr, int from, int end) {
        return arr != null && arr.length > 1 && from >= 0 && from <= arr.length && end >= from && end <= arr.length;
    }
}
