package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
    }

    // Метод newArrayList должен возвращать ArrayList,
    // который содержит переданные в метод объекты.
    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        ArrayList<T> resultList = new ArrayList<>();
        for (T element : elements) {
            resultList.add(element);
        }
        return resultList;
    }

//    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код (option 2)
//        return new ArrayList<>(Arrays.asList(elements));
//    }

    // Метод newHashSet должен возвращать HashSet,
    // который содержит переданные в метод объекты.
    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet<T> resultSet = new HashSet<>();

        for (T element : elements) {
            resultSet.add(element);
        }

        return resultSet;
    }

    // 	Метод newHashMap должен возвращать HashMap,
    // 	который содержит переданные в метод ключи и значения.
    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        // напишите тут ваш код
        // нужно проверить чтобы списки ключей и значений совпадали по размерам,
        // в противном случае кинь IllegalArgumentException.
        HashMap<K, V> resultMap = new HashMap<>();
        if (keys.size() == values.size()) {
            for (int i = 0; i < keys.size(); i++) {
                resultMap.put(keys.get(i), values.get(i));
            }
        } else {
            throw new IllegalArgumentException();
        }
        return resultMap;
    }
}
