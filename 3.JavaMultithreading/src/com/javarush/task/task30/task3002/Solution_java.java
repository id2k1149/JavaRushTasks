package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
если число начинается 0x то шестнадцатиричная система
если число начинается на 0 - это восьмиричная система
если число начинается на 0b это двоичная система
всё остальное это обычная
*/

public class Solution_java {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        if (s.startsWith("0")) {
            char[] chars = s.toCharArray();
            if (chars[1] == 'b') {
                s = s.substring(2);
                return "" + Integer.parseInt(s, 2);
            } else if (chars[1] == 'x') {
                s = s.substring(2);
                return "" + Integer.parseInt(s, 16);
            }
            return "" + Integer.parseInt(s, 8);
        } else {
            return s;
        }
    }
}
