package com.javarush.task.task27.task2712.ad;

/*
у каждого планшета будет свой объект менеджера,
который будет подбирать оптимальный набор роликов
и их последовательность для каждого заказа.
 */

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.List;

public class AdvertisementManager {
    final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
//        ConsoleHelper.writeMessage("calling processVideos method");
        List<Advertisement> playList = storage.list();

        if (playList.isEmpty()) {
            throw new NoVideoAvailableException();
        }

//        for (Advertisement ad : playList){
//
//            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
//                    ad.getName(), ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying()*1000/ad.getDuration()));
//        }
    }
}
