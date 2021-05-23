package ciphers;

import java.util.Scanner;
import java.lang.Math;

public class Rabin {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Hello..! This program implements Rabin Cryptosystem. Choose" +
                " the operation you want to perform:\n1.Encryption\n2.Decryption");
        int choice=scan.nextInt();
        scan.nextLine();
        if(choice==1){
            System.out.println("Enter the plaintext(Must be a number!!): ");
            int plain=scan.nextInt();
            System.out.println("Enter the value of p: ");
            int p= scan.nextInt();
            System.out.println("Enter the value of q: ");
            int q= scan.nextInt();
            if(!isPrime(p) || !isPrime(q)){
                System.out.println("Error has occurred. p and q MUST be primes.");
            }
            else{
                System.out.println("Public key: "+publickey(p,q));
                System.out.println("Private keys: "+p+" and "+q);
                System.out.println("Ciphertext: " + encrypt(plain,p,q));
            }

        }
        else if(choice==2){
            System.out.println("Enter the ciphertext(Must be a number!!): ");
            int cipher=scan.nextInt();
            System.out.println("Enter the value p: ");
            int p= scan.nextInt();
            System.out.println("Enter the value q: ");
            int q= scan.nextInt();
            if(!isPrime(p) || !isPrime(q)){
                System.out.println("Error has occurred. p and q MUST be primes.");
            }
            else{
                System.out.println("Public key: "+publickey(p,q));
                System.out.println("Private keys: "+p+" and "+q);
                decrypt(cipher,p,q);
            }

        }
        else {
            System.out.println("Invalid choice. Enter either 1 or 2 ONLY.");
        }
    }
    public static int encrypt(int plaintext, int p,int q){
        int power= (int) Math.pow(plaintext,2);
        return power % publickey(p,q);
    }
    public static int decrypt(int cipher, int p, double q){
        double a1,a2,b1,b2,pow1,pow2;
        pow1= Math.pow(cipher,(p+1)/4);
        pow2=Math.pow(cipher,(q+1)/4);
        a1=pow1 % p;
        a2=(-pow1)%p;
        if (a2<0){
            a2=a2+p;
        }
        b1=pow2%q;
        b2=(-pow2)%q;
        if(b2<0){
            b2=b2+q;
        }
        System.out.println("Possible plaintext values: \n"+"M1: "+CRTfor2(a1,b1,p,q)+
                "\nM2: "+CRTfor2(a1,b2,p,q)+"\nM3: "+CRTfor2(a2,b1,p,q)+"\nM4: "+CRTfor2(a2,b2,p,q));
        return 0;
    }
    public static int publickey(int p,int q){
        return p*q;
    }
    public static boolean isPrime(int n)
    {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }
    public static int CRTfor2(double a,double b,double p, double q){
        int M= (int) (p*q);
        int inv1=inverseValue(q,p);
        int inv2=inverseValue(p,q);
        int finalVAL= (int) ((a* q *inv1+b* p *inv2)%M);
        return finalVAL;
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
}