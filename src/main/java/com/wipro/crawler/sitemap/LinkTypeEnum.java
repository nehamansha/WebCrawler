package com.wipro.crawler.sitemap;

public enum LinkTypeEnum {

  INTERNAL("Internal"),
  EXTERNAL("External"),
  RESOURCE("--> Static Resource");

  public final String type;

  LinkTypeEnum(String type) {
    this.type=type;
  }
}
