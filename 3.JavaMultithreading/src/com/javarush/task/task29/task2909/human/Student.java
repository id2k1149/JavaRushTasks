package com.javarush.task.task29.task2909.human;

import java.util.Date;

public class Student extends UniversityPerson {

    private double averageGrade;
    private Date beginningOfSession;
    private Date endOfSession;
    private int course;

    public Student(String name, int age, double averageGrade) {
        super(name, age);
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
    }

    public void live() {
        learn();
    }

    public void learn() {
    }

    public void incAverageGrade(double delta) {
        double grade = getAverageGrade();
        grade += delta;
        setAverageGrade(grade);
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }

//    public void setEndOfSession(int day, int month, int year) {
//        endOfSession = new Date(year, month, day);
//    }

    public void setEndOfSession(Date date) {
        endOfSession = date;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getCourse() {
        return course;
    }

    @Override
    public String getPosition() {
        return "Студент";
    }
}