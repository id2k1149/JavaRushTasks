package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //add your code here

        // Верни объект из cache для ключа key.
        V value = cache.get(key);

        // Если объекта не существует в кэше,
        // то добавьте в кэш новый экземпляр используя рефлексию
        // публичный конструктор Cache с одним параметром типа K
        if (value == null) {
            Constructor<V> constructor = clazz.getDeclaredConstructor(key.getClass());
            value = constructor.newInstance(key);

            put(value);

        }
        return value;

    }

    public boolean put(V obj) {
        //add your code here
        K key;

        // Используя рефлексию получи ссылку на метод,
        // метод K getKey() с любым модификатором доступа.
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");

            // Используя рефлексию разреши к нему доступ.
            method.setAccessible(true);

            // Используя рефлексию вызови метод getKey у объекта obj,
            // таким образом ты получишь ключ key.
            key = (K) method.invoke(obj);

        } catch (Exception ignore) {
            return false;
        }

        // Добавь в кэш пару <key, obj>.
        cache.put(key, obj);

        // Верни true, если метод отработал корректно
        return true;
    }

    public int size() {
        return cache.size();
    }
}
