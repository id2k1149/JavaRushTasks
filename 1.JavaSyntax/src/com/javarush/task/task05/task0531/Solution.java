package com.javarush.task.task05.task0531;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Совершенствуем функциональность
*/

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n1 = Integer.parseInt(reader.readLine());
        int n2 = Integer.parseInt(reader.readLine());
        int n3 = Integer.parseInt(reader.readLine());
        int n4 = Integer.parseInt(reader.readLine());
        int n5 = Integer.parseInt(reader.readLine());

        int minimum = min(n1, n2, n3, n4, n5);

        System.out.println("Minimum = " + minimum);
    }


    public static int min(int n1, int n2, int n3, int n4, int n5) {
        int min1 = n1 < n2 ? n1 : n2;
        int min2 = n3 < n4 ? n3 : n4;
        int min  = min1 < min2 ? min1 : min2;
        return min < n5 ? min : n5;
    }
}
