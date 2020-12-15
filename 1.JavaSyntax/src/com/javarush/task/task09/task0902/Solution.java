package com.javarush.task.task09.task0902;

/* 
И снова StackTrace
*/

public class Solution {
    public static void main(String[] args) {
        method1();
    }

    public static String method1() {
        method2();
        //напишите тут ваш код
        StackTraceElement[] stack_element = Thread.currentThread().getStackTrace();
//        System.out.println("Я метод " + stack_element[1].getMethodName());
//        System.out.println("Меня вызвал " + stack_element[2].getMethodName());

        return stack_element[2].getMethodName();
    }

    public static String method2() {
        method3();
        //напишите тут ваш код
        StackTraceElement[] stack_element = Thread.currentThread().getStackTrace();
//        System.out.println("Я метод " + stack_element[1].getMethodName());
//        System.out.println("Меня вызвал " + stack_element[2].getMethodName());

        return stack_element[2].getMethodName();
    }

    public static String method3() {
        method4();
        //напишите тут ваш код
        StackTraceElement[] stack_element = Thread.currentThread().getStackTrace();
//        System.out.println("Я метод " + stack_element[1].getMethodName());
//        System.out.println("Меня вызвал " + stack_element[2].getMethodName());

        return stack_element[2].getMethodName();
    }

    public static String method4() {
        method5();
        //напишите тут ваш код
        StackTraceElement[] stack_element = Thread.currentThread().getStackTrace();
//        System.out.println("Я метод " + stack_element[1].getMethodName());
//        System.out.println("Меня вызвал " + stack_element[2].getMethodName());

        return stack_element[2].getMethodName();
    }

    public static String method5() {
        //напишите тут ваш код
        StackTraceElement[] stack_element = Thread.currentThread().getStackTrace();
//        System.out.println("Я метод " + stack_element[1].getMethodName());
//        System.out.println("Меня вызвал " + stack_element[2].getMethodName());

        return stack_element[2].getMethodName();
    }
}
