package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) {
        TreeMap<String, Double> salary = new TreeMap<>();
        if (!(args == null || args.length < 1)){
            String fileName = args[0];
            try (BufferedReader rd = new BufferedReader(new FileReader(fileName))) {
                String[] splitedLine;
                String line;
                Double currentValue;

                while ((line = rd.readLine()) != null) {
                    splitedLine = line.split(" ");
                    String name = splitedLine[0];
                    double value = Double.parseDouble(splitedLine[1]);

                    if (salary.containsKey(name)) {
                        currentValue = salary.get(name);
                        salary.put(name, value + currentValue);
                    } else {
                        salary.put(name, value);
                    }
                }
            } catch (IOException ignore) {
                /*NOP */
            }
        }

        double max = salary.firstEntry().getValue();
        for (String key : salary.keySet()) {
            if (salary.get(key) > max) max = salary.get(key);
        }
        for (String key : salary.keySet()) {
            if (salary.get(key) == max) {
                System.out.print(key + " ");
            }
        }
    }
}
