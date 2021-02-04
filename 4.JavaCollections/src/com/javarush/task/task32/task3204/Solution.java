package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
//        StringBuilder result = new StringBuilder(PASSWORD_LENGTH);
        int PASSWORD_LENGTH = 8;
        int min_number_of_digits = 1;
        int min_number_of_uppers = 1;

        ArrayList<Byte> byteArrayList = new ArrayList<>();
        int number_of_lowers = (int) (Math.random() * (PASSWORD_LENGTH - min_number_of_digits - min_number_of_uppers) + 1);

        for (int i = 0; i < number_of_lowers; i++) {
            byteArrayList.add(String.valueOf(randomChar('a','z')).getBytes()[0]);
        }

        int number_of_uppers = (int) (Math.random() * (PASSWORD_LENGTH - min_number_of_digits - number_of_lowers) + 1);

        for (int i = 0; i < number_of_uppers; i++) {
            byteArrayList.add(String.valueOf(randomChar('A','Z')).getBytes()[0]);
        }

        int number_of_digits = PASSWORD_LENGTH - number_of_lowers - number_of_uppers;

        for (int i = 0; i < number_of_digits; i++) {
            byteArrayList.add(String.valueOf(randomChar('0','9')).getBytes()[0]);
        }

        Collections.shuffle(byteArrayList);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        for (byte b: byteArrayList) {
            result.write(b);

        }

        return result;
    }

    public static char randomChar(char min, char max){
        return ((char) (Math.random() * (max - min + 1) + min));
    }
}
