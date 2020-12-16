package com.javarush.task.task10.task1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Integer> map = new HashMap<>();
        
        String id_string = reader.readLine();
        while (!id_string.isEmpty()){
            int id = Integer.parseInt(id_string);
            String name = reader.readLine();
            map.put(name, id);
            if (name.isEmpty()){
                break;
            }
            id_string = reader.readLine();
        }

        for (Map.Entry<String, Integer> pair : map.entrySet())
        {
            String key_name = pair.getKey();                      //ключ
            int value_id = pair.getValue();                  //значение

            System.out.println(value_id + " " + key_name);
        }

        //Java
        while (true) {
            String number = reader.readLine();
            if (number.isEmpty()) {
                break;
            }

            int id = Integer.parseInt(number);
            String name = reader.readLine();
            map.put(name, id);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }


    }
}
