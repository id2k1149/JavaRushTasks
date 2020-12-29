package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultPrintStream = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        testString.printSomething();
        System.setOut(defaultPrintStream);

        String result = byteArrayOutputStream.toString();
        String[] list = result.split(" ");

        if (list[1].equals("+")) {
            result += String.valueOf(Integer.parseInt(list[0]) + Integer.parseInt(list[2]));
        }
        else if (list[1].equals("-")) {
            result += String.valueOf(Integer.parseInt(list[0]) - Integer.parseInt(list[2]));
        }
        else if (list[1].equals("*")) {
            result += String.valueOf(Integer.parseInt(list[0]) * Integer.parseInt(list[2]));
        }
        System.out.println(result);

    }

    public static class TestString {
        public void printSomething() {
            System.out.print("3 + 6 = ");
        }
    }
}

