package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        int[] list = new int[15];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < list.length; i++) {
            list[i] = Integer.parseInt(reader.readLine());
        }

        int odd = 0, even = 0;

        for (int i = 0; i < list.length; i++) {
//            System.out.println("i= " + i + " number = " + list[i]);
            if (i % 2 == 0) {
                even += list[i];
//                System.out.println("i= " + i + " even = " + even);
            }
            else {
                odd += list[i];
//                System.out.println("i= " + i + " odd = " + odd);
            }
        }

        if (even > odd) System.out.println("В домах с четными номерами проживает больше жителей.");
        else System.out.println("В домах с нечетными номерами проживает больше жителей.");

    }
}
