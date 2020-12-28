package com.javarush.task.task18.task1820;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName_1 = bufferedReader.readLine();
        String fileName_2 = bufferedReader.readLine();
        bufferedReader.close();

//        String fileName_1 = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1820/file_1.txt";
//        String fileName_2 = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1820/file_2.txt";

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName_1));
             FileWriter fileWriter = new FileWriter(fileName_2)) {
            while (fileReader.ready()) {
                String string = fileReader.readLine();
                for (String each: string.split(" ")) {
                    double dnum = Double.parseDouble(each);
                    int inum=(int) Math.round(dnum);
                    fileWriter.write(inum + " ");
                }
            }
        }
    }
}
