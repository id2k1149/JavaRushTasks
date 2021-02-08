package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

public class StatisticManager {
    private static StatisticManager instance;

    private StatisticManager() {}

    public static StatisticManager getInstance() {
        //если объект еще не создан
        if (instance == null) {
            //создать новый объект
            instance = new StatisticManager();
        }
        // вернуть ранее созданный объект
        return instance;
    }

    public void register(EventDataRow data) {}
}
