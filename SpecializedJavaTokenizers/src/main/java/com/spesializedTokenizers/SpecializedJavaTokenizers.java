package com.spesializedTokenizers;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;
import opennlp.tools.tokenize.*;

import java.io.*;

/**
 * {@code SpecializedJavaTokenizers} class.
 */
public class SpecializedJavaTokenizers
{
    private static final String SENTENCE = "A simple  sentence,\twith\tembedd  white space.";

    public static void main(String[] args)
    {
        simpleTokenizer();
        tokenizerMEtokenizer();
        whitespaceTokenizer();
        lingPipeTokenizer();
    }

    private static void simpleTokenizer()
    {
        System.out.println("SimpleTokenizer");

        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = simpleTokenizer.tokenize(SENTENCE);
        for (String token : tokens) {
            System.out.println("[" + token + "]");
        }
        System.out.println();
        System.out.println();
    }

    private static void tokenizerMEtokenizer()
    {
        System.out.println("TokenizerME");

        final String path = "SpecializedJavaTokenizers/src/main/resources/en-token.bin";
        try (InputStream modelInputStream = new FileInputStream(new File(path))) {
            TokenizerModel tokenizerModel = new TokenizerModel(modelInputStream);
            Tokenizer tokenizer = new TokenizerME(tokenizerModel);
            String[] tokens = tokenizer.tokenize(SENTENCE);
            for (String token : tokens) {
                System.out.println("[" + token + "]");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
    }

    private static void whitespaceTokenizer()
    {
        System.out.println("WhitespaceTokenizer");
        String[] tokens = WhitespaceTokenizer.INSTANCE.tokenize(SENTENCE);
        for (String token : tokens) {
            System.out.println("[" + token + "]");
        }
        System.out.println();
        System.out.println();
    }

    private static void lingPipeTokenizer()
    {
        System.out.println("LingPipe Tokenizer");
        char[] text = SENTENCE.toCharArray();
        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
        com.aliasi.tokenizer.Tokenizer tokenizer = tokenizerFactory.tokenizer(text, 0, text.length);
        for (String token : tokenizer) {
            System.out.println("[" + token + "]");
        }
    }
}
