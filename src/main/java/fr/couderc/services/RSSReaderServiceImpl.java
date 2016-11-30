package fr.couderc.services;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class RSSReaderServiceImpl {

    public void readRSS() {
//        String url = "http://www.cerclefinance.com/rss/rss.asp";
        String url = "http://www.boursier.com/syndication/rss/news";
        //String url = "http://www.cerclefinance.com/default.asp?pub=listeSocietes&rub=CONSEIL";
        //url = "http://www.feedforall.com/blog-feed.xml";

        SyndFeed feed = null;
        try {
            feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        log.info(feed.getTitle() + " " + feed.getCategories());
        log.info("feed published date : " + feed.getPublishedDate());
        List entries = feed.getEntries();

        Iterator itEntries = entries.iterator();

        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            log.info("Title: " + entry.getTitle());
            log.info("Link: " + entry.getLink());
            log.info("Author: " + entry.getAuthor());
            log.info("Publish Date: " + entry.getPublishedDate());
            log.info("Update Date: " + entry.getUpdatedDate());
            log.info("Description: " + entry.getDescription().getValue());
            List categories = entry.getCategories();

            Iterator itCategories = categories.iterator();

            while (itCategories.hasNext()) {
                SyndCategory category = (SyndCategory)itCategories.next();
                log.info("categorie : " + category.getName() + "\n");
            }
        }


    }

    public void parseHtml() {
        // JSoup Example 2 - Reading HTML page from URL
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cerclefinance.com/default.asp?pub=conseil&rub=CONSEIL").get();
            Elements links = doc.select("a[href]");
            List<Element> titles = doc.select("div.zoneInfoA").select("div.title");
            for (Element title : titles) {
                if (title.text().contains("les avis du jour")) {
                    Elements posts = title.nextElementSibling().select("a[href]");
                    for (Element post : posts) {
                        log.debug(post.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseHtml2() {
        // JSoup Example 2 - Reading HTML page from URL
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cerclefinance.com/").get();
            Element main = doc.body().getElementById("main");
            Element content = main.getElementById("contenu");
            Element listActu = content.getElementById("listActu");
            Element listPane = listActu.getElementById("listPane");
            Element listContainer = listPane.getElementById("listContainer");
            Element listItems = listContainer.getElementById("listItems");

            Elements conseils = listItems.select("li");
            log.debug("##################################");
            for (Element conseil : conseils) {
                if (conseil.getElementsByClass("conseils") != null) {
                    log.debug(conseil.toString());
                }
            }

//            log.debug("##################################");
//            Elements posts = content.select("#listActu");
//            for (Element post : posts) {
//                log.debug(post.toString());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
