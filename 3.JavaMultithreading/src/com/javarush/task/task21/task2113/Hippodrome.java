package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    // список всех лошадей
    private List<Horse> horses;

    // метод getHorses возвращающий список horses
    public List<Horse> getHorses() {
        return horses;
    }

    // конструктор класса Hippodrome с одним параметром типа List.
    public Hippodrome(List<Horse> list) {
        this.horses = list;
    }



    
    public static void main(String[] args) {
//        Horse horse_1 = new Horse();
//        Horse horse_2 = new Horse();
//        List<Horse> horses = new ArrayList<>();
//        horses.add(horse_1);
//        horses.add(horse_2);
//
//        Hippodrome hippodrome = new Hippodrome(horses);
//        System.out.println(hippodrome.horses.get(0).toString());
    }



}
