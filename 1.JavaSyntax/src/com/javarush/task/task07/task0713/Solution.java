package com.javarush.task.task07.task0713;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Играем в Jолушку
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<Integer> main_list = new ArrayList<Integer>();
        ArrayList<Integer> list_1 = new ArrayList<Integer>();
        ArrayList<Integer> list_2 = new ArrayList<Integer>();
        ArrayList<Integer> list_3 = new ArrayList<Integer>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 20; i++) {
            main_list.add(Integer.parseInt(reader.readLine()));
        }

        for (Integer each: main_list) {
            if (each % 3 == 0) list_1.add(each);
            if (each % 2 == 0) list_2.add(each);
            if (each % 2 != 0 && each % 3 != 0) list_3.add(each);
        }

        printList(list_1);
        printList(list_2);
        printList(list_3);

    }

    public static void printList(ArrayList<Integer> list) {
        //напишите тут ваш код
        for (Integer each: list) {
            System.out.println(each);
        }

    }
}
