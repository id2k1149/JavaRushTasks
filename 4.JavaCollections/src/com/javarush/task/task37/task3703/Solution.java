package com.javarush.task.task37.task3703;

import java.util.concurrent.ConcurrentSkipListMap;

/* 
Найти класс по описанию Ӏ Java Collections: 7 уровень, 6 лекция
1. Реализует интерфейс Map.
2. Используется при работе с трэдами.
3. Является неблокирующей версией списка с пропусками,
   который адаптирован для хеш-таблицы.
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        return ConcurrentSkipListMap.class;
    }
}
