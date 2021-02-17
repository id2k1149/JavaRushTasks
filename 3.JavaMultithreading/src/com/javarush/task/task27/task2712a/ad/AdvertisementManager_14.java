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
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager_14 {
    final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds; //  время выполнения заказа поваром в секундах.
    private List<Advertisement> playList; // список видео из доступных,
    // просмотр которых обеспечивает максимальную выгоду

    public AdvertisementManager_14(int timeSeconds) {
        this.timeSeconds = timeSeconds;
        playList = new ArrayList<>();
    }

    public void processVideos() {
        if (storage.list().isEmpty()) {
            NoAvailableVideoEventDataRow noEventDataRow = new NoAvailableVideoEventDataRow(timeSeconds);
            StatisticManager.getInstance().register(noEventDataRow);

            throw new NoVideoAvailableException();
        }

        List<Advertisement> storageList = storage.list();

        // В набор должны отбираться только ролики с положительным числом показов.
        for (Advertisement each: storageList) {
            if (each.getHits() <= 0 ) storageList.remove(each);
        }

        getPlayList(storageList);

        playList.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());  //сортировка по уменьшению стоимости показа одного ролика
            }
        }.thenComparing(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {        //сортировка по увеличению стоимости показа одной секунды рекламного ролика в тысячных частях копейки
                return o2.getDuration() - o1.getDuration();
            }
        }));

        //4. Перед отображением списка видео должно быть зарегистрировано событие "видео выбрано".
        //конструктор: public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration)
        VideoSelectedEventDataRow eventVideoSelected = new VideoSelectedEventDataRow(playList, getTotalAmount(playList), getTotalTime(playList));

        //5. Зарегистрируй событие "видео выбрано" перед отображением рекламы пользователю.
        StatisticManager.getInstance().register(eventVideoSelected);

        // First Video is displaying... 50, 277
        // где First Video - название рекламного ролика
        //где 50 - стоимость показа одного рекламного ролика в копейках
        //где 277 - стоимость показа одной секунды
        for (Advertisement advertisement : playList) {
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

            // Уменьшать количество показов.
            advertisement.revalidate();
        }
    }

    //создание всех наборов перестановок значений
    /*
    метод перебирает все возможные наборы (перестановки) предметов для рюкзака.
     Данный метод рекурсивный.
     Сначала в наборе все N предметов,
     затем, при переходе вглубь на один уровень рекурсии,
     один предмет удаляется.
     Выход из рекурсии произойдёт, когда список предметов станет пустым.
     */
    private void getPlayList(List<Advertisement> list) {
        if (list.size() > 0) {
            checkList(list);
        }

        for (int i = 0; i < list.size(); i++) {
            List<Advertisement> newList = new ArrayList<>(list);
            newList.remove(i);
            getPlayList(newList);
        }
    }

    //проверка листа является ли данный набор лучшим решением задачи
    private void checkList(List<Advertisement> list){
        if (playList == null){
            if (getTotalTime(list) <= timeSeconds){
                playList = list;
            }
        }
        else{
            // сумма денег, полученная от показов,
            // должна быть максимальной из всех возможных вариантов
            if (getTotalTime(list) <= timeSeconds && getTotalAmount(list) > getTotalAmount(playList)){
                playList = list;

        // 1.Если существует несколько вариантов набора видео-роликов с одинаковой суммой денег,
        // полученной от показов, то должен быть выбран вариант с максимальным суммарным временем.
        } else if (getTotalTime(list) <= timeSeconds && getTotalAmount(list) == getTotalAmount(playList)){
                if (getTotalTime(list) > getTotalTime(playList)){
                    playList = list;
        // 2.Если существует несколько вариантов набора видео-роликов с одинаковой суммой денег
        // и одинаковым суммарным временем, то должен быть выбран вариант с минимальным количеством роликов
        }else if (getTotalTime(list) == getTotalTime(playList)){
                    if (list.size() < playList.size()){
                        playList = list;
                    }
                }
            }
        }
    }

    //вычисляет общее время
    private int getTotalTime(List<Advertisement> list) {
        int totalTime = 0;
        for (Advertisement each : list) {
            totalTime += each.getDuration();
        }
        return totalTime;
    }

    //вычисляет общую стоимость
    private long getTotalAmount(List<Advertisement> list) {
        long totalAmount = 0;
        for (Advertisement each : list) {
            totalAmount += each.getAmountPerOneDisplaying();
        }
        return totalAmount;
    }
}
