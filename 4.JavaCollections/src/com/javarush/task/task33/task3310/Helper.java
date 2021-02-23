package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {

    public static String generateRandomString() {
        SecureRandom secureRandom = new SecureRandom();
        // первым параметром задаёться максимальное колличество бит этого BigInteger
        // (что в итоге определяеться максимальным колличеством символов в строке
        // и в нашей задаче может быть произвольным, я поставил 100 и это было равно 20
        // символам), а второй параметр - это наш генератор "рандомностей".
        // .toString(36) - это приводит число к системе исчисления с корнем 36.
        // В это системе исчисления используются все буквы английского алфавита
        // (напрмер в 16 системе используются только A, B, C, D, E, F)
        return new BigInteger(130, secureRandom).toString(36);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
