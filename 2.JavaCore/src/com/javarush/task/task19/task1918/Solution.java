package com.javarush.task.task19.task1918;

import com.javarush.task.task17.task1710_java.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
//        String fileName;
//        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
//        {
//            fileName = bufferedReader.readLine();
//        }

        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1918/data_file.txt";
        String string = "";
        int count_begin = 0;
        int count_end = 0;
        String a_tag = "span";
        String the_tag_1 = ".*<" + a_tag + ".*";
        String the_tag_2 = ".*</" + a_tag + ".*";
        System.out.println(the_tag_1 + " " + the_tag_2);
        ArrayList<String> splitedList = new ArrayList<>();
        String result;
        int index_begin = 0;
        int index_end = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                System.out.println(line);
                String[] splitedLine = line.replaceAll("><", "> <").split(" ");

                for (String each: splitedLine) {
                    splitedList.add(each);
                }
            }

            int end = splitedList.size();

            for (String each: splitedList) {
                string += each + " ";
            }

            System.out.println(string);
            String[] splitedLine2 = string.split(" ");
            System.out.println("----------------");

            for (int i = 0; i < splitedLine2.length; i++) {
                System.out.println("i = " + i + " " + splitedLine2[i]);
                if (splitedLine2[i].matches(the_tag_1)) {
                    if (count_begin == 0) index_begin = i;
                    count_begin++;
                    System.out.println("----->" + i + " " + splitedLine2[i] + " " + count_begin);
                    System.out.println("begin j cicle " + (i + 1));
                    for (int j = i + 1; j < splitedLine2.length; j++) {
                        if (splitedLine2[j].matches(the_tag_2)) {
                            count_end++;
                            System.out.println("jjjj->" + j + " " + splitedLine2[j] + " " + count_end);

                            if (count_end == count_begin) {
                                System.out.println("+++++++++");
                                for (int k = index_begin; k < j+1; k++) {
                                    System.out.print(splitedLine2[k] + " ");
                                }
                                System.out.println("+++++++++");
                            }
                        }
                    }
                }
            }

//                    for (String word : splitedLine) {
//                        if (word.matches(the_tag_1)) {
//                            count_begin++;
//                            System.out.println(word + " " + count_begin);
//                        }
//
//                    }

//                string += line.replaceAll("><", "> <");


//        System.out.println(string);
            System.out.println("--------------");


//        String the_tag_1 = "<" + a_tag + ">(\\S+)</" + a_tag + ">";

            //Creating a pattern object
//        Pattern pattern = Pattern.compile(the_tag_1);

            //Matching the compiled pattern in the String
//        Matcher matcher = pattern.matcher(string);

//        while(matcher.find()) {
//            String tag = matcher.group(0);
//            System.out.println(tag);
//        }


//        while (list.size() != 0) {

//        }

//        if (!(args == null || args.length < 1)){
//        }
        }
    }
}
