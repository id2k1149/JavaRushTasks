package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        int n = crossword.length;
        int m = crossword[0].length;

//        int nn = n + 2;
//        int mm = m + 2;
//        int[][] b = new int[nn][mm];
//        for (int i = 0; i < nn ; i++) {
//            for (int j = 0; j < mm; j++) {
//                if (i == 0 || i == nn -1 || j == 0 || j == mm -1 ) b[i][j] = 0;
//                else b[i][j] = crossword[i-1][j-1];
//            }
//        }

        ArrayList<String> words_list = new ArrayList<>();
        for (String each: words) {
            words_list.add(each);
        }
        for (String each: words_list) {
            System.out.println(each);
            char[] letters = each.toCharArray();
            while (true) {
                letter_check(crossword, letters[0]);
            }


        }


        return null;
    }

    public static int letter_check(int[][] crossword, char letter){
        int n = crossword.length;
        int m = crossword[0].length;
        int[][] find_letter = new int[1][1];
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                if (crossword[i][j] == letter) {
                    find_letter[0][0] = {i, j};
                }

            }
        }



        return ;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
