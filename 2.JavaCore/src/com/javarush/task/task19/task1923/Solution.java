package com.javarush.task.task19.task1923;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Слова с цифрами
*/

public class Solution {

    public static void main(String[] args) {
        if (!(args == null || args.length < 1)) {
            String fileName1 = args[0];
            String fileName2 = args[1];

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName1));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2))) {
                String[] splitedLine;
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    splitedLine = line.split(" ");

                    for (String word: splitedLine) {
                        if (word.matches(".*\\d+.*")) {
                            bufferedWriter.write(word + " ");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}