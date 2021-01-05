package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) {
            throw new TooShortStringException();
        }

        if (string.indexOf("\t") == string.lastIndexOf("\t")) {
            throw new TooShortStringException();
        }

        if (string.isEmpty()) {
            throw new TooShortStringException();
        }

        if (!string.contains("\t")) {
            throw new TooShortStringException();
        }

//        String result;
//        int first_tab = string.indexOf("\t");
//        String new_string = string.substring(first_tab + 1);
//        int second_tab = new_string.indexOf("\t");
//        result = string.substring(first_tab, second_tab);

        return string.split("\t")[1];
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
//        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
//        System.out.println("-->" + getPartOfString("\t\t\ts\t"));
        String string = null;
        System.out.println(getPartOfString(string));
    }

}
