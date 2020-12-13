package com.javarush.task.task07.task0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/* 
Минимаксы в массивах
*/

public class Solution {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int maximum;
        int minimum;

        //напишите тут ваш код
        int[] numbers = getInts();

        maximum = numbers[0];
        minimum = numbers[0];

        for (int number : numbers) {
            if (number > maximum) maximum = number;
            if (number < minimum) minimum = number;
        }
        System.out.print(maximum + " " + minimum);
    }

    //напишите тут ваш код
    public static int[] getInts() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int m = 20;
        int[] numbers = new int[m];

        //заполнение массива
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.parseInt(reader.readLine());

        return numbers;
    }
}
