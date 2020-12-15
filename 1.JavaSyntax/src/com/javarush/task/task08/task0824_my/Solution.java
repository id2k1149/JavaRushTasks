package com.javarush.task.task08.task0824_my;

import java.util.ArrayList;

/* 
Собираем семейство
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код

        Human father_of_father = new Human("дедушка Вася_1", true, 99);
        Human father_of_mother = new Human("дедушка Вася_2", true, 89);
        Human mother_of_father = new Human("бабушка Мурка_1", false, 98);
        Human mother_of_mother = new Human("бабушка Мурка_2", false, 88);
        Human father = new Human("папа Котофей", true, 40, father_of_father, mother_of_father);
        Human mother = new Human("мама Василиса", false, 30, father_of_mother, mother_of_mother);
        Human child_1 = new Human("сын Мурчик_1", true, 20, father, mother);
        Human child_2 = new Human("сын Мурчик_2", true, 10, father, mother);
        Human child_3 = new Human("дочь Пушинка", false, 10, father, mother);

        System.out.println(father_of_father);
        System.out.println(father_of_mother);
        System.out.println(mother_of_father);
        System.out.println(mother_of_mother);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(child_1);
        System.out.println(child_2);
        System.out.println(child_3);
    }

    public static class Human {
        //напишите тут ваш код
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<>();

        public Human(String name, boolean sex, int age){
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, Human father, Human mother){
            this.name = name;
            this.sex = sex;
            this.age = age;
            if (father != null) father.children.add(this);
            if (mother != null) mother.children.add(this);
        }


        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }
}
