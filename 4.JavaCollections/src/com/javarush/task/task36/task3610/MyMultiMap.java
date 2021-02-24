package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    // должен возвращать количество значений в нашей коллекции.
    public int size() {
        //напишите тут ваш код
        return values().size();
    }

    /*
    java
    @Override
    public int size() {
        int size = 0;
        for (List<V> list : map.values()) {
            size += list.size();
        }
        return size;
    }
     */

    @Override
    public V put(K key, V value) {
        List<V> valuesList = new ArrayList<>();

        // Если по ключу key значений еще нет - верни null.
        if ( ! map.containsKey(key)) {
            valuesList.add(value);
            map.put(key, valuesList);
            return null;
        }

        valuesList = map.get(key);
        // Метод должен возвращать значение последнего
        // добавленного элемента по ключу key
        V lastElement = valuesList.get(valuesList.size() - 1);

        // Если по такому ключу количество значений равняется repeatCount
        if (valuesList.size() == repeatCount) {
            // удали из листа в объекте map элемент с индексом ноль
            valuesList.remove(0);
        }

        // добавь в конец листа value.
        valuesList.add(value);
        return lastElement;
    }

    @Override
    // должен удалить элемент по ключу key.
    public V remove(Object key) {
        //напишите тут ваш код
        // Метод должен возвращать элемент, который ты удалил.
        V elementToRemove = null;

        // Если в мапе нет ключа key - верни null.
        if (!map.containsKey(key)) return null;

        // по ключу хранится лист
        List<V> list = map.get(key);

        // Если по этому ключу хранится несколько элементов
        // - должен удаляться элемент из листа с индексом ноль.
        if (list != null && list.size() > 0) {
            elementToRemove = list.get(0);
            list.remove(0);
        }

        // Если по какому-то ключу хранится лист размером ноль элементов
        // - удали такую пару ключ : значение.
        if (list.size() == 0) {
            map.remove(key);
        }

        return elementToRemove;
    }

    @Override
    //  должен вернуть сет всех ключей, которые есть в мапе map.
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    // должен вернуть ArrayList<V> всех значений.
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> allValues = new ArrayList<>();
        for (List<V> each : map.values()) {
            allValues.addAll(each);
        }
        return allValues;
    }

    @Override
    // должен вернуть true, если в мапе присутствует ключ
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    // должен вернуть true, если в мапе присутствует значение value,
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}