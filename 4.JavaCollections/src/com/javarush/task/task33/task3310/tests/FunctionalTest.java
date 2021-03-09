package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        // Создавать три строки.
        // Текст 1 и 3 строк должен быть одинаковым.
        String string1 = "Test #1";
        String string2 = "Test #2";
        String string3 = "Test #1";

        // Получать и сохранять идентификаторы
        // для всех трех строк с помощью shortener
        Long id1 = shortener.getId(string1);
        Long id2 = shortener.getId(string2);
        Long id3 = shortener.getId(string3);

        // Проверять, что идентификатор для 2 строки
        // не равен идентификатору для 1 и 3 строк.
        Assert.assertNotEquals(string2, string1);
        Assert.assertNotEquals(string2, string3);
        // Проверять, что идентификаторы для 1 и 3 строк равны.
        Assert.assertEquals(string1, string3);

        // Получать три строки по трем идентификаторам с помощью shortener.
        String newString1 = shortener.getString(id1);
        String newString2 = shortener.getString(id2);
        String newString3 = shortener.getString(id3);

        // Проверять, что new строки эквивалентны оригинальным.
        Assert.assertEquals(string1, newString1);
        Assert.assertEquals(string2, newString2);
        Assert.assertEquals(string3, newString3);
    }

    // Каждый тест должен иметь аннотацию @Test
    @Test
    public void testHashMapStorageStrategy() {
        // создавать подходящую стратегию
        StorageStrategy strategy = new HashMapStorageStrategy();
        // создавать объект класса Shortener на базе этой стратегии
        Shortener shortener = new Shortener(strategy);
        // вызывать метод testStorage для объекта класса Shortener
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        StorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        StorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        StorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        StorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        StorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

}
