package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();

        int n = string.indexOf("?");
        String new_string = string.substring(n+1);
        System.out.println(new_string);
        while (true){
            int i = new_string.indexOf("&");
            System.out.println(i);
            String string2 = new_string.substring(n+1);
            System.out.println(string2);
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
