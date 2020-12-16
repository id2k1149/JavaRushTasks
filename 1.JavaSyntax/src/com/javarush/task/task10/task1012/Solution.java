package com.javarush.task.task10.task1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Количество букв
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Алфавит
        List<Character> alphabet = Arrays.asList(
                'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж',
                'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о',
                'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц',
                'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я');

        // Ввод строк
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String line = reader.readLine();
            list.add(line.toLowerCase());
        }

        // напишите тут ваш код
        for (char each: alphabet) {
            int count = 0;
            for (String word: list){
                while (word.length()>0){
                    char letter = word.charAt(0);
                    if (letter == each) count++;
                    word = word.substring(1);
                }
            }
            System.out.println(each + " " + count);
        }

        // Java
        //подсчет количества букв
        int[] count = new int[alphabet.size()];
        for (String line : list) {
            for (char character : line.toCharArray()) {
                int index = alphabet.indexOf(character);
                if (index < 0) {
                    continue;
                }

                count[index]++;
            }
        }
        //вывод результата
        for (int i = 0; i < alphabet.size(); i++) {
            char character = alphabet.get(i);
            System.out.println(character + " " + count[i]);
        }

    }
}
