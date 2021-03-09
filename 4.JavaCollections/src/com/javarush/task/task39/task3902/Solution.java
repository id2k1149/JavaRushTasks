package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Биты были биты
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number: ");

        long l = Long.parseLong(reader.readLine());
        String result = isWeightEven(l) ? "even" : "odd";
        System.out.println("The entered number has " + result + "ones");

    }

    public static boolean isWeightEven(long number) {
        int bitCount = 0;

        // Цикл с 64 итерациями (именно столько битов в long).
        for (int i = 0; i < 64; i++) {
            // В каждом цикле побитовое сравнение number и 1.
            // Если результат сравнения == 1,
            // то bitCount++.
            if ((number & 1) == 1) {
                bitCount++;
            }
            //  побитовый сдвиг number вправо на 1.
            number >>= 1;
        }
        // Проверяем count на чётность
        return bitCount % 2 == 0;
    }
}
