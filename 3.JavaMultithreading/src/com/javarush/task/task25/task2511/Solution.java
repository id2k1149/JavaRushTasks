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
        class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
//                System.out.println(t.getName());
                    String stars = "";
                    for (int i = 0; i < t.getName().length(); i++) {
                        stars += "*";
                    }

                    String[] e_message = e.getMessage().split(t.getName());
                    String result = "";
                    for (int i = 0; i < e_message.length; ++i) {
                        result += e_message[i];
                        if (i != e_message.length - 1) {
                            result += stars;
                        }
                    }
                    System.out.println(result);
            }
        }
        this.handler = new UncaughtExceptionHandler();
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

//        TimerTask timerTask = new TimerTask() {  //original
//            @Override
//            public void run() {
//                throw new ArithmeticException();
//            }
//        };
//        Solution solution = new Solution(timerTask);
//        solution.run();

//        Thread thread = new Thread(new Solution(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println(5/0);
//            }
//        }));
//        thread.start();

    }
}
