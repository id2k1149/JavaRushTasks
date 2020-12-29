package com.javarush.task.task19.task1910;

import java.io.*;
import java.util.ArrayList;

/* 
Пунктуация
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName_1;
        String fileName_2;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
        {
            fileName_1 = bufferedReader.readLine();
            fileName_2 = bufferedReader.readLine();
        }

        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName_1))) {
            while (fileReader.ready()) {
                String string = fileReader.readLine();
                list.add(string.replaceAll("[\\p{Punct}]", ""));

            }
        }

        try (BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(fileName_2))) {
            for (String line : list) {
                outputFileWriter.write(line);
            }
        }
    }
}
