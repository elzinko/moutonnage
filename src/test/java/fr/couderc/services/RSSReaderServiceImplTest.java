package fr.couderc.services;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by elzinko on 21/11/16.
 */
public class RSSReaderServiceImplTest {
    @Test
    public void readRSS() throws Exception {
        RSSReaderServiceImpl service = new RSSReaderServiceImpl();
        service.readRSS();
    }

    @Test
    public void parseHtml() throws Exception {
        RSSReaderServiceImpl service = new RSSReaderServiceImpl();
        service.parseHtml();
    }

    @Test
    public void parseHtml2() throws Exception {
        RSSReaderServiceImpl service = new RSSReaderServiceImpl();
        service.parseHtml2();
    }
}