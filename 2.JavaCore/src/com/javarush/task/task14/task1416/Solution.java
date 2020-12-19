package com.javarush.task.task14.task1416;

/* 
Исправление ошибок Ӏ Java Core: 4 уровень, 8 лекция
*/

public class Solution {
    public static void main(String[] args) {
        Swimmable animal = new Orca();
        animal.swim();
        animal = new Whale();
        animal.swim();
        animal = new Otter();
        animal.swim();
    }

    public static void test(Swimmable animal) {
        animal.swim();
    }

    interface Walkable {
        void walk();
    }

    interface Swimmable {
        default void swim() {
            System.out.println(this.getClass().getSimpleName() + " is swimming");
        }
    }

    static abstract class OceanAnimal implements Swimmable {
    }

    static class Orca extends OceanAnimal {
    }

    static class Whale extends OceanAnimal {
    }


    static class Otter implements Swimmable, Walkable {
        @Override
        public void walk() {

        }
    }
}
