package basic_algos;

import java.util.Random;
import java.util.Scanner;

public class MillerRabin {

    public static void main(String args[]){
        int n;
        System.out.println("Hello..! " +
                "This program is a simple implementation of Miller-Rabin primality " +
                "test to check whether a number is probably prime " +
                "or not.\nFormat: n-1 = 2^k * m \nEnter a number 'n' to check for primality: ");
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        if(n%2!=0) {
            millerrabin(n);
        }
        else{
            System.out.println(n + " is not prime");
        }
    }

    private static void millerrabin(int n) {
        double tempm = 0;
        int m = 1;
        int k = 1;
        boolean primality=false;
        int a;
        double b,b2;

        Random rand = new Random();
        a = rand.nextInt((int)(n-1));

        while (true) {
            tempm = (n - 1) / Math.pow(2, k);
            if (tempm%1!=0) {
                break;
            }
            m = (int)tempm;
            k++;
        }
        System.out.println("Computed k: " + k + " and m: " + m);

        a=2;
        b = Math.pow(a, m) % n;
        if (b == 1) {
            System.out.println(n + " is Prime");
        }
        else {
            for (int j = 0; j < k - 1; j++) {
                b2 = Math.pow(a, (Math.pow(2, j) * m)) % n;
                if ( b2== n - 1) {
                    System.out.println(n + " is Prime");
                    primality = true;
                }
            }
            if (!primality) {
                System.out.println(n + " is not Prime");
            }
        }
    }
}