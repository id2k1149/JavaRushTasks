package com.javarush.task.task04.task0406;

/* 
Программа учета имен
*/

public class Cat {
    private String fullName;

    public void setName(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;

        //напишите тут ваш код
        this.fullName = fullName;


    }

    public static void main(String[] args) {
        Cat cat1 = new Cat();
        cat1.setName("first_1", "last_1");
        System.out.println(cat1.fullName);

        Cat cat2 = new Cat();
        cat2.setName("first_2", "last_2");
        System.out.println(cat2.fullName);

    }
}
