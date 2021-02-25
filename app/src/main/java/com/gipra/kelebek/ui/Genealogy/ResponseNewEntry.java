package com.gipra.kelebek.ui.Genealogy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseNewEntry {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("c_strategy")
    @Expose
    private String cStrategy;
    @SerializedName("goingSide")
    @Expose
    private String goingSide;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("up_username")
    @Expose
    private String upUsername;
    @SerializedName("level")
    @Expose
    private Integer level;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCStrategy() {
        return cStrategy;
    }

    public void setCStrategy(String cStrategy) {
        this.cStrategy = cStrategy;
    }

    public String getGoingSide() {
        return goingSide;
    }

    public void setGoingSide(String goingSide) {
        this.goingSide = goingSide;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUpUsername() {
        return upUsername;
    }

    public void setUpUsername(String upUsername) {
        this.upUsername = upUsername;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
