package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
//        e.printStackTrace();
//
        List<Throwable> list = new ArrayList<>();
        list.add(e);
        while(list.get(list.size()-1).getCause() != null)
            list.add(list.get(list.size()-1).getCause());
        Collections.reverse(list);
        for (Throwable each:list)
            System.out.println(each.getClass().getName() + ": " + each.getMessage());

    }

    public static void main(String[] args) {
        new Solution().uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
