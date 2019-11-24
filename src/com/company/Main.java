package com.company;

import java.util.*;

public class Main {

    private static String text = "THIS is a test string which would help me to test how the program works, this madamGmadam";
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


    //Task 3
    public static void findPalindrome() {
        char[] textTmp = addBoundaries(text.toCharArray());
        int[] palindromeRadius = new int[textTmp.length];
        int palindromeCenter = 0;
        int palindromeRightBoard = 0;
        int tmp1;
        int tmp2 = 0;
        for (int i = 1; i<textTmp.length; i++) {
            if (i>palindromeRightBoard) {
                palindromeRadius[i] = 0; tmp1 = i-1; tmp2 = i+1;
            } else {
                int i2 = palindromeCenter*2-i;
                if (palindromeRadius[i2]<(palindromeRightBoard-i)) {
                    palindromeRadius[i] = palindromeRadius[i2];
                    tmp1 = -1;
                } else {
                    palindromeRadius[i] = palindromeRightBoard-i;
                    tmp2 = palindromeRightBoard+1; tmp1 = i*2-tmp2;
                }
            }
            while (tmp1>=0 && tmp2<textTmp.length && textTmp[tmp1]==textTmp[tmp2]) {
                palindromeRadius[i]++; tmp1--; tmp2++;
            }
            if ((i+palindromeRadius[i])>palindromeRightBoard) {
                palindromeCenter = i; palindromeRightBoard = i+palindromeRadius[i];
            }
        }
        int len = 0; palindromeCenter = 0;
        for (int i = 1; i<textTmp.length; i++) {
            if (len<palindromeRadius[i]) {
                len = palindromeRadius[i]; palindromeCenter = i;
            }
        }
        char[] res = Arrays.copyOfRange(textTmp, palindromeCenter-len, palindromeCenter+len+1);
        System.out.println("\n"+String.valueOf(removeBoundaries(res)));
    }

    public static char[] addBoundaries(char[] cs) {
        if (cs==null || cs.length==0)
            return "||".toCharArray();

        char[] cs2 = new char[cs.length*2+1];
        for (int i = 0; i<(cs2.length-1); i = i+2) {
            cs2[i] = '|';
            cs2[i+1] = cs[i/2];
        }
        cs2[cs2.length-1] = '|';
        return cs2;
    }

    public static char[] removeBoundaries(char[] cs) {
        if (cs==null || cs.length<3)
            return "".toCharArray();

        char[] cs2 = new char[(cs.length-1)/2];
        for (int i = 0; i<cs2.length; i++) {
            cs2[i] = cs[i*2+1];
        }
        return cs2;
    }

    public static void main(String[] args) {
	    countWords();//Task 1
	    sortByALetter();//Task 2
        findPalindrome();//Task 3
    }
}
