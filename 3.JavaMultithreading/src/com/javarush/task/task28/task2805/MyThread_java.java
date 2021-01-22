package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread_java extends Thread {
    private static volatile AtomicInteger priority = new AtomicInteger(1);

    private synchronized void correctPriority() {
        int newPriority = priority.getAndIncrement();
        newPriority = getThreadGroup() != null && newPriority > getThreadGroup().getMaxPriority() ? getThreadGroup().getMaxPriority() : newPriority;
        setPriority(newPriority);
        if (priority.intValue() > MAX_PRIORITY) {
            priority.set(1);
        }
    }

    public MyThread_java() {
        super();
        correctPriority();
    }

    public MyThread_java(Runnable target) {
        super(target);
        correctPriority();
    }

    public MyThread_java(ThreadGroup group, Runnable target) {
        super(group, target);
        correctPriority();
    }

    public MyThread_java(String name) {
        super(name);
        correctPriority();
    }

    public MyThread_java(ThreadGroup group, String name) {
        super(group, name);
        correctPriority();
    }

    public MyThread_java(Runnable target, String name) {
        super(target, name);
        correctPriority();
    }

    public MyThread_java(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        correctPriority();
    }

    public MyThread_java(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        correctPriority();
    }
}
