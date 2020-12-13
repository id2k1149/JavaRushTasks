package com.javarush.task.task07.task0716;

import java.util.ArrayList;

/* 
Р или Л
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("роза");
        strings.add("лоза");
        strings.add("лира");
        strings = fix(strings);

        for (String string : strings) {
            System.out.println(string);
        }
    }

    public static ArrayList<String> fix(ArrayList<String> strings) {
        //напишите тут ваш код
        ArrayList<String> new_strings = new ArrayList<>();
        for (String each: strings) {

            if (each.contains("р") && each.contains("л")){
                new_strings.add(each);
            }
            else if (each.contains("л")){
                new_strings.add(each);
                new_strings.add(each);
            }
            else if (!(each.contains("р"))){
                new_strings.add(each);
            }

        }

        return new_strings;
    }
}