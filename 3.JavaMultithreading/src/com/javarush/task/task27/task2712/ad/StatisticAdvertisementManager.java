package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;


//синглтон
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();;
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {}

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    // Активным роликом считается тот, у которого есть минимум один доступный показ.
    public List<Advertisement> getActiveVideoList() {
        List<Advertisement> activeVideo = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() > 0) {
                activeVideo.add(advertisement);
            }
        }
        return activeVideo;
    }

    // Неактивным роликом считается тот, у которого количество показов равно 0.
    public List<Advertisement> getArchivedVideoList() {
        List<Advertisement> archivedVideo = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() == 0) {
                archivedVideo.add(advertisement);
            }
        }
        return archivedVideo;
    }


//    public List<Advertisement> getVideoSet(boolean isActive) {
//        List<Advertisement> result = new ArrayList<>();
//        for (Advertisement advertisement : advertisementStorage.list()) {
//            if (!isActive ^ advertisement.isActive()) {
//                result.add(advertisement);
//            }
//        }
//        return result;
//    }

}
