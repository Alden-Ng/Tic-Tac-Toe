/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_1;

import static java.lang.Math.sqrt;

/**
 *
 * @author alden
 */
public class DiceClient {
    /**
     *
     * @param args
     */
    
    //method to find the standard deviation of a list of numbers
     private static double std_dev1(int a[], int n){
        if(n==0){
            return 0.0;
        }
        double sum = 0;
        for (int i = 0; i < n; i++){
            sum+=a[1];
        }
        double mean = sum/n;
        double sq_diff_sum = 0;
        for(int i = 0; i < n; i++){
            double diff = a[i] - mean;
            sq_diff_sum += diff * diff;
        }
        double variance = sq_diff_sum / n;
        return sqrt(variance);
    }
    
    
    
    public static void main(String[] args){
        
        //creating 2000 pair of dice (4000 dice)
        Dice dice = new Dice(4000);
        double sum = dice.roll();   //finding the sum of 2000 rolled dice
        int listDice[] = dice.getDieValues();
        double std_dev = std_dev1(listDice, 4000);  //standard deivation of dice method
        
        //showcasing the outputs
        System.out.println("The average roll was "+ sum/4000);
        System.out.println("The standard deviation of the rolls was " + std_dev);
        System.out.println("The histogram of the rolls is:");
        
        //creating bins for histogram
        int bin1 = 0, bin2 = 0, bin3 = 0, bin4 = 0, bin5 = 0, bin6 = 0;
        //creating histogram
        for (int i = 0; i < 4000; i ++){
            //puts roll values into their respective bins
            switch (listDice[i]) {
                case 1:
                    bin1++;
                    break;
                case 2:
                    bin2++;
                    break;
                case 3:
                    bin3++;
                    break;
                case 4:
                    bin4++;
                    break;
                case 5:
                    bin5++;
                    break;
                case 6:
                    bin6++;
                    break;
                default:
                    break;
            }
        }
        
        //printing the "*" to represent every 10 rolls per bin
        System.out.print("1("+bin1+") :");
        for (int i = 0; i < bin1/10; i++){
            System.out.print("*");
        }
        System.out.print("\n2("+bin2+") :");
        for (int i = 0; i < bin2/10; i++){
            System.out.print("*");
        }
        System.out.print("\n3("+bin3+") :");
        for (int i = 0; i < bin3/10; i++){
            System.out.print("*");
        }
        System.out.print("\n4("+bin4+") :");
        for (int i = 0; i < bin4/10; i++){
            System.out.print("*");
        }
        System.out.print("\n5("+bin5+"):");
        for (int i = 0; i < bin5/10; i++){
            System.out.print("*");
        }
        System.out.print("\n6("+bin6+") :");
        for (int i = 0; i < bin6/10; i++){
            System.out.print("*");
        }
        System.out.println();
    }
}
