package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;

/* 
Выделяем числа
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
                String[] line_list = string.split(" ");
                for (String each: line_list) {
                    if (each.matches("\\d+")) list.add(each + " ");
                }
            }
        }

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName_2))) {
            for (int i = 0; i < list.size(); i++) {
                fileWriter.write(list.get(i));
            }
        }
    }
}
