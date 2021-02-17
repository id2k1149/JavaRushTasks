package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.*;

public class DirectorTablet {

    // какую сумму заработали на рекламе, сгруппировать по дням;
    public void printAdvertisementProfit() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map<String, Long> profitMap = statisticManager.getAdvertisementProfit();
        ArrayList<String> list = new ArrayList(profitMap.keySet());
        // в убывающем порядке даты
        Collections.sort(list);

        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("какую сумму заработали на рекламе по дням");
        for (String key : list) {
            double amount = 1.0 * profitMap.get(key) / 100;
            System.out.println(key + " - " + String.format(Locale.ENGLISH, "%.2f", amount));
        }
    }

    // загрузка (рабочее время) повара, сгруппировать по дням;
    public void printCookWorkloading() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map<String, Map<String, Integer>> cookWorkloadingMap = statisticManager.getCookWorkloading();
        ArrayList<String> list = new ArrayList(cookWorkloadingMap.keySet());
        // в убывающем порядке даты,
        Collections.sort(list);

        for (String key : list) {
            Map<String, Integer> cookMap = cookWorkloadingMap.get(key);
            System.out.println(key);

            // Поваров сортировать по имени
            ArrayList<String> cookNames = new ArrayList(cookMap.keySet());
            Collections.sort(cookNames);
            for (String cookName : cookNames) {
                System.out.println(cookName + " - " + ((cookMap.get(cookName) + 59) / 60) + " min");
            }

            System.out.println();
        }
    }

    // список активных роликов и оставшееся количество показов по каждому;
    public void printActiveVideoSet() {
        StatisticAdvertisementManager statisticAdvertisementManager = StatisticAdvertisementManager.getInstance();
        List<Advertisement> advertisements = statisticAdvertisementManager.getActiveVideoList();
        Collections.sort(advertisements, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("список активных роликов и оставшееся количество показов");
        for (Advertisement advertisement : advertisements) {
            ConsoleHelper.writeMessage(advertisement.getName() + " - " + advertisement.getHits());
        }
    }

    // список неактивных роликов (с оставшемся количеством показов равным нулю).
    public void printArchivedVideoSet() {
        StatisticAdvertisementManager statisticAdvertisementManager = StatisticAdvertisementManager.getInstance();

        List<Advertisement> advertisements = statisticAdvertisementManager.getArchivedVideoList();
        Collections.sort(advertisements, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("список неактивных роликов (с оставшемся количеством показов равным 0)");
        for (Advertisement advertisement : advertisements) {
            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }


//    public void printActiveVideoSet() {
//        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getVideoSet(true);
//        Collections.sort(videoSet, new Comparator<Advertisement>() {
//            @Override
//            public int compare(Advertisement o1, Advertisement o2) {
//                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
//            }
//        });
//
//        for (Advertisement advertisement : videoSet) {
//            System.out.println(advertisement.getName() + " - " + advertisement.getHits());
//        }
//    }

//    public void printArchivedVideoSet() {
//        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getVideoSet(false);
//        Collections.sort(videoSet, new Comparator<Advertisement>() {
//            @Override
//            public int compare(Advertisement o1, Advertisement o2) {
//                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
//            }
//        });
//
//        for (Advertisement advertisement : videoSet) {
//            System.out.println(advertisement.getName());
//        }
//    }
}
