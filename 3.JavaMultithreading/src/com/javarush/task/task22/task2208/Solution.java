package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
//        map.put("name", "Ivanov");
//        map.put("country", "Ukraine");
//        map.put("city", "Kiev");
        map.put("name", null);
        map.put("country", null);
        map.put("city", null);
        map.put("age", null);
//        for (Map.Entry entry: map.entrySet()) {
//            System.out.println(entry.toString());
//        }
        String string = getQuery(map);
        System.out.println(string);

    }

    public static String getQuery(Map<String, String> params) {
        String result = "";
        StringBuilder string = new StringBuilder(result);


        for (Map.Entry each: params.entrySet()) {
            if (each.getValue() != null) {
                string.append(each.getKey());
                string.append(" = '");
                string.append(each.getValue());
                string.append("' and ");
            }
        }

        if (string.length() > 0) {
            for (int i = 0; i < 4; i++) {
                string.deleteCharAt(string.length()-1);
            }
        }

        result = string.toString().trim();
        return result;
    }
}
