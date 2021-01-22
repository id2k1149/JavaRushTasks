package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread_01 extends Thread {
    private static final AtomicInteger number = new AtomicInteger(1);

    public MyThread_01() {
        if (number.get() == 11) {
            number.set(1);
        }
        setPriority(number.getAndIncrement());
    }

    public MyThread_01(ThreadGroup group, String name) {
        if (number.get() == group.getMaxPriority() + 1) {
            number.set(1);
        }
        setPriority(number.getAndIncrement());
    }

    public MyThread_01(Runnable target) {
        super(target);
    }

    public MyThread_01(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public MyThread_01(String name) {
        super(name);
    }

    public MyThread_01(Runnable target, String name) {
        super(target, name);
    }

    public MyThread_01(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public MyThread_01(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

}
