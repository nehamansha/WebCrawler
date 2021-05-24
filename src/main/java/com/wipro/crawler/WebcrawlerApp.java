package com.wipro.crawler;

import com.wipro.crawler.jsoup.DocumentFactory;
import com.wipro.crawler.jsoup.LinkExtractor;
import com.wipro.crawler.sitemap.SiteMapCrawler;
import com.wipro.crawler.sitemap.SiteMapCrawlerImpl;

import java.io.Console;

/**
 * @author neha.srivastava
 */
public class WebcrawlerApp {

    public static void main(String[] args) {
        SiteMapCrawler siteMapCrawler = new SiteMapCrawlerImpl(
                new DocumentFactory(),
                new LinkExtractor());

        Console console = System.console();
        if (null != console) {
            String url = console.readLine("Web crawl the url : ");
            System.out.println(siteMapCrawler.crawlForSiteMap(url));
        }
    }
}
