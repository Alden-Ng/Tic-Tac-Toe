/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_1;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author alden
 */
public class Dice {

    // creating instance variables
    private int[] die;
    private Random random;

    // default method creates two dice
    public Dice() {
        this.die = new int[2];
        this.random = new Random(new Date().hashCode());
        for (int i = 0; i<this.die.length; i++){
            this.die[i] = rollDie();
        }
    }

    // method can set the number of dice to any number > 1
    public Dice(int numDice) {
        if (numDice < 1) {
            throw new IllegalArgumentException("Number of dice must be greater than 1");
        }
        this.die = new int[numDice];
        this.random = new Random(new Date().hashCode());
    }

    //rolls all the dice and gives the sum of all the dice values
    public int roll() {
        int sum = 0;
        for (int i = 0; i < this.die.length; i++) {
            this.die[i] = rollDie();
            sum += this.die[i];
        }
        return sum;
    }

    //rolls a dice value between 1 and 6
    private int rollDie() {
        return random.nextInt(6) + 1;
    }

    // gives a copy of the value of the dice
    public int[] getDieValues() {
        int copyDie[] = this.die.clone();
        return copyDie;
    }

    //checks whether there are two dice with the same value
    public boolean hasDoubles() {
        for (int i = 0; i < this.die.length; i++) {
            for (int j = 0; j < this.die.length; j++) {
                if (this.die[i] == this.die[j] && i != j) {
                    return true;
                }
            }
        }
        return false;
    }

    // prints out all the dice values
    @Override
    public String toString() {
        String sDice = "";
        for (int i = 0; i<this.die.length;i++){
             sDice += Integer.toString(this.die[i])+ " ";
        }
        return sDice;
    }
}
