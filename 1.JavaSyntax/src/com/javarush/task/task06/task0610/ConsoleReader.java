package com.javarush.task.task06.task0610;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Класс ConsoleReader
*/

public class ConsoleReader {
    public static String readString() throws Exception {
        //напишите тут ваш код
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        return buffer.readLine();
    }

    public static int readInt() throws Exception {
        //напишите тут ваш код
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(buffer.readLine());
    }

    public static double readDouble() throws Exception {
        //напишите тут ваш код
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        return Double.parseDouble(buffer.readLine());
    }

    public static boolean readBoolean() throws Exception {
        //напишите тут ваш код
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        return Boolean.parseBoolean(buffer.readLine());
    }

    public static void main(String[] args) throws Exception {

    }
}
