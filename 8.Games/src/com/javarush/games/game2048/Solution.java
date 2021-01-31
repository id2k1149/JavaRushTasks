package com.javarush.games.game2048;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] row = {0, 0, 0, 4};

//        int[] result = start.clone();
//        boolean flag = false;
//        System.out.println(Arrays.toString(start));
//
//        for (int i = 0; i < result.length; i++) {
//            if (result[i] == 0) {
//                for (int j = i; j < result.length; j++) {
//                    if (result[j] > 0) {
//                        result[i] = result[j];
//                        result[j] = 0;
//                        flag = true;
//                    }
//                }
//            }
//        }
//        System.out.println(Arrays.toString(result));
//        System.out.println(flag);

//        System.out.println(Arrays.toString(row));
//        boolean changes = false;
//        boolean repeat;
//        do {
//            repeat = false;
//            for (int i = 0; i < row.length-1; i++) {
//                if (row[i] == 0 && row[i+1] > 0) {
//                    row[i] = row[i+1];
//                    row[i+1] = 0;
//                    changes = true;
//                    repeat = true;
//                }
//            }
//        } while (repeat);
//        System.out.println(Arrays.toString(row));
//        System.out.println(changes);


        System.out.println(Arrays.toString(row));
        boolean changes = false;
        for (int i = 0; i < row.length-1; i++) {
            if (row[i] == row[i+1] && row[i] > 0) {
                row[i] *= 2;
                row[i+1] = 0;
                changes = true;
            }
        }
        System.out.println(Arrays.toString(row));
        System.out.println(changes);
    }
}
