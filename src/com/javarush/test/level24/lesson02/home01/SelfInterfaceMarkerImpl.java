package com.javarush.test.level24.lesson02.home01;

public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker
{
    int a;
    public SelfInterfaceMarkerImpl(){a = 0;}

    public SelfInterfaceMarkerImpl(int a){this.a = a;}

    public int getA()
    {
        return a;
    }

    public void setA(int a)
    {
        this.a = a;
    }
}
