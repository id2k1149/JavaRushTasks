package com.javarush.task.task07.task0707;

import java.util.ArrayList;

/* 
Что за список такой?
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        String s = "string #";
        for (int i = 0; i < 5; i++) {
//            System.out.println(s + i);
            list.add(i, s + i);
        }
        System.out.println(list.size());

        // 1
        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i));
        }

        // 2
        for (String each: list) {
            System.out.println(each);
        }
    }
}
