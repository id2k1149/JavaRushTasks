package com.javarush.task.task21.task2106;

import java.util.Date;
import java.util.Objects;

/* 
Ошибка в equals/hashCode
*/

public class Solution_java {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution_java solution;

    public Solution_java(int anInt, String string, double aDouble, Date date, Solution_java solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }



    public static void main(String[] args) {
    }
}
