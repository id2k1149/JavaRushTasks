package com.javarush.task.task04.task0414;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Количество дней в году
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int year = Integer.parseInt(reader.readLine());

        int x;
        if (year % 400 == 0) x = 366;
        else if (year % 100 == 0) x = 365;
        else if (year % 4 == 0) x = 366;
        else x = 365;

        System.out.println("количество дней в году: " + x);

    }
}