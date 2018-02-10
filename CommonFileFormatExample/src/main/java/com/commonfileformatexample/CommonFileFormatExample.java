package com.commonfileformatexample;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * {@code CommonFileFormatExample} class.
 */
public class CommonFileFormatExample
{
    private final static String DIR = System.getProperty("user.dir");
    private static final String CSV = "/CommonFileFormatExample/src/main/resources/sample.csv";
    private static final String JSON1 = "/CommonFileFormatExample/src/main/resources/sample1.json";
    private static final String JSON2 = "/CommonFileFormatExample/src/main/resources/sample2.json";

    public static void main(String[] args)
    {
        try {
            // BufferedReader
            FileReader file = new FileReader(DIR + CSV);
            BufferedReader br = new BufferedReader(file);

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String element : values) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }
            System.out.println();

            // CSVReader
            CSVReader csvReader = new CSVReader(new FileReader(DIR + CSV));
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                for (String element : values) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }

            // Process person json
            processPerson();

            // Process persons json
            processPersons();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayPerson(final JsonParser parser)
    {
        try {
            while (parser.nextToken() != null) {
                parser.nextToken();
                if ("id".equals(parser.getCurrentName())) {
                    parser.nextToken();
                    System.out.println("ID: " + parser.getIntValue());
                }
                parser.nextToken();
                if ("firstname".equals(parser.getCurrentName())) {
                    parser.nextToken();
                    System.out.println("First Name: " + parser.getText());
                }
                parser.nextToken();
                if ("lastname".equals(parser.getCurrentName())) {
                    parser.nextToken();
                    System.out.println("Last Name: " + parser.getText());
                }
                parser.nextToken();
                System.out.println();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processPersons()
    {
        try {
            System.out.println("--- People ---");
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser parser = jsonFactory.createParser(new File(DIR + JSON2));
            parser.nextToken();
            parser.nextToken();
            if ("people".equals(parser.getCurrentName())) {
                parser.nextToken();
                parser.nextToken();
                String token = parser.currentName();
                if ("persons".equals(token)) {
                    parser.nextToken();
                    displayPerson(parser);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processPerson()
    {
        try {
            System.out.println("--- Person ---");
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(new File(DIR + JSON1));
            displayPerson(parser);
            parser.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
