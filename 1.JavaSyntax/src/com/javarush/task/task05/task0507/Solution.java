package com.javarush.task.task05.task0507;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Среднее арифметическое
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int all_sum = 0;
        int count = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            int number = Integer.parseInt(reader.readLine());
            if (number == -1){
                if (count == 0) count = 1;
                break;
            }
            else {
                all_sum += number;
                count++;
            }
        }
        System.out.println((float)all_sum/count);
    }
}

