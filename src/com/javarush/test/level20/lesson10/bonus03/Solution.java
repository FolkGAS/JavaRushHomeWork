package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
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
        List<Word> wordsInCross = new ArrayList<>();
        wordsInCross = detectAllWords(crossword, "home", "same", "rr");
        for (Word w : wordsInCross)
            System.out.println(w);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> wordsInCross = new ArrayList<>();
        int numWords = words.length, crossX = crossword[0].length + 2, crossY = crossword.length + 2,
                startX = 0, startY = 0, endX = 0, endY = 0, storeX = 0, storeY = 0;
        int [][] cross = new int[crossY][crossX],
                delta = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
        boolean br = false;
        for (int y = 0; y < crossY-2; y++)
            for (int x = 0; x < crossX-2; x++)
                cross[y+1][x+1] = crossword[y][x];
        for (int iWord = 0; iWord < numWords; iWord++){
            for (int y = 1; y < crossY-1; y++)
                for (int x = 1; x < crossX-1; x++)
                    if (cross[y][x] == words[iWord].charAt(0)){
                        startX = x;
                        startY = y;
                        for (int d = 0; d < delta.length; d++)
                            if (cross[y + delta[d][0]][x + delta[d][1]] == words[iWord].charAt(1)){
                                storeX = x;
                                storeY = y;
                                br = false;
                                for (int ch = 1; ch < words[iWord].length(); ch++)
                                    if (cross[y + delta[d][0]][x + delta[d][1]] == words[iWord].charAt(ch) && !br){
                                        x = x + delta[d][1];
                                        y = y + delta[d][0];
                                        if (ch == words[iWord].length() - 1){
                                            endX = x;
                                            endY = y;
                                            Word w = new Word(words[iWord]);
                                            w.setStartPoint(startX-1, startY-1);
                                            w.setEndPoint(endX-1, endY-1);
                                            wordsInCross.add(w);
                                            x = storeX;
                                            y = storeY;
                                        }
                                    }else{
                                        x = storeX;
                                        y = storeY;
                                        br = true;
                                        break;
                                    }
                            }
                    }

        }
        return wordsInCross;
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
