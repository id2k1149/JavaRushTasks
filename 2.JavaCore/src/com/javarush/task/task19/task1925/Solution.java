package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;

/* 
Длинные слова
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
                String result = "";

                while ((line = bufferedReader.readLine()) != null) {
                    splitedLine = line.split(" ");

                    for (String word: splitedLine) {
                        if (word.length() > 6) {
                            result += word + ",";
                        }
                    }
                }
                result = result.substring(0, result.length()-1);
                bufferedWriter.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
