package com.wipro.crawler;

import static com.wipro.crawler.WebCrawlerUtil.hasSameDomain;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WebCrawlerUtilTest {

    @Test
    public void detectsLinkToSameDomain() {
        String doc = "http://www.abc.com";

        assertThat(hasSameDomain(doc, "http://www.abc.com/services"), is(true));

        assertThat(hasSameDomain(doc, "http://subdomain.abc.com"), is(false));
        assertThat(hasSameDomain(doc, "http://amazon.com"), is(false));
    }

    @Test
    public void detectsLinkToSameDomainWhenTestingSiblingPages() {
        String doc = "http://www.abc.com/contact";

        assertThat(hasSameDomain(doc, "http://www.abc.com/services"), is(true));
        assertThat(hasSameDomain(doc, "http://www.abc.com"), is(true));

        assertThat(hasSameDomain(doc, "http://subdomain.abc.com"), is(false));
        assertThat(hasSameDomain(doc, "http://amazon.com"), is(false));
    }
}