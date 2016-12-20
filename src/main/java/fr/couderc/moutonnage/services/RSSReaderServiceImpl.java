package fr.couderc.moutonnage.services;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import fr.couderc.moutonnage.entity.RSS;
import fr.couderc.moutonnage.resources.RSSRepository;
import fr.couderc.moutonnage.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RSSReaderServiceImpl {
    
    @Autowired
    private RSSRepository rssRepository;

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
        
        List<RSS> rssList = new ArrayList<>();

        while (itEntries.hasNext()) {
            
            SyndEntry entry = (SyndEntry) itEntries.next();
            log.info("Title: " + entry.getTitle());
            log.info("Link: " + entry.getLink());
            log.info("Author: " + entry.getAuthor());
            log.info("Publish Date: " + entry.getPublishedDate());
            log.info("Update Date: " + entry.getUpdatedDate());
            log.info("Description: " + entry.getDescription().getValue());
            List<SyndCategory> categories = entry.getCategories();

            Iterator<SyndCategory> itCategories = categories.iterator();

            while (itCategories.hasNext()) {
                SyndCategory category = itCategories.next();
                log.info("categorie : " + category.getName() + "\n");
            }
            
            rssList.add(
                RSS.builder()
                    .title(entry.getTitle())
                    .link(entry.getLink())
                    .author(entry.getAuthor())
                    .publisheddate(DateUtils.convert(entry.getPublishedDate()))
//                    .updateTime(DateUtils.convert(entry.getUpdatedDate()))
                    .description(entry.getDescription().getValue())
                    .category(((SyndCategory)entry.getCategories().iterator().next()).getName())
                    .build()
            );
            
        }
        
        rssRepository.save(rssList);
        

    }

}
