package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//синглтон
public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();

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

    public void register(EventDataRow data) {
        //5. В StatisticManager создадим публичный метод void register(EventDataRow data),
        // который будет регистрировать событие в хранилище.
        //Хранилище связано 1 к 1 с менеджером, т.е. один менеджер и одно хранилище на приложение.
        //К хранилищу может доступиться только StatisticManager. Поэтому...
        //Из вышеперечисленного следует, что хранилище должно быть приватным иннер классом.
        //Назовем его StatisticStorage.
        statisticStorage.put(data);
    }

    private class StatisticStorage {
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            EventType[] eventType = EventType.values();
            for (EventType each: eventType) {
                storage.put(each, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data); // добавляет поле data типа EventDataRow согласно одному из трех EventDataRow
        }
    }
}
