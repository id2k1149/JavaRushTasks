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
        reader.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        String strNumber;
        int number;

        int maxNumber = 0;
        strNumber = "";

        while (bufferedReader.ready() && strNumber != null){
            strNumber = bufferedReader.readLine();
            strNumber = strNumber.substring(0, 8).trim();
            number = Integer.parseInt(strNumber);
            if (number > maxNumber)
                maxNumber = number;
        }
        bufferedReader.close();

        maxNumber = maxNumber + 1;
        String id = String.valueOf(maxNumber);
        id = add_spaces(id, 8);


        String productName, price, quantity;
        if (args.length != 0 && args[0].equals("-c")) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName, true))
            {
                productName = add_spaces(args[1], 30);
                price = add_spaces(args[2], 8);
                quantity = add_spaces(args[3], 4);
                String result = "\n" + id + productName + price + quantity;
                fileOutputStream.write(result.getBytes());
            }
        }
    }

    public static String add_spaces (String name, int needed) {
        for (int i = name.length(); i < needed; i++) {
            name += " ";
        }
        return name;
    }
}