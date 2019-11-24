package com.company;

import java.util.*;

public class Main {

    private static String text = "THIS is a test string which would help me to test how the program works, 11011, this";
    private static String[] words = {"ThIs","test","a","to"};

    //Task 1
    public static void countWords(){
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

    //Task 2
    public static int countLettersInTheWord(String word, char c){
        if(word.isEmpty()) return 0;
        return (((word.charAt(0) == c) ? (1) : (0)) + countLettersInTheWord(word.substring(1), c));
    }

    private static ArrayList<String> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparingInt(Map.Entry::getValue));
        Collections.reverse(list);
        ArrayList<String> result = new ArrayList<>();

        list.sort((o1, o2) -> ((o1.getValue().equals(o2.getValue())) ?
                (o1.getKey().compareTo(o2.getKey())) : (0)));

        for (Map.Entry<String, Integer> entry : list) {
            result.add(entry.getKey());
        }

        return result;
    }

    public static void sortByALetter(){
        char userChar = 't';
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> notUsed = new ArrayList<>();
        String[] wordsSecondTask = text.split("\\W");

        for (String word: wordsSecondTask){
            int i = countLettersInTheWord(word,userChar);
            if(i>0)
                map.put(word,i);
            else notUsed.add(word);
        }

        Collections.sort(notUsed);
        System.out.println();
        sortByValue(map).stream().distinct().forEach(System.out::println);
    }

    public static void main(String[] args) {
	    countWords();//Task 1
	    sortByALetter();//Task 2
    }
}
