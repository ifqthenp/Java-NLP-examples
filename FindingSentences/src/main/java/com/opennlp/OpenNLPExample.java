package com.opennlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.*;

public class OpenNLPExample
{
    private static final String PARAGRAPH = "We can start with a simple sentence. " +
            "Next we check for sentences ending with exclamation marks! " +
            "Within a sentence we may find real numbers like 3.14159. " +
            "A sentence may contain abbreviations such as found in Mr. Jones. " +
            "We may find ellipses within a sentence …, or at the end of a sentence….";

    public static void main(String[] args)
    {
        try (InputStream inputStream = new FileInputStream(new File("FindingSentences/src/main/resources/en-sent.bin"))) {
            SentenceModel modelStream = new SentenceModel(inputStream);
            SentenceDetectorME detector = new SentenceDetectorME(modelStream);

            String[] sentences = detector.sentDetect(PARAGRAPH);
            double[] probabilities = detector.getSentenceProbabilities();
            for (int i = 0; i < sentences.length; i++) {
                System.out.printf("%-73s - %f%n", sentences[i], probabilities[i]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
