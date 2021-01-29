package com.javarush.task.task29.task2907;

import java.math.BigDecimal;

/* 
Этот странный BigDecimal
https://habr.com/ru/post/201334/
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getValue(1.1d, 1.2d));
    }

    public static BigDecimal getValue(double v1, double v2) {
//        return new BigDecimal(v1).add(new BigDecimal(v2));
        BigDecimal bigDecimal_1 = new BigDecimal(BigDecimal.valueOf(v2).toString()) ;
        BigDecimal bigDecimal_2 = new BigDecimal( BigDecimal.valueOf(v1).toString());
        return bigDecimal_1.add(bigDecimal_2);
    }
}
