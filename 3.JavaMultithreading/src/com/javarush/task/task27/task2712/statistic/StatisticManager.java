package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

//синглтон
public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

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

    // будет регистрировать событие в хранилище.
    public void register(EventDataRow data) {
        //5. В StatisticManager создадим публичный метод void register(EventDataRow data),
        // который будет регистрировать событие в хранилище.
        //Хранилище связано 1 к 1 с менеджером, т.е. один менеджер и одно хранилище на приложение.
        //К хранилищу может доступиться только StatisticManager. Поэтому...
        //Из вышеперечисленного следует, что хранилище должно быть приватным иннер классом.
        //Назовем его StatisticStorage.
        if (data != null) statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    //посчитает общую прибыль за каждый день.
    public Map<String, Long> getAdvertisementProfit(){
        Map<String, Long> result = new HashMap();
        List<EventDataRow> rows = statisticStorage.get(EventType.SELECTED_VIDEOS);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        long total = 0l;
        for (EventDataRow row : rows) {
            VideoSelectedEventDataRow dataRow = (VideoSelectedEventDataRow) row;
            String date = dateFormat.format(dataRow.getDate());
            if (!result.containsKey(date)) {
                result.put(date, 0l);
            }
            total += dataRow.getAmount();
            result.put(date, result.get(date) + dataRow.getAmount());
        }

        // В конце вывести слово Total и общую сумму.
        result.put("Total", total);

        return result;
    }

    //посчитает общую продолжительность работы для каждого повара отдельно
    public Map<String, Map<String, Integer>> getCookWorkloading() {
        Map<String, Map<String, Integer>> result = new HashMap(); //name, time
        List<EventDataRow> rows = statisticStorage.get(EventType.COOKED_ORDER);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (EventDataRow row : rows) {
            CookedOrderEventDataRow dataRow = (CookedOrderEventDataRow) row;
            String date = dateFormat.format(dataRow.getDate());

            if (!result.containsKey(date)) {
                result.put(date, new HashMap<String, Integer>());
            }

            Map<String, Integer> cookMap = result.get(date);
            String cookName = dataRow.getCookName();

            if (!cookMap.containsKey(cookName)) {
                cookMap.put(cookName, 0);
            }

            Integer totalTime = cookMap.get(cookName);
            cookMap.put(cookName, totalTime + dataRow.getTime());
        }

        return result;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        public StatisticStorage() {
            EventType[] eventType = EventType.values();
            for (EventType each: eventType) {
                storage.put(each, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            // добавляет поле data типа EventDataRow
            // согласно одному из трех EventDataRow
            if (storage.containsKey(data.getType()))
            { storage.get(data.getType()).add(data);}
        }

        // чтобы получить доступ к данным.
        private List<EventDataRow> get(EventType type) {
            return storage.get(type);
        }
    }
}
