package com.javarush.task.task27.task2712.ad;

/*
у каждого планшета будет свой объект менеджера,
который будет подбирать оптимальный набор роликов
и их последовательность для каждого заказа.
 */

import com.javarush.task.task27.task2712.ConsoleHelper;

public class AdvertisementManager {
    final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        ConsoleHelper.writeMessage("calling processVideos method");
    }
}
