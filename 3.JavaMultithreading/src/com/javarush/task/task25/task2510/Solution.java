package com.javarush.task.task25.task2510;

/* 
Поживем - увидим
*/

public class Solution extends Thread  {
    Thread thread;



    public Solution() {
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                /*проверки*/
                if (e instanceof Error) System.out.println("Нельзя дальше работать");
                else if (e instanceof Exception) System.out.println("Надо обработать");
                else System.out.println("Поживем - увидим");
             }
        });
    }

    public static void main(String[] args) {
    }
}
