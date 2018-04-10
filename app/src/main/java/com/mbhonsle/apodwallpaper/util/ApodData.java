package com.mbhonsle.apodwallpaper.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class ApodData {

    @JsonIgnore
    private String copyright;
    private Date date;
    private String explanation;
    @JsonIgnore
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;
    private static ApodData DEFAULT = new ApodData();

    public static ApodData getDefault() {
        if (DEFAULT.title.isEmpty()) DEFAULT.title = "default";
        return DEFAULT;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getHdurl() {
        return hdurl;
    }

    public Date getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return this.title + " " + this.url;
    }
}
