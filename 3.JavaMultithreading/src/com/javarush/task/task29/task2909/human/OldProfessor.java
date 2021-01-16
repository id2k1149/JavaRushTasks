package com.javarush.task.task29.task2909.human;

// previous class Professor
public class OldProfessor extends Teacher {
    OldProfessor(String name, int age, int numberOfStudents) {
        super(name, age, numberOfStudents);
    }

    public void live() {
        teach();
    }

}