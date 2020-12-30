package com.javarush.task.task19.task1927;

import java.io.*;
import java.util.ArrayList;

/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {


        //запоминаем настоящий PrintStream в специальную переменную
        PrintStream defaultPrintStream = System.out;

        //Создаем динамический массив
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //создаем адаптер к классу PrintStream
        PrintStream stream = new PrintStream(byteArrayOutputStream);

        //Устанавливаем адаптер как текущий System.out
        System.setOut(stream);

        //Вызываем функцию,
        testString.printSomething();

        //Преобразовываем записанные в наш ByteArray данные в строку
        String result = byteArrayOutputStream.toString();

        String[] list = result.split("\\n");
        ArrayList<String> copy = new ArrayList<>();

        for (int i = 0 ; i < list.length; i++) {
            if (i % 2 != 0 || i == 0) {
                copy.add(list[i]);
            }
            else {
                copy.add("JavaRush - курсы Java онлайн");
                copy.add(list[i]);
            }
        }

        String new_result = "";
        for (String each: copy) {
            new_result += (each + "\n");
        }

        //Возвращаем все как было
        System.setOut(defaultPrintStream);

        System.out.println(new_result);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
