package com.javarush.test.level05.lesson05.task03;

/* Геттеры и сеттеры для класса Dog
Создать class Dog. У собаки должна быть кличка String name и возраст int age.
Создайте геттеры и сеттеры для всех переменных класса Dog.
*/

import com.sun.corba.se.impl.oa.poa.POAPolicyMediatorImpl_NR_UDS;
import org.omg.CORBA.PUBLIC_MEMBER;

public class Dog {
    //добавьте тут переменные класса
    String name;
    int age;
    //добавьте тут геттеры и сеттеры
    public String getName(){
        return this.name;
    }

    public void setName (String name){
        this.name = name;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }
}
