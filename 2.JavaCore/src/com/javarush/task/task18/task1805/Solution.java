package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
//        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1805/file_1.txt";
        
        ArrayList<Integer> arrayList = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            while (fileInputStream.available() > 0) {
                int currByte = fileInputStream.read();
                if (!arrayList.contains(currByte)) arrayList.add(currByte);
            }
        }

        Collections.sort(arrayList);

        for (int each: arrayList) {
            System.out.print(each + " ");
        }
    }
}
