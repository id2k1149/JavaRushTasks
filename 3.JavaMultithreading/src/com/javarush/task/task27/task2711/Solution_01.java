package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;

/* 
CountDownLatch
*/

public class Solution_01 implements Runnable {
    private final Object lock = new Object();
    private volatile boolean isWaitingForValue = true;

    CountDownLatch latch = new CountDownLatch(1);

    public void someMethod() throws InterruptedException {
        retrieveValue();
        latch.countDown();
        latch.await();
    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }

    public static void main(String[] args) {
        //создаем объект
        Solution_01 solution = new Solution_01();
        //создаем поток
        Thread t1 = new Thread(solution);
        t1.start();

        //просто считаем до пяти.
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + "...");
        }

        synchronized (solution.lock) {
            solution.isWaitingForValue = false;
            solution.lock.notify();
        }
    }

    @Override
    public void run() {
        try {
            someMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
