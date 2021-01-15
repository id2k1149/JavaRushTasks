package com.javarush.task.task26.task2601;

import java.util.*;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
        Integer[] massiv = {13, 8, 15, 5, 17};
        System.out.println(sort(massiv).toString());

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        List<Integer> arrayList = Arrays.asList(array);
        Collections.sort(arrayList);
        System.out.println("->" + arrayList.toString());
        int mediana;
        if (arrayList.size() % 2 != 0) {
            mediana = arrayList.get(((arrayList.size() + 1) / 2) - 1);
        }
        else {
            mediana = (arrayList.get((arrayList.size() / 2) - 1) + arrayList.get(arrayList.size() / 2)) / 2;
        }
        System.out.println(mediana);


        return array;
    }
}
