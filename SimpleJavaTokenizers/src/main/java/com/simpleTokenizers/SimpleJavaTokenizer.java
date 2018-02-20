package com.simpleTokenizers;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * {@code SimpleJavaTokenizer} class.
 */
public class SimpleJavaTokenizer
{
    private static final String SENTENCE = "A simple  sentence,\twith\tembbed  white space";

    public static void main(String[] args)
    {
        stringTokenizerTokenizer();
    }

    private static void scannerTokenizer()
    {
        System.out.println("Scanner Class Example");
        Scanner scanner = new Scanner(SENTENCE);
        scanner.useDelimiter("[\\t ,.]");
        List<String> tokenList = new ArrayList<>();

        while (scanner.hasNext()) {
            tokenList.add(scanner.next());
        }

        tokenList.forEach(token -> System.out.println("[" + token + "]"));
        System.out.println();
        System.out.println();
    }

    private static void breakIteratorTokenizer()
    {
        System.out.println("BreakIterator Example");
        BreakIterator wordIterator = BreakIterator.getWordInstance();

        int position = wordIterator.first();
        while (position != BreakIterator.DONE) {
            int begin = position;
            System.out.print(position + "-");
            position = wordIterator.next();
            if (position == BreakIterator.DONE) {
                break;
            } else {
                System.out.println(position + " [" + SENTENCE.substring(begin, position) + "]");
            }
        }
        System.out.println();
        System.out.println();
    }

    private static void stringTokenizerTokenizer()
    {
        System.out.println("StringTokenizer Class Example");
        StringTokenizer stringTokenizer = new StringTokenizer(SENTENCE);
        while (stringTokenizer.hasMoreTokens()) {
            System.out.println("[" + stringTokenizer.nextToken() + "]");
        }
    }
}
