package com.javarush.task.task21.task2101;

/* 
Определяем адрес сети
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] netAddress = new byte[ip.length];
        for (int i = 0; i < ip.length; i++) {
            netAddress[i] = (byte) (ip[i] & mask[i]);
        }
        return netAddress;
    }

    public static void print(byte[] bytes) {
        String s = "";
        for (int i = 0; i < 4; i++) {
            int n  = bytes[i];
            if (n < 0) n = n + 256;
            int[] digits = new int[8];
            for (int j = 7; j >= 0; j--) {
                digits[j] = n % 2;
                n = (n - n % 2) / 2;
            }

            for (int digit: digits) {
                s += digit;
            }
            s += " ";
        }
        System.out.println(s);
    }
}
