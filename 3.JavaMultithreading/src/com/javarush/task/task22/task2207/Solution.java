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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task22/task2207/file.txt";

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

//        for (String each: fileContent) {
//            System.out.println(each.trim());
//        }

        int count = 0;
        while (fileContent.size() > 1) {
//            System.out.println("---------------------");
//            System.out.println("length = " + fileContent.size());
//            System.out.println("while starts");
            StringBuilder word = new StringBuilder(fileContent.get(0));
//            System.out.println("i = 0 -> " + word.toString());
            String reverse = word.reverse().toString();
//            System.out.println(" reverse = " + reverse);
//            System.out.println("---------------------");
            for (int i = 1; i < fileContent.size(); i++) {
//                System.out.println(" i = " + i + " -> " + fileContent.get(i));
                if (fileContent.get(i).equals(reverse)) {
//                    System.out.println("if works");
                    Pair pair = new Pair();
                    pair.first = fileContent.get(0);
                    pair.second = fileContent.get(i);
                    result.add(count, pair);
                    count++;
                    fileContent.remove(i);
                    break;
                }
            }
            fileContent.remove(0);
        }

//        Iterator<String> iterator = word_array.iterator();
//        while (iterator.hasNext()) {
//    .......
//            iterator.remove();
//    .......
//        }

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
