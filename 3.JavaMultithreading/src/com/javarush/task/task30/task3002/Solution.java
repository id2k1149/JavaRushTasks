package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
если число начинается 0x то шестнадцатиричная система
если число начинается на 0 - это восьмиричная система
если число начинается на 0b это двоичная система
всё остальное это обычная
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        if (s.startsWith("0b")) {
            s = Integer.toString(Integer.parseInt(s.substring(2), 2));
        }
        else if (s.startsWith("0x")) {
            s = Integer.toString(Integer.parseInt(s.substring(2), 16));
        }
        else if (s.startsWith("0")) {
            s = Integer.toString(Integer.parseInt(s.substring(1), 8));
        }

        return s;
    }
}
