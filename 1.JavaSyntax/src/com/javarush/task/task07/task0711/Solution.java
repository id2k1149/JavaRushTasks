package com.javarush.task.task07.task0711;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Удалить и вставить
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            list.add(reader.readLine());
        }

        for (int i = 0; i < 13; i++) {
            int last = list.size() - 1;

            // 1 option
            String string = list.get(last);
            list.remove(last);

            // 2 option
//            String string = list.remove(last);

            list.add(0, string);
        }

        for (String each: list) {
            System.out.println(each);
        }

    }
}
