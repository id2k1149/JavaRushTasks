package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

public class Restaurant {

    public static void main(String[] args) {
        Tablet tablet = new Tablet(5);
        int numberOfOrders = 4;
        for (int i = 0; i < numberOfOrders; i++) {
            tablet.createOrder();
        }
    }
}
