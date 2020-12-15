package com.javarush.task.task09.task0903;

/* 
Кто меня вызывал?
*/

public class Solution {
    public static void main(String[] args) {
        method1();
    }

    public static int method1() {
        method2();
        return  /* номер строки кода, из которого вызвали этот метод */
                Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    public static int method2() {
        method3();
        return  /* номер строки кода, из которого вызвали этот метод */
                Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    public static int method3() {
        method4();
        return  /* номер строки кода, из которого вызвали этот метод */
                Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    public static int method4() {
        method5();
        return  /* номер строки кода, из которого вызвали этот метод */
                Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    public static int method5() {
        return  /* номер строки кода, из которого вызвали этот метод */
                Thread.currentThread().getStackTrace()[2].getLineNumber();
    }
}
