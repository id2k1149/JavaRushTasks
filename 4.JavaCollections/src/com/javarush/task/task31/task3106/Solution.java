package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        String resultFileName = args[0];
        System.out.println(resultFileName);

        List<String> names_list = new ArrayList<>();
        int names_number = args.length;
        for (int i = 1; i < names_number; i++) {
            names_list.add(args[i]);
        }

        Collections.sort(names_list);
        for (int i = 0; i < names_list.size(); i++) {
            System.out.println(names_list.get(i));
        }

        FileOutputStream fos = new FileOutputStream(args[0]);

        FileInputStream zipFile = new FileInputStream(resultFileName);
        ZipInputStream zip = new ZipInputStream(zipFile);


    }
}
