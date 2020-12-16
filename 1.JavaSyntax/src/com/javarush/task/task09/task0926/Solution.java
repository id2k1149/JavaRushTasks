package com.javarush.task.task09.task0926;

import java.util.ArrayList;

/* 
Список из массивов чисел
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList() {
        //напишите тут ваш код
        ArrayList<int[]> list = new ArrayList<>();

        int[] element_1 = new int[]{1, 2, 3, 4, 5};
        list.add(element_1);
        int[] element_2 = new int[]{6, 7};
        list.add(element_2);
        int[] element_3 = new int[]{8, 9, 10, 11};
        list.add(element_3);
        int[] element_4 = new int[]{12, 13, 14, 15, 16, 17, 18};
        list.add(element_4);
        int[] element_5 = new int[]{};
        list.add(element_5);

        return list;
    }

    public static void printList(ArrayList<int[]> list) {
        for (int[] array : list) {
            for (int x : array) {
                System.out.println(x);
            }
        }
    }
}
