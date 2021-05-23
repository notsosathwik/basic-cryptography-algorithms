package ciphers;

import java.util.Scanner;

public class AffineCipher {

    public static void main(String[] args) {
        String message;
        int k1;
        int k2;
        int m=26;
        int inverseOfA;
        int length;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello..! This program implements Affine Cipher. " +
                "\nFormat: c = (p*k1)+k2 % m\n(Where c = ciphertext's position value" +
                " and p = plaintext's position value)\nEnter the " +
                "choice of operation you want to perform.\n1.Encryption\n2.Decryption");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("Enter the plaintext: ");
            message = scanner.nextLine();
            message = message.toUpperCase();
            System.out.println("Enter key for k1 (must be co-prime with m): ");
            k1 = scanner.nextInt();
            System.out.println("Enter key for k2 (must be between 0 and m): ");
            k2 = scanner.nextInt();

            length = message.length();

            for (int i = 0; i < length; i++) {
                System.out.print(Character.toString((((k1 * (message.charAt(i) - 'A')) + k2) % m) + 'A'));
            }
            System.out.println("\n");
        } else if (choice == 2) {
            System.out.println("Enter the ciphertext: ");
            message = scanner.nextLine();
            message = message.toUpperCase();
            System.out.println("Enter key for k1 (must be co-prime with m): ");
            k1 = scanner.nextInt();
            System.out.println("Enter key for k2 (must be between 0 and m): ");
            k2 = scanner.nextInt();

            length = message.length();
            inverseOfA = (multiInverse(k1));

            for (int i = 0; i < length; i++) {
                System.out.print(Character.toString(((inverseOfA * ((message.charAt(i) + 'A' - k2)) % m)) + 'A'));
            }
            System.out.println("\n");
        } else {
            System.out.println("Invalid choice. Enter either 1 or 2 ONLY.");
        }
    }

    static int multiInverse(int a) {
        int bool;
        int inverseOfA = 0;
        for (int i = 0; i < 26; i++) {
            bool = (a * i) % 26;
            if (bool == 1) {
                inverseOfA = i;
            }

        }
        return inverseOfA;
    }
}