package com.javarush.task.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int[] list_20 = new int[20];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < list_20.length; i++) {
            list_20[i] = Integer.parseInt(reader.readLine());
        }

        int[] list_1_10 = new int[10];
        int[] list_2_10 = new int[10];

        for (int i = 0; i < list_1_10.length; i++) {
            list_1_10[i] = list_20[i];
        }

        for (int i = 0; i < list_2_10.length; i++) {
            list_2_10[i] = list_20[i+10];
        }

        for (int value : list_2_10) System.out.println(value);
    }
}
