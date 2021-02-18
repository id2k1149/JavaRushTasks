package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StatisticManager statisticManager = StatisticManager.getInstance();
                Set<Cook> cooks = statisticManager.getCooks();
                while (true) {
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e) {}
                    if (!orderQueue.isEmpty()) {
                        for (Cook cook : cooks) {
                            if (!cook.isBusy()) {
                                Order order = orderQueue.poll();
                                cook.startCookingOrder(order);
                            }
                        }
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


//    public OrderManager() {
//        StatisticManager statisticManager = StatisticManager.getInstance();
//        Thread daemonThread = new Thread( () ->
//        {
//            while (true) {
//                for (Cook cook : statisticManager.getCooks()) {
//                    if (!cook.isBusy() && !orderQueue.isEmpty()) {
//                        cook.startCookingOrder(orderQueue.poll());
//                    }
//
//                }
//
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        } );
//        daemonThread.setDaemon(true);
//        daemonThread.start();
//
//    }

    @Override
    // складывай все заказы, поступающие в метод update
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        // Inserts the specified element at the tail of this queue
        orderQueue.offer(order);
    }
}
