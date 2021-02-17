package com.javarush.task.task27.task2712a.ad;

/*
у каждого планшета будет свой объект менеджера,
который будет подбирать оптимальный набор роликов
и их последовательность для каждого заказа.
 */

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.AdvertisementStorage;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementManager_10_1 {
    final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds; //  время выполнения заказа поваром в секундах.
    private List<Advertisement> playList;
    private long bestAmount;
    private int bestDuration = Integer.MAX_VALUE;

    public AdvertisementManager_10_1(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }
        List<Advertisement> advertisements = storage.list().stream()
                .filter(advertisement -> advertisement.getHits() > 0)
                .collect(Collectors.toList());

        getPlayList(advertisements);

        playList
        .sort(Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying)
        .thenComparingInt(Advertisement::getDuration)
        .reversed());

//        playList.forEach(advertisement -> {
//            ConsoleHelper.writeMessage(advertisement.toString());
//            advertisement.revalidate();
//        });

        for (Advertisement advertisement : playList) {
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());
            advertisement.revalidate();
        }
    }


    //вычисляет общее время
    private int getDuration(List<Advertisement> advertisements) {
        return advertisements.stream().mapToInt(Advertisement::getDuration).sum();
    }

    //вычисляет общую стоимость
    private long getAmount(List<Advertisement> advertisements) {
        return advertisements.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
    }

    //проверка, является ли данный набор лучшим решением задачи
    private void checkSet(List<Advertisement> advertisements) {
        int newDuration = getDuration(advertisements);
        long newAmount = getAmount(advertisements);

        if (playList == null && newDuration <= timeSeconds) {
            playList = advertisements;
            bestAmount = newAmount;
            bestDuration = newDuration;

        } else {
            if (newDuration <= timeSeconds) {

                if (newAmount > bestAmount) {
                    playList = advertisements;
                    bestAmount = newAmount;
                    bestDuration = newDuration;
                }

                if (newAmount == bestAmount) {
                    if (newDuration > bestDuration) {
                        playList = advertisements;
                        bestAmount = newAmount;
                        bestDuration = newDuration;
                    }

                    if (newDuration == bestDuration && advertisements.size() < playList.size()) {
                        playList = advertisements;
                        bestAmount = newAmount;
                        bestDuration = newDuration;
                    }
                }

            }
        }
    }

    //создание всех наборов перестановок значений
    private void getPlayList(List<Advertisement> advertisements) {
        if (advertisements.size() > 0) {
            checkSet(advertisements);
        }
        for (int i = 0; i < advertisements.size(); i++) {
            List<Advertisement> newAdvertisements = new ArrayList<>(advertisements);
            newAdvertisements.remove(i);
            getPlayList(newAdvertisements);
        }
    }
}
