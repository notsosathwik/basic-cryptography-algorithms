package ciphers;

import java.util.Scanner;

public class RSA {

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        boolean pPrime= false;
        boolean qPrime= false;
        int q= -1;
        int p= 2;
        long m, c, choice;
        int n= -1, d= -1, e= -1;
        System.out.println("Hello..! This program implements RSA Algorithm" +
                " for encrypting or decrypting the data.\nFormat: c = t^e mod n\n(Where " +
                "c = ciphertext and t = plaintext)\nChoose the desired" +
                " operation:\n1.Encryption\n2.Decryption");
        choice= scan.nextInt();

        if (choice == 1) {
            while (n == -1) {
                System.out.println("Enter n: ");
                n= scan.nextInt();
            }

            while (e == -1) {
                System.out.print("Choose e: ");
                e= scan.nextInt();
                d= computeD(p, q, e);
            }

            System.out.print("Enter the plaintext: ");
            m= scan.nextInt();
            c= encrypt(m, e, n);
            System.out.print("Ciphertext: " + c);

        }

        else if (choice == 2) {

            while (!pPrime && !qPrime) {
                System.out.println("Choose primes p and q: ");
                q= scan.nextInt();
                p= scan.nextInt();
                n= p * q;

                if (!isPrime(p) || !isPrime(q))
                    System.out.print("Error. A value was not" +
                            " prime. Choose again: ");
                else if (isPrime(p) && isPrime(q)) {
                    pPrime= true;
                    qPrime= true;
                }
            }

            while (d == -1) {
                System.out.print("Choose  e: ");
                e= scan.nextInt();
                d= computeD(p, q, e);

                if (d == -1) {
                    System.out.println("Error");
                } else {
                    System.out.println("Secret private key is: " + d);
                }

            }

            System.out.print("Enter the ciphertext: ");
            c= scan.nextInt();
            m= decode(c, d, n);
            System.out.print("Plaintext: " + m);
        }

        scan.close();
    }
    public static boolean isPrime(int x) {
        for (int i= 2; i < x / 2; i++ ) {
            if (x % i == 0) { return false; }
        }
        return true;
    }

    public static long encrypt(long m, int publicE, int publicN) {
        return fastExpo(m, publicE, publicN);
    }

    public static long decode(long c, int privateD, int publicN) {
        return fastExpo(c, privateD, publicN);
    }

    public static int computeD(int p, int q, int e) {
        int phi= (p - 1) * (q - 1);
        return modInverse(e, phi);
    }

    public static int modInverse(int e, int phi) {
        e= e % phi;
        for (int d= 1; d < phi; d++ )
            if (e * d % phi == 1)
                return d;
        return -1;
    }

    public static long fastExpo(long x, int y, int n) {
        long res= 1;

        x= x % n;

        if (x == 0) return 0;

        while (y > 0) {
            if ((y & 1) == 1)
                res= res * x % n;

            y= y >> 1;
            x= x * x % n;
        }
        return res;
    }
}