package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFile = reader.readLine();
        String outputFile = reader.readLine();

        ArrayList<Integer> arrayList = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(inputFile);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

        while (fileInputStream.available() > 0) {
            int currByte = fileInputStream.read();
            arrayList.add(currByte);
        }


        for (int i = arrayList.size() - 1; i >= 0; i--) {
                fileOutputStream.write(arrayList.get(i));
        }

        fileInputStream.close();
        fileOutputStream.close();

        //Java
//        try (FileInputStream fileInputStream = new FileInputStream(inputFile);
//             FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
//
//            List<Integer> inputBytes = new ArrayList<>();
//            while (fileInputStream.available() > 0) {
//                inputBytes.add(fileInputStream.read());
//            }
//            Collections.reverse(inputBytes);
//            for (Integer aByte : inputBytes) {
//                fileOutputStream.write(aByte);
//            }
//        }

    }
}

