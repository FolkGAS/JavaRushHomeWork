package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {

    private Solution (){}
    private Solution (long l){}
    private Solution (long l, long ll){}
    protected Solution(int i){}
    protected Solution(int i, int ii){}
    protected Solution(int i, int ii, int iii){}
    Solution (String st){}
    Solution (String st, String stst){}
    Solution (String st, String stst, String ststst){}
    public Solution (boolean b){}
    public Solution (boolean b, boolean bb){}
    public Solution (boolean b, boolean bb, boolean bbb){}
}

