package com.javarush.task.task08.task0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/* 
Модернизация ПО
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> city_list = new ArrayList<>();
        List<String> family_list = new ArrayList<>();
        while (true) {
            String city = reader.readLine();
            if (city.isEmpty()) {
                break;
            }
            city_list.add(city);

            String family = reader.readLine();
            if (family.isEmpty()) {
                break;
            }
            family_list.add(family);
        }

        // Read the city
        String city_name = reader.readLine();

        if (city_list.contains(city_name)) {
            int key = city_list.indexOf(city_name);
            String familyName = family_list.get(key);
            System.out.println(familyName);
        }

    }
}
