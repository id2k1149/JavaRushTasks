package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file_name = reader.readLine();

        //создаем объект FileInputStream, привязанный к файлу
        FileInputStream inputStream = new FileInputStream(file_name);
        long max = 0;

        while (inputStream.available() > 0) //пока остались непрочитанные байты
        {
            int data = inputStream.read(); //прочитать очередной байт
            if (data > max) max = data;

        }
        inputStream.close(); // закрываем поток

        System.out.println(max); //выводим  на экран.

        // Java
//        int maxByte = 0;
//        try (FileInputStream fileInputStream = new FileInputStream(file_name)) {
//            while (fileInputStream.available() > 0) {
//                int currByte = fileInputStream.read();
//                if (currByte > maxByte) maxByte = currByte;
//            }
//        }
//        System.out.println(maxByte);

    }
}
