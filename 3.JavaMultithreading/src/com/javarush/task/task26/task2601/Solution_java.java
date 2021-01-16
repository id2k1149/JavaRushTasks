package com.javarush.task.task26.task2601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* 
Почитать в инете про медиану выборки
*/

public class Solution_java {

    public static void main(String[] args) {
//        Integer[] massiv = {10,20,20,30,20,50,60,22};
//        massiv = sort(massiv);
//        for (int i = 0; i < massiv.length; i++) {
//            System.out.print(massiv[i] + " ");
//        }
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        final double mediana = getMediana(array);

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double v1 = o1.intValue() - mediana;
                double v2 = o2.intValue() - mediana;
                return (int) ((v1 * v1 - v2 * v2) * 100);
            }
        });

        return array;
    }

    private static double getMediana(Integer[] array) {
        Arrays.sort(array);
        double res;
        int length = array.length;

        if (length % 2 == 1) {
            res = array[length / 2];
        } else {
            res = (array[length / 2 - 1] + array[length / 2]) / 2.;
        }
        return res;
    }
}
