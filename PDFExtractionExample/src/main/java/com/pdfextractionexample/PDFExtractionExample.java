package com.pdfextractionexample;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * {@code PDFExtractionExample} class.
 */
public class PDFExtractionExample
{
    private final static String DIR = System.getProperty("user.dir");
    private static final String PDF = "/PDFExtractionExample/src/main/resources/PDF File.pdf";

    public static void main(String[] args)
    {
        try {
            PDDocument document = PDDocument.load(new File(DIR + PDF));
            PDDocumentInformation pdd = document.getDocumentInformation();

            System.out.println("Author: " + pdd.getAuthor());
            System.out.println("Number of Pages: " + document.getNumberOfPages());
            System.out.println("Title: " + pdd.getTitle());
            System.out.println("Subject: " + pdd.getSubject());
            System.out.println();

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String documentText = pdfTextStripper.getText(document);
            System.out.println(documentText);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
