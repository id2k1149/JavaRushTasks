package com.javarush.task.task05.task0526_my;

/* 
Мужчина и женщина
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Man man1 = new Man("name_1", 18, "US");
        System.out.println(man1.name + " " + man1.age + " " + man1.address);

        Man man2 = new Man("name_2", 19, "US");
        System.out.println(man2.name + " " + man2.age + " " + man2.address);

        Woman woman1 = new Woman("name_3", 20, "US");
        System.out.println(woman1.name + " " + woman1.age + " " + woman1.address);

        Woman woman2 = new Woman("name_4", 21, "US");
        System.out.println(woman2.name + " " + woman2.age + " " + woman2.address);
    }

    //напишите тут ваш код
    public static class Man {
        public String name;
        public int age;
        public String address;

        public Man(String name, int age, String address){
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }

    public static class Woman {
        public String name;
        public int age;
        public String address;

        public Woman(String name, int age, String address){
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }
}
