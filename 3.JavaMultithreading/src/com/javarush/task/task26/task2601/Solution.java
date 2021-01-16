package com.javarush.task.task26.task2601;

import java.util.*;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
//        Integer[] massiv = {10,20,20,30,20,50,60,22};
//        massiv = sort(massiv);
//        for (int i = 0; i < massiv.length; i++) {
//            System.out.print(massiv[i] + " ");
//        }
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here

        Integer[] sortArray = array;
        Arrays.sort(sortArray);
        int mediana;
        if (sortArray.length % 2 != 0) {
            mediana = sortArray[((sortArray.length + 1) / 2) - 1];
        }
        else {
            mediana = (sortArray[sortArray.length / 2 - 1] + sortArray[sortArray.length / 2]) / 2;
        }

        List<ArrayList<Integer>> arrayList = new ArrayList<>();
        for (int i = 0; i < sortArray.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(Math.abs(sortArray[i] - mediana));
            list.add(sortArray[i]);
            arrayList.add(list);
        }

        // Comparator code #1
//        Comparator<List> compareBy = new Comparator<List>() {
//
//            public int compare(List o1, List o2) {
//                int result = ((int)o1.get(0)) - ((int)o2.get(0));
//                return result;
//            }
//        };

        // lambda code #2
//        Comparator<List> compareBy = (o1, o2) -> {
//            int result = ((int)o1.get(0)) - ((int)o2.get(0));
//            return result;
//        };

        // short line #3
        Comparator<List> compareBy = Comparator.comparingInt(o -> ((int) o.get(0)));

       arrayList.sort(compareBy);

        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i).get(1);
        }

        return array;
    }
}
