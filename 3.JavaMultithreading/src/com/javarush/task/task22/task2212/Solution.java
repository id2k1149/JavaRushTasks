package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
        if (telNumber.equals("")) return false;
        if (telNumber.isEmpty()) return false;
        if (telNumber.length() < 10) return false;
        if (telNumber.length() > 17) return false;
        if (telNumber.matches(".*[a-zA-ZА-Яа-я].*")) return false;

        int count = 0;
        for (int i = 0; i < telNumber.length(); i++) {
            if (Character.isDigit(telNumber.charAt(i))) count++;
        }

        if (count == 12)  {
            if (telNumber.matches("^\\+\\d{12}" )) return true;
            if (telNumber.matches("^\\+\\d{2}\\(\\d{3}\\)\\d{7}")) return true;
            if (telNumber.matches("^\\+\\d{8}-\\d{2}-\\d{2}" )) return true;
        }

        if (count == 10)  {
            if (telNumber.matches("^\\(?\\d{10}" )) return true;
        }

        if (telNumber.matches("(\\+\\d+)?\\d*(\\(\\d{3}\\))?\\d+(-?\\d+){0,2}"))
        if (telNumber.matches("^[0-9]+-[0-9]+$" )) return true;
        if (telNumber.matches("^[0-9]+-[0-9]+-[0-9]+$" )) return true;
        if (telNumber.matches("\\+?[0-9]+\\([0-9]{3}\\)[0-9]+\\d+" )) return true;

        return false;
    }

    public static void main(String[] args) {
//        System.out.println("+380501234567 - " + checkTelNumber("+380501234567"));
//        System.out.println("012-3456789 - " + checkTelNumber("012-3456789"));
//        System.out.println("(0123456789 - " + checkTelNumber("(0123456789"));
//        System.out.println("(012)3456789 - " + checkTelNumber("(012)3456789"));
//        System.out.println("012-3456-789 - " + checkTelNumber("012-3456-789"));
//
//        System.out.println("+38(050)123-45-678" + checkTelNumber("+38(050)123-45-678")); // false
//        System.out.println(checkTelNumber("123456789")); // false
//        System.out.println(checkTelNumber("+38(050)1-23-45-67")); // false
//        System.out.println(checkTelNumber("050ххх4567")); // false
//        System.out.println(checkTelNumber("0-50(123)456")); // false
//        System.out.println(checkTelNumber("(0501)234567")); // false
//        System.out.println(checkTelNumber("+38050123456")); // false
//        System.out.println(checkTelNumber("+380501234567-")); // false
//        System.out.println(checkTelNumber("0501234560")); // true
//        System.out.println(checkTelNumber("+380501234567")); // true
//        System.out.println(checkTelNumber("+38(050)12345-67"));// true
//        System.out.println(checkTelNumber("+38(050)1234567"));// true
//        System.out.println(checkTelNumber("+38050123-45-67"));// true
//        System.out.println(checkTelNumber("050123-4567"));// true
//        System.out.println(checkTelNumber("05(012)34567")); // true
//        System.out.println(checkTelNumber("0501234567")); // true

//        System.out.println(checkTelNumber("1234567890")); // 10 символов
//        System.out.println(checkTelNumber("123-4567890")); // 11 символов
//        System.out.println(checkTelNumber("123-456-7890")); // 12 символов
//        System.out.println(checkTelNumber("123(456)7890")); // 12 символов
//        System.out.println(checkTelNumber("123(456)78-90")); // 13 символов
//        System.out.println(checkTelNumber("+380501234561")); // 13 символов
//        System.out.println(checkTelNumber("+380-501234561")); // 14 символов
//        System.out.println(checkTelNumber("1(234)56-78-90")); // 14 символов
//        System.out.println(checkTelNumber("+3(805)01234561")); // 15 символов
//        System.out.println(checkTelNumber("+3805012-34-561")); // 15 символов
//        System.out.println(checkTelNumber("+3(805)01234-561")); // 16 символов
//        System.out.println(checkTelNumber("+3(805)012-34-561")); // 17 символов

    }
}
