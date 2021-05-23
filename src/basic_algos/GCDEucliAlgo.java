package basic_algos;

import java.util.Scanner;

public class GCDEucliAlgo {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Hello..! This program finds GCD of two numbers using Euclidean Algorithm.");
        System.out.println("Enter the first number: ");
        int a = scan.nextInt();
        System.out.println("Enter the second number: ");
        int b = scan.nextInt();

        int gcd = euclideanGCD(a, b);
        System.out.printf("The GCD of "+a+" and "+b+ " is equal to %d", gcd);
        scan.close();
    }

    public static int euclideanGCD(int a, int b) {
        int x = a;
        int y = b;

        while(y != 0) {
            int remainder = x % y;
            x = y;
            y = remainder;
        }
        return x;
    }
}
