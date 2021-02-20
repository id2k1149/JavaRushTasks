package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        dishes = new ArrayList<>();
        List<Dish> dishValues = Arrays.asList(Dish.values());

        int randomTotalDishes = (int) (Math.random() * dishValues.size() * 2);

        for (int i = 0; i < randomTotalDishes; i++) {
            dishes.add(Dish.values()[(int) (Math.random() * dishValues.size())]);
        }
    }
}
