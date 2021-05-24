package com.wipro.crawler.sitemap;

import com.wipro.crawler.jsoup.DocumentFactory;
import com.wipro.crawler.jsoup.LinkExtractor;
import org.jsoup.nodes.Document;

public class SiteMapCrawlerImpl implements SiteMapCrawler {
    private final DocumentFactory documentFactory;
    private final LinkExtractor linkExtractor;

    public SiteMapCrawlerImpl(DocumentFactory documentFactory, LinkExtractor linkExtractor) {
        this.documentFactory = documentFactory;
        this.linkExtractor = linkExtractor;
    }

    @Override
    public SiteMap crawlForSiteMap(String rootUrl) {
        Page rootPage = new Page(rootUrl);
        return findLinks(rootPage, new SiteMap(rootUrl));
    }

    /** Recursively find links for internal links only
     * @param page
     * @param siteMap
     * @return
     */
    private SiteMap findLinks(Page page, SiteMap siteMap) {
        siteMap.addPage(page);
        if(null != documentFactory.documentFor(page.getUrl())) {
            for (Link link : linkExtractor.processLinksFromDoc(
                documentFactory.documentFor(page.getUrl()))) {
                page.addLink(link);
                if (link.isInternalLink() && !siteMap.hasPageFor(link)) {
                    findLinks(new Page(link.getUrl()), siteMap);
                }
            }
        }
        return siteMap;
    }
}