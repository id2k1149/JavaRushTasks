package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

//    public static String decode(StringReader reader, int key) throws IOException {
//        if (reader == null) return "";
//
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        StringBuilder stringBuilder = new StringBuilder();
//        String line;
//        while ((line = bufferedReader.readLine()) != null)
//        {
//            stringBuilder.append(line);
//        }
//
//        String result = stringBuilder.toString();
//        char[] chars = result.toCharArray();
//        stringBuilder = new StringBuilder();
//        for (char each: chars) {
//            stringBuilder.append((char)(each + key));
//        }
//        result = stringBuilder.toString();
//        return result;
//    }

//    public static String decode(StringReader reader, int key) throws IOException {
//        if (reader == null) return "";
//
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int c = reader.read(); c != -1; c = reader.read()) {
//            stringBuilder.append((char) (c + key));
//        }
//        return stringBuilder.toString();
//    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null) return "";

        int oneByte;
        StringWriter writer = new StringWriter();
        while ((oneByte = reader.read()) != -1 ){
            writer.write(oneByte + key);
        }
        return writer.toString();
    }
}
