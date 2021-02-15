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
//        Advertisement advertisement4 = new Advertisement(someContent, "Forth Video", 100, 10, 16 * 60); //16 min

        videos.add(advertisement1);
        videos.add(advertisement2);
        videos.add(advertisement3);
//        videos.add(advertisement4);

//        videos.add(new Advertisement(someContent, "Video 001", 500, 10, 5 * 60));   // 5 min
//        videos.add(new Advertisement(someContent, "Video 002", 700, 10, 7 * 60));   // 7 min
//        videos.add(new Advertisement(someContent, "Video 003", 500, 10, 5 * 60));   // 5 min
//        videos.add(new Advertisement(someContent, "Video 004", 1500, 10, 15 * 60)); // 15 min
//        videos.add(new Advertisement(someContent, "Video 005", 700, 10, 7 * 60));   // 7 min
//        videos.add(new Advertisement(someContent, "Video 006", 100, 10, 1 * 60));   // 1 min

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
