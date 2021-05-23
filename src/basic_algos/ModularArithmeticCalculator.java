package basic_algos;

import java.util.Scanner;

public class ModularArithmeticCalculator {
    public int Modulo(int a, int m) {
        return ((a % m) + m) % m;
    }

    public static int ModAdd(int a, int b, int m) {
        return (((a % m) + m) % m + ((b % m) + m) % m) % m;
    }

    public static int ModSub(int a, int b, int m) {
        return (((a % m) + m) % m - ((b % m) + m) % m) % m;
    }

    public static int ModMul(int a, int b, int m) {
        return (((a % m) + m) % m * ((b % m) + m) % m) % m;
    }

    public static int ModDiv(int a, int b, int m) {
        if(modInverse(b,m)==-1)
            return -1;
        else
            return (a * modInverse(b, m)) % m;
    }

    public static int modInverse(int a, int m)
    {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            try {
                int q = a / m;
                int t = m;

                m = a % m;
                a = t;
                t = y;

                y = x - q * y;
                x = t;
            }
            catch(Exception e){
                System.out.println("Inverse modulo DOES NOT exist for chosen numbers.");
                return -1;
            }

        }

        if (x < 0)
            x += m0;

        return x;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello..! Welcome to Modular Arithmetic Calculator. \nEnter the number 'a' 'b' 'm' " +
                " on which you want to perform arithmetic operations: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int m = scan.nextInt();
        System.out.println("Enter the choice of operation" +
                " you want:\n1.Modular Addition\n2.Modular Subtraction\n3.Modular Multiplication" +
                "\n4.Modular Division\n5.Inverse of a and m ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Modular Addition of " + a + " and "
                        + b + " over " + m + " gives: " + ModAdd(a, b, m));
                break;
            case 2:
                System.out.println("Modular Subtraction of " + a + " and "
                        + b + " over " + m + " gives: " + ModSub(a, b, m));
                break;
            case 3:
                System.out.println("Modular Multiplication of " + a + " and "
                        + b + " over " + m + " gives: " + ModMul(a, b, m));
                break;
            case 4:
                System.out.println("Modular Division of " + a + " and "
                        + b + " over " + m + " gives: " + ModDiv(a, b, m));
                break;
            case 5:
                System.out.println("Modular Inverse of " + a +  " over " + m + " gives: " + modInverse(a, m));
                break;
            default:
                System.out.println("Error. Your choices must ONLY be from 1 to 5.");
                break;
        }
    }
}
