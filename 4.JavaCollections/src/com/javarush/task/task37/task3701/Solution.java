package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* 
Круговой итератор
*/

// Класс Solution должен быть потомком класса ArrayList.
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    // Метод iterator() в классе Solution
    // должен возвращать объект типа RoundIterator.
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T> {
        Iterator<T> roundIterator = Solution.super.iterator();

        @Override
        public boolean hasNext() {
            if (size() == 0) {
                return false;
            }
            if (!roundIterator.hasNext()) {
                roundIterator = Solution.super.iterator();
            }
            return true;
        }

        @Override
        public T next() {
            return roundIterator.next();
        }

        @Override
        // Метод remove без параметров должен удалять текущий элемент.
        public void remove() {
            roundIterator.remove();
        }
    }
}
