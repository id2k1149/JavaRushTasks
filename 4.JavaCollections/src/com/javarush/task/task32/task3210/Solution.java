package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        // Убедись, что сначала выставляется позиция для считывания,
        // читаем данные с файла, а потом выставляется позиция для записи.
        try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            int number = Integer.parseInt(args[1]);
            raf.seek(number);

            String text = args[2];

            byte [] bytes = new byte[text.length()];
            raf.read(bytes, 0,text.length());

            String textFromFile = new String(bytes);
            String result = (text.equals(textFromFile)) ? "true" : "false";

            raf.seek(raf.length());
            raf.write(result.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
