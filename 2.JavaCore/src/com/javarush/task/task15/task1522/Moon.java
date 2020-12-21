package com.javarush.task.task15.task1522;

public class Moon implements Planet {
    private static Moon instance;

    private Moon() {
    }

    public static Moon getInstance(){
        //если объект еще не создан
        if (instance == null) {
            //создать новый объект
            instance = new Moon();
        }
        // вернуть ранее созданный объект
        return instance;
    }
}
