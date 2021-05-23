package ciphers;

import java.util.Scanner;

public class VigenereCipher {
    private static final char[] alphabet ={'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N','O','P', 'Q', 'R','S', 'T', 'U','V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            System.out.println("Hello..! This program implements " +
                    "Vigenere Cipher.\nChoose the operation you want to perform:\n1.Encryption\n2.Decryption");
            String text;
            String key;
            int choice = sc.nextInt();
            if(choice==1){
                System.out.println("Enter text: ");
                text=sc.next();
                System.out.println("Enter the key: ");
                key=sc.next();
                System.out.print("Cipher text: ");
                encrypt(text, key);
            }else if(choice==2){
                System.out.println("Enter ciphertext: ");
                text=sc.next();
                System.out.println("Enter the key: ");
                key=sc.next();
                System.out.print("Decrypted text: ");
                decrypt(text,key);
            }
            else{
                System.out.println("Your choice must be either 1 or 2.");
            }

        }

    public static void encrypt(String text, String key){
        text=text.replaceAll("[^a-zA-Z]","");

        String keyMap=keySize(text,key);
        //System.out.println(keyMap);

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

    public static void decrypt(String cText, String key){

        char cypText[]=cText.replaceAll("[^a-zA-Z]","").toUpperCase().toCharArray();
        key=keySize(cText,key);
        char cKey[]=key.replaceAll("[^a-zA-Z]","").toUpperCase().toCharArray();

        int cText_idx[]=new int[cypText.length];
        int key_idx[]=new int[cypText.length];

        for(int i=0;i<cypText.length;i++){
            for(int j=0; j<alphabet.length;j++){
                if(cypText[i]==alphabet[j]){
                    cText_idx[i]=j;
                }
            }
        }
        for(int f=0; f<cKey.length; f++){
            for(int j=0; j<alphabet.length;j++){
                if(cKey[f]==alphabet[j]){
                    key_idx[f]=j;
                }
            }
        }
        int plaintext[]=new int[cypText.length];
        for(int i=0; i<cypText.length;i++){
            plaintext[i]=((cText_idx[i]-key_idx[i])%26);
            if(plaintext[i]<0){
                plaintext[i]=plaintext[i]+26;
            }
            System.out.print(alphabet[plaintext[i]]);
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