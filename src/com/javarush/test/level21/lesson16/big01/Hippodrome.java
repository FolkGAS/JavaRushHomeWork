package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

public class Hippodrome
{
    private ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;

    public static void main (String args[]){
        game = new Hippodrome();
        Horse alpha = new Horse("Alpha", 3, 0);
        Horse beta = new Horse("Beta", 3, 0);
        Horse gamma = new Horse("Gamma", 3, 0);
        game.getHorses().add(alpha);
        game.getHorses().add(beta);
        game.getHorses().add(gamma);
        game.run();
        game.printWinner();
    }

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public void run(){
        for (int i = 0; i < 100; i++){
            move();
            print();
            try {
                Thread.sleep(200);
            }catch (InterruptedException exc){exc.printStackTrace();}
        }
    }
    public void move(){
        for (Horse h : horses)
            h.move();
    }
    public void print(){
        for (Horse h : horses)
            h.print();
        System.out.println();
        System.out.println();
    }
    public Horse getWinner(){
        double max = 0;
        Horse winner = null;
        for (Horse h : horses)
            max = max < h.getDistance() ? h.getDistance() : max;
        for (Horse h : horses)
            if (max == h.getDistance()) winner = h;
        return winner;
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
