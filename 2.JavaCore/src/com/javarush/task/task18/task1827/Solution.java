package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        String mode = args[0];
        if (!(mode.equals("-c")) || args.length == 0) return;
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            for (int i = 1; i < args.length; i+=3) {

            }


        }


    }
}
