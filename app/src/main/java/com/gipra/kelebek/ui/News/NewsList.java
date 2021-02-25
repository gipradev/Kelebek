package com.gipra.kelebek.ui.News;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsList {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("d_date")
    @Expose
    private String dDate;
    @SerializedName("c_title")
    @Expose
    private String cTitle;
    @SerializedName("c_news")
    @Expose
    private String cNews;
    @SerializedName("c_priority")
    @Expose
    private String cPriority;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDDate() {
        return dDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

    public String getCTitle() {
        return cTitle;
    }

    public void setCTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getCNews() {
        return cNews;
    }

    public void setCNews(String cNews) {
        this.cNews = cNews;
    }

    public String getCPriority() {
        return cPriority;
    }

    public void setCPriority(String cPriority) {
        this.cPriority = cPriority;
    }

}
