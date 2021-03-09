package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Provider;

public class Aggregator_10 {
    public static void main(String[] args) {
        Provider provider = new Provider(new HHStrategy());
        Controller_10 controller = new Controller_10(provider);
        controller.scan();
    }
}
