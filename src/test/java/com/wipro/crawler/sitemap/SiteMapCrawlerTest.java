package com.wipro.crawler.sitemap;

import static com.wipro.crawler.sitemap.Link.newLinkByType;
import static com.wipro.crawler.sitemap.LinkTypeEnum.EXTERNAL;
import static com.wipro.crawler.sitemap.LinkTypeEnum.INTERNAL;
import static com.wipro.crawler.sitemap.LinkTypeEnum.RESOURCE;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.wipro.crawler.jsoup.DocumentFactory;
import com.wipro.crawler.jsoup.LinkExtractor;
import java.util.ArrayList;
import java.util.Collection;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class SiteMapCrawlerTest {
    private final DocumentFactory documentFactory = mock(DocumentFactory.class);
    private final Document mockHomePage = mock(Document.class);
    private final Document mockChildPage = mock(Document.class);
    private final LinkExtractor linkExtractor = mock(LinkExtractor.class);
    private SiteMapCrawlerImpl siteMapBuilder;

    @Before
    public void setUp() throws Exception {
        siteMapBuilder = new SiteMapCrawlerImpl(documentFactory, linkExtractor);
    }

    @Test
    public void returnsSiteMapForDomainWithCyclicLinks() {
        when(documentFactory.documentFor("home-page")).thenReturn(mockHomePage);
        when(linkExtractor.processLinksFromDoc(mockHomePage)).thenReturn(homePageLinks());
        when(mockHomePage.baseUri()).thenReturn("www.abc.com");

        when(documentFactory.documentFor("child-page")).thenReturn(mockChildPage);
        when(linkExtractor.processLinksFromDoc(mockChildPage)).thenReturn(childPagesLinks());
        when(mockChildPage.baseUri()).thenReturn("www.abc.com");

        SiteMap siteMap = siteMapBuilder.crawlForSiteMap("home-page");

        assertThat(siteMap, isA(SiteMap.class));
    }

    private Collection<Link> childPagesLinks() {
        Collection<Link> links = new ArrayList<>(5);
        links.add(newLinkByType("home-page", INTERNAL));
        links.add(newLinkByType("a", EXTERNAL));
        links.add(newLinkByType("b", RESOURCE));
        return links;
    }

    private Collection<Link> homePageLinks() {
        Collection<Link> links = new ArrayList<>(5);
        links.add(newLinkByType("child-page", INTERNAL));
        links.add(newLinkByType("b", EXTERNAL));
        links.add(newLinkByType("c", EXTERNAL));
        links.add(newLinkByType("d", RESOURCE));
        links.add(newLinkByType("e", RESOURCE));
        return links;
    }
}
