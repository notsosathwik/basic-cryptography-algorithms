package ciphers;

import java.util.Scanner;
import java.lang.Math;

public class OneTimePad {
    private static final char[] alphabet ={'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N','O','P', 'Q', 'R','S', 'T', 'U','V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello..! This program implements " +
                "One-time pad Cipher.\nEvery time you input a plaintext, a key of the same size is randomly generated.");
        String text;
            System.out.println("Enter the plaintext: ");
            text=sc.next();
            String randomKey = "";
            for(int i=0;i<text.length();i++){
                double rand= Math.random()*1000;
                int randInt = (int) Math.round(rand);
                int AlphaInt=randInt%26;
                randomKey=randomKey+alphabet[AlphaInt];
            }

            System.out.println("One-Time Random Key: "+randomKey);
            System.out.print("Cipher text: ");
            encrypt(text, randomKey);
            System.out.println("\nYou can decipher this text using the generated one-time key by a normal Vigenere Cipher.");
    }

    public static void encrypt(String text, String key){
        text=text.replaceAll("[^a-zA-Z]","");
        String keyMap=keySize(text,key);
        text=text.toUpperCase();
        char pText[] = text.toCharArray();
        char pKey[]=keyMap.replaceAll("[^a-zA-Z]","").toUpperCase().toCharArray();
        int textSize=pText.length;
        int text_index[]=new int[textSize];
        int key_index[]= new int[textSize];


        for(int i=0; i<pText.length;i++){
            for(int j=0; j<alphabet.length;j++){
                if(pText[i]==alphabet[j]){
                    text_index[i]=j;
                }
            }
        }
        for(int i=0; i<pKey.length;i++){
            for(int j=0; j<alphabet.length;j++){
                if(pKey[i]==alphabet[j]){
                    key_index[i]=j;
                }
            }
        }
        char cText[]=new char[textSize];
        int res[]=new int[textSize];
        for(int i=0; i<textSize;i++){
            res[i]=(text_index[i]+key_index[i])%26;

        }
        for(int c=0;c<res.length;c++){
            System.out.print(alphabet[res[c]]);
        }
    }

    public static String keySize(String msg, String key){
        String keyMap="";
        for(int i=0,j=0; i<msg.length();i++){
            if(j<key.length()){
                keyMap+=key.charAt(j);
                j++;
            }else{
                j=0;
                keyMap+=key.charAt(j);
                j++;
            }
        }
        return keyMap;
    }
}
