package com.breakiterator;

import java.text.BreakIterator;

/**
 * {@code BreakIteratorExample} class.
 */
public class BreakIteratorExample
{
    private static final String PARAGRAPH = "We can start with a simple sentence. " +
            "Next we check for sentences ending with exclamation marks! " +
            "Within a sentence we may find real numbers like 3.14159. " +
            "A sentence may contain abbreviations such as found in Mr. Jones. " +
            "We may find ellipses within a sentence …, or at the end of a sentence….";

    public static void main(String[] args)
    {
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance();
        sentenceIterator.setText(PARAGRAPH);
        int boundaryPosition = sentenceIterator.first();
        while (boundaryPosition != BreakIterator.DONE) {
            int start = boundaryPosition;
            System.out.print(boundaryPosition + "-");
            boundaryPosition = sentenceIterator.next();
            int end = boundaryPosition;
            if (end == BreakIterator.DONE) {
                break;
            }
            System.out.println(boundaryPosition + " [" + PARAGRAPH.substring(start, end) + "]");
        }
    }
}
