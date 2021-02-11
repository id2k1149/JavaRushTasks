package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.Observable;



public class Restaurant {

    public static void main(String[] args) {
        Cook cook = new Cook("Amigo");

        Tablet tablet1 = new Tablet(1);
        tablet1.addObserver(cook);

        Waiter waiter = new Waiter();
        cook.addObserver(waiter);

        int numberOfOrders = 1;
        for (int i = 0; i < numberOfOrders; i++) {
            tablet1.createOrder();
        }
    }
}
