package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        TreeMap<Integer, String> files_names = new TreeMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readString = reader.readLine();
        String result = readString.split("\\.part")[0];

        while (!(readString.equals("end"))) {
            int part = Integer.parseInt(readString.split("\\.part")[1]) ;
            files_names.put(part, readString);
            readString = reader.readLine();
        }

        for (Map.Entry<Integer, String> pair : files_names.entrySet()) {
//            System.out.println(pair.getKey() + " " + pair.getValue());
            String file_donor = pair.getValue();

            FileInputStream inStream = new FileInputStream(file_donor);
            FileOutputStream outStream = new FileOutputStream(result);
            byte[] buffer = new byte[inStream.available()];
            while (inStream.available() > 0)
            {
                int count = inStream.read(buffer);
                outStream.write(buffer, 0, count);
            }
            inStream.close();
            outStream.close();
        }
    }
}
