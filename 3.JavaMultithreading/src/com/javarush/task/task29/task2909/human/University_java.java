package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class University_java {
    String name;
    int age;
    private List<Student> students = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public University_java(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student each: students) {
            if (each.getAverageGrade() == averageGrade) return each;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //метод должен возвращать студента с максимальным средним балом
        if (students.size() > 0) {
            Student studentWithMaxAverageGrade = students.get(0);
            double maxAverageGrade = studentWithMaxAverageGrade.getAverageGrade();

            for (Student student : students) {
                if (student.getAverageGrade() > maxAverageGrade) {
                    studentWithMaxAverageGrade = student;
                    maxAverageGrade = student.getAverageGrade();
                }
            }
            return studentWithMaxAverageGrade;
        }
        return null;
    }

    public Student getStudentWithMinAverageGrade(){
        // метод должен возвратить студента с минимальным средним балом
        if (students.size() > 0) {
            Student studentWithMinAverageGrade = students.get(0);
            double maxAverageGrade = studentWithMinAverageGrade.getAverageGrade();

            for (Student student : students) {
                if (student.getAverageGrade() < maxAverageGrade) {
                    studentWithMinAverageGrade = student;
                    maxAverageGrade = student.getAverageGrade();
                }
            }
            return studentWithMinAverageGrade;
        }
        return null;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}