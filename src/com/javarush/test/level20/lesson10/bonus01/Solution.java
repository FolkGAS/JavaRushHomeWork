package com.javarush.test.level20.lesson10.bonus01;

import java.util.TreeSet;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static int[] getNumbers(int N) {
        int[][] pows = new int[11][11];
        int[] dec = new int[11], fors = new int[10];
        int num, num_pows, digits, digPows, arm = 0;
        TreeSet<Integer> res = new TreeSet<>();
        for (int j = 1; j < 11; j++){
            for (int i = 0; i < 11; i++)
                pows[j][i] = (int) Math.pow(i, j);
            dec[j] = (int) Math.pow(10, j);
        }
        for (fors[9] = 0; fors[9] < 10; fors[9]++){
        for (fors[8] = fors[9]; fors[8] < 10; fors[8]++){
        for (fors[7] = fors[8]; fors[7] < 10; fors[7]++){
        for (fors[6] = fors[7]; fors[6] < 10; fors[6]++){
        for (fors[5] = fors[6]; fors[5] < 10; fors[5]++){
        for (fors[4] = fors[5]; fors[4] < 10; fors[4]++){
        for (fors[3] = fors[4]; fors[3] < 10; fors[3]++){
        for (fors[2] = fors[3]; fors[2] < 10; fors[2]++){
        for (fors[1] = 0; fors[1] < 10; fors[1]++){
        for (fors[0] = 0; fors[0] < 10; fors[0]++){
            num = fors[0];
            for (int i = 0; i < 10; i++)
                num += fors[i] * dec[i];
            if (num < 1 || num > N) num = 1;
            digits = String.valueOf(num).length();
            num_pows = 0;
            for (int i = 0; i < 10; i++)
                num_pows += pows[digits][fors[i]];
            if (num_pows < 1 || num_pows > N) num_pows = 1;
            digPows = String.valueOf(num_pows).length();
            if (digits == digPows){
                for (int i = 0; i < digPows; i++)
                    arm += pows[digPows][Integer.parseInt(String.valueOf(num_pows).substring(i, i+1))];
                if (arm == num_pows){
                    res.add(num_pows);
                }
                arm = 0;
            }
        }}}}}}}}}}
        int[] result = new int[res.size()];
        int j = 0;
        for (Integer i : res){
            result[j] = i;
            j++;
        }
        return result;
    }

}
