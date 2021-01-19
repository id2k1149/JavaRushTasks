package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Word> list = detectAllWords(crossword,  "home", "same");

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */

        for (Word each: list) {
            System.out.println(each.toString());
        }
    }

    enum Directions {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        RIGHT_UP,
        LEFT_UP,
        RIGHT_DOWN,
        LEFT_DOWN
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        int crosswordHeight = crossword.length;
        int crosswordWidth = crossword[0].length;
        boolean rightBorder, leftBorder, upBorder, downBorder;
        for(String s : words) {
            int firstCh = s.charAt(0);
            int wordLength = s.length();
            for(int h = 0; h < crosswordHeight; h++) {
                for(int w = 0; w < crosswordWidth; w++) {
                    rightBorder = leftBorder = upBorder = downBorder = false;
                    if(crossword[h][w] == firstCh) {

                        if((crosswordWidth - w) >= wordLength) rightBorder = true;
                        if((w + 1) >= wordLength) leftBorder = true;
                        if((h + 1) >= wordLength) upBorder = true;
                        if((crosswordHeight - h) >= wordLength) downBorder = true;

                        if(rightBorder) searchAndAdd(list, crossword, w, h, Directions.RIGHT, s);
                        if(leftBorder) searchAndAdd(list, crossword, w, h, Directions.LEFT, s);
                        if(upBorder) searchAndAdd(list, crossword, w, h, Directions.UP, s);
                        if(downBorder) searchAndAdd(list, crossword, w, h, Directions.DOWN, s);
                        if(rightBorder && upBorder) searchAndAdd(list, crossword, w, h, Directions.RIGHT_UP, s);
                        if(leftBorder && upBorder) searchAndAdd(list, crossword, w, h, Directions.LEFT_UP, s);
                        if(rightBorder && downBorder) searchAndAdd(list, crossword, w, h, Directions.RIGHT_DOWN, s);
                        if(leftBorder && downBorder) searchAndAdd(list, crossword, w, h, Directions.LEFT_DOWN, s);
                    }
                }
            }
        }
        return list;
    }

    public static void searchAndAdd(List<Word> list, int[][]crossword, int x, int y, Directions direction, String word) {
        int wordLength = word.length();
        int endX = 0;
        int endY = 0;
        boolean wordIsFind = false;
        char[] wordChars = word.toCharArray();
        for(int ch = 1; ch < wordLength; ch++) {
            switch (direction) {
                case RIGHT: endX = x + ch; endY = y; break;
                case LEFT: endX = x - ch; endY = y; break;
                case UP: endX = x; endY = y - ch; break;
                case DOWN: endX = x; endY = y + ch; break;
                case RIGHT_UP: endX = x + ch; endY = y - ch; break;
                case LEFT_UP: endX = x - ch; endY = y - ch; break;
                case RIGHT_DOWN: endX = x + ch; endY = y + ch; break;
                case LEFT_DOWN: endX = x - ch; endY = y + ch; break;
            }
            if(wordChars[ch] == (char) crossword[endY][endX])
                wordIsFind = true;
            else {
                wordIsFind = false;
                break;
            }
        }
        if(wordIsFind) {
            Word word_to_add = new Word(word);
            word_to_add.setStartPoint(x, y);
            word_to_add.setEndPoint(endX, endY);
            list.add(word_to_add);
        }
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
