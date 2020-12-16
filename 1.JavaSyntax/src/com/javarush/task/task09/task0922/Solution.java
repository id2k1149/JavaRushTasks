package com.javarush.task.task09.task0922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Какое сегодня число?
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код

//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String strDate = reader.readLine();
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = formatter.parse(strDate);
//        SimpleDateFormat new_formatter = new SimpleDateFormat("MMM dd, yyyy");
//        strDate = new_formatter.format(date);
//        System.out.println(strDate.toUpperCase());

        // Java
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = reader.readLine();

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = inputFormat.parse(inputString);

        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        System.out.println(outputFormat.format(date).toUpperCase());

    }
}
