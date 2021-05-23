package ciphers;

import java.util.Scanner;

public class CaesarCipher {
    public static String encrypt(String string, int key) {
        String coded = "";
        for (int i = 0; i < string.length(); i++) {
            char sm = (char) (string.codePointAt(i) + key);
            coded += sm;
        }
        return coded;
    }

    public static String decrypt(String coded, int key) {
        String decoded = "";
        for (int i = 0; i < coded.length(); i++) {
            char sm = (char) (coded.codePointAt(i) - key);
            decoded += sm;
        }
        return decoded;
    }

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Hello..! This program implements Caesar Cipher provided a text and key.\n" +
                "Enter the text which you want to encrypt/decrypt: ");
        String text=scan.nextLine();
        System.out.println("Enter the key value: ");
        int key=scan.nextInt();
        System.out.println("Enter the operation you want to perform: \n1.Encryption\n2.Decryption");
        int choice=scan.nextInt();
        if(choice==1)
            System.out.println(encrypt(text,key));
        else if(choice==2)
            System.out.println(decrypt(text,key));
        else
            System.out.println("Invalid choice. Enter either 1 or 2 ONLY.");
    }
}
