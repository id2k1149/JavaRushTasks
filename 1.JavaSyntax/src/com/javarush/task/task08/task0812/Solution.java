package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Cамая длинная последовательность
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        List<Integer> numbers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            numbers.add(Integer.parseInt(reader.readLine()));
        }

        int max = 1;
        int current = 1;

        for (int i = 1; i < 10; i++) {
            if (numbers.get(i).equals(numbers.get(i-1))) {
                current++;
                if (current > max) max = current;
            }
            else current = 1;
        }
        System.out.println(max);
    }
}
