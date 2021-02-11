package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public void update(Observable o, Object order) {
        Order orderToCook = (Order) order;
        ConsoleHelper.writeMessage("Start cooking - " + order);

        // Повар во время приготовления еды должен генерировать соответствующее событие.
        CookedOrderEventDataRow cookedOrderEventDataRow =
                new CookedOrderEventDataRow(
                o.toString(),
                name,
                orderToCook.getTotalCookingTime()*60,
                orderToCook.getDishes());
        StatisticManager.getInstance().register(cookedOrderEventDataRow);
        setChanged();
        notifyObservers(order);
    }
}
