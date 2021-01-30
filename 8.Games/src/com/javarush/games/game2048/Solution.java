package com.javarush.games.game2048;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] start = {0, 4, 0, 2};
        int[] result = start.clone();
        System.out.println(Arrays.toString(start));

        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0) {
                for (int j = i + 1; j < result.length; j++) {
                    if (result[j] > 0) {
                        result[i] = result[j];
                        result[j] = 0;
                        System.out.println(Arrays.toString(result));
                        break;
                    }
                }
            }
        }
        if (Arrays.equals(start, result)) System.out.println("false");
        else System.out.println("true");
    }
}
