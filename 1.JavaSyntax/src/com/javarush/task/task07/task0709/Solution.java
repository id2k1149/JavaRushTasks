package com.javarush.task.task07.task0709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Выражаемся покороче
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> strings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            strings.add(reader.readLine());
        }

        int minStringLength = strings.get(0).length();
        for (String each: strings) {
            if (each.length() < minStringLength) {
                minStringLength = each.length();
            }
        }

        for (String string : strings) {
            if (string.length() == minStringLength) {
                System.out.println(string);
            }
        }
    }
}