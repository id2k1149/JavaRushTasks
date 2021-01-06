package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {

        String fileName = null;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            fileName = reader.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task22/task2207/file.txt";

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

        int count = 0;
        for (int i = 0; i < fileContent.size(); i++) {
            StringBuilder word = new StringBuilder(fileContent.get(i));
            for (int j = i + 1; j < fileContent.size(); j++) {
                if  (fileContent.get(j).equals(word.reverse().toString())) {
                    Pair pair = new Pair();
                    pair.first = fileContent.get(i);
                    pair.second = fileContent.get(j);
//                    && !(result.contains(Pair.reversePlace(pair)))
                    if (!(result.contains(pair))) {
                        result.add(count, pair);
                        count++;
                    }
                }
            }
        }

        for (Pair each: result) {
            System.out.println(each.first + " " + each.second);
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }
}
