package com.javarush.task.task09.task0923;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Гласные и согласные
*/

public class Solution {
    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();

        ArrayList<Character> line_one = new ArrayList<>();
        ArrayList<Character> line_two = new ArrayList<>();
        char[] chars = string.toCharArray();
        for (char a_char : chars) {
            if (a_char == ' ') {
                continue;
            }
            if (isVowel(a_char)) {
                line_one.add(a_char);
            } else {
                line_two.add(a_char);
            }
        }
        for (char each : line_one) {
            System.out.print(Character.toString(each) + ' ');
        }
        System.out.println();
        for (char each : line_two) {
            System.out.print(Character.toString(each) + ' ');
        }

        //Java
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        for (char character : string.toCharArray()) {
            if (isVowel(character)) {
                buffer1.append(character).append(" ");
            } else if (character != ' ') {
                buffer2.append(character).append(" ");
            }
        }
        System.out.println(buffer1);
        System.out.println(buffer2);

    }

    // метод проверяет, гласная ли буква
    public static boolean isVowel(char character) {
        character = Character.toLowerCase(character);  // приводим символ в нижний регистр - от заглавных к строчным буквам
        for (char vowel : vowels) {  // ищем среди массива гласных
            if (character == vowel) {
                return true;
            }
        }
        return false;
    }
}