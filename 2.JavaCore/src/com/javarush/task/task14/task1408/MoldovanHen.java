package com.javarush.task.task14.task1408;

public class MoldovanHen extends Hen implements Country {
    public int getCountOfEggsPerMonth() {
        return 20;
    }

    public String getDescription(){
        String string =
                super.getDescription() +
                " Моя страна - " +
                        Country.MOLDOVA +
                        ". Я несу " +
                        getCountOfEggsPerMonth() +
                        " яиц в месяц.";
        return string;

    }
}
