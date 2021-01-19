package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class University {
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

    public University(String name, int age) {
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
//        for (Student each: students) {
//            if (each.getAverageGrade() == averageGrade) return each;
//        }
//        return null;
        return students
                .stream()
                .filter(x -> averageGrade == x.getAverageGrade())
                .findFirst()
                .get();
    }

    public Student getStudentWithMaxAverageGrade() {
        //метод должен возвращать студента с максимальным средним балом
//        double max = 0;
//        int number = 0;
//        for (int i = 0; i < students.size(); i++) {
//            if (students.get(i).getAverageGrade() > max) {
//                max = students.get(i).getAverageGrade();
//                number = i;
//            }
//        }
//        return students.get(number);
        return Collections.max(students, Comparator.comparingDouble(Student::getAverageGrade));

    }

//    public void getStudentWithMinAverageGradeAndExpel() {
//    }

    public Student getStudentWithMinAverageGrade(){
        // метод должен возвратить студента с минимальным средним балом
//        double min = students.get(0).getAverageGrade();
//        int number = 0;
//        for (int i = 0; i < students.size(); i++) {
//            if (students.get(i).getAverageGrade() < min) {
//                min = students.get(i).getAverageGrade();
//                number = i;
//            }
//        }
//        return students.get(number);
        return Collections.min(students, Comparator.comparingDouble(Student::getAverageGrade));

    }

    public void expel(Student student) {
        students.remove(student);
    }
}