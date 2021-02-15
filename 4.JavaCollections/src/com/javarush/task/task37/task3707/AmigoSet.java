package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {

        // Вычисли свою Capacity по такой формуле:
        // максимальное из двух чисел:
        // 16 и округленного в большую сторону значения (collection.size()/.75f)
        int Capacity = Math.max(16, (int) Math.ceil(collection.size()/.75f));
        map = new HashMap<>(Capacity);
        for (E e: collection){
            map.put(e, PRESENT);
        }
    }

    // должен возвращать true в случае,
    // если новый элемент был успешно добавлен,
    // иначе - false
    @Override
    public boolean add(E e) {
        return map.put(e,PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
