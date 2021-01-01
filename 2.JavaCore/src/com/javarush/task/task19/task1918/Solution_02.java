package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* 
Знакомство с тегами
*/

public class Solution_02 {
    public static void main(String[] args) throws IOException {
//        String fileName;
//        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
//        {
//            fileName = bufferedReader.readLine();
//        }

        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1918/file.properties";

        String string = "";
        int count_begin = 0;
        int count_end = 0;
        String a_tag = "span";
        String the_tag = ".*" + a_tag + ".*";
        String the_tag_1 = ".*<" + a_tag + ".*";
        String the_tag_2 = ".*</" + a_tag + ".*";
        System.out.println(the_tag_1 + " " + the_tag_2);
        ArrayList<String> splitedList = new ArrayList<>();
        String result;
        int index_begin = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                System.out.println(line);
                String[] splitedLine = line.replaceAll(">", "> ").replaceAll("<", " <").split(" ");

                for (String each: splitedLine) {
                    splitedList.add(each);
                }
            } 


            for (String each: splitedList) {
                string += each + " ";
            }

            System.out.println(string);
            String[] splitedLine2 = string.split(" ");
            System.out.println("----------------");

            for (int i = 0; i < splitedLine2.length; i++) {

                String word = splitedLine2[i];
                System.out.println("i = " + i + " " + word);
                if (word.matches(the_tag)) {
                    if (word.matches(the_tag_1)) {
                        if (count_begin == 0) index_begin = i;
                        count_begin++;
                    }
                    else if (word.matches(the_tag_2)) {
                        count_end++;
                        if (count_end == count_begin) {
                            for (int j = index_begin; j < i; j++) {
                                System.out.print(splitedLine2[j]);
                            }
                            System.out.println(splitedLine2[i]);
                            count_begin = 0;
                            count_end = 0;
                        }
                    }
                }
            }

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
