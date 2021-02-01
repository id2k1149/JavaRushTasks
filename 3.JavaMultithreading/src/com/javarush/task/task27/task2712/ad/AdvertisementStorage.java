package com.javarush.task.task27.task2712.ad;

// хранилище рекламных роликов.
// https://javarush.ru/groups/posts/589-patternih-i-singleton--dlja-vsekh-kto-vpervihe-s-nimi-stolknulsja

public class AdvertisementStorage {
    private static AdvertisementStorage instance;

    private AdvertisementStorage() {
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
}
