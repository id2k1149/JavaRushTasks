package com.javarush.task.task05.task0532;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Задача по алгоритмам Ӏ Java Syntax: 5 уровень, 12 лекция
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //напишите тут ваш код
        int n = Integer.parseInt(reader.readLine());
        if (n > 0) {
            int maximum = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n-1; i++) {
                int number = Integer.parseInt(reader.readLine());
                if (number > maximum) maximum = number;
            }
            System.out.println(maximum);
        }
    }
}
