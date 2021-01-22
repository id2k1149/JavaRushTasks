package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) {
        String resultFileName = args[0];

        Vector<FileInputStream> files = new Vector<>();
        Arrays.sort(args, 1, args.length);

        try {
            for(int i = 1; i < args.length; i++) {
                files.addElement(new FileInputStream(args[i]));
            }
        } catch (FileNotFoundException exc) {}

        try (ZipInputStream zip = new ZipInputStream(new SequenceInputStream(files.elements()));
             FileOutputStream fos = new FileOutputStream(resultFileName)) {
            byte[] buffer = new byte[2048];
            int len;
            while (zip.getNextEntry() != null) {
                while ((len = zip.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
        } catch (IOException exc) {}
    }
}
