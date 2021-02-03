package com.javarush.task.task27.task2712.ad;

// хранилище рекламных роликов.
// https://javarush.ru/groups/posts/589-patternih-i-singleton--dlja-vsekh-kto-vpervihe-s-nimi-stolknulsja

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage instance;
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        Advertisement advertisement1 = new Advertisement(someContent, "First Video", 5000, 100, 3 * 60); // 3 min
        Advertisement advertisement2 = new Advertisement(someContent, "Second Video", 100, 10, 15 * 60); //15 min
        Advertisement advertisement3 = new Advertisement(someContent, "Third Video", 400, 2, 10 * 60); //10 min
        videos.add(advertisement1);
        videos.add(advertisement2);
        videos.add(advertisement3);
    }

    public static AdvertisementStorage getInstance() {
        //если объект еще не создан
        if(instance == null){
            //создать новый объект
            instance = new AdvertisementStorage();
        }
        // вернуть ранее созданный объект
        return instance;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }


}
