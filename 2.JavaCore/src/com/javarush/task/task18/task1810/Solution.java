package com.javarush.task.task18.task1810;

import java.io.*;
import java.util.ArrayList;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inputFile = reader.readLine();

            ArrayList<Integer> arrayList = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(inputFile);

            while (fileInputStream.available() > 0) {
                int currByte = fileInputStream.read();
                arrayList.add(currByte);
            }

            if (arrayList.size() < 1000) {
                fileInputStream.close();
                throw new DownloadException();
            }
        }
    }

    public static class DownloadException extends Exception {


    }
}
