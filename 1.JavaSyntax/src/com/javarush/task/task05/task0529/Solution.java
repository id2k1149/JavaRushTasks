package com.javarush.task.task05.task0529;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Консоль-копилка
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int number;
        int summa = 0;

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s = buffer.readLine();
            if (s.equals("сумма")){
                System.out.println(summa);
                break;
            }
            else {
                number = Integer.parseInt(s);
                summa += number;
            }
        }
    }
}
