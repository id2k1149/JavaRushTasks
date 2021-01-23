package com.javarush.task.task28.task2811;

import java.util.LinkedHashMap;

/* 
ReentrantReadWriteLock
https://tproger.ru/translations/java8-concurrency-tutorial-2/    раздел "ReadWriteLock"
*/

public class Solution {
    public static void main(String[] args) {
        ReadWriteMap<Integer, String> linkedSafeMap = new ReadWriteMap<>(new LinkedHashMap<>());
    }
}
