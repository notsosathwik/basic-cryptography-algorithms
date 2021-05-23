package basic_algos;

import java.util.*;

public class ChineseRemainderTheorem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size;

        System.out.println("Hello..! This program implements the working of the Chinese Remainder " +
                "Theorem.\nEnter the number of equations: ");
        size = input.nextInt();

        int[] remainder = new int[size];
        int[] number = new int[size];

        System.out.println("Enter the " + size + " remainders: ");
        for (int i = 0; i < size; i++) {
            remainder[i] = input.nextInt();
        }

        System.out.println("Enter the " + size + " mod numbers: ");
        for (int i = 0; i < size; i++) {
            number[i] = input.nextInt();
        }
        System.out.println();
        System.out.println("Mod numbers: " + Arrays.toString(number));
        System.out.println("Remainders: " + Arrays.toString(remainder));
        System.out.println("The smallest value of X is: " + computeX(remainder, number));
    }

    public static int computeX(int[] remValue, int[] numb) {
        int product = 1;
        for (int j : numb) {
            product *= j;
        }

        System.out.println("Product of all numbers [M(common modulus)] is: " + product);
        int[] partialProduct = new int[numb.length];
        int[] inverse = new int[numb.length];
        int total = 0;

        for (int i = 0; i < numb.length; i++) {
            partialProduct[i] = product / numb[i];
            inverse[i] = inverseValue(partialProduct[i], numb[i]);
            total += partialProduct[i] * inverse[i] * remValue[i];
        }

        System.out.println("Partial product array [m1, m2, ...] is: " + Arrays.toString(partialProduct));
        System.out.println("Multiplicative inverse modulo of partial product [x1, x2, ...] is: " + Arrays.toString(inverse));
        System.out.println("Total = " + total);
        System.out.println("So, " + total + " (mod " + product + ") gives the final solution: ");
        return total % product;
    }

    public static int inverseValue(int a, int b) {
        int m = b, t, q;
        int x = 0, y = 1;

        if (b == 1) {
            return 0;
        }

        while (a > 1) {
            q = a / b;
            t = b;

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