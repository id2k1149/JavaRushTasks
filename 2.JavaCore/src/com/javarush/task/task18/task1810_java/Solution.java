package com.javarush.task.task18.task1810_java;

import java.io.*;
import java.util.ArrayList;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String fileName = reader.readLine();

            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                if (fileInputStream.available() < 1000) throw new DownloadException();
            }
        }
    }

    public static class DownloadException extends Exception {
    }
}
