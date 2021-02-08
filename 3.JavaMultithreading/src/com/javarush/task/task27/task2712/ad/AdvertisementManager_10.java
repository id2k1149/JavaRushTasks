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

public class AdvertisementManager_10 {
    final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds; //  время выполнения заказа поваром в секундах.
    private List<Advertisement> playList; // список видео из доступных,
    // просмотр которых обеспечивает максимальную выгоду

    public AdvertisementManager_10(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }

        List<Advertisement> storageList = storage.list();
        getPlayList(storageList);

//        playList
//        .sort(Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying)
//        .thenComparingInt(Advertisement::getDuration)
//        .reversed());

        Collections.sort(playList, (Comparator.comparingInt(Advertisement::getDuration)));
        Collections.sort(playList, ((v1, v2) -> (int) (v1.getAmountPerOneDisplaying() - v2.getAmountPerOneDisplaying())));
        Collections.reverse(playList);

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

    //проверка, является ли данный набор лучшим решением задачи
    private void checkList(List<Advertisement> list) {

        if (playList == null) {
            if (getTotalTime(list) <= timeSeconds) {
                playList = list;
            }

            // сумма денег, полученная от показов,
            // должна быть максимальной из всех возможных вариантов
        } else if (getTotalTime(list) <= timeSeconds) {
            if (getTotalAmount(list) > getTotalAmount(playList)) {
                playList = list;
            }

            // если существует несколько вариантов набора видео-роликов с одинаковой суммой денег,
        } else if (getTotalTime(list) <= timeSeconds && getTotalAmount(playList) == getTotalAmount(list)) {
            // выбрать тот вариант, у которого суммарное время максимальное;
            if (getTotalTime(list) > getTotalTime(playList)) {
                playList = list;
            }

            // если суммарное время у вариантов с одинаковой суммой денег, одинаковое,
        } else if (getTotalTime(list) <= timeSeconds && getTotalAmount(playList) == getTotalAmount(list) && getTotalTime(list) == getTotalTime(playList)) {
            // то выбрать вариант с минимальным количеством роликов;
            if (list.size() < playList.size()) {
                playList = list;
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
