package com.wipro.crawler.sitemap;

public interface SiteMapCrawler {

    /** Find the links for a given URL ex: https://wiprodigital.com/
     * @param rootUrl
     * @return
     */
    SiteMap crawlForSiteMap(String rootUrl);
}
