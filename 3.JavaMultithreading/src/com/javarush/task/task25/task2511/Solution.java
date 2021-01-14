package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/

public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        //init handler here
        class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                String s = e.getMessage();
                s = s.replace("Blah", " ").trim();
                s = s.replace("blah-blah-blah", " ").trim();
//                System.out.println(s);
                String stars = "";
                for (int i = 0; i < s.length(); i++) {
                    stars += "*";
                }
                System.out.println("Blah " + stars + " blah-blah-blah");
            }
        }
        this.handler = new MyUncaughtExceptionHandler();
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
//        })).start();

    }
}
