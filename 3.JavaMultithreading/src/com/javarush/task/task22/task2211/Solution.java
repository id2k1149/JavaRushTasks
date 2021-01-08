package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedMap;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //start here - начни тут

        // список всех кодировок, с которыми Java может работать
//        SortedMap<String,Charset> charsets = Charset.availableCharsets();
//        for(Map.Entry<String, Charset> pair: charsets.entrySet())
//        {
//            String key = pair.getKey();
//            Charset value = pair.getValue();
//            System.out.println(key + ":" + value.toString());
//        }

        if (args != null || args.length > 0) {
            String fileName_1 = args[0];
            String fileName_2 = args[1];

//            String fileName_1 = "/Users/mikepol/IdeaProjects/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task22/task2211/file1.txt";
//            String fileName_2 = "/Users/mikepol/IdeaProjects/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task22/task2211/file2.txt";

            Charset windows1251 = Charset.forName("Windows-1251");
//            Charset utf8 = Charset.forName("UTF-8");
            Charset utf8 = StandardCharsets.UTF_8;

            FileInputStream inputStream = new FileInputStream(fileName_1);
            FileOutputStream outputStream = new FileOutputStream(fileName_2);

            while (inputStream.available() > 0) //пока остались непрочитанные байты
            {
                byte[] buffer = new byte[1000];
                inputStream.read(buffer);
                String s = new String(buffer, windows1251);
                buffer = s.getBytes(utf8);
                outputStream.write(buffer);

            }
            inputStream.close();
            outputStream.close();
        } else {
            throw new RuntimeException();
        }
    }
}
