package com.javarush.task.task14.task1419;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        //напишите тут ваш код
        // 2
        try {
            String a = null; //null value
            System.out.println(a.charAt(0));
        } catch (Exception e) {
            exceptions.add(e);
        }

        // 3
        try {
            String a = "This is like chipping "; // length is 22
            char c = a.charAt(24); // accessing 25th element
            System.out.println(c);
        } catch (Exception e) {
            exceptions.add(e);
        }

        // 4
        try {
            // "akki" is not a number
            int num = Integer.parseInt ("akki") ;
            System.out.println(num);
        } catch (Exception e) {
            exceptions.add(e);
        }

        // 5
        try {
            int a[] = new int[5];
            a[6] = 9; // accessing 7th element in an array of
            // size 5
        } catch (Exception e) {
            exceptions.add(e);
        }

        // 6
        FileInputStream input1 = null;
        try {
            input1 = new FileInputStream("D:/file_1.txt");
        } catch (Exception e) {
            exceptions.add(e);
        }

        // 7
        try {
            Class loadedClass = Class.forName("CLASS_TO_LOAD");
            System.out.println("Class " + loadedClass + " found!");

        } catch (Exception e) {
            exceptions.add(e);
        }

        // 8
        exceptions.add(new ArrayStoreException());

        // 9
        exceptions.add(new NegativeArraySizeException());

        // 10
        exceptions.add(new InterruptedException());

        // Java
//        exceptions.add(new ArrayIndexOutOfBoundsException());
//        exceptions.add(new IllegalArgumentException());
//        exceptions.add(new IllegalAccessException());
//        exceptions.add(new NumberFormatException());
//        exceptions.add(new ClassCastException());
//        exceptions.add(new IOException());
//        exceptions.add(new InterruptedIOException());
//        exceptions.add(new InterruptedException());
//        exceptions.add(new SecurityException());

    }
}
