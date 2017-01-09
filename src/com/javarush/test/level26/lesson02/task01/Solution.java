package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        double mean = array[0];
        Arrays.sort(array);
        if (array.length % 2 != 0)
            mean = array[array.length/2];
        else mean = (array[array.length/2 - 1] + array[array.length/2])/2.0;
        final double m = mean;
        Arrays.sort(array, new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                double check = Math.abs(o1 - m) - Math.abs(o2 - m);
                if (check < 0.001 && check > -0.001)
                    check = o1 - o2;
                return (int)check;
            }
        });
        return array;
    }

}
