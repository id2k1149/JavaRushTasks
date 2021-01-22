package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private static int priority = Thread.MIN_PRIORITY;

    public MyThread() {
        if (priority > Thread.MAX_PRIORITY) {
            priority = Thread.MIN_PRIORITY;
        }
        setPriority(priority++);
    }

    public MyThread(ThreadGroup group, String name) {
        if (priority > Thread.MAX_PRIORITY) {
            priority = Thread.MIN_PRIORITY;
        }
        if (priority > group.getMaxPriority()) {
            setPriority(group.getMaxPriority());
        }
        else {
            setPriority(priority);
        }
        priority++;
    }

    public MyThread(Runnable target) {
        super(target);
        if (priority > Thread.MAX_PRIORITY) {
            priority = Thread.MIN_PRIORITY;
        }
        setPriority(priority++);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if (priority > Thread.MAX_PRIORITY) {
            priority = Thread.MIN_PRIORITY;
        }
        if (priority > group.getMaxPriority()) {
            setPriority(group.getMaxPriority());
        }
        else {
            setPriority(priority);
        }
        priority++;

    }

    public MyThread(String name) {
        super(name);
        if (priority > Thread.MAX_PRIORITY) {
            priority = Thread.MIN_PRIORITY;
        }
        setPriority(priority++);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        if (priority > Thread.MAX_PRIORITY) {
            priority = Thread.MIN_PRIORITY;
        }
        setPriority(priority++);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if (priority > Thread.MAX_PRIORITY) {
            priority = Thread.MIN_PRIORITY;
        }
        if (priority > group.getMaxPriority()) {
            setPriority(group.getMaxPriority());
        }
        else {
            setPriority(priority);
        }
        priority++;
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if (priority > Thread.MAX_PRIORITY) {
            priority = Thread.MIN_PRIORITY;
        }
        if (priority > group.getMaxPriority()) {
            setPriority(group.getMaxPriority());
        }
        else {
            setPriority(priority);
        }
        priority++;
    }

}
