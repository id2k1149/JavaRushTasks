package com.javarush.task.task12.task1231;

/* 
Ненужные абстракции

- По условию задачи объект Horse создать можно, а он обозначен как abstract.
oбъекты абстрактных классов создавать нельзя.
Значит Horse не может быть abstract.
- По условию задачи объект Pegasus создать можно,
значит он тоже не может быть абстрактным.
если класс содержит хотя бы один абстрактный метод,
то весь класс будет абстрактным.
Значит метод в классе Pegasus не должен быть abstract.
- В классе SwimmingPegasus у нас есть не реализованный метод public void swim();
Не реализован т.к. после круглых скобок стоит точка с запятой,
а не фигурные скобки. Делаем вывод, что метод является абстрактным,
а значит его необходимо обозначить ключевым словом abstract.
Но теперь наш класс содержит абстрактный метод и значит класс SwimmingPegasus
тоже является абстрактным и его необходимо обозначить ключевым словом abstract.

*/



public class Solution {

    public static void main(String[] args) {
        Horse horse = new Pegasus();
        horse.run();
    }

    public interface CanFly {
        void fly();
    }

    public static class Horse {
        public void run() {
        }
    }

    public static class Pegasus extends Horse implements CanFly {
        public void fly() {
        }
    }

    public static abstract class SwimmingPegasus extends Pegasus {
        public abstract void swim();
    }

}
