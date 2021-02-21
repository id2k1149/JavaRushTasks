package com.javarush.task.task36.task3605;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
//        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task36/task3605/file1.txt";

        File file = new File(fileName);
        List<String> list = Files.readAllLines(file.toPath(), Charset.defaultCharset());

        StringBuilder stringBuilder = new StringBuilder();
        for (String each: list) {
            stringBuilder.append(each);
        }

        String string = stringBuilder.toString().toLowerCase();

        char[] charArray = string.toCharArray();

        TreeSet<Character> treeSet = new TreeSet<>();
        for (char each: charArray) {
            if (Character.isLetter(each)) {
                treeSet.add(each);
            }
        }
        stringBuilder = new StringBuilder();
        Iterator<Character> it = treeSet.iterator();
        int i = 0;
        Character current;
        while(it.hasNext() && i < 5) {
            current = it.next();
            stringBuilder.append(current);
            i++;
        }

        System.out.println(stringBuilder.toString());

    }
}
