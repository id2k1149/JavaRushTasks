package com.javarush.task.task08.task0827;

import java.util.Date;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isDateOdd("MAY 1 2013"));
    }

    public static boolean isDateOdd(String date) {
//        Date currentDate = new Date(date);
//        Date startDate = new Date(date);
//        startDate.setDate(1);
//        startDate.setMonth(0);

        Date given_date = new Date(date);
        int year = given_date.getYear();
        Date start_date = new Date(year, 0, 1);

        long msTimeDistance = given_date.getTime() - start_date.getTime();
        long msDay = 24 * 60 * 60 * 1000;  //сколько миллисекунд в одних сутках

        int dayCount = (int) (msTimeDistance/msDay); //количество целых дней

        return dayCount % 2 == 0;
    }
}
