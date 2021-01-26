package com.javarush.task.task29.task2913;

import java.util.ArrayList;
import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

//    public static String getAllNumbersBetween(int a, int b) {
//        if (a > b) {
//            return a + " " + getAllNumbersBetween(a - 1, b);
//        } else {
//            if (a == b) {
//                return Integer.toString(a);
//            }
//            return a + " " + getAllNumbersBetween(a + 1, b);
//        }
//    }

    public static String getAllNumbersBetween(int a, int b) {
//        System.out.println(a + " " + b);
        StringBuilder result;
        if (a > b) {
            result = new StringBuilder(String.valueOf(a));
            int count = a - b;
            for (int i = count; i > 0; i--) {
               result.append(" ").append(--a);
            }
        } else {
            if (a == b) {
                return Integer.toString(a);
            }
            int count = b - a;
            result = new StringBuilder(String.valueOf(a));
            for (int i = 0; i < count; i++) {
                result.append(" ").append(++a);
            }

        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}