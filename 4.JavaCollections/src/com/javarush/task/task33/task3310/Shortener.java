package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {
    //последнее значение идентификатора,
    // которое было использовано при добавлении новой строки в хранилище
    private Long lastId = 0L;

    // поле в котором будет храниться стратегия хранения данных.
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string) {
        if (!storageStrategy.containsValue(string)) {
            lastId++;
            storageStrategy.put(lastId, string);
        }
        return storageStrategy.getKey(string);

    }

    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
