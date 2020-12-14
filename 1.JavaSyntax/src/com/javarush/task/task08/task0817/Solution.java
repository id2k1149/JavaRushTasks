package com.javarush.task.task08.task0817;

import java.util.*;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static Map<String, String> createMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<>();
        map.put("Смирнов", "Роман");
        map.put("Иванов", "Дмитрий");
        map.put("Кузнецов", "Дмитрий");
        map.put("Соколов", "Роман");
        map.put("Попов", "Максим");
        map.put("Лебедев", "Дмитрий");
        map.put("Новиков", "Александр");
        map.put("Козлов", "Михаил");
        map.put("Морозов", "Илья");
        map.put("Петров", "Дмитрий");
        return map;

    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        //напишите тут ваш код
        Set<String> set = new HashSet<>();
        Set<String> final_set = new HashSet<>();

        for (String name : map.values())
        {
            if (set.add(name)) set.add(name);
            else final_set.add(name);
        }

        for (String name: final_set) {
            removeItemFromMapByValue(map, name);
        }

    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        Map<String, String> copy = new HashMap<>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value)) {
                map.remove(pair.getKey());
            }
        }
    }

    public static void removeTheFirstNameDuplicates_JavaRush(Map<String, String> map) {
        Map<String, String> copy = new HashMap<>(map);

        for (String name : copy.values()) {
            int count = 0;
            for (String nameTmp : map.values()) {
                if (nameTmp.equals(name)) {
                    count++;
                }
            }
            if (count > 1) {
                removeItemFromMapByValue(map, name);
            }
        }
    }

    public static void main(String[] args) {
//        Map<String, String> map = createMap();
//        removeTheFirstNameDuplicates(map);
//        for (Map.Entry<String, String> pair : map.entrySet())
//        {
//            String key = pair.getKey();                      //ключ
//            String value = pair.getValue();                  //значение
//            System.out.println(key + ":" + value);
//        }
    }
}
