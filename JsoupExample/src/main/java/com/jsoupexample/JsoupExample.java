package com.jsoupexample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * {@code JsoupExample} class.
 */
public class JsoupExample
{
    public static void main(String[] args)
    {
        String dir = System.getProperty("user.dir");
        String filePath = "/JsoupExample/src/main/resources/sample.html";

        try {
            File file = new File(dir + filePath);
            Document document = Jsoup.parse(file, "UTF-8", "");
            String title = document.title();
            System.out.println("Title: " + title);

            System.out.println("--- Body ---");
            Elements element = document.select("body");
            System.out.println(element.text());

            System.out.println("--- Links ---");
            Elements links = document.select("a[href]");
            for (Element link : links) {
                System.out.println(link.attr("href") + " " + link.text());
            }

            System.out.println("--- Images ---");
            Elements images = document.select("img[src$=.png");
            for (Element image : images) {
                System.out.println(image);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
