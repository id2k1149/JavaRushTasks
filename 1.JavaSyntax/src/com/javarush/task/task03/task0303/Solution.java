package com.javarush.task.task03.task0303;

/* 
Обмен валют
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        double usd_1 = convertEurToUsd(2, 1.1);
        double usd_2 = convertEurToUsd(3, 1.1);
        System.out.println(usd_1);
        System.out.println(usd_2);
    }

    public static double convertEurToUsd(int eur, double exchangeRate) {
        //напишите тут ваш код
        return eur * exchangeRate;
    }
}
