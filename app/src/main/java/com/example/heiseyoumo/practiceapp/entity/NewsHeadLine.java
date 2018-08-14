package com.example.heiseyoumo.practiceapp.entity;

/**
 * 新闻头条的实体类
 */


public class NewsHeadLine {
    //标题
    private String title;
    //日期
    private String date;
    //新闻URL
    private String url;
    //新闻来源
    private String source;
    //图片URL
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
