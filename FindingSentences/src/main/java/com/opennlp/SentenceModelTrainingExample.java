package com.opennlp;

import opennlp.tools.sentdetect.*;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class SentenceModelTrainingExample
{
    private static final String PARAGRAPH = "We can start with a simple sentence. " +
            "Next we check for sentences ending with exclamation marks! " +
            "Within a sentence we may find real numbers like 3.14159. " +
            "A sentence may contain abbreviations such as found in Mr. Jones. " +
            "We may find ellipses within a sentence …, or at the end of a sentence….";

    private static String getTrainingText()
    {
        try (FileReader fileReader = new FileReader("FindingSentences/src/main/resources/sentence.train");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder
                        .append(line)
                        .append(System.lineSeparator());
            }
            return stringBuilder.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SentenceModel getTrainingModel(final String trainText)
    {
        InputStreamFactory inputStreamFactory = () -> new ByteArrayInputStream(trainText.getBytes());

        try (ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, Charset.forName("UTF-8"));
             ObjectStream<SentenceSample> sampleTextStream = new SentenceSampleStream(lineStream)) {

            SentenceDetectorFactory sentenceDetectorFactory = new SentenceDetectorFactory("en", true, null, null);
            return SentenceDetectorME.train("en", sampleTextStream, sentenceDetectorFactory, TrainingParameters.defaultParams());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void testModel(SentenceModel sentenceModel)
    {
        SentenceDetector sentenceDetector = new SentenceDetectorME(sentenceModel);
        String[] sentences = sentenceDetector.sentDetect(PARAGRAPH);

        System.out.println();

        System.out.println("Number of Sentences: " + sentences.length);

        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Training text --------------");
        System.out.println(getTrainingText());
        System.out.println();

        System.out.println("Testing model --------------");
        SentenceModel sentenceModel = getTrainingModel(getTrainingText());
        testModel(sentenceModel);
    }
}
