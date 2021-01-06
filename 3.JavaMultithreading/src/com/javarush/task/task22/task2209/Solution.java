package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) {
        //...
        String fileName = null;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            fileName = reader.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task22/task2209/file.txt";

        ArrayList<String> fileContent = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String[] splitedLine;
            String line;

            while ((line = br.readLine()) != null) {
                splitedLine = line.split(" ");
                for (String each: splitedLine) {
                    fileContent.add(each);
                }
            }
        } catch (IOException ignore) {
            /*NOP */
        }

        String[] words = new String[fileContent.size()];
        for (int i = 0; i < fileContent.size(); i++) {
            words[i] = fileContent.get(i);
        }



        StringBuilder result = getLine(words);
//        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();




        for (int i = 0; i < words.length; i++) {
            int last_index = words[i].length() - 1;
            char last_letter = words[i].charAt(last_index);

            while (true){
                for (int j = i + 1; j < words.length; j++) {
                    char first_letter = words[j].charAt(0);
                    if (last_letter == first_letter) {
                        result.append(words[j] + " ");
                    }

                }

            }


        }





        return null;
    }
}
