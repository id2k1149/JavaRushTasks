package com.javarush.task.task10.task1016;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Одинаковые слова в списке
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            words.add(reader.readLine());
        }

        Map<String, Integer> map = countWords(words);

        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }

    public static Map<String, Integer> countWords(ArrayList<String> list) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();

        //напишите тут ваш код
        while (list.size() > 1){
            int count = 0;
            String first_word = list.get(0);
            for (String each : list) {
                if (first_word.equals(each)) count++;
            }
            list.removeIf(word -> word.equals(first_word));
            result.put(first_word, count);
        }
        if (list.size() == 1) result.put(list.get(0), 1);

        //Java
        for (String word : list) {
            int count = result.getOrDefault(word, 0);
            result.put(word, count + 1);
        }

        return result;
    }

}

