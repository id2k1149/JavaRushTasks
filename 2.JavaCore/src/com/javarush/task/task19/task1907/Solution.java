package com.javarush.task.task19.task1907;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
        {
            fileName = bufferedReader.readLine();
        }

        int count = 0;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                String string = fileReader.readLine();
                String[] list = string.replaceAll("[\\p{P}\\s]", " ").split(" ");
                for (String each: list) {
                    if (each.contains("world") && each.length()==5) count++;
                }
            }
        }
        System.out.println(count);
    }
}
