package com.javarush.task.task10.task1015;

import java.util.ArrayList;

/* 
Массив списков строк
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String>[] arrayOfStringList = createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList() {
        //напишите тут ваш код

        ArrayList<String> arrayList_1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrayList_1.add("text_1_" + i);
        }

        ArrayList<String> arrayList_2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrayList_2.add("text_2_" + i);
        }

        ArrayList<String>[] list = new ArrayList[2];
        list[0] = arrayList_1;
        list[1] = arrayList_2;

        return list;
    }

    //Java
    public static ArrayList<String>[] createList_Java() {
        ArrayList<String>[] result = new ArrayList[3];

        result[0] = new ArrayList<>();
        result[1] = new ArrayList<>();
        result[2] = new ArrayList<>();

        result[0].add("Mama");
        result[1].add("Mila");
        result[2].add("Ramu");

        return result;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList) {
        for (ArrayList<String> list : arrayOfStringList) {
            for (String string : list) {
                System.out.println(string);
            }
        }
    }
}