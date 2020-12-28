package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        String fileName_1 =
//                "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1823/file_1.txt";
//
//        String fileName_2 =
//                "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1823/file_2.txt";

        String fileName = reader.readLine();

        try {
            while (!(fileName.equals("exit"))) {
                ReadThread thread = new ReadThread(fileName);
                thread.start();
                fileName = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            //implement constructor body
            super(fileName);
            this.fileName = fileName;
        }

        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run() {
            int[] byteCountArray = new int[255];

            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                while (fileInputStream.available() > 0) {
                    byteCountArray[fileInputStream.read()] += 1;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int maxCount = 0;

            for (int byteCount : byteCountArray) {
                if (byteCount > maxCount) maxCount = byteCount;
            }

            for (int i = 0; i < byteCountArray.length; i++) {
                if (byteCountArray[i] == maxCount) {
                    Solution.resultMap.put(fileName, i);
                    break;
                }
            }
        }
    }
}
