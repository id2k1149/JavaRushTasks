package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime() {
        int time = 0;
        for (Dish each: dishes) {
            time += each.getDuration();
        }
        return time;
    }

    public boolean isEmpty() {
        return dishes.size() == 0;
    }

    @Override
    public String toString() {
        String yourOrder = "";
        if (dishes.size() != 0 && dishes != null) {
            yourOrder = "Your order: " + dishes + " of " + tablet +
            ", cooking time " + getTotalCookingTime() + "min";
        }
        return yourOrder;
    }
}
