package com.javarush.task.task18.task1824;

import java.io.*;

/* 
Файлы и исключения
*/

public class Solution {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";
        FileInputStream inputStream = null;

        while(true){
            try{
                fileName = reader.readLine();
                inputStream = new FileInputStream(fileName);
                inputStream.close();
            } catch (FileNotFoundException e) {
                System.out.println(fileName);
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
