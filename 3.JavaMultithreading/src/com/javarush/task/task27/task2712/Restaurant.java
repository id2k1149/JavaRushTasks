package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();


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

        Waiter waiter = new Waiter();

        // должно быть создано 2 повара.
        int cooksNumber = 2;
        String[] cookName = {"Amigo", "Hans Noodles"};

        for (int i = 0; i < cooksNumber; i++) {
            Cook cook = new Cook(cookName[i]);

            // сразу после создания повара, установи ему константу
            // orderQueue в качестве значения для созданного поля.
            cook.setQueue(ORDER_QUEUE);

            // Повара должны быть зарегистрированы в множестве поваров в StatisticManager.
            // Task 22 - Из класса StatisticManager удали сет поваров,
            // его геттер и метод register(Cook cook).
//        StatisticManager.getInstance().register(cook);

            cook.addObserver(waiter);

            // создай и запусти трэды на основании тасок Cook.
            Thread threadCook = new Thread(cook);
            threadCook.setDaemon(true);
            threadCook.start();
        }

        // OrderManager, который будет Observer для планшетов.
        // only in task 21
//        OrderManager orderManager = new OrderManager();


        // должно быть создано 5 планшетов.
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i+1);

            // при создании планшета
            // установи ссылку на очередь orderQueue
            tablet.setQueue(ORDER_QUEUE);

            tablets.add(tablet);
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
