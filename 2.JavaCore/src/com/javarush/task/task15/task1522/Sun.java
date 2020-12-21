package com.javarush.task.task15.task1522;

public class Sun implements Planet {
    private static Sun instance;

    private Sun() {
    }

    public static Sun getInstance(){
        //если объект еще не создан
        if (instance == null) {
            //создать новый объект
            instance = new Sun();
        }
        // вернуть ранее созданный объект
        return instance;
    }
}
