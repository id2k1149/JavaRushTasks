package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
        System.out.println(getPartOfString("Амиго"));
    }

    public static String getPartOfString(String string) {
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        if (string.split(" ").length < 5) throw new TooShortStringException();

        String result;
        try {
            String word = string.split(" ")[4];
            int last_index = string.indexOf(word) + word.length();
            result = string.substring(string.indexOf(" "), last_index).trim();
        } catch (TooShortStringException e) {
            throw new TooShortStringException();
        }
        return result;
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
