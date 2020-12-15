package com.javarush.task.task08.task0828_my_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Номер месяца
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String month = reader.readLine();
        String map_month = month.toLowerCase();

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "january");
        map.put(2, "february");
        map.put(3, "march");
        map.put(4, "april");
        map.put(5, "may");
        map.put(6, "june");
        map.put(7, "july");
        map.put(8, "august");
        map.put(9, "september");
        map.put(10, "october");
        map.put(11, "november");
        map.put(12, "december");

        for (Map.Entry<Integer, String> pair : map.entrySet())
        {
            Integer key = pair.getKey();
            String value = pair.getValue();
            if (value.equals(map_month)) {
                System.out.println(month + " is the " + key + " month");
                break;
            }
        }
    }
}
