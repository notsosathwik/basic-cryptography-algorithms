package basic_algos;

import java.util.Scanner;

public class FermatsLittleTheorem {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Hello..! This program is a simple " +
                "implementation of Fermat's little theorem.\nFormat: If " +
                "x^(p-1) % p =1 then p is prime.\nEnter a number 'p' to check for primality: ");
        int p= scan.nextInt();
        if(fermat(p)){
            System.out.println(p+" is prime.");
        }
        else{
            System.out.println(p+" is NOT prime.");
        }
    }
    public static boolean fermat(int p){
        int x=2;
//        You can comment out declared x value and give your desired x value by executing below three statements.
//        Scanner sc=new Scanner(System.in);
//        System.out.println("Enter the number 'x': ");
//        int x=sc.nextInt();
        int pow= (int) Math.pow(x,p-1);
        return pow % p == 1;
    }
}
