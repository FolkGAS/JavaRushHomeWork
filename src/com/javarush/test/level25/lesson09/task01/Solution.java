package com.javarush.test.level25.lesson09.task01;

/* Поживем - увидим
Все исключения, которые возникают в процессе работы нити Solution, должны быть обработаны одним из вариантов:
1. Если это Error, то вывести в консоль "Нельзя дальше работать"
2. Если это Exception, то вывести в консоль "Надо обработать"
3. Если это Throwable, то вывести в консоль "ХЗ"
Реализуйте эту логику.
*/
public class Solution extends Thread {
    public Solution() {
        UncaughtExceptionHandler ueh = new com.javarush.test.level25.lesson09.task01.UncaughtExceptionHandler();
        this.setUncaughtExceptionHandler(ueh);
    }
}
