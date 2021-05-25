package com.wipro.crawler.sitemap;

import java.util.HashMap;
import java.util.Map;

class SiteMap {

    private final Map<String, Page> pages = new HashMap<>();
    private final String domain;

    SiteMap(String domain) {
        this.domain = domain;
    }

    void addPage(Page page) {
        pages.put(page.getUrl(), page);
    }

    boolean hasPageFor(Link link) {
        return pages.containsKey(link.getUrl());
    }
    public Map<String, Page> getPages() {
        return pages;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Site map for: ").append(domain).append("\n");
        for (Page p : pages.values()) {
            sb.append("\t").append(p).append("\n");
        }
        return sb.toString();
    }
}
