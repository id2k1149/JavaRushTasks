package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;

/* 
CountDownLatch
https://www.youtube.com/watch?v=6dx3Ma4JeZE&list=PL786bPIlqEjRFPH8Z9IOwJWseG6Dq_Qxb&index=18
https://pro-java.ru/parallelizm-v-java/klass-countdownlatch-primery-realizacii-koda-v-java/
*/

public class Solution {
    private volatile boolean isWaitingForValue = true;

    CountDownLatch latch = new CountDownLatch(1);

    public void someMethod() throws InterruptedException {
        retrieveValue();
        latch.countDown();
        latch.await();

//        java
//        latch.await();
//        retrieveValue();
//        latch.countDown();

    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        try {
            solution.someMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
