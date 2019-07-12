package com.lili.vo;

public class ImageVO {
    private String uri;
    private String url;

    public ImageVO() {
    }

    public ImageVO(String uri, String url) {
        this.uri = uri;
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
