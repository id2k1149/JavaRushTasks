package com.javarush.task.task27.task2712a.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    public Tablet getTablet() {
        return tablet;
    }

    public List<Dish> getDishes() {
        return dishes;
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

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        String yourOrder = "";
        if (dishes.size() != 0 && dishes != null) {
            ConsoleHelper.writeMessage("");
            yourOrder = "Your order: " + dishes + " of " + tablet +
            ", cooking time " + getTotalCookingTime() + "min";
        }
        return yourOrder;
    }
}
