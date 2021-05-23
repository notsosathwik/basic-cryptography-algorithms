package ciphers;

import java.util.Scanner;

public class AutoKeyCipher {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello..! This program implements AutoKey Cipher provided a text and key.\n" +
                "Enter the text which you want to encrypt/decrypt: ");
        String msg = scan.nextLine();
        msg = msg.toUpperCase();
        System.out.println("Enter the key letter: ");
        String key = scan.nextLine();
        key = key.toUpperCase();
        String enc = Encryption(msg, key);
        System.out.println("Enter the operation you want to perform: \n1.Encryption\n2.Decryption");
        int choice = scan.nextInt();
        if (choice == 1)
            System.out.println("Ciphertext: " + enc);
        else if (choice == 2)
            System.out.println("Plaintext: " + Decryption(msg, key));
        else
            System.out.println("Invalid choice. Enter either 1 or 2 ONLY.");
    }

    public static String Encryption(String text, String key) {
        int len = text.length();

        String subkey = key + text;
        subkey = subkey.substring(0, subkey.length() - key.length());

        String sb = "";
        for (int x = 0; x < len; x++) {
            int get1 = alphabet.indexOf(text.charAt(x));
            int get2 = alphabet.indexOf(subkey.charAt(x));

            int total = (get1 + get2) % 26;

            sb += alphabet.charAt(total);
        }

        return sb;
    }

    public static String Decryption(String text, String key) {

        String current = key;
        String sb = "";

        for (int x = 0; x < text.length(); x++) {
            int get1 = alphabet.indexOf(text.charAt(x));
            int get2 = alphabet.indexOf(current.charAt(x));

            int total = (get1 - get2) % 26;
            total = (total < 0) ? total + 26 : total;
            sb += alphabet.charAt(total);

            current += alphabet.charAt(total);
        }
        return sb;
    }
}