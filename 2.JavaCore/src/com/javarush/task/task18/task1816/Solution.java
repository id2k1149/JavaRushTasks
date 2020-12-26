package com.javarush.task.task18.task1816;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        alphabet = alphabet + alphabet.toUpperCase();

        char[] letters = alphabet.toCharArray();

        FileInputStream inputStream = new FileInputStream(args[0]);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 200);
        int i;
        int count = 0;
        while((i = bufferedInputStream.read())!= -1){
            for (char each: letters) {
                if (each == ((char) i)) count++;
            }
        }
        System.out.println(count);
        inputStream.close();
    }
}
