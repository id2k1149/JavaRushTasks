package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args)  throws IOException  {
        if (!(args == null || args.length < 1)){

            String fileName = args[0];
            TreeMap<String, Double> treeMap = new TreeMap<>();

            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                while (fileReader.ready()) {
                    String line = fileReader.readLine();
                    String[] new_line = line.split(" ");
                    String name = new_line[0];
                    double number = Double.parseDouble(new_line[1]);
                    if (treeMap.containsKey(name)) {
                        double d_number = treeMap.get(name).doubleValue() + number;
                        treeMap.put(name, d_number);
                    }
                    else treeMap.put(name, number);
                }
            }

            for (Map.Entry pair: treeMap.entrySet()){
                System.out.println(pair.getKey() + " " + pair.getValue());
            }
        }
    }
}
