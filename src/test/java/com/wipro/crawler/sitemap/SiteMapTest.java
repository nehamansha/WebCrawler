package com.wipro.crawler.sitemap;

import static com.wipro.crawler.sitemap.LinkTypeEnum.INTERNAL;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SiteMapTest {
    private SiteMap siteMap;

    @Before
    public void setUp() throws Exception {
        siteMap = new SiteMap("some-domain");
    }

    @Test
    public void returnsTrueWhenPageIsListed() {
        Link mappedPage = Link.newLinkByType("some-page-url", INTERNAL);
        siteMap.addPage(new Page(mappedPage.getUrl()));

        assertThat(siteMap.hasPageFor(mappedPage), is(true));
    }

    @Test
    public void returnsFalseWhenPageNotListed() {
        Link mappedPage = Link.newLinkByType("some-page-url", INTERNAL);
        siteMap.addPage(new Page(mappedPage.getUrl()));

        Link unmappedPage = Link.newLinkByType("some-other-page-url", INTERNAL);

        assertThat(siteMap.hasPageFor(unmappedPage), is(false));
    }
}
