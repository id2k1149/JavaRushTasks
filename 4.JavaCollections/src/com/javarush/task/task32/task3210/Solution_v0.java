package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution_v0 {
    public static void main(String... args) {
        // Убедись, что сначала выставляется позиция для считывания,
        // читаем данные с файла, а потом выставляется позиция для записи.
        try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            long number = Long.parseLong(args[1]);
            String text = args[2];
            int length = text.length();

            raf.seek(number);

            String textFromFile = String.valueOf(raf.read(new byte[length], 0, length));

            String result;
            if (text.equals(textFromFile)) {
                result = "true";
            }
            else {
                result = "false";
            }
            raf.write(result.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
