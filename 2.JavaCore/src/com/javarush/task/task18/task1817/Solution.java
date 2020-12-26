package com.javarush.task.task18.task1817;

import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
Пробелы
*/

public class Solution {

    public static void main(String[] args) throws IOException {

        int countOfSpaces = 0;
        int countOfChars = 0;
        try (FileReader fileReader = new FileReader(args[0])) {
            while (fileReader.ready()) {
                int readByte = fileReader.read();
                if (readByte==32) {
                    countOfSpaces++;
                }
                countOfChars++;
            }
        }
        double result = (1.0 * countOfSpaces / countOfChars) * 100;
//        DecimalFormat df = new DecimalFormat("####0.00");
//        System.out.println(df.format(result));
        System.out.printf("%.2f", result);
    }
}
