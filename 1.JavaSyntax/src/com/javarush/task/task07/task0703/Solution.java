package com.javarush.task.task07.task0703;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Общение одиноких массивов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        String[] list_string = new String[10];
        int[] list_int = new int[10];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < list_string.length; i++)
        {
            list_string[i] = reader.readLine();
            list_int[i] = list_string[i].length();
        }

        for (int value : list_int) System.out.println(value);
    }
}
