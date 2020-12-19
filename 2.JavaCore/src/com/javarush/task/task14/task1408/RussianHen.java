package com.javarush.task.task14.task1408;

public class RussianHen extends Hen implements Country {
    public int getCountOfEggsPerMonth() {
        return 30;
    }

    public String getDescription(){
        String string =
                super.getDescription() +
                " Моя страна - " +
                Country.RUSSIA +
                ". Я несу " +
                getCountOfEggsPerMonth() +
                " яиц в месяц.";
        return string;

    }
}
