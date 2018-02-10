package com.cleaningoperations;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

/**
 * {@code BasicCleaningOperations} class.
 */
public class BasicCleaningOperations
{
    public static void main(String[] args)
    {
        String text = "A simple demonstration of basic DATA\tleaning operations. ";
        text += "The second Sentence\t\talso has issues";

        System.out.println("Original text: " + text);

        // Java SE library

        String cleanedText = text.toLowerCase();
        cleanedText = cleanedText.trim();
        cleanedText = cleanedText.replaceAll("\t", " ");

        while (cleanedText.contains("  ")) {
            cleanedText = cleanedText.replaceAll("  ", " ");
        }

        System.out.println("Cleaned text: " + cleanedText);
        System.out.println();

        // Google Guava library

        // Splitter example

        System.out.println("Splitter Example");

        Iterable<String> iterator = Splitter.on(CharMatcher.whitespace())
                .omitEmptyStrings()
                .trimResults()
                .split(text);
        iterator.forEach(token -> System.out.print(token + ' '));
        System.out.println();
        System.out.println();

        // CharMatcher Example

        System.out.println("CharMatcher Example");
        cleanedText = CharMatcher.whitespace().trimAndCollapseFrom(text, ' ');
        System.out.println(cleanedText);
        System.out.println();

        // CharMatcher Retention Example

        String retentionText = CharMatcher.inRange('0', '9')
                .or(CharMatcher.whitespace())
                .or(CharMatcher.inRange('a', 'z'))
                .retainFrom("He is 5 FEET and 6 inches tall.");
        System.out.println(retentionText);
    }
}
