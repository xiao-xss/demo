package xiao.xss.study.demo.adt.recursive;

import java.util.Map;

/**
 * 递归
 *
 * @author xiaoliang
 * @since 2019-06-12 15:39
 */
public class Recursive {
    public static long counter = 0;
    public static long fibo(int n, Map<Integer, Long> map) {
        counter++;
        if(map.containsKey(n)) {
            return map.get(n);
        }
        long result;
        if(n < 2) {
            result = 1;
        } else {
            result = fibo1(n - 1) + fibo1(n - 2);
        }
        map.put(n, result);
        return result;
    }
    public static long fibo1(int n) {
        counter++;
        long result;
        if(n < 2) {
            result = 1;
        } else {
            result = fibo1(n - 1) + fibo1(n - 2);
        }
        return result;
    }
    public static void hano(int num, char start, char temp, char end) {
        counter++;
        while(num > 0) {
            hano(num - 1, start, end, temp);
            num--;
            char c = temp;
            start = temp;
            temp = c;
        }
    }
    public static void hano1(int num, char start, char temp, char end) {
        counter++;
        if(num > 0){
            hano1(num - 1, start, end, temp);
//            System.out.println(String.format("%s -> %s", start, end));
            hano1(num - 1, temp, start, end);
        }
    }
    public static long powOf(int x, int n) {
        counter++;
        long result;
        if(n == 1) {
            result = x;
        } else {
            result = powOf(x, n / 2);
            result = result * result;
            if(n % 2 != 0) {
                result = x * result;
            }
        }
        return result;
    }
    public static long powOf1(int x, int n) {
        counter++;
        long result;
        if(n == 1) {
            result = x;
        } else {
            result = powOf(x, n / 2) * powOf(x, n / 2);
            if(n % 2 != 0) {
                result = x * result;
            }
        }
        return result;
    }
    public static int multiplyOf(int n) {
        counter++;
        int result;
        if(n == 1) {
            result = 1;
        } else {
            result = n * multiplyOf(n - 1);
        }
        return result;
    }
    public static int sumOf(int n) {
        counter++;
        int sum;
        if(n == 1) {
            sum = 1;
        } else {
            sum = n + sumOf(n - 1);
        }
        return sum;
    }
    public static void countUp(int n) {
        counter++;
        if(n > 0) {
//            System.out.print(n + " ");
            countUp(n - 1);
        }
//        System.out.println();
    }
    public static void countDown(int n) {
        counter++;
        if(n > 0) {
//            System.out.println(n + " ");
            countDown(n - 1);
        }
//        System.out.println();
    }
}
