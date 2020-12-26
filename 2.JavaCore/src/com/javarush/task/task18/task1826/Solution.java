package com.javarush.task.task18.task1826;

import java.io.*;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream;
        FileOutputStream outputStream;

        if (args.length != 0) {
            inputStream = new FileInputStream(args[1]);
            outputStream = new FileOutputStream(args[2]);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 200);

            int i;
            switch (args[0]) {
                case "-e": {
                    while((i = bufferedInputStream.read())!= -1){
                        outputStream.write(i+1);
                    }
                    break;
                }
                case "-d": {
                    while((i = bufferedInputStream.read())!= -1){
                        outputStream.write(i-1);
                    }
                    break;
                }
            }
            inputStream.close();
            outputStream.close();
        }
    }
}
