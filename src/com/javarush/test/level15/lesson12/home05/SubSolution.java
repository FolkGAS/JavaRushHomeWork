package com.javarush.test.level15.lesson12.home05;

public class SubSolution extends Solution
{
    private SubSolution(long i)
    {
        super("");
    }

    private SubSolution(long i, long ii)
    {
        super("i, ii");
    }

    private SubSolution(long i, long ii, long iii)
    {
        super("i, ii, iii");
    }
    protected SubSolution(int i)
    {
        super(i);
    }

    protected SubSolution(int i, int ii)
    {
        super(i, ii);
    }

    protected SubSolution(int i, int ii, int iii)
    {
        super(i, ii, iii);
    }

    SubSolution(String st)
    {
        super(st);
    }

    SubSolution(String st, String stst)
    {
        super(st, stst);
    }

    SubSolution(String st, String stst, String ststst)
    {
        super(st, stst, ststst);
    }

    public SubSolution(boolean b)
    {
        super(b);
    }

    public SubSolution(boolean b, boolean bb)
    {
        super(b, bb);
    }

    public SubSolution(boolean b, boolean bb, boolean bbb)
    {
        super(b, bb, bbb);
    }
}
