package com.javarush.task.task21.task2113;

public class Horse {
    String name;
    double speed;
    double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void move() {
        // случайное число от нуля до единицы
        // полученное с помощью вызова метод Math.random().
        double speed_change = Math.random();

        // значение поля distance должно увеличиваться на расстояние
        // пройденное за один "ход"(значение поля speed) умноженное
        // на случайное число
        distance += speed * speed_change;
    }

    public void print() {
        //  в методе print надо вывести на экран строку состоящую из точек
        //  и имени лошади. Количество точек равно distance, округленному
        //  (в меньшую сторону) до целого числа.
        int dots = (int) distance;
        for (int i = 0; i < dots; i++) {
            System.out.print(".");
        }
        System.out.println(name);

        // java
//        StringBuilder track = new StringBuilder();
//        for (int i = 0; i < (int) distance; i++) {
//            track.append(".");
//        }
//        System.out.println(track + name);

    }
}
