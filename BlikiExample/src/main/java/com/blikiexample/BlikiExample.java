package com.blikiexample;

import info.bliki.api.Page;
import info.bliki.api.User;
import info.bliki.wiki.filter.SectionHeader;
import info.bliki.wiki.model.ITableOfContent;
import info.bliki.wiki.model.Reference;
import info.bliki.wiki.model.WikiModel;

import java.io.IOException;
import java.util.List;

/**
 * {@code BlikiExample} class.
 */
public class BlikiExample
{
    public static void main(String[] args) throws IOException
    {
        String title = "Wiki software";
        User user = new User("", "", "https://en.wikipedia.org/w/api.php");
        user.login();

        String[] titles = {title};
        List<Page> pages = user.queryContent(titles);

        for (Page page : pages) {
            WikiModel wikiModel = new WikiModel("${image}", "${title}");
            String text = wikiModel.render(page.toString());
            System.out.println(text);

            System.out.println("---References---");
            List<Reference> references = wikiModel.getReferences();
            for (Reference reference : references) {
                System.out.println(reference.getRefString());
            }

            System.out.println("---Section Headers---");
            ITableOfContent toc = wikiModel.getTableOfContent();
            List<SectionHeader> sections = toc.getSectionHeaders();
            for (SectionHeader sectionHeader : sections) {
                System.out.println(sectionHeader.getFirst());
            }
        }
    }
}
