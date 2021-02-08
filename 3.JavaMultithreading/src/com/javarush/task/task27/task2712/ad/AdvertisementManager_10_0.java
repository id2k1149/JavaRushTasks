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

public class AdvertisementManager_10_0 {
    final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds; //  время выполнения заказа поваром в секундах.

    public AdvertisementManager_10_0(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        List<Advertisement> storageList = storage.list();
        if (storageList.isEmpty()) {
            throw new NoVideoAvailableException();
        }
        else {
//            List<Advertisement> possiblePlayList = new ArrayList<>();
//
//            System.out.println(timeSeconds + " " + timeSeconds/60);
//            System.out.println("_________");

//            for (Advertisement each: storageList) {
//                System.out.println(each.getName() + " " + each.getDuration() + " $" + each.getAmountPerOneDisplaying());
//                if (each.getDuration() <= timeSeconds) {
//                    possiblePlayList.add(each);
//                }
//            }
//
//            if (possiblePlayList.isEmpty()) {
//                throw new NoVideoAvailableException();
//            }

            List<Advertisement> playList = new ArrayList<>();
            getBestPlayList(playList, 0);


            Collections.sort(playList, (Comparator.comparingInt(Advertisement::getDuration)));
            Collections.sort(playList, ((v1, v2) -> (int) (v1.getAmountPerOneDisplaying() - v2.getAmountPerOneDisplaying())));
            Collections.reverse(playList);



            for (Advertisement advertisement : playList) {
                ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                        + advertisement.getAmountPerOneDisplaying() + ", "
                        + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());
                advertisement.revalidate();
            }

        }

//        for (Advertisement ad : playList){
//
//            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
//                    ad.getName(), ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying()*1000/ad.getDuration()));
//        }
    }

    public void getBestPlayList(List<Advertisement> list, int index){
        if ((storage.list().size() > index)) {
            int totalDuration = 0;

            for (Advertisement each : list){
                totalDuration += each.getDuration();
            }
//            System.out.println("Total duration " + totalDuration + " dlina rolika v spiske " + storage.list().get(index).getDuration() + " index=" + index);
            if ((timeSeconds - totalDuration) >= storage.list().get(index).getDuration()){
                list.add(storage.list().get(index));
            }
            getBestPlayList(list, ++index);
        }
    }
}
