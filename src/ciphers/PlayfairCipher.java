package ciphers;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayfairCipher {

    public static void main(String[] args) {

        System.out.println("Hello..! This program implements Playfair cipher. Choose the operation you" +
                " would like to perform.");
        System.out.println("1.Encryption");
        System.out.println("2.Decryption");

        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();

        if(number == 1) {
            encrypt();
        }
        else if(number == 2) {
            decrypt();
        }

    }

    public static void encrypt() {

        Scanner sc = new Scanner(System.in);

        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        System.out.println("Enter the plaintext you want to encrypt: ");
        String wordToEncrypt = sc.nextLine();

        System.out.println("Enter the keyword: ");
        String wordKey = sc.nextLine()+alphabet;

        String plain = deleteSpace(wordToEncrypt);
        String wordKeyF = deleteSpace(wordKey);

        String plainFormatted = plain.toUpperCase();
        String wordKeyF1 = wordKeyF.toUpperCase();

        String plainFormatted2 = addingXInDoubleLetter(plainFormatted);
        String wordKeyF2 = deleteDoubleLetter(wordKeyF1);

        plainFormatted2=deleteSpace(plainFormatted2);
        wordKeyF2=deleteSpace(wordKeyF2);

        if(plainFormatted2.length()%2!=0)
            plainFormatted2+="X";

        String encrypt = encrypting(plainFormatted2, wordKeyF2);


        System.out.println("Plaintext: "+plainFormatted2);
        System.out.println("Playfair matrix: "+wordKeyF2);

        System.out.println(encrypt);

    }

    public static void decrypt() {

        Scanner sc = new Scanner(System.in);
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        System.out.println("Enter the ciphertext you want to decrypt: ");
        String wordToDecrypt = sc.nextLine();

        System.out.println("Enter keyword: ");
        String wordKey = sc.nextLine()+alphabet;

        String wordToDecryptUpperCase = wordToDecrypt.toUpperCase();
        String wordKeyUpperCase = wordKey.toUpperCase();

        String wordKeyF1 = deleteSpace(wordToDecryptUpperCase);
        String wordKeyForm = deleteSpace(wordKeyUpperCase);

        String wordKeyF2 = deleteDoubleLetter(wordKeyForm);

        if(wordKeyF1.length()%2 != 0) {
            System.out.println("The sentence has odd number of letters.");
            return;
        }

        String decrypt = decrypting(wordKeyF1, wordKeyF2);

        System.out.println("Ciphertext: "+wordKeyF1);
        System.out.println("Playfair matrix: "+wordKeyF2);
        System.out.println(decrypt);

    }

    public static String deleteSpace(String word) {
        ArrayList<Character> arrayWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) != ' ')
                arrayWord.add(word.charAt(i));
        }
        word="";
        for (Character character : arrayWord) {
            word+=character;
        }
        return word;
    }

    public static String addingXInDoubleLetter(String word) {
        ArrayList<Character> arrayWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            arrayWord.add(word.charAt(i));
        }

        arrayWord.add(' ');
        arrayWord.add(' ');

        for (int i = 0; i < arrayWord.size()-3; i++) {
            if(arrayWord.get(i) == arrayWord.get(i+1)) {
                arrayWord.add(i+1, 'X');
                arrayWord.add(i+3, 'X');
            }
        }
        word="";
        for (Character character : arrayWord) {
            word+=character;
        }
        return word;
    }

    public static String deleteDoubleLetter(String word) {
        ArrayList<Character> arrayWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            arrayWord.add(word.charAt(i));
        }

        for (int i = 0; i < arrayWord.size()-1; i++) {
            for (int j = i+1; j < arrayWord.size(); j++) {
                if(arrayWord.get(i)==arrayWord.get(j)) {
                    arrayWord.remove(j);
                    j--;
                }
            }
        }

        word="";
        for (Character character : arrayWord) {
            word+=character;
        }

        return word;
    }

    public static String encrypting(String word, String key) {
        ArrayList<Character> arrayWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            arrayWord.add(word.charAt(i));
        }
        ArrayList<Character> arrayKey = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            arrayKey.add(key.charAt(i));
        }

        int first = 0;
        int second = 0;

        for (int i = 0; i < arrayWord.size(); i+=2) {
            for (int j = 0; j < arrayKey.size(); j++) {
                if(arrayWord.get(i) == arrayKey.get(j)) {
                    for (int j2 = 0; j2 < arrayKey.size(); j2++) {
                        if(arrayWord.get(i+1) == arrayKey.get(j2)) {

                            first=j;
                            second=j2;

                            if(first/5 == second/5) {

                                if(first%5 == 4) {
                                    first=j-4;
                                    second=++j2;
                                }
                                else if(second%5 == 4) {
                                    first=++j;
                                    second=j2-4;
                                }
                                else {
                                    first++;
                                    second++;
                                }
                            }
                            else if(first%5 == second%5) {

                                if(first/5 == 4) {
                                    first=j%5;
                                    second=j2+5;
                                }
                                else if(second/5 == 4) {
                                    first=j+5;
                                    second=j2%5;
                                }
                                else {
                                    first=j+5;
                                    second=j2+5;
                                }
                            }
                            else {

                                if(first/5<second/5 && first%5<second%5) {
                                    first=j+(j2-j)%5;
                                    second=j2-(j2-j)%5;
                                }
                                else if(first/5<second/5 && first%5>second%5) {
                                    first=j-(5-(j2-j)%5);
                                    second=j2+(5-(j2-j)%5);
                                }
                                else if(first/5>second/5 && first%5<second%5) {
                                    first=j+(5-(j-j2)%5);
                                    second=j2-(5-(j-j2)%5);
                                }
                                else if(first/5>second/5 && first%5>second%5) {
                                    first=j-(j-j2)%5;
                                    second=j2+(j-j2)%5;
                                }
                            }
                        }
                    }
                }
            }
            arrayWord.set(i, arrayKey.get(first));
            arrayWord.set(i+1, arrayKey.get(second));
        }

        word="";
        for (Character character : arrayWord) {
            word+=character;
        }
        return "Ciphertext: "+word;
    }

    public static String decrypting(String word, String key) {

        ArrayList<Character> arrayWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            arrayWord.add(word.charAt(i));
        }
        ArrayList<Character> arrayKey = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            arrayKey.add(key.charAt(i));
        }

        int first = 0;
        int second = 0;

        for (int i = 0; i < arrayWord.size(); i+=2) {
            for (int j = 0; j < arrayKey.size(); j++) {
                if(arrayWord.get(i) == arrayKey.get(j)) {
                    for (int j2 = 0; j2 < arrayKey.size(); j2++) {
                        if(arrayWord.get(i+1) == arrayKey.get(j2)) {

                            first=j;
                            second=j2;

                            if(first/5 == second/5) {

                                if(first%5 == 0) {
                                    first=j+4;
                                    second=j2-1;
                                }
                                else if(second%5 == 0) {
                                    first=j-1;
                                    second=j2+4;
                                }
                                else {
                                    first--;
                                    second--;
                                }
                            }
                            else if(first%5 == second%5) {

                                if(first/5 == 0) {
                                    first=j+20;
                                    second=j2-5;
                                }
                                else if(second/5 == 0) {
                                    first=j-5;
                                    second=j2+20;
                                }
                                else {
                                    first=j-5;
                                    second=j2-5;
                                }
                            }
                            else {

                                if(first/5<second/5 && first%5<second%5) {
                                    first=j+(j2-j)%5;
                                    second=j2-(j2-j)%5;
                                }
                                else if(first/5<second/5 && first%5>second%5) {
                                    first=j-(5-(j2-j)%5);
                                    second=j2+(5-(j2-j)%5);
                                }
                                else if(first/5>second/5 && first%5<second%5) {
                                    first=j+(5-(j-j2)%5);
                                    second=j2-(5-(j-j2)%5);
                                }
                                else if(first/5>second/5 && first%5>second%5) {
                                    first=j-(j-j2)%5;
                                    second=j2+(j-j2)%5;
                                }
                            }
                        }
                    }
                }
            }
            arrayWord.set(i, arrayKey.get(first));
            arrayWord.set(i+1, arrayKey.get(second));
        }
        word="";
        for (Character character : arrayWord) {
            word+=character;
        }

        return "Plaintext: "+word;
    }

}