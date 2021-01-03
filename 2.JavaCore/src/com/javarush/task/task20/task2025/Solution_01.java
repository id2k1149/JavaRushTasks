package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Алгоритмы-числа
*/

public class Solution_01 {

    public static long[] getNumbers(long N) {
        int range = find_range(N);
        long[] result = new long[range];
        int number;
        long[] n_list_2 = new long[range];
        ArrayList<Integer>  n_list = new ArrayList<>();

        while (N > 0) {
            number = find_number(N);
            N = N - number;
            N = N / 10;
            n_list.add(number);
        }

        for (int i = 0; i < n_list.size(); i++) {
            n_list_2[i] = n_list.get(i);
        }


        for (int i = 0; i < range ; i++) {
            result[i] = n_list_2[range - i - 1];
        }

        return result;
    }

    public static int find_number(long N) {
        int number = (int) N % 10;
        return number;
    }

    public static int find_range(long N) {
        int count = 0;
        while (N > 0) {
            N = (N - (N % 10)) / 10;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(370)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

//        a = System.currentTimeMillis();
//        System.out.println(Arrays.toString(getNumbers(1000000)));
//        b = System.currentTimeMillis();
//        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
//        System.out.println("time = " + (b - a) / 1000);
    }
}
