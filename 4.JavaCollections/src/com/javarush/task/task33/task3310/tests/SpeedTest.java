package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    // должен возвращать время в миллисекундах необходимое
    // для получения идентификаторов для всех строк из strings.
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        long start = System.currentTimeMillis();

        for (String string : strings) {
            ids.add(shortener.getId(string));
        }

        long finish = System.currentTimeMillis();

        return finish - start;
    }

    // должен возвращать время в миллисекундах необходимое
    // для получения строк для всех идентификаторов из ids.
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        long start = System.currentTimeMillis();

        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }

        long finish = System.currentTimeMillis();

        return finish - start;
    }

    @Test
    // В методе testHashMapStorage должно быть выполнено сравнение
    // времени получения множества ключей и множества значений
    // для стратегий HashMapStorageStrategy и HashBiMapStorageStrategy.
    public void testHashMapStorage() {
        // Создавать два объекта типа Shortener,
        // один на базе HashMapStorageStrategy,
        // второй на базе HashBiMapStorageStrategy.
        StorageStrategy hashMapStrategy = new HashMapStorageStrategy();
        StorageStrategy hashBiMapStrategy = new HashBiMapStorageStrategy();
        // Назовем их shortener1 и shortener2.
        Shortener shortener1 = new Shortener(hashMapStrategy);
        Shortener shortener2 = new Shortener(hashBiMapStrategy);

        // Генерировать с помощью Helper 10000 строк
        // и помещать их в сет со строками,
        // назовем его origStrings.
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10_000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        // Получать время получения идентификаторов для origStrings
        // (вызывать метод getTimeToGetIds для shortener1,
        // а затем для shortener2).
        Set<Long> idsSet1 = new HashSet<>();
        Set<Long> idsSet2 = new HashSet<>();
        Long shortenerIdsTime1 = getTimeToGetIds(shortener1, origStrings, idsSet1);
        Long shortenerIdsTime2 = getTimeToGetIds(shortener2, origStrings, idsSet2);

        // Проверять с помощью junit, что время,
        // полученное в предыдущем пункте для shortener1 больше,
        // чем для shortener2.
        Assert.assertTrue(shortenerIdsTime1 > shortenerIdsTime2);

        // Получать время получения строк
        // (вызывать метод getTimeToGetStrings для shortener1 и shortener2).
        Long shortenerStringsTime1 = getTimeToGetStrings(shortener1, idsSet1, new HashSet<>());
        Long shortenerStringsTime2 = getTimeToGetStrings(shortener2, idsSet2, new HashSet<>());

        // Проверять с помощью junit, что время, полученное в предыдущем пункте
        // для shortener1 примерно равно времени для shortener2.
        // Используй метод assertEquals(float expected, float actual, float delta).
        // В качестве delta можно использовать 30, этого вполне достаточно для наших экспериментов.
        Assert.assertEquals(shortenerStringsTime1, shortenerStringsTime2, 30);
    }
}
