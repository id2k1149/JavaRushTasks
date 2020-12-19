package com.javarush.task.task14.task1408;

public class UkrainianHen extends Hen implements Country {
    public int getCountOfEggsPerMonth() {
        return 40;
    }

    public String getDescription(){
        String string =
                super.getDescription() +
                " Моя страна - " +
                        Country.UKRAINE +
                        ". Я несу " +
                        getCountOfEggsPerMonth() +
                        " яиц в месяц.";
        return string;

    }
}
