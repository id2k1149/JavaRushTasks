package com.javarush.task.task05.task0502a;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        //напишите тут ваш код
        int ageScore = Integer.compare(this.age, anotherCat.age);
        System.out.println("ageScore = " + ageScore);
        int weightScore = Integer.compare(this.weight, anotherCat.weight);
        System.out.println("weightScore = " + weightScore);
        int strengthScore = Integer.compare(this.strength, anotherCat.strength);
        System.out.println("strengthScore = " + strengthScore);

        int score = ageScore + weightScore + strengthScore;
        System.out.println("score = " + score);
        return score > 0;
    }

    public static void main(String[] args) {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();

        cat1.weight = 10;
        cat1.age = 2;
        cat1.strength = 30;

        cat2.weight = 30;
        cat2.age = 10;
        cat2.strength = 2;

        System.out.println(cat1.fight(cat2));
        System.out.println(cat2.fight(cat1));


    }
}
