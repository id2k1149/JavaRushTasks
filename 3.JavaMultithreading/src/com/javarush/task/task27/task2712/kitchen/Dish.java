package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;
import java.util.List;

public enum Dish {
    FISH(25),
    STEAK(30),
    SOUP(15),
    JUICE(5),
    WATER(3);

    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
        List<Dish> dishValues = Arrays.asList(Dish.values());
        StringBuilder allDishes = new StringBuilder();
        allDishes.append(dishValues.get(0));
        for (int i = 1; i < dishValues.size(); i++) {
            allDishes.append(", ");
            allDishes.append(dishValues.get(i));
        }
        return allDishes.toString();
    }
}


