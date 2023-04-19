package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;


public class Crawler {
    HashSet<String> urlSet;
    int Max_depth=2;

    Crawler(){
        urlSet = new HashSet<>();
    }

    public void getPageTextAndLinks(String url,int depth){
        if(urlSet.contains(url)) return;
        if(depth>=Max_depth) return;
        if(urlSet.add(url)){
            System.out.println(url);
        }
        depth++;
        //now we have to parse HTML object into java object(links)
        try {
            Document document = Jsoup.connect(url).timeout(5000).get();
            //indexer works start here
            Indexer indexer = new Indexer(document,url);

            System.out.println(document.title());
            Elements availableLinkOnPage = document.select("a[href]");
            for (Element currLink : availableLinkOnPage) {
                getPageTextAndLinks(currLink.attr("abs:href"), depth);
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.getPageTextAndLinks("https://www.javatpoint.com",0);
    }
}