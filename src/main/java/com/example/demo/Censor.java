package com.example.demo;


import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Censor {
    //ловим нежелательные слова_
    public static Boolean check(String InputText) throws  IOException {
        String WordsFilePatch = "./src/main/resources/BadWords.txt";
        //ArrayList<String> BadWords = new ArrayList<>();
        FileReader fr = new FileReader(WordsFilePatch);
        Scanner scan = new Scanner(fr);
        String str = "";
        boolean messаge = true;

        while(scan.hasNextLine()){
            str = scan.nextLine();
            if(InputText.indexOf(str) == -1){

            }
            else {
                messаge = false;
            }
        }
        fr.close();
        return messаge;

    }
}
