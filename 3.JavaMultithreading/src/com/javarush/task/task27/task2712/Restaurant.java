package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {

        Cook cook_1 = new Cook("Amigo");
        Waiter waiter_1 = new Waiter();
        Tablet tablet_0 = new Tablet(0);
        tablet_0.addObserver(cook_1);
        cook_1.addObserver(waiter_1);
        tablet_0.createOrder();

//        Cook cook_2 = new Cook("Hans Noodles");
//        Waiter waiter_2 = new Waiter();
//        Tablet tablet_2 = new Tablet(2);
//        tablet_2.addObserver(cook_2);
//        cook_2.addObserver(waiter_2);
//        tablet_2.createOrder();

//        Cook cook_2 = new Cook("Hans Noodles");
//        Waiter waiter_2 = new Waiter();
//        cook_2.addObserver(waiter_2);
//
//        List<Tablet> tablets = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            tablets.add(new Tablet(i+1));
//            if (i % 2 == 0) tablets.get(i).addObserver(cook_1);
//            else tablets.get(i).addObserver(cook_2);
//        }
//        RandomOrderGeneratorTask orders = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
//        orders.run();

        DirectorTablet directorTablet = new DirectorTablet();

        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
