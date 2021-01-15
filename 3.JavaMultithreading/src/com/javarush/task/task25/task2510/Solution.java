package com.javarush.task.task25.task2510;

/* 
Поживем - увидим
*/

public class Solution extends Thread  {
    Thread thread;



    public Solution() {
        setUncaughtExceptionHandler(new UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                /*проверки*/
                if (e instanceof Error) System.out.println("Нельзя дальше работать");
                else if (e instanceof Exception) System.out.println("Надо обработать");
                else if (e.getClass() == Throwable.class) System.out.println("Поживем - увидим");

//                  #2
//                switch (e.getClass().getSimpleName()) {
//                    case "Error":
//                        System.out.println("Нельзя дальше работать");
//                        break;
//                    case "Exception":
//                        System.out.println("Надо обработать");
//                        break;
//                    case "Throwable":
//                        System.out.println("Поживем - увидим");
//                        break;
//                }
             }
        });
    }

//    public void run(){
//        throw new Error("error");
//    }

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.start();
    }
}
