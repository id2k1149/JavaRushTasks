package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/* 
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) {

        String fileName = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task22/task2209/file.txt";

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

    public static StringBuilder getLine(String... words)  {
        StringBuilder sb = new StringBuilder();
        if (words.length==0) return sb;
        for (String s : words) {
            if (found(s, new ArrayList<String>(Arrays.asList(words)), sb)) break;
        }
        return sb;
    }

    public static boolean found(String current, ArrayList<String> possibilities, StringBuilder result) {
        ArrayList<String> nextPossibilities = new ArrayList<>(possibilities);
        if (nextPossibilities.size()== 1) {
            result.append(current);
            return true;
        }
        nextPossibilities.remove(current);

        for (String next: nextPossibilities) {
            if (next.toLowerCase().substring(0,1).equals(current.toLowerCase().substring(current.length()-1))) {
                if  (found(next,nextPossibilities,result)) {
                    result =result.insert(0,current + " ");
                    return true;
                }
            }
        }
        return false;
    }
}
