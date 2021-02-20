package com.javarush.task.task37.task3707;

import java.io.*;
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

    // Напиши свою реализацию метода Object clone(),
    // сделай поверхностное клонирование.
    public Object clone() {
        AmigoSet amigoClone;
        try {
            // Клонируй множество
            amigoClone = (AmigoSet) super.clone();
            // клонируй map
            amigoClone.map = (HashMap) map.clone();

        } catch (Exception e) {
            throw new InternalError();
        }
        return amigoClone;
    }

    // Реализуй свою логику сериализации (запись)
    // Т.к. наш map отмечен transient, сериализовать его придется самим
    private void writeObject (ObjectOutputStream outputStream) throws IOException {
        // Первым делом в сериализации  вызывается  defaultWriteObject
        outputStream.defaultWriteObject();

        // сериализуй сет
        Set<E> keySet = map.keySet();

        /*
        пишем capacity, loadFactor и размер map.
        Первые два достаем через рефлексию, они по другому не доступны
        Далее в цикле пишем keySet мапы
        Пишем keySet а не Entry, т.к. сериализовать values нет смысла,
        они же одинаковые.
         */

        Object[] array = keySet.toArray();
        outputStream.writeObject(array);

        // сериализуй loadFactor у объекта map,
        // воспользуйся утилитным классом HashMapReflectionHelper,
        // чтобы достать их.
        // этот класс нам подкинули с обновлением задачки.
        Object loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        outputStream.writeObject(loadFactor);

        // сериализуй capacity у объекта map,
        // воспользуйся утилитным классом HashMapReflectionHelper,
        // чтобы достать их.
        // этот класс нам подкинули с обновлением задачки.
        Object capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        outputStream.writeObject(capacity);

    }

    // Реализуй свою логику десериализации (чтение)
    // Т.к. наш map отмечен transient, десериализовать (восстанавливать) его придется самим.
    private void readObject (ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        // Первым делом в десериализации вызывается defaultReadObject
        inputStream.defaultReadObject();

        /* Создаем новый объект с capacity и loadFactor в конструкторе.
        В цикле читаем и добавляем в новый map восстановленные объекты E.
        В качестве value используем PRESENT.
         */

        Object[] keysArray = (Object[]) inputStream.readObject();
        float loadFactor = (float) inputStream.readObject();
        int capacity = (int) inputStream.readObject();

        map = new HashMap<>(capacity, loadFactor);

        for (Object e : keysArray) {
            map.put((E) e, PRESENT);
        }

    }




    // должен возвращать true в случае,
    // если новый элемент был успешно добавлен,
    // иначе - false
    @Override
    public boolean add(E e) {
        return map.put(e,PRESENT) == null;
    }

    // Метод iterator должен возвращать итератор для множества ключей поля map.
    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    // Метод contains должен возвращать true,
    // если map содержит анализируемый элемент, иначе - false
    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == null;
    }

    /*
    public static void main(String[] args) {
        AmigoSet<Integer> amigoSet = new AmigoSet<>();
        amigoSet.add(0);
        amigoSet.add(1);
        amigoSet.add(2);
        amigoSet.add(3);
        amigoSet.add(4);
        System.out.println("Before: " + amigoSet.size());

        AmigoSet<Integer> amigoSetCopy = null;
        String name = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task37/task3707/test.out";
        File file = new File(name);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
             ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            out.writeObject(amigoSet);
            amigoSetCopy = (AmigoSet<Integer>) in.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("After: " + amigoSet.size());
        System.out.println(amigoSet.hashCode());
        System.out.println(amigoSetCopy.hashCode());
    }
     */
}
