package com.wipro.crawler.sitemap;

import static com.wipro.crawler.sitemap.LinkTypeEnum.INTERNAL;

public class Link {

    private final String url;
    private final LinkTypeEnum type;

    private Link(String url, LinkTypeEnum type) {
        this.url = url;
        this.type = type;
    }

    public boolean isInternalLink() {
        return type == INTERNAL;
    }

    public LinkTypeEnum getType() {
        return type;
    }

    public static Link newLinkByType(String url, LinkTypeEnum type) {
        return new Link(url, type);
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return type.type + " link: " + url;
    }
}
