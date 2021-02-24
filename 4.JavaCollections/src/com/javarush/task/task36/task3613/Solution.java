package com.javarush.task.task36.task3613;

import java.util.concurrent.SynchronousQueue;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 10 лекция
Описание класса:
1. Пакет этого класса java.util.concurrent.
2. Реализует интерфейс BlockingQueue.
3. Используется при работе с трэдами.
4. Является блокирующей очередью, в которой каждая операция добавления должна ждать соответствующей операции удаления в другом потоке и наоборот.
5. Не имеет никакой внутренней емкости, даже емкости в один элемент.
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        return SynchronousQueue.class;
    }
}
