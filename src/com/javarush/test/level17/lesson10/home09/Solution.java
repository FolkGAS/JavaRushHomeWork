package com.javarush.test.level17.lesson10.home09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines
4. Если список allLines НЕ содержит каких-либо строк, которые есть в forRemoveLines, то
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        String read = "";
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String file1 = reader.readLine();
            String file2 = reader.readLine();
            try (FileReader fileReader1 = new FileReader(file1);
                 FileReader fileReader2 = new FileReader(file2);
                 BufferedReader bfr1 = new BufferedReader(fileReader1);
                 BufferedReader bfr2 = new BufferedReader(fileReader2);) {
                while ((read = bfr1.readLine()) != null) {
                    allLines.add(read);
                }
                while ((read = bfr2.readLine()) != null) {
                    forRemoveLines.add(read);
                }
            }
        } catch (IOException exc) {exc.printStackTrace();}

        Solution sol = new Solution();
        try {
            sol.joinData();
        } catch (CorruptedDataException exc) {exc.printStackTrace();}

    }

    public void joinData () throws CorruptedDataException {

        if (allLines.containsAll(forRemoveLines))
            allLines.removeAll(forRemoveLines);
            else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
