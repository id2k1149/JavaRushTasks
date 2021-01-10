package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* 
Составить цепочку слов
*/

public class Solution_01 {
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
        StringBuilder result = new StringBuilder("");
        StringBuilder copy = new StringBuilder("");
        if (words.length == 0) {
            return result;
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));

        System.out.println("---------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("list.size = " + list.size());
        System.out.println("---------");

        int random_index = (int) Math.random() * list.size();
        System.out.println("random_index = " + random_index);
        result = new StringBuilder(list.get(random_index));
        list.remove(random_index);


        while (list.size() > 1) {

            System.out.println("---------");
            System.out.println(result.toString());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            System.out.println("---------");



            char r_first_letter = result.toString().toLowerCase().charAt(0);
            char r_last_letter = result.charAt(result.length() - 1);

            System.out.println("r_first_letter = " + r_first_letter + " r_last_letter = " + r_last_letter);


            for (int i = 0; i < list.size(); i++) {
                char first_letter = list.get(i).toLowerCase().charAt(0);
                char last_letter = list.get(i).charAt(list.get(i).length() - 1);

                System.out.println("first_letter = " + first_letter + " last_letter = " + last_letter);
                if (r_last_letter == first_letter && r_first_letter != last_letter) {
                    result.append(" " + list.get(i));
                    list.remove(i);
                    System.out.println(result.toString());
                    break;
                }
                else if (r_first_letter == last_letter && r_last_letter != first_letter) {
                    copy = new StringBuilder(list.get(i));
                    copy.append(" " + result);
                    result = copy;
                    list.remove(i);
                    System.out.println(result.toString());
                    break;
                }
            }
        }

        result = new StringBuilder(list.get(0));

        return result;
    }
}
