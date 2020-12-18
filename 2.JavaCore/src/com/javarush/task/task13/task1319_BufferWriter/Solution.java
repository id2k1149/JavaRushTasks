package com.javarush.task.task13.task1319_BufferWriter;

import java.io.*;
import java.util.ArrayList;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file_path = reader.readLine();

        ArrayList<String> arrayList = new ArrayList<>();

        while (true){
            String line = reader.readLine();
            arrayList.add(line);
            if (line.equals("exit")) break;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file_path));
        for (String value : arrayList) {
            writer.write(value + "\n");
        }

        //закрываем потоки
        writer.close();
        reader.close();
    }
}
