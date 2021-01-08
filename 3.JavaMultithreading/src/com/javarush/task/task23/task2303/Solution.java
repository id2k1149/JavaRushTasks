package com.javarush.task.task23.task2303;

/* 
Запрети создание экземпляров класса
*/

public class Solution {

    // Абстрактный класса разрешает наследование,
    // но запрещает создание объектов класса.
    //А финальный класс наоборот запрещает наследование,
    // но разрешает создание объектов класса.
    public static abstract class Listener {
        public void onMouseDown(int x, int y) {
            // Do something when the mouse down event occurs
        }

        public void onMouseUp(int x, int y) {
            // Do something when the mouse up event occurs
        }
    }

    public static void main(String[] args) {
//        Listener listener = new Listener();
//        System.out.println(listener instanceof Listener);
    }
}
