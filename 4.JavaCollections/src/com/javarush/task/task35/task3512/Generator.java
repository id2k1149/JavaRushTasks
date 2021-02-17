package com.javarush.task.task35.task3512;

public class Generator<T> {
    private Class<T> clazz;


    public Generator(Class<T> clazz) {
        this.clazz = clazz;
    }

    T newInstance() {
        T newInstanceT = null;
        try {
            newInstanceT = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return newInstanceT;
    }
}
