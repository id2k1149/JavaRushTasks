package com.javarush.task.task09.task0906;

/* 
Логирование стек-трейса
*/

public class Solution {
    public static void main(String[] args) {
        log("In main method");
    }

    public static void log(String text) {
        //напишите тут ваш код
        System.out.println(Thread.currentThread().getStackTrace()[2].getClassName()
                        + ": "
                        + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + ": "
                        + text);

//        System.out.println(Thread.currentThread().getStackTrace()[2].getClassName()
//                + ": "
//                + Thread.currentThread().getName()
//                + ": "
//                + text);

        // JavaRush
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTraceElements[2];
        String className = element.getClassName();
        String methodName = element.getMethodName();
        String message = className + ": " + methodName + ": " + text;
        System.out.println(message);
    }
}
