package com.gipra.kelebek.ui.IncomeDetails.Leader_Level_BV;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaderLevelBv {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("d_to_date")
    @Expose
    private String dToDate;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("pair_bv")
    @Expose
    private String pairBv;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("percentage")
    @Expose
    private String percentage;
    @SerializedName("level_bv")
    @Expose
    private String levelBv;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDToDate() {
        return dToDate;
    }

    public void setDToDate(String dToDate) {
        this.dToDate = dToDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPairBv() {
        return pairBv;
    }

    public void setPairBv(String pairBv) {
        this.pairBv = pairBv;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getLevelBv() {
        return levelBv;
    }

    public void setLevelBv(String levelBv) {
        this.levelBv = levelBv;
    }
}
