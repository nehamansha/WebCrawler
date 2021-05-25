package com.wipro.crawler.sitemap;

import java.util.ArrayList;
import java.util.List;

class Page {
    private final String url;



    private final List<Link> links = new ArrayList<>();

    Page(String url) {
        this.url = url;
    }

    void addLink(Link l) {
        links.add(l);
    }

    public String getUrl() {
        return url;
    }

    public List<Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Page: ").append(url).append("\n");
        for (Link l : links) {
            sb.append("\t\t").append(l).append("\n");
        }

        return sb.toString();
    }
}
