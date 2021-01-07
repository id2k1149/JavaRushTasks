package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* 
Составить цепочку слов
*/

public class Solution_02 {
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
                    if (!(each.isEmpty())) fileContent.add(each.trim());
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
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        if (words.length == 0) {
            result.append(" ");
            return result;
        }

        ArrayList<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            list.add(new StringBuilder(words[i]));
        }

        while (list.size() > 1) {
            System.out.println("---------");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            System.out.println("---------");
            char last_letter = list.get(0).charAt(list.get(0).length() - 1);
            System.out.println("last_letter = " + last_letter);

            for (int i = 1; i < list.size(); i++) {
                char first_letter = list.get(i).toString().toLowerCase().charAt(0);
                System.out.println("first_letter = " + first_letter);
                if (last_letter == first_letter) {
                    list.get(0).append(" " + list.get(i));
                    list.remove(i);
                    System.out.println(list.get(0).toString());
                    break;
                }
                if (i == list.size() - 1) {
                    System.out.println("i = " + i);
                    result = new StringBuilder(list.get(0));
                    System.out.println(result.toString());
                    int last = list.get(0).length();
                    list.get(0).replace(0, last , list.get(i).toString());
                    list.get(i).replace(0, list.get(i).length(), result.toString());
                }
            }
        }

        result = new StringBuilder(list.get(0));

        return result;
    }
}
