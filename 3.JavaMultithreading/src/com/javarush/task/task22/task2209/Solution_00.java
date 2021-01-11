package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* 
Составить цепочку слов
*/

public class Solution_00 {
    public static void main(String[] args) {

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

    public static StringBuilder getLine(String... words)  {
        StringBuilder result = new StringBuilder("");
        if (words.length == 0) {
            return result;
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));

        System.out.println("---------");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        System.out.println("list.size = " + list.size());

        String word = "";
        String word2 = "";


        for (int i = 0; i < list.size(); i++) {
            System.out.println("i = " + i);
            word = list.get(i);
            char w_first_letter = word.toLowerCase().charAt(0);
            char w_last_letter = word.toLowerCase().charAt(word.length() - 1);
            if (w_first_letter == w_last_letter) {
                System.out.println("we found word " + word);
                list.remove(i);
                for (int j = 0; j < list.size(); j++) {
                    System.out.println("list.size = " + list.size());
                    word2 = list.get(j);
                    System.out.println("word2 = " + word2);
                    char l_first_letter = word2.toLowerCase().charAt(0);
                    char l_last_letter = word2.toLowerCase().charAt(word2.length() - 1);
                    if (w_last_letter == l_first_letter) {
                        word += (" " + word2);
                        System.out.println("< " + word + " >");
                        list.remove(j);
                        list.add(word);
                        System.out.println("---------");
                        System.out.println("list.size = " + list.size());
                        for (int k = 0; k < list.size(); k++) {
                            System.out.print("<" + list.get(k) + " > ");
                        }
                        System.out.println();
                        System.out.println("---------");
                        break;

                    } else if (w_first_letter == l_last_letter) {
                        word2 += (" " + word);
                        System.out.println("< " + word2 + " >");
                        list.remove(j);
                        list.add(word2);
                        System.out.println("---------");
                        System.out.println("list.size = " + list.size());
                        for (int k = 0; k < list.size(); k++) {
                            System.out.print("<" + list.get(k) + " > ");
                        }
                        System.out.println();
                        System.out.println("---------");
                        break;
                    }
                }
            }
        }


        String first_word = "";
        String last_word = "";

        boolean condition = true;
        while (condition) {
            for (int i = 0; i < list.size(); i++) {
//                System.out.println("i = " + i);
                word = list.get(i);
//                System.out.println("word = " + word);
                char w_last_letter = word.toLowerCase().charAt(word.length() - 1);
//                System.out.println(w_last_letter);
                for (int j = 0; j < list.size(); j++) {
                    word2 = list.get(j);
//                    System.out.println("word2 = " + word2);
                    char w2_first_letter = word2.toLowerCase().charAt(0);
//                    System.out.println(w2_first_letter);
                    if (w_last_letter == w2_first_letter) {
//                        System.out.println("w_last_letter == w2_first_letter");
                        break;
                    }
                    if (j == list.size() - 1) {
//                        System.out.println("j = " + j);
                        last_word = word;
//                        System.out.println("last_word = " + last_word);
                        condition = false;
                        break;
                    }
                }
                if (condition == false) break;
            }
            condition = false;
        }

        condition = true;
        while (condition) {
            for (int i = 0; i < list.size(); i++) {
//                System.out.println("i = " + i);
                word = list.get(i);
//                System.out.println("word = " + word);
                char w_first_letter = word.toLowerCase().charAt(0);
//                System.out.println(w_first_letter);
                for (int j = 0; j < list.size(); j++) {
                    word2 = list.get(j);
//                    System.out.println("word2 = " + word2);
                    char w2_last_letter = word2.toLowerCase().charAt(word2.length() - 1);
//                    System.out.println(w2_last_letter);
                    if (w_first_letter == w2_last_letter){
//                        System.out.println("w_first_letter == w2_last_letter");
                        break;
                    }
                    if (j == list.size() - 1) {
//                        System.out.println("j = " + j);
                        first_word = word;
//                        System.out.println("first_word = " + first_word);
                        condition = false;
                        break;
                    }
                }
                if (condition == false) break;
            }
            condition = false;
        }

        System.out.println("first_word = " + first_word);
        if (first_word.length() > 0) list.remove(first_word);
        System.out.println("last_word = " + last_word);
        if (last_word.length() > 0) list.remove(last_word);


        System.out.println("---------");
        System.out.println("list.size = " + list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.print("<" + list.get(i) + " > ");
        }
        System.out.println();
        System.out.println("---------");


        if (list.size() > 1 && first_word.length() > 0) {
            word = first_word;
            char last_letter = word.toLowerCase().charAt(word.length() - 1);

            for (int i = 0; i < list.size(); i++) {
                char first_letter = list.get(i).toLowerCase().charAt(0);
                System.out.println("first_letter = " + first_letter);
                if (last_letter == first_letter) {
                    word += " " + list.get(i);
                    list.remove(i);
                    break;
                }
            }
            list.add(word);
        }



        while (list.size() > 1) {

            System.out.println("---------");
            System.out.println("list.size = " + list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.print("<" + list.get(i) + " > ");
            }
            System.out.println();

            word = list.get(0);
            list.remove(word);
            char last_letter = word.toLowerCase().charAt(word.length() - 1);
            System.out.println("word = " + word +  " last_letter = " + last_letter);

            for (int i = 0; i < list.size(); i++) {
                char first_letter = list.get(i).toLowerCase().charAt(0);
                System.out.println("first_letter = " + first_letter);
                if (last_letter == first_letter) {
                    word += " " + list.get(i);
                    list.remove(i);
                    break;
                }
            }
            System.out.println("word --->" + word);
            char w_first_letter = word.charAt(0);
            char w_last_letter = word.toLowerCase().charAt(word.length() - 1);
            if (w_first_letter == w_last_letter) {
                String[] splited = word.split(" ");
                System.out.println(splited);
                String word3 = splited[0];
                System.out.println("word3 --->" + word3);
                word = word.substring(word3.length() + 1);
                word += (" " + word3);
                System.out.println("new word --->" + word);
            }
            list.add(word);
        }

        if (last_word.length() > 0) word += " " + last_word;

        System.out.println("WORD = " + word);

        result = new StringBuilder(word.trim());

        return result;
    }
}
