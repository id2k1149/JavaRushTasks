package com.javarush.task.task28.task2813;

import java.util.concurrent.Callable;

public class FactorialCalculator_java implements Callable {
    private final int number;

    public FactorialCalculator_java(int number) {
        this.number = number;
    }

    @Override
    public Long call() {
        try {
            return factorial(number);
        } catch (InterruptedException ex) {
            System.out.println("INTERRUPTED BY " + ex.getMessage());
        }
        return 0L;
    }

    public long factorial(int number) throws InterruptedException {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be greater than zero");
        }
        long result = 1;
        while (number > 1) {
            Thread.sleep(1);
            result = result * number;
            number--;
        }
        return result;
    }
}