package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
//        String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
//        String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
//        String DIGIT = "0123456789";
//        String PASSWORD_ALLOW = CHAR_LOWERCASE + CHAR_UPPERCASE + DIGIT;
        int PASSWORD_LENGTH = 8;


        StringBuilder result = new StringBuilder(PASSWORD_LENGTH);

        ArrayList<Byte> byteArrayList = new ArrayList<>();
        int lower = (int) (Math.random() * 8);
        System.out.println();
        for (int i = 0; i < 3; i++) {
            byteArrayList.add(String.valueOf(randomChar('A','Z')).getBytes()[0]);
            byteArrayList.add(String.valueOf(randomChar('a','z')).getBytes()[0]);
            byteArrayList.add(String.valueOf(randomChar('0','9')).getBytes()[0]);

        }
        return null;
    }

    public static char randomChar(char min, char max){
        return ((char) (Math.random() * (max - min + 1) + min));
    }
}
