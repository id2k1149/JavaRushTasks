package com.javarush.task.task07.task0708_my;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самая длинная строка
*/

public class Solution {
    private static ArrayList<String> strings;

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        strings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            strings.add(i, reader.readLine());
        }

        ArrayList<String> new_list = new ArrayList<>();
        String s_max = strings.get(0);
        new_list.add(0, s_max);
        int max = s_max.length();
        int max_index = 0;

        for (int i = 1; i < 5; i++) {
            String s = strings.get(i);

            if (s.length() > max) {
                new_list.clear();
                new_list.add(0, s);
                max = s.length();
                max_index = 0;
            }
            else if (s.length() == max) {
                max_index++;
                new_list.add(max_index, s);
                max = s.length();
            }
        }

        for (String each: new_list) {
            System.out.println(each);
        }
    }
}
