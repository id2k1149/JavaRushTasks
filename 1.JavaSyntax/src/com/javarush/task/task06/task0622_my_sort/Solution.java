package com.javarush.task.task06.task0622_my_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
Числа по возрастанию
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //напишите тут ваш код
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int number = Integer.parseInt(reader.readLine());
            numbers.add(number);
        }

        Collections.sort(numbers);

        for(int item: numbers){
            System.out.println(item);
        }
    }
}
