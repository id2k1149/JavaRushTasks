package com.javarush.task.task19.task1906;

import java.io.*;
import java.util.ArrayList;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName_1 = bufferedReader.readLine();
        String fileName_2 = bufferedReader.readLine();
        bufferedReader.close();

        FileReader reader = new FileReader(fileName_1);
        FileWriter writer = new FileWriter(fileName_2);

        int i = 1;
        while (reader.ready()) //пока есть непрочитанные байты в потоке ввода
        {
            int data = reader.read(); //читаем один символ (char будет расширен до int)
            if (i % 2 == 0) {
                writer.write(data);
            }
            i++;
        }
        //закрываем потоки после использования
        reader.close();
        writer.close();
    }
}
