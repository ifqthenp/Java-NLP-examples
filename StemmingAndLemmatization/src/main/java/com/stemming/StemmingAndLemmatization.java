package com.stemming;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;
import com.aliasi.tokenizer.Tokenization;
import com.aliasi.tokenizer.TokenizerFactory;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import opennlp.tools.stemmer.PorterStemmer;

import java.util.List;
import java.util.Properties;

public class StemmingAndLemmatization
{
    private static final String[] words =
            { "bank", "banking", "banks", "banker", "banked", "bankart" };

    public static void main(String[] args)
    {
        porterStemmer();
        lingPipeStemmer();
        stanfordLemmatizer();
    }

    private static void porterStemmer()
    {
        System.out.println("Porter Stemmer Example");
        PorterStemmer porterStemmer = new PorterStemmer();
        for (String word : words) {
            String stem = porterStemmer.stem(word);
            System.out.printf("Word: %-8s Stem: %-8s%n", word, stem);
        }
        System.out.println();
    }

    private static void lingPipeStemmer()
    {
        System.out.println("LingPipe Stemmer Example");
        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
        TokenizerFactory porterFactory = new PorterStemmerTokenizerFactory(tokenizerFactory);

        for (String word : words) {
            Tokenization tokenizer = new Tokenization(word, porterFactory);
            String[] stems = tokenizer.tokens();
            String wordOut = String.format("Word: %-8s", word);
            for (String stem : stems) {
                System.out.printf("%s Stem: %-8s%n", wordOut, stem);
            }
        }
        System.out.println();
    }

    private static void stanfordLemmatizer()
    {
        System.out.println("Stanford Lemmatizer Example");
        String sentences = "We have encountered numerous difficulties in our "
                + "travels. However, amongst these difficulties "
                + "the most daunting has been maintaining perseverance.";

        Properties props = new Properties();
        props.put("annotations", "tokenize, ssplit, pos, lemma");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = new Annotation(sentences);
        pipeline.annotate(annotation);

        List<CoreMap> sentenceList = annotation.get(SentencesAnnotation.class);
        for (CoreMap sentence : sentenceList) {
            for (CoreLabel word : sentence.get(TokensAnnotation.class)) {
                System.out.printf("Word %-15s Lemma: %-10s%n", word.originalText(), word.lemma());
            }
        }
        System.out.println();
    }
}

