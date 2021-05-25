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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SiteMapCrawlerTest {
    private final DocumentFactory documentFactory = mock(DocumentFactory.class);
    private final Document mockPage = mock(Document.class);
    private final Document mockChildPages = mock(Document.class);
    private final LinkExtractor linkExtractor = mock(LinkExtractor.class);
    private SiteMapCrawlerImpl siteMapBuilder;

    @Before
    public void setUp() throws Exception {
        siteMapBuilder = new SiteMapCrawlerImpl(documentFactory, linkExtractor);
    }

    @Test
    public void returnsSiteMapWithPages() {
        when(documentFactory.documentFromUrl("home-page")).thenReturn(mockPage);
        when(linkExtractor.processLinksFromDoc(mockPage)).thenReturn(pageLinks());
        when(mockPage.baseUri()).thenReturn("www.abc.com");

        when(documentFactory.documentFromUrl("child-page")).thenReturn(mockChildPages);
        when(linkExtractor.processLinksFromDoc(mockChildPages)).thenReturn(childPages());
        when(mockChildPages.baseUri()).thenReturn("www.abc.com");

        SiteMap siteMap = siteMapBuilder.crawlForSiteMap("home-page");

        assertThat(siteMap, isA(SiteMap.class));
        Assert.assertEquals( true,siteMap.hasPageFor(newLinkByType("home-page", INTERNAL)));
        Assert.assertEquals( true,siteMap.hasPageFor(newLinkByType("child-page", INTERNAL)));
        Assert.assertEquals( 3,siteMap.getPages().size());

        Assert.assertEquals( 3 ,siteMap.getPages().get("home-page").getLinks().size());
        Assert.assertEquals( "child-page", siteMap.getPages().get("home-page").getLinks().get(0).getUrl());
        Assert.assertEquals( "ext_1", siteMap.getPages().get("home-page").getLinks().get(1).getUrl());
        Assert.assertEquals( "res_1", siteMap.getPages().get("home-page").getLinks().get(2).getUrl());
        Assert.assertEquals( INTERNAL, siteMap.getPages().get("home-page").getLinks().get(0).getType());

        Assert.assertEquals( 3 ,siteMap.getPages().get("child-page").getLinks().size());
        Assert.assertEquals( "int_2", siteMap.getPages().get("child-page").getLinks().get(0).getUrl());
        Assert.assertEquals( "ext_2", siteMap.getPages().get("child-page").getLinks().get(1).getUrl());
        Assert.assertEquals( "res_2", siteMap.getPages().get("child-page").getLinks().get(2).getUrl());
        Assert.assertEquals( EXTERNAL, siteMap.getPages().get("child-page").getLinks().get(1).getType());

    }

    private Collection<Link> childPages() {
        Collection<Link> links = new ArrayList<>(5);
        links.add(newLinkByType("int_2", INTERNAL));
        links.add(newLinkByType("ext_2", EXTERNAL));
        links.add(newLinkByType("res_2", RESOURCE));
        return links;
    }

    private Collection<Link> pageLinks() {
        Collection<Link> links = new ArrayList<>(5);
        links.add(newLinkByType("child-page", INTERNAL));
        links.add(newLinkByType("ext_1", EXTERNAL));
        links.add(newLinkByType("res_1", RESOURCE));
        return links;
    }
}
