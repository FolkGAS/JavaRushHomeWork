package com.javarush.test.level25.lesson02.task02;

import java.util.ArrayList;
import java.util.List;

/* Машину на СТО не повезем!
Инициализируйте поле wheels используя данные из loadWheelNamesFromDB.
Обработайте некорректные данные.
Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            if (loadWheelNamesFromDB().length != 4) throw new IllegalArgumentException();
            wheels = new ArrayList<>();
            for (String str : loadWheelNamesFromDB())
                for (Wheel wheel : Wheel.values())
                    if (wheel.toString().equals(str) && !wheels.contains(wheel))
                        wheels.add(wheel);
            if (wheels.size() != 4) throw new IllegalArgumentException();
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }
}