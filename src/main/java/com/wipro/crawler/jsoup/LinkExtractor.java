package com.wipro.crawler.jsoup;

import com.wipro.crawler.WebCrawlerUtil;
import com.wipro.crawler.sitemap.Link;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.wipro.crawler.sitemap.LinkTypeEnum.EXTERNAL;
import static com.wipro.crawler.sitemap.LinkTypeEnum.INTERNAL;
import static com.wipro.crawler.sitemap.LinkTypeEnum.RESOURCE;
import static java.util.stream.Collectors.toList;

public class LinkExtractor {

  public static final String HREF = "href";
  public static final String SRC = "src";
  public static final String LINK = "link";
  public static final String SCRIPT = "script";
  public static final String IMG = "img";
  public static final String A = "a";

  public Collection<Link> processLinksFromDoc(Document doc) {
    List<Link> links = new ArrayList<>();
    links.addAll(anchorsFromDoc(doc));
    links.addAll(imagesFromDoc(doc));
    links.addAll(scriptsFromDoc(doc));
    links.addAll(resourceLinksFromDoc(doc));

    return links;
  }

  private Collection<Link> anchorsFromDoc(Document doc) {
    return doc.getElementsByTag(A).stream()
        .filter((e) -> !e.attr(HREF).isEmpty())
        .map((e) -> e.absUrl(HREF))
        .map((url) -> WebCrawlerUtil.hasSameDomain(doc.baseUri(), url) ? Link.newLinkByType(url, INTERNAL)
            : Link.newLinkByType(url, EXTERNAL))
        .collect(toList());
  }

  private Collection<Link> imagesFromDoc(Document doc) {
    return doc.getElementsByTag(IMG).stream()
        .filter((e) -> !e.attr(SRC).isEmpty())
        .map((e) -> e.absUrl(SRC))
        .map((url) -> Link.newLinkByType(url, RESOURCE))
        .collect(toList());
  }

  private Collection<Link> scriptsFromDoc(Document doc) {
    return doc.getElementsByTag(SCRIPT).stream()
        .filter((e) -> !e.attr(SRC).isEmpty())
        .map((e) -> e.absUrl(SRC))
        .map((url) -> Link.newLinkByType(url, RESOURCE))
        .collect(toList());
  }

  private Collection<Link> resourceLinksFromDoc(Document doc) {
    return doc.getElementsByTag(LINK).stream()
        .filter((e) -> !e.attr(HREF).isEmpty())
        .map((e) -> e.absUrl(HREF))
        .map((url) -> Link.newLinkByType(url, RESOURCE))
        .collect(toList());
  }
}
