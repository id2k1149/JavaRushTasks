package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName_1 = bufferedReader.readLine();
        String fileName_2 = bufferedReader.readLine();
        String fileName_3 = bufferedReader.readLine();

        FileInputStream inputStream = new FileInputStream(fileName_1);
        FileOutputStream outputStream_1 = new FileOutputStream(fileName_2);
        FileOutputStream outputStream_2 = new FileOutputStream(fileName_3);


        if (inputStream.available() > 0) {
            //читаем весь файл одним куском
            byte[] buffer = new byte[inputStream.available()];


            int half = (buffer.length + 1) / 2;
            inputStream.read(buffer);
            outputStream_1.write(buffer, 0, half);
            outputStream_2.write(buffer, half, buffer.length-half);


//            int count = inputStream.read(buffer);
//            if (count % 2 == 0) {
//                outputStream_1.write(buffer, 0, count / 2);
//                outputStream_2.write(buffer, count / 2, count);
//            }
//            else {
//                outputStream_1.write(buffer, 0, (count / 2 + 1));
//                outputStream_2.write(buffer, (count / 2 + 1), count);
//            }
        }

        inputStream.close();
        outputStream_1.close();
        outputStream_2.close();
    }
}
