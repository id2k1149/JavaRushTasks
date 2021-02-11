package com.javarush.task.task27.task2712.ad;


// (Рекламное объявление)
public class Advertisement {
    private Object content; // - видео
    private String name; // - имя/название
    private long initialAmount; // - начальная сумма, стоимость рекламы в копейках.
    private int hits; // - количество оплаченных показов
    private int duration; // - продолжительность в секундах

    private long amountPerOneDisplaying; //стоимости одного показа рекламного объявления в копейках


    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        if (hits != 0) amountPerOneDisplaying = initialAmount / hits;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void setAmountPerOneDisplaying(long amountPerOneDisplaying) {
        this.amountPerOneDisplaying = amountPerOneDisplaying;
    }

    public void revalidate() {
//        if (hits < 1) throw new UnsupportedOperationException();
        if (hits < 1) throw new NoVideoAvailableException();
        hits--;
    }

    public int getHits() {
        return hits;
    }


}
