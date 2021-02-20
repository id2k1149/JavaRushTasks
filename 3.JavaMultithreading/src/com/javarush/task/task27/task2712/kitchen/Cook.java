package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Restaurant;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Cook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage(name + " starts cooking - " + order);

        // Повар во время приготовления еды должен генерировать
        // соответствующее событие.
        CookedOrderEventDataRow row = new CookedOrderEventDataRow(
                order.getTablet().toString(),
                name,
                order.getTotalCookingTime() * 60,
                order.getDishes());
        StatisticManager.getInstance().register(row);

        // задержка при приготовлении блюда
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {

        }

        setChanged();
        notifyObservers(order);
        busy = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {}
            if (!isBusy()) {
                if (!queue.isEmpty()) {

                    //  вместо poll(), надо использовать take()
//                    Order order = queue.poll();

                    Order order = null;
                    try {
                        order = queue.take();
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }

                    assert order != null;
                    startCookingOrder(order);

                }
            }
        }
    }
}
