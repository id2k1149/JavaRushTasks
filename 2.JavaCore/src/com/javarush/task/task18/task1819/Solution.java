package com.javarush.task.task18.task1819;

import java.io.*;
import java.util.ArrayList;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        ArrayList<Integer> Buffer1 = new ArrayList<>();
        ArrayList<Integer> Buffer2 = new ArrayList<>();

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileName1));
        while (inputStream.available() > 0) {
            Buffer1.add(inputStream.read());
        }
        inputStream.close();

        inputStream = new BufferedInputStream(new FileInputStream(fileName2));
        while (inputStream.available() > 0) {
            Buffer2.add(inputStream.read());
        }
        inputStream.close();

        Buffer2.addAll(Buffer1);
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName1));
        for (int i = 0; i < Buffer2.size(); i++) {
            outputStream.write(Buffer2.get(i));
        }
        outputStream.close();
    }
}
    