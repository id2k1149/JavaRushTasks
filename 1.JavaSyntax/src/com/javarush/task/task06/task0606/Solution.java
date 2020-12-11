package com.javarush.task.task06.task0606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(buffer.readLine());
        while (number != 0) {
            int v = number % 10;
            if (v % 2 == 0) {
                even++;
//                System.out.println("Even: " + even);
            } else {
                odd++;
//                System.out.println("Odd: " + odd);
            }
            number -= v;
            number /= 10;
        }
        System.out.println("Even: " + even + " Odd: " + odd);

    }
}
