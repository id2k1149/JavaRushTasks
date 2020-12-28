package com.javarush.task.task18.task1816;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/* 
Английские буквы
*/

public class Solution_each_letter {
    public static void main(String[] args) throws IOException {
        HashMap<Character, Integer> hashMap = new HashMap<>();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        char[] alphabet_1 = alphabet.toCharArray();
        char[] alphabet_2 = alphabet.toUpperCase().toCharArray();

        for (char each: alphabet_1) {
            hashMap.put(each, 0);
        }
        for (char each: alphabet_2) {
            hashMap.put(each, 0);
        }

        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1816/file_1.txt";

        FileInputStream inputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 200);
        int i;
        while((i = bufferedInputStream.read())!= -1){
            char a = (char) i;
            System.out.println(i + " " + a);
            for (HashMap.Entry<Character, Integer> pair : hashMap.entrySet())
            {
                char key = pair.getKey();
                if (a == pair.getKey()) {
                    int new_value = pair.getValue() + 1;
                    pair.setValue(new_value);
                }
            }

        }

        for (HashMap.Entry<Character, Integer> pair : hashMap.entrySet())
        {
            if (!(pair.getValue() == 0)){
                char key = pair.getKey();
                int value = pair.getValue();
                System.out.println(key + " " + value);
            }
        }
        inputStream.close();
    }
}
