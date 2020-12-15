package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();

        //напишите тут ваш код
//        while(string.contains("  "))
//        {
//            string = string.replace("  ", " ");
//        }
//
//        String[] splited = string.split(" ");
//        string = "";

//        for (String each: splited) {
//            each = each.substring(0, 1).toUpperCase() + each.substring(1);
//            string += each + " ";
//        }



//        for (int i = 0; i < splited.length; i++) {
//            splited[i] = splited[i].substring(0, 1).toUpperCase() + splited[i].substring(1);
//            if (i != splited.length - 1) string += splited[i] + " ";
//            else string += splited[i];
//        }

        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 || chars[i - 1] == ' ') {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }

        System.out.println(chars);


        // JavaRush
        String result = "";
        char[] chars2 = string.toCharArray();
        for (int i = 0; i < chars2.length; i++) {
            char character = (i == 0 || chars2[i - 1] == ' ') ? Character.toUpperCase(chars2[i]) : chars2[i];
            result += character;
        }
        System.out.println(result);



    }
}
