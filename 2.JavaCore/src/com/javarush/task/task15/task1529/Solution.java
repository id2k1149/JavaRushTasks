package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        //add your code here - добавьте код тут
        reset();
    }

    public static CanFly result;

    public static void reset() {
        //add your code here - добавьте код тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String string = reader.readLine();
            if (string.equals("helicopter")) result = new Helicopter();
            else if (string.equals("plane")) {
                int number = Integer.parseInt(reader.readLine());
                result = new Plane(number);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
