package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static
    {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        String fileName = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
        {
            fileName = bufferedReader.readLine();
        }
        catch (IOException ignore) {
            /* NOP */
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                String result = "";
                String[] splited_line = line.split(" ");
                for (String word: splited_line) {
                    if (word.matches("\\d+")) {
                        int number = Integer.parseInt(word);
                        if (map.containsKey(number)) {
                            word = map.get(number);
                        }
                    }
                    result += word + " ";
                }
                System.out.println(result);
            }
        }
        catch (IOException ignore) {
            /* NOP */
        }
    }
}
