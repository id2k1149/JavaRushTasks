package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        List<Dish> newListOfDishes = new ArrayList<>();
        writeMessage("We have:");
        writeMessage(Dish.allDishesToString());
        writeMessage("Select your dish or write exit");
        while (true) {
            String clientChoice = null;
            try {
                clientChoice = readString();
                if (clientChoice.equals("exit")) {
                    break;
                }
                newListOfDishes.add(Dish.valueOf(clientChoice));
            } catch (IllegalArgumentException e) {
                writeMessage("такого блюда нет");
            }
        }
        return newListOfDishes;
    }
}
