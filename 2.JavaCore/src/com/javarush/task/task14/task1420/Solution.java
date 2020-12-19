package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number_1 = Integer.parseInt(reader.readLine());
        int number_2 = Integer.parseInt(reader.readLine());
        if ((number_1 <= 0) || (number_2 <= 0)) {
            throw new Exception();
        }

        int result = 1;

        if (number_1 < number_2){
            for (int i = number_1; i > 1; i--) {
                if ((number_2 % i == 0) && (number_1 % i == 0)) {
                    result = i;
                    break;
                }
            }
        }
        else {
            for (int i = number_2; i > 1; i--) {
                if ((number_1 % i == 0) && (number_2 % i == 0)) {
                    result = i;
                    break;
                }
            }
        }

        System.out.println(result);

    }
}
