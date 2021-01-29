package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/* 
Конвертер систем счислений
https://streletzcoder.ru/perevod-sistem-schisleniya-s-pomoshhyu-klassa-biginteger-ili-kak-perevesti-chislo-v-druguyu-sistemu-schisleniya-s-pomoshhyu-odnoy-sroki-koda/
*/

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef

//        number = new Number(NumberSystemType._2, "120");
//        result = convertNumberToOtherNumberSystem(number, NumberSystemType._10);
//        System.out.println(result);
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        //напишите тут ваш код
        if (number.getDigit().contains(".") || number.getDigit().isEmpty() || number.getDigit().contains("-")) throw new NumberFormatException();

        BigInteger bigInteger_1 = new BigInteger(number.getDigit(), number.getNumberSystem().getNumberSystemIntValue());
        String s = bigInteger_1.toString(expectedNumberSystem.getNumberSystemIntValue());
        BigInteger bigInteger_2 = new BigInteger(s, expectedNumberSystem.getNumberSystemIntValue());

        return new Number(expectedNumberSystem, bigInteger_2.toString(expectedNumberSystem.getNumberSystemIntValue()));
    }
}
