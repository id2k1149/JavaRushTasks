package com.javarush.task.task30.task3010;

import java.math.BigInteger;
import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            String s = args[0];
            BigInteger bigInteger;

            if (s.length() != 0 && s.length() <= 255) {
                for (int i = 2; i <= 36; i++) {
                    try {
                        bigInteger = new BigInteger(s, i);
                        System.out.println(i);
                        return;
                    } catch (Exception exception) {
                    }
                }
            }
            System.out.println("incorrect");
        } catch (Exception exception) {
        }
    }
}