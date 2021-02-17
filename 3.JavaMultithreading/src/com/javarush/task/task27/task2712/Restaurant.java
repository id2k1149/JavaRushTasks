package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {

//        Cook cook_1 = new Cook("Amigo");
//        Waiter waiter_1 = new Waiter();
//        Tablet tablet_0 = new Tablet(0);
//        tablet_0.addObserver(cook_1);
//        cook_1.addObserver(waiter_1);
//        tablet_0.createOrder();

//        Cook cook_2 = new Cook("Hans Noodles");
//        Waiter waiter_2 = new Waiter();
//        Tablet tablet_2 = new Tablet(2);
//        tablet_2.addObserver(cook_2);
//        cook_2.addObserver(waiter_2);
//        tablet_2.createOrder();

        // должно быть создано 2 повара.
        Cook cook_1 = new Cook("Amigo");
        Cook cook_2 = new Cook("Hans Noodles");

        // Повара должны быть зарегистрированы в множестве поваров в StatisticManager.
        StatisticManager.getInstance().register(cook_1);
        StatisticManager.getInstance().register(cook_2);

        Waiter waiter = new Waiter();
        cook_1.addObserver(waiter);
        cook_2.addObserver(waiter);

        // OrderManager, который будет Observer для планшетов.
        OrderManager orderManager = new OrderManager();

        // должно быть создано 5 планшетов.
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i+1);
            tablets.add(tablet);
            tablet.addObserver(orderManager);
        }

        // создан и запущен тред на основе RandomOrderGeneratorTask.
        RandomOrderGeneratorTask task = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(task);
        thread.start();

        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }

        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();

        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
