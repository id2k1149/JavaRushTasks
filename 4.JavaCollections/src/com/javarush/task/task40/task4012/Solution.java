package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(String[] args) {

    }

    // должен принимать дату и возвращать true,
    // если год является високосным, иначе - false.
    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();
    }

    // должен принимать дату и возвращать true,
    // если она предшествует текущей дате, иначе - false.
    public static boolean isBefore(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    // должен возвращать полученное в качестве параметра время,
    // увеличенное на n СhronoUnit.
    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    // должен принимать две даты
    // и возвращать временной промежуток между ними.
    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        LocalDate beforeDate;
        LocalDate afterDate;

        if (firstDate.isBefore(secondDate)) {
            beforeDate = firstDate;
            afterDate = secondDate;

        } else {
            afterDate = firstDate;
            beforeDate = secondDate;
        }

        return Period.between(beforeDate, afterDate);
    }

    /*
    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        if (firstDate.isBefore(secondDate)) {
            return Period.between(firstDate, secondDate);
        } else {
            return Period.between(secondDate, firstDate);
        }
    }
     */
}
