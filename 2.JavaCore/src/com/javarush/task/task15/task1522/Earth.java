package com.javarush.task.task15.task1522;

public class Earth implements Planet {

    private static Earth instance;

    private Earth() {
    }

    public static Earth getInstance(){
        //если объект еще не создан
        if (instance == null) {
            //создать новый объект
            instance = new Earth();
        }
        // вернуть ранее созданный объект
        return instance;
    }
}
