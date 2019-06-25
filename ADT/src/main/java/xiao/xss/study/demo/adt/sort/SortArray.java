package xiao.xss.study.demo.adt.sort;

import xiao.xss.study.demo.adt.queue.ArrayQueue;
import xiao.xss.study.demo.adt.queue.Queue;

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
    public static <T extends Comparable<? super T>> void redixSort(Integer[] arr, int from, int end, int digits) {
        if(!needSort(arr, from, end)) return;
        Queue<Integer>[] bucket = new ArrayQueue[10];
        for(int i = 0; i < 10; i++) bucket[i] = new ArrayQueue<>(5);
        for(int i = 1; i <= digits; i++) {
            for(int idx = from; idx <= end; idx++) {
                String temp = arr[idx].toString();
                if(temp.length() < i) {
                    bucket[0].add(arr[idx]);
                } else {
                    int x = Integer.valueOf(temp.substring(temp.length() - i, temp.length() - i + 1));
                    bucket[x].add(arr[idx]);
                }
            }
            int k = from;
            for(int m = 0; m < 10; m++) {
                if(bucket[m].size() > 0) {
                    int size = bucket[m].size();
                    for(int j = 0; j < size; j++) {
                        arr[k++] = bucket[m].remove();
                    }
                }
            }
        }
    }
    /**
     * 快速排序
     * @param arr 数组
     * @param from 开始位置，0~len-1
     * @param end 结束位置，~len-1
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] arr, int from, int end) {
        if(!needSort(arr, from, end)) return;
        if(end - from + 1 < 3) {
            insertionSort(arr, from, end);
        } else {
            int mid = (from + end) / 2;
            sortFML(arr, from, mid, end);
            swap(arr, mid, end - 1);
            int x = from + 1, y = end - 2;
            while(x <= y) {
                while(arr[x].compareTo(arr[end - 1]) < 0) x++;
                while(arr[y].compareTo(arr[end - 1]) > 0) y--;
                if(x < y) {
                    swap(arr, x, y);
                    x++;
                    y--;
                } else {
                    break;
                }
            }
            swap(arr, x, end - 1);
            quickSort(arr, from, x - 1);
            quickSort(arr, x + 1, end);
        }
    }
    private static <T extends Comparable<? super T>> void sortFML(T[] arr, int first, int mid, int last) {
        T[] temp = (T[]) new Comparable<?>[]{arr[first], arr[mid], arr[last]};
        insertionSort(temp, 0, 2);
        arr[first] = temp[0];
        arr[mid] = temp[1];
        arr[last] = temp[2];
    }
    /**
     * 归并排序
     * @param arr 数组
     * @param from 开始位置，0~len-1
     * @param end 结束位置，~len-1
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr, int from, int end) {
        if(!needSort(arr, from, end)) return;
        T[] temp = (T[]) new Comparable<?>[arr.length];
        mergeSort(arr, from, end, temp);
    }
    private static <T extends Comparable<? super T>> void mergeSort(T[] arr, int from, int end, T[] temp) {
        int mid = (from + end) / 2;
        if(from < end) {
            mergeSort(arr, from, mid, temp);
            mergeSort(arr, mid + 1, end, temp);
            int idx = from;
            int s1 = from, s2 = mid + 1;
            while(s1 <= mid && s2 <= end) {
                if(arr[s1].compareTo(arr[s2]) <= 0) {
                    temp[idx] = arr[s1++];
                } else {
                    temp[idx] = arr[s2++];
                }
                idx++;
            }
            while(s1 <= mid) {
                temp[idx++] = arr[s1++];
            }
            while(s2 <= end) {
                temp[idx++] = arr[s2++];
            }
            idx = from;
            while(idx <= end) {
                arr[idx] = temp[idx];
                idx++;
            }
        }
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
                for(int y = x; y + step <= end; y += step) {
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
