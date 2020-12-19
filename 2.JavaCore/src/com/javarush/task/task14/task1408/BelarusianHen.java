package com.javarush.task.task14.task1408;

public class BelarusianHen extends Hen implements Country {
    public int getCountOfEggsPerMonth() {
        return 10;
    }

    public String getDescription(){
        String string =
                super.getDescription() +
                " Моя страна - " +
                        Country.BELARUS +
                        ". Я несу " +
                        getCountOfEggsPerMonth() +
                        " яиц в месяц.";
        return string;

    }
}
