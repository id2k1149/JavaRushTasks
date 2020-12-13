package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        Human grandDadOne = new Human("дедушка Вася_1", true, 99);
        Human grandDadTwo = new Human("дедушка Вася_2", true, 89);
        Human grandMomOne = new Human("бабушка Мурка_1", false, 98);
        Human grandMomTwo = new Human("бабушка Мурка_2", false, 88);
        Human dad = new Human("папа Котофей", true, 40, grandDadOne, grandMomOne);
        Human mom = new Human("мама Василиса", false, 30, grandDadTwo, grandMomTwo);
        Human sonOne = new Human("сын Мурчик_1", true, 20, dad, mom);
        Human sonTwo = new Human("сын Мурчик_2", true, 10, dad, mom);
        Human  daughter = new Human("дочь Пушинка", false, 10, dad, mom);

        System.out.println(grandDadOne);
        System.out.println(grandDadTwo);
        System.out.println(grandMomOne);
        System.out.println(grandMomTwo);
        System.out.println(dad);
        System.out.println(mom);
        System.out.println(sonOne);
        System.out.println(sonTwo);
        System.out.println(daughter);
    }

    public static class Human {
        // напишите тут ваш код
        String name;
        boolean sex;
        int age;
        Human father;
        Human mother;

        public Human(String name, boolean sex, int age){
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, Human father, Human mother){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }


        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null) {
                text += ", отец: " + this.father.name;
            }

            if (this.mother != null) {
                text += ", мать: " + this.mother.name;
            }

            return text;
        }
    }
}