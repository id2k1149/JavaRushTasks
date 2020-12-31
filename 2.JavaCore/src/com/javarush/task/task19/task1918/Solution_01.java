package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* 
Знакомство с тегами
*/

public class Solution_01 {
    public static void main(String[] args) throws IOException  {
//        String fileName;
//        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
//        {
//            fileName = bufferedReader.readLine();
//        }

        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1918/data_file.txt";
        String all_tags = "";

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                all_tags = all_tags + line.replaceAll("\\n", " ");
            }
        }
        
        System.out.println(all_tags);
        System.out.println("--------------");

        if (!(args == null || args.length < 1)){
            String tag_to_find = args[0];
            String start = "<" + tag_to_find;
            String finish = "</" +tag_to_find + ">";
            System.out.println(start + " " + finish);
            System.out.println("--------------");
            
//            while (all_tags.length() != 0){
//            }

            int k = 1;
            if (all_tags.charAt(0) != '<') {
                while (all_tags.charAt(k) != '<') {
                    k++;
                }
            }
            String to_remove = all_tags.substring(0, k);
            System.out.println(to_remove);

            all_tags = all_tags.replace(to_remove, "");
            System.out.println(all_tags);
            System.out.println(all_tags.charAt(0));

            int start_index;
            int tag_count = 0;
            while (all_tags.length() != 0){
                for (int i = 0; i < all_tags.length(); i++) {
                    if (all_tags.charAt(0) == '<') {
                        String tag_to_check = all_tags.substring(0, start.length());
                        if (tag_to_check.equals(start)) {
                            start_index = 0;
                            tag_count++;
                            System.out.println("tag_count = " + tag_count);
                        }
                    }
                    if (tag_count == 5) {
                        String to_print = all_tags.substring(0, i);
                        System.out.println(to_print);
                        break;
                    }
                }



            }



            

            



        }
        else throw new RuntimeException();






    }
}
