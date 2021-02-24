package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Древний Рим
просто проходите с конца и суммируете,
если цифра не меньше чем все просмотренные ранее, иначе - отнимаете.
https://www.kalkulaator.ee/ru/konverter-rimskix-i-arabskix-chisel
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] charArray = s.toCharArray();
        int result = getInteger(charArray[charArray.length - 1]);
        for (int i = charArray.length-2; i >= 0; i--) {
            int number = getInteger(charArray[i]);
            int previous = getInteger(charArray[i + 1]);
            if (number >= previous) result += number;
            else result -= number;
        }
        return result;
    }

    private static int getInteger(char c) {
        switch(c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
