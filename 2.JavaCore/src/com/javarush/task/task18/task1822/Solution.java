package com.javarush.task.task18.task1822;

import java.io.*;
import java.util.ArrayList;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

//        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1822/file_1.txt";

        ArrayList<String> buffer = new ArrayList<>();
        String id;


        if(args.length != 0) {
            id = args[0];
            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                while (fileReader.ready()) {
                    String string = fileReader.readLine();
                    buffer.add(string);
                }
            }

            for (String each: buffer) {
                String each_id = each.split(" ")[0];

                if (each_id.equals(id)) {
                    System.out.println(each);
                }
            }
        }
    }
}
