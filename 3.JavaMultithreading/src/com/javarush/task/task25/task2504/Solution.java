package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/

import java.util.ArrayList;

public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (Thread each: threads) {
//            System.out.println(state);
            switch (each.getState()) {
                case NEW:
                    each.start();
                    break;
                case RUNNABLE:
                    each.isInterrupted();
                    break;
                case BLOCKED:
                case TIMED_WAITING:
                case WAITING:
                    each.interrupt();
                    break;
                case TERMINATED:
                    System.out.println(each.getPriority());
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Thread thread_1 = new Thread();
        Thread thread_2 = new Thread();

        while (true) {
            processThreads(thread_1, thread_2);
            if (thread_1.getState().toString().equals("TERMINATED")) break;
        }
    }
}
