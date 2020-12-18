package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file_path = reader.readLine();
        FileInputStream fileInputStream = new FileInputStream(file_path);

// Создаем цикл для считывания информации нашего текста( в байтах),
// как только достигнет конца файла, то вернет (-1).

        int i ;
        while((i = fileInputStream.read()) != -1){
            System.out.print((char)i);
        }

        // Java
//        Scanner scanner = new Scanner(fileInputStream);
//        StringBuilder builder = new StringBuilder();
//
//        while (scanner.hasNextLine()) {
//            builder.append(scanner.nextLine()).append("\n");
//        }
//        System.out.print(builder.toString());

        //закрываем потоки
        fileInputStream.close();
        reader.close();

    }
}