package com.wipro.crawler.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DocumentFactory {

    public Document documentFor(String url) {
        try {
            return connectToUrl(url).get();
        } catch (IOException e) {
            return null;
        }
    }

    private Connection connectToUrl(String url) {
        return Jsoup.connect(url).timeout(2000);
    }
}
