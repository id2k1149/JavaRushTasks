package com.javarush.task.task04.task0411;

/* 
Время года
*/

public class Solution {
    public static void main(String[] args) {
        checkSeason(12);
        checkSeason(4);
        checkSeason(7);
        checkSeason(10);
    }

    public static void checkSeason(int month) {
        //напишите тут ваш код
//        if (month <= 2) System.out.println("зима");
//        else if (month <= 5) System.out.println("весна");
//        else if (month <= 8) System.out.println("лето");
//        else if (month <= 11) System.out.println("осень");
//        else  System.out.println("зима");
        switch (month) {
            case 1:
            case 2:
            case 12:
                System.out.println("зима");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("весна");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("лето");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("осень");
                break;
            default:
                System.out.println("неверный номер месяца");
                break;
        }

    }
}