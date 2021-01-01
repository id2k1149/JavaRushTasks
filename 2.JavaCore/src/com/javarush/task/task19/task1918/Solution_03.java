package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* 
Знакомство с тегами
*/

public class Solution_03 {

    public static void find_tags(String r, String tag) {
        int currentIndex = 0;
        boolean turnOnOutput = false;
        int level = 0;

        while (currentIndex <= r.length() - tag.length() - 1) {
//            System.out.println(r.substring(currentIndex, currentIndex + args[0].length()));
            String p = r.substring(currentIndex, currentIndex + tag.length() + 1);
            if (p.equals("<" + tag)) {
                ++level;
//                System.out.println("На индексе " + currentIndex + " нашли то, что нам надо! Старт вывода! Текущий уровень вложенности: " + level);

            } else if (p.equals("/" + tag)) {
                --level;
//                System.out.println("На индексе " + currentIndex + " видим окончание! Текущий уровень вложенности: " + level);
                if (level == 0) {
                    System.out.println("/" + tag + ">");
                }
            }
            if (level > 0) {
                String new_r = r.substring(currentIndex, currentIndex + 1);

                System.out.print(new_r);

            }

            currentIndex++;

        }
    }

    public static void main(String[] args) throws IOException {

        String s = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1918/file.properties";

        String r = new String();

        BufferedReader b = new BufferedReader(new FileReader(s));
        while (b.ready()) {
            r = r + b.readLine().replaceAll("\\n", "");
        }
        b.close();

        String tag = "span";
        find_tags(r, tag);

    }
}
