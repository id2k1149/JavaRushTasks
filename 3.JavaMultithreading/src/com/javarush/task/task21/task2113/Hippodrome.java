package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    // список всех лошадей
    private List<Horse> horses;

    // метод (getter) getHorses возвращающий список horses
    public List<Horse> getHorses() {
        return horses;
    }

    // конструктор класса Hippodrome с одним параметром типа List.
    public Hippodrome(List<Horse> list) {
        this.horses = list;
    }

    // статическое поле game типа Hippodrome
    static Hippodrome game;


    // Метод move будет управлять движением всех лошадей.
    void move() {
        // В методе move класса Hippodrome
        // должен быть вызван метод move  класса Horse по одному разу
        // для каждой лошади (каждого элемента списка horses).
        for (Horse each: horses) {
            each.move();
        }
    }

    // Метод print будет отрисовывать всех лошадей на экран.
    void print() {

    }

    // метод run будет управлять всем этим.
    void run() throws InterruptedException {
        // Это будет наш забег.
        for (int i = 0; i < 100 ; i++) {
            move();
            print();

            // Чтобы весь цикл не отработал за долю секунды
            Thread.sleep(200);
        }
    }

    public static void main(String[] args) {
        // инициализировать поле game
//        game = new Hippodrome(new ArrayList());

        // Создать список лошадей (horses)
        List<Horse> horses = new ArrayList<>();

        // Создать три объекта "лошадь"
        Horse horse_1 = new Horse("name_1", 3, 0);
        Horse horse_2 = new Horse("name_2", 3, 0);
        Horse horse_3 = new Horse("name_3", 3, 0);

        // Добавить созданных лошадей в список horses
        horses.add(horse_1);
        horses.add(horse_2);
        horses.add(horse_3);

        // Создать объект типа Hippodrome,
        // используя созданный ранее список лошадей,
        // и сохранить этот объект в поле game.
//        game.horses.addAll(horses);
        game = new Hippodrome(horses);

//        for (Horse h : game.getHorses()){
//            System.out.println("" + h.getName() + h.getSpeed() + h.getDistance());
//        }



    }
}
