package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        // Напишите тут ваши переменные и конструкторы
        private String name;
        private boolean sex;
        private int age;
        private int weight;
        private int height;
        private boolean children;

        public Human(String name,
                     boolean sex,
                     int age,
                     int weight,
                     int height,
                     boolean children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.children = children;
        }

        public Human(String name,
                     boolean sex,
                     int age,
                     int weight,
                     int height) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        public Human(String name,
                     boolean sex,
                     int age,
                     int weight) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.weight = weight;
        }

        public Human(String name,
                     boolean sex,
                     int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name,
                     boolean sex) {
            this.name = name;
            this.sex = sex;
        }

        public Human(String name) {
            this.name = name;
        }

        public Human(String name,
                     int age,
                     int weight,
                     int height) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        public Human(String name,
                     int weight,
                     int height) {
            this.name = name;
            this.weight = weight;
            this.height = height;
        }

        public Human(String name,
                     int height) {
            this.name = name;
            this.height = height;
        }

        public Human(int weight,
                     int height) {
            this.weight = weight;
            this.height = height;
        }
    }
}
