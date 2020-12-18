package com.javarush.task.task13.task1326_Buffer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код

        ArrayList<Integer> list = new ArrayList<>();

        BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = readerFileName.readLine();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(nameFile))))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                if (Integer.parseInt(line) % 2 == 0)
                {
                    list.add(Integer.parseInt(line));
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Collections.sort(list);
        for (int print : list)
        {
            System.out.println(print);
        }
    }
}

