package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            list.add(reader.readLine());
        }
        
        int short_line_length = list.get(0).length();
        int short_index = 0;
        int long_line_length = list.get(0).length();
        int long_index = 0;

        for (int i = 0; i < 10; i++) {
            if(list.get(i).length() < short_line_length){
                short_line_length = list.get(i).length();
                short_index = i;
//                System.out.println("index short " + short_index);
            }
            if(list.get(i).length() > long_line_length){
                long_line_length = list.get(i).length();
                long_index = i;
//                System.out.println("index long " + long_index);
            }
        }

        if (short_index < long_index) System.out.println(list.get(short_index));
        else System.out.println(list.get(long_index));

    }
}
