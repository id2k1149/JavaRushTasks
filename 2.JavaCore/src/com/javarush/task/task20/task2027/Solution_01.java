package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution_01 {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
//                {'s', 'd', 'e', 'r', 'l', 'k'},
//                {'u', 's', 'a', 'm', 'e', 'o'},
//                {'s', 'n', 'g', 'r', 'o', 'v'},
//                {'u', 'l', 'p', 'r', 'r', 'h'},
//                {'s', 'u', 's', 'e', 'j', 'j'}
        };
        detectAllWords(crossword,  "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        int n = crossword.length;
        int m = crossword[0].length;
        int end[] = new int[2];
        List<Word> list = new ArrayList<>();

        ArrayList<String> words_list = new ArrayList<>();
        for (String each: words) {
            words_list.add(each);

        }
        for (String each: words_list) {
            System.out.println(each + " " + each.length());

            Word word = new Word(each);

            char first = each.charAt(0);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (crossword[i][j] == first) {
                        System.out.println((char) crossword[i][j] + " " + i + " " + j);
                        end = check(i, j, each, crossword);

                        word.startX = j;
                        word.startY = i;

                        System.out.println("XY --->" + end[0] + " " + end[1]);

                        word.endX = end[1];
                        word.endY = end[0];
                        list.add(word);


                    }
                }
            }
        }


        for (Word each: list) {
            System.out.println("---->" + each.toString());
        }


        return list;
    }

    public static int[] check(int i, int j, String word, int[][] crossword) {
        System.out.println("start to check " + word);

        int end[] = new int[2];
        int[][] directions = new int[][]{
                {1, 1},
                {1, 0},
                {1, -1},
                {0, -1},
                {-1, -1},
                {-1, 0},
                {-1, 1},
                {0, 1},
        };
        for (int k = 0; k < 8; k++) {
            System.out.println(directions[k][0] + " " + directions[k][1]);

            if (i + word.length()*directions[k][0] > crossword.length ||
                    j + word.length()*directions[k][1] > crossword[0].length ||
                    i + word.length()*directions[k][0] < 0 ||
                    j + word.length()*directions[k][1] < 0
            ) {
                System.out.println(" go to another direction ");
                continue;
            }
            int ii = i + directions[k][0];
            int jj = j +directions[k][1];

            char letter =  (char )(crossword[ii][jj]);
            System.out.println(letter);

            if (letter == word.charAt(1)) {
                System.out.println(" we found direction !!!!" + directions[k][0] + " " + directions[k][1]);

                end = go_this_way(directions[k][0], directions[k][1], ii, jj, word, crossword);



            }
        }

        return end;
    }

    public static int[] go_this_way(int x, int y, int ii, int jj, String word, int[][] crossword) {
        System.out.println("we go - > " + x + " " + y + " " + ii + " " + jj);
        int[] end = new int[2];
        for (int i = 2; i < word.length(); i++) {
            ii += x;
            jj += y;
            char letter = (char )(crossword[ii][jj]);
            System.out.println(letter);
            if (letter == word.charAt(i)) {
                end[0] = ii;
                end[1] = jj;
                continue;
            }
            else break;
        }
        System.out.println("we have whole word!!! " + end[0]  + " " + end[1]);
        return end;
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
