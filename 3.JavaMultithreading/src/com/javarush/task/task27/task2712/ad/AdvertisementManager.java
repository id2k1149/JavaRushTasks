package com.javarush.task.task27.task2712.ad;

/*
у каждого планшета будет свой объект менеджера,
который будет подбирать оптимальный набор роликов
и их последовательность для каждого заказа.
 */

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds; //  время выполнения заказа поваром в секундах.

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        List<Advertisement> storageList = storage.list();
        if (storageList.isEmpty()) {
            throw new NoVideoAvailableException();
        }
        else {
            List<Advertisement> playList_v1 = new ArrayList<>();

            Collections.sort(storageList, (Comparator.comparingInt(Advertisement::getDuration)));
            Collections.sort(storageList, ((v1, v2) -> (int) (v1.getAmountPerOneDisplaying() - v2.getAmountPerOneDisplaying())));
            Collections.reverse(storageList);

            for (Advertisement each: storageList) {
                System.out.println(each.getDuration() + " " + each.getAmountPerOneDisplaying());
                if (each.getDuration() < timeSeconds) {
                    playList_v1.add(each);
                }
            }


        }

//        for (Advertisement ad : playList){
//
//            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
//                    ad.getName(), ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying()*1000/ad.getDuration()));
//        }
    }
}
