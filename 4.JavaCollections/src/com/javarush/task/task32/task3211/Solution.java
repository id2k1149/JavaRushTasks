package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        //Creating the MessageDigest object
        MessageDigest md = MessageDigest.getInstance("MD5");

        //Passing data to the created MessageDigest Object
        byte[] bytes = byteArrayOutputStream.toByteArray();
        md.update(bytes);

        //Compute the message digest
        byte[] digest = md.digest();

        StringBuilder stringBuilder = new StringBuilder();
        // convert the byte to hex format
        for (int i = 0; i < digest.length; i++) {
            String string = Integer.toHexString(0xff & digest[i]);
            string = (string.length() == 1) ? "0" + string : string;
            stringBuilder.append(string);
        }

        // convert the byte to hex format
//        for(byte each : digest) {
//            stringBuilder.append(String.format("%02x", each & 0xff));
//        }

        System.out.println("Hex format: " + stringBuilder.toString());
        return stringBuilder.toString().equals(md5);
    }
}
