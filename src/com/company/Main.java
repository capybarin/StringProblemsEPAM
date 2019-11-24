package com.company;

import java.util.*;

public class Main {

    private static String text = "THIS is a test string which would help me to test how the program works, 11011, this";
    private static String[] words = {"ThIs","test","a","to"};

    //Task 1
    public static void count(){
        int tmpRes = 0;
        text = text.toLowerCase();
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
        }

        for (int j = 0; j<words.length; j++) {
            int i = text.indexOf(words[j]);
            while(i >= 0){
                tmpRes++;
                i = text.indexOf(words[j],i+1);
            }
            System.out.println(words[j]+" - "+tmpRes+" time(s)");
            tmpRes=0;
        }
    }
    

    public static void main(String[] args) {
	    count();
    }
}
