package com.gipra.kelebek.ui.UpgradeBA;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseViewUpline {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("uplineid")
    @Expose
    private String uplineid;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUplineid() {
        return uplineid;
    }

    public void setUplineid(String uplineid) {
        this.uplineid = uplineid;
    }
}
