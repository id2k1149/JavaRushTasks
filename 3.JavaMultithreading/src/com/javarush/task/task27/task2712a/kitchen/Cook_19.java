package com.javarush.task.task27.task2712a.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook_19 extends Observable implements Observer {
    private String name;

    public Cook_19(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {

        Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - " + order);

        setChanged();
        notifyObservers(order);

        // Повар во время приготовления еды должен генерировать
        // соответствующее событие.
        CookedOrderEventDataRow row = new CookedOrderEventDataRow(
                order.getTablet().toString(),
                name,
                order.getTotalCookingTime() * 60,
                order.getDishes());
        StatisticManager.getInstance().register(row);

    }

}
