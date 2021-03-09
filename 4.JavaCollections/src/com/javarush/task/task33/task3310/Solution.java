package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        long elementsNumber = 500;
        // стратегия #1
        testStrategy(new HashMapStorageStrategy(), elementsNumber);
        // стратегия #2
        testStrategy(new OurHashMapStorageStrategy(), elementsNumber);
        // стратегия #3
        testStrategy(new FileStorageStrategy(), elementsNumber);
        // стратегия #4
        testStrategy(new OurHashBiMapStorageStrategy(), elementsNumber);
        // стратегия #5
        testStrategy(new HashBiMapStorageStrategy(), elementsNumber);
        // стратегия #6
        testStrategy(new DualHashBidiMapStorageStrategy(), elementsNumber);

    }

    // метод должен для переданного множества строк
    // возвращать множество идентификаторов.
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();

        for (String each: strings) {
            ids.add(shortener.getId(each));
        }
        return ids;
    }

    // 	Метод должен возвращать множество строк,
    // 	которое соответствует переданному множеству идентификаторов.
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();

        for (Long each : keys) {
            strings.add(shortener.getString(each));
        }
        return strings;
    }

    // Метод будет тестировать работу переданной стратегии
    // на определенном количестве элементов elementsNumber.
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {

        // Метод должен выводить на экран имя класса стратегии.
        Helper.printMessage(strategy.getClass().getSimpleName());

        // Метод должен генерировать тестовое множество строк
        // используя метод generateRandomString класса Helper
        // (количество элементов должно быть равно параметру elementsNumber).
        Set<String> randomStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            randomStrings.add(Helper.generateRandomString());
        }

        // Метод должен создавать объект типа Shortener
        // используя переданную стратегию.
        Shortener shortener = new Shortener(strategy);

        // Метод должен замерять количество времени выполнения
        // getIds используя объекты типа Date.
        Date start_1 = new Date();
        Set<Long> keys = getIds(shortener, randomStrings);
        Date finish_1 = new Date();
        Helper.printMessage(String.valueOf(finish_1.getTime() - start_1.getTime()));

        // Метод должен замерять количество времени выполнения
        // getStrings используя объекты типа Date.
        Date start_2 = new Date();
        Set<String> strings = getStrings(shortener, keys);
        Date finish_2 = new Date();
        Helper.printMessage(String.valueOf(finish_2.getTime() - start_2.getTime()));

        // Метод должен Сравнивать одинаковое ли содержимое множества строк,
        // которое было сгенерировано и множества, которое было возвращено
        // методом getStrings.
        if (randomStrings.equals(strings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
        Helper.printMessage("-------------");
    }
}
