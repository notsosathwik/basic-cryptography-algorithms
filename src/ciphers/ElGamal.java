package ciphers;

import java.util.Scanner;

public class ElGamal {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello..! This program implements ElGamal Cryptosystem. Choose" +
                " the operation you want to perform:\n1.Encryption\n2.Decryption");
        int choice = scan.nextInt();
        scan.nextLine();
        if (choice == 1) {
            encrypt();
        } else if (choice == 2) {
            decrypt();
        } else {
            System.out.println("Invalid choice. Enter either 1 or 2 ONLY.");
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static void encrypt() {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter the prime number p: ");
        int p = sc1.nextInt();
        if (!isPrime(p)) {
            System.out.println("The number p must be a prime.");
        } else {
            System.out.println("Enter the private key d(Such that GCD of d and p must equal to 1): ");
            int d = sc1.nextInt();
            if (findGCD(p, d) == 1) {
                System.out.println("Enter the second part of encryption/public key e1: ");
                int e1 = sc1.nextInt();
                if (e1 <= 1 || d <= 1) {
                    System.out.println("d and e1 MUST be greater than 1");
                } else {
                    int pow1 = (int) Math.pow(e1, d);
                    int e2 = pow1 % p;
                    System.out.println("Public key trio:\n(e1,e2,p) = (" + e1 + "," + e2 + "," + p + ")");
                    System.out.println("Enter the value of plaintext(MUST be an integer greater than zero!!!): ");
                    int plain = sc1.nextInt();
                   // int lowRand;
                  //  double rand = Math.random() * 10000;
                  //  lowRand = (int) (rand % 100);
        //above three lines generate truly random numbers. you can use it and comment out below three lines if you want.
                    Scanner sc=new Scanner(System.in);
                    System.out.println("Enter your desired random integer: ");
                    int lowRand=sc.nextInt();

                    int pow_e1 = (int) Math.pow(e1, lowRand);
                    int pow_e2 = (int) Math.pow(e2, lowRand);
                    int c1 = pow_e1 % p;
                    int c2 = (pow_e2 * plain) % p;
                    System.out.println("Generated random integer R = " + lowRand);
                    System.out.println("Cipher (c1,c2) = (" + c1 + "," + c2 + ")");
                }
            }
            else{
                System.out.println("Sorry. GCD of the entered numbers was not 1. (Computed GCD = "+findGCD(p,d)+")");
            }
        }
    }

    public static void decrypt() {
        System.out.println("Enter the prime number p: ");
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        if (!isPrime(p)) {
            System.out.println("The number p must be a prime.");
        } else {
            System.out.println("Enter the private key d: ");
            int d = sc.nextInt();
            System.out.println("Enter the cipher value c1: ");
            int c1 = sc.nextInt();
            System.out.println("Enter the cipher value c2: ");
            int c2 = sc.nextInt();
            if (d <= 1 || c1 <= 0 || c2 <= 0) {
                System.out.println("d must be greater than 1 & c1,c2 must be greater than zero.");
            } else {
                int pow_d1 = (int) Math.pow(c1, d);
                int inv = inverseValue(pow_d1, p);
                int firstpart = c2 % p;
                int finalVal = (firstpart * inv) % p;
                System.out.println("Plaintext value: " + finalVal);
            }
        }
    }

    public static int inverseValue(double a, double b) {
        int m = (int) b, t, q;
        int x = 0, y = 1;

        if (b == 1) {
            return 0;
        }

        while (a > 1) {
            q = (int) (a / b);
            t = (int) b;

            b = a % b;
            a = t;
            t = x;
            x = y - q * x;
            y = t;
        }

        if (y < 0) {
            y += m;
        }
        return y;
    }

    public static int findGCD(int a, int b) {
        while (b != 0) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }
}
