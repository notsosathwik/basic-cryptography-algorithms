package basic_algos;

import java.util.Scanner;

public class InverseModExEuAl {
    static int modInverse(int a, int m)
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
                System.out.println("Inverse modulo does not exist for chosen numbers.");
                return -1;
            }

        }

        if (x < 0)
            x += m0;

        return x;
    }

    public static void main(String args[])
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Hello..! This program finds the modular multiplicative inverse" +
                " using Extended Euclidean Algorithm in the format 'a x = 1 mod m' where" +
                " x is the inverse.");
        System.out.println("Enter the value 'a' for which inverse is needed: ");
        int a = scan.nextInt();
        System.out.println("Enter the modulo value 'm': ");
        int m = scan.nextInt();

        System.out.println("Modular multiplicative "
                + "inverse is "
                + modInverse(a, m));
    }

}
