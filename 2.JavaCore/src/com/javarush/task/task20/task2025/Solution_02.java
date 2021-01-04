package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Алгоритмы-числа
*/

public class Solution_02 {
    public static long[] getNumbers(long N) {
        ArrayList<Long> list = new ArrayList<>();
        long[] por = new long[65];
        int M;
        long S;
        long X;
        for (int i = 1; i < N; i++){
            M = kolCifr(i);
            S = (long) i;
            X = 0;
            for (int j = 1; j <= M; j++){
                por[j] = S % 10;
                S = S / 10;
            }
            for (int j = 1; j <= M; j++){
                S = 1;
                for (int k = 1; k <= M; k++ ) {
                    S = S * por[j];
                }
                por[j] = S;
            }
            for (int j = 1; j <= M; j++){
                X = X + por[j];
            }
            if (X == (long) i)     list.add(X);
        }
        long[] result = new long[list.size()];
        for (int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
    public static int kolCifr(long n){
        long x = 1;
        int m = 0;
        while (true){
            x = 10 * x;
            m = m + 1;
            if ((n / x) == 0)  break;
        }
        return m;
    }
    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}
