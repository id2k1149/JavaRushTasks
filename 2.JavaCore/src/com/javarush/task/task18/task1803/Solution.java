package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
//        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1803/file.txt";

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = 1;

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            while (fileInputStream.available() > 0) {
                int currByte = fileInputStream.read();
                if (hashMap.containsKey(currByte)) {
                    count += hashMap.get(currByte);
                }
                hashMap.put(currByte, count);
                count = 1;
            }
        }

//        for (HashMap.Entry<Integer, Integer> pair : hashMap.entrySet())
//        {
//            int key = pair.getKey();                      //ключ
//            int value = pair.getValue();                  //значение
//            System.out.println(key + ":" + value);
//        }

        int maxValueInMap=(Collections.max(hashMap.values()));  // This will return max value in the Hashmap
        for (HashMap.Entry<Integer, Integer> pair : hashMap.entrySet()) {  // Itrate through hashmap
            if (pair.getValue()==maxValueInMap) {
                System.out.print(pair.getKey() + " ");     // Print the key with max value
            }
        }
    }
}
