package com.cleaningoperations;

import com.aliasi.tokenizer.EnglishStopTokenizerFactory;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@code StopWordExamples} class.
 */
public class StopWordExamples
{
    public static void main(String[] args)
    {
        String sentence = "The first thing she caught was a 5 pound salmon";
        String[] stopWords = { "the", "and", "or", "a", "with" };

        removeStopWords(sentence, stopWords);

        System.out.println();

        removeStopWordsWithLingPipe(sentence);
    }

    public static void removeStopWordsWithLingPipe(String text)
    {
        System.out.println("LingPipe example");
        System.out.println("Original Text: " + text);
        text = text.toLowerCase().trim();

        TokenizerFactory factory = IndoEuropeanTokenizerFactory.INSTANCE;
        factory = new EnglishStopTokenizerFactory(factory);
        Tokenizer tokenizer = factory.tokenizer(text.toCharArray(), 0, text.length());

        System.out.print("Text without stop words: ");
        for (String word : tokenizer) {
            System.out.print(word + " ");
        }
        System.out.println();
    }

    public static void removeStopWords(String text, final String[] stopWords)
    {
        System.out.println("Core Java Example");
        System.out.println("Original Text: " + text);
        text = text.toLowerCase().trim();

        List<String> wordList = new ArrayList<>(Arrays.asList(text.split(" ")));

        List<String> stopWordList = new ArrayList<>(Arrays.asList(stopWords));

        wordList.removeAll(stopWordList);

        System.out.println("Text without stop words: " + wordList.toString());
    }
}
