package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/

public class Solution_java extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution_java(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        //init handler here
        this.handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < t.getName().length(); i++) {
                    builder.append('*');
                }
                System.out.println(e.getMessage().replaceAll(t.getName(), builder.toString()));
            }
        };
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
//        new Solution(new TimerTask() {
//            @Override
//            public void run() {
//                throw new UnsupportedOperationException();
//            }
//        }).run();

//        new Thread(new Solution(new TimerTask() {
//            @Override
//            public void run() {
//                throw new UnsupportedOperationException();
//            }
//        })).run();
//
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                throw new ArithmeticException();
//            }
//        };
//        Solution solution = new Solution(timerTask);
//        solution.run();
//
        Thread thread = new Thread(new Solution(new TimerTask() {
            @Override
            public void run() {
                System.out.println(5/0);
            }
        }));
        thread.start();

    }
}
