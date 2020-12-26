package com.javarush.task.task18.task1818;

import java.io.*;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName_1 = bufferedReader.readLine();
        String fileName_2 = bufferedReader.readLine();
        String fileName_3 = bufferedReader.readLine();
        bufferedReader.close();

        FileOutputStream outputStream = new FileOutputStream(fileName_1);
        FileInputStream inputStream_1 = new FileInputStream(fileName_2);
        FileInputStream inputStream_2 = new FileInputStream(fileName_3);

        while (inputStream_1.available() > 0) {
            int data = inputStream_1.read();
            outputStream.write(data);
        }

        while (inputStream_2.available() > 0) {
            int data = inputStream_2.read();
            outputStream.write(data);
        }

        outputStream.close();
        inputStream_1.close();
        inputStream_2.close();
    }
}

