package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {
    //последнее значение идентификатора,
    // которое было использовано при добавлении новой строки в хранилище
    private Long lastId = 0L;

    // поле в котором будет храниться стратегия хранения данных.
    private StorageStrategy storageStrategy;

    // конструктор, который принимает StorageStrategy и
    // инициализирует соответствующее поле класса.
    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    // Проверить есть ли переданное значение в хранилище,
    // если есть - вернуть его ключ.
    // Предусмотреть возможность вызова из разных потоков
    public synchronized Long getId(String string) {
        // Если преданного значения нет в хранилище, то:
        if (!storageStrategy.containsValue(string)) {
            // Увеличить значение lastId на единицу;
            lastId++;
            // Добавить в хранилище новую пару ключ-значение
            // (новое значение lastId и переданную строку);
            storageStrategy.put(lastId, string);
            // Вернуть новое значение lastId.
            return lastId;
        }
        return storageStrategy.getKey(string);
    }

    // метод должен вернуть строку по заданному идентификатору (ключу).
    // Предусмотреть возможность вызова из разных потоков
    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
