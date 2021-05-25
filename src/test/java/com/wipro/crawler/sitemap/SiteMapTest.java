package com.wipro.crawler.sitemap;

import static com.wipro.crawler.sitemap.LinkTypeEnum.EXTERNAL;
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
    public void returnsTrueWhenPageAdded() {
        Link mappedPage = Link.newLinkByType("child-internal-url", INTERNAL);
        siteMap.addPage(new Page(mappedPage.getUrl()));
        assertThat(siteMap.hasPageFor(mappedPage), is(true));

    }

    @Test
    public void returnsFalseWhenExternalPageNotAdded() {

        Link unmappedPage = Link.newLinkByType("child-external-url", EXTERNAL);
        assertThat(siteMap.hasPageFor(unmappedPage), is(false));
    }

    @Test
    public void returnsFalseWhenInternalPageNotAdded() {
        Link unmappedPage = Link.newLinkByType("child-external-url", INTERNAL);
        assertThat(siteMap.hasPageFor(unmappedPage), is(false));
    }
}
