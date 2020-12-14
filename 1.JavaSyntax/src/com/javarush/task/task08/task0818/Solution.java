package com.javarush.task.task08.task0818;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static Map<String, Integer> createMap() {
        //напишите тут ваш код
        Map<String, Integer> map = new HashMap<>();
        map.put("Смирнов", 100);
        map.put("Иванов", 200);
        map.put("Кузнецов", 300);
        map.put("Соколов", 400);
        map.put("Попов", 500);
        map.put("Лебедев", 600);
        map.put("Новиков", 700);
        map.put("Козлов", 800);
        map.put("Морозов", 900);
        map.put("Петров", 1000);
        return map;
    }

    public static void removeItemFromMap(Map<String, Integer> map) {
        //напишите тут ваш код
        Map<String, Integer> copy = new HashMap<>(map);

        for (Map.Entry<String, Integer> pair : copy.entrySet())
        {
            String key = pair.getKey();
            Integer value = pair.getValue();
            if (value < 500) {
                map.remove(key);
            }
        }
    }

    public static void main(String[] args) {
//        Map<String, Integer> map = createMap();
//        removeItemFromMap(map);
//        for (Map.Entry<String, Integer> pair : map.entrySet())
//        {
//            String key = pair.getKey();                      //ключ
//            Integer value = pair.getValue();                  //значение
//            System.out.println(key + " : " + value);
//        }
    }
}