package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
https://programming.guide/java/list-of-java-exceptions.html
*/

public class VeryComplexClass {
    // должен всегда кидать Runtime исключение ClassCastException
    public void methodThrowsClassCastException() {
        Object x = new Integer(0);
        System.out.println((String)x);
    }

    // должен всегда кидать Runtime исключение NullPointerException
    public void methodThrowsNullPointerException() {
        ArrayList<Integer> list = null;
        System.out.println(list.size());
    }

    public static void main(String[] args) {

    }
}
