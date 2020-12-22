package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();

        ArrayList<String> arrayList = new ArrayList<>();
        double value_double;
        String value_string = null;

        int n = url.indexOf("?");
        String new_string = url.substring(n+1);

        while (new_string.contains("&")){
            int i = new_string.indexOf("&");
            String to_add = new_string.substring(0, i);
            if (to_add.contains("=")) {
                if (to_add.substring(0, to_add.indexOf("=")).equals("obj")) {
                    value_string = to_add.substring(4);
                }
                to_add = to_add.substring(0, to_add.indexOf("="));
            }
            arrayList.add(to_add);
            new_string = new_string.substring(i+1);
            if (!new_string.contains("&")) {
                if (new_string.contains("=")) {
                    if (new_string.substring(0, new_string.indexOf("=")).equals("obj")) {
                        value_string = new_string.substring(4);
                    }
                    new_string = new_string.substring(0, new_string.indexOf("="));
                }
                arrayList.add(new_string);
            }
        }

        for (String each: arrayList) {
            System.out.print(each + " ");
        }
        System.out.println();

        if (!value_string.equals(null)) {
            try {
                value_double = Double.parseDouble(value_string);
                alert(value_double);
            } catch (NumberFormatException e) {
                alert(value_string);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
