package com.wipro.crawler;

import java.net.URI;

public class WebCrawlerUtil {

    public static boolean hasSameDomain(String domainLink, String url) {
        URI uri = URI.create(domainLink);
        return url.startsWith(uri.getScheme() + "://" + uri.getHost());
    }
}
