package com.javarush.task.task18.task1821;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) throws IOException {


        String fileName = args[0];
//        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1821/file_1.txt";

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();


        FileInputStream inputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 200);
        int i;

        while((i = bufferedInputStream.read())!= -1){

            if (!treeMap.containsKey(i)) {
                treeMap.put(i, 1);
            }
            else {
                treeMap.put(i, (treeMap.get(i) + 1));
            }
        }

        inputStream.close();
        bufferedInputStream.close();

        for (Map.Entry<Integer, Integer> pair : treeMap.entrySet())
        {
            if (!(pair.getValue() == 0)){
                int key = pair.getKey();
                char symbol = (char) key;
                int value = pair.getValue();
                System.out.println(symbol + " " + value);
            }
        }
    }
}
