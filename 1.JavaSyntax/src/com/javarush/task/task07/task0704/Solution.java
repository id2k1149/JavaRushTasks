package com.javarush.task.task07.task0704;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Переверни массив
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int[] list = new int[10];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < list.length; i++)
        {
            list[i] = Integer.parseInt(reader.readLine());
        }

        for (int i = 9; i >= 0 ; i--) {
            System.out.println(list[i]);
        }

    }
}

